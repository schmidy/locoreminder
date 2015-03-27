/*
 * Mike Schmidt
 * Regis MSSE692 Software Engineering Practicum
 * Spring 2015
 */
package com.tpm.georeminder.service;

import com.tpm.georeminder.business.AccountMgr;
import com.tpm.georeminder.business.ReminderMgr;
import com.tpm.georeminder.domain.Account;
import com.tpm.georeminder.domain.JsonReminder;
import com.tpm.georeminder.domain.Reminder;
import com.tpm.georeminder.exceptions.InvalidArgumentException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;

/**
 *
 * @author Mike
 */
@Consumes("text/*")
@Path("reminder")
@RequestScoped
public class ReminderResource {
    static Logger log = Logger.getLogger(ReminderResource.class.getName());
    
    @Inject
    private ReminderMgr mgr;
    
    @Inject 
    private AccountMgr acctMgr;
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Response getReminder(@PathParam("id") String reminderId)
            throws EntityNotFoundException, InvalidArgumentException {
        log.debug("Get reminder for id: " + reminderId);
        Integer id;
        
        try {
            id = Integer.parseInt(reminderId);
                    
        } catch (Exception ex) {
            throw new InvalidArgumentException(ex.getMessage(), ex);
        }
   
        Reminder reminder = mgr.read(id);
        
        if (reminder == null) {
            throw new EntityNotFoundException();
        }
        
        reminder.getAccount().setReminders(null);
        
        Response.ResponseBuilder builder = Response.ok(reminder);
        return builder.build();           
    }
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getReminder()
            throws EntityNotFoundException, InvalidArgumentException {
        log.debug("Get all reminders");
        
        GenericEntity<List<Reminder>> entity = 
                new GenericEntity<List<Reminder>>(clearAccounts(mgr.read())){};
                
        Response.ResponseBuilder builder = Response.ok(entity);
        
        Response response = builder.build();
        
        return response;           
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response CreateReminder(final JsonReminder newReminder) {
        
        log.debug("Create Reminder REST");
        
        Response.ResponseBuilder builder;
        
        Reminder reminder = new Reminder();
        reminder.setDescription(newReminder.getDescription());
        reminder.setType(newReminder.getType());
        
        Account acct = acctMgr.read(newReminder.getAccountId());
        
        if (acct != null) {
            reminder.setAccount(acct);
            reminder = mgr.create(reminder);
            log.debug("Set account in reminder to null");
            reminder.getAccount().setReminders(null);
            
            GenericEntity<Reminder> entity = new GenericEntity<Reminder>(reminder){};
            builder = Response.ok(entity);
            //builder = Response.ok(reminder);
        } else {
            //log.error("Account for id "+ accountId + " not found");
            builder = Response.serverError();
        }

        return builder.build();
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateReminder (@PathParam("id") int reminderId,
            final JsonReminder newReminder)
            throws InvalidArgumentException {
        log.debug("Update Reminder");
        
        if (newReminder.getDescription().isEmpty() || 
                newReminder.getType().isEmpty()) {
            throw new InvalidArgumentException("Description cannot be empty");
        }
        Reminder reminder = mgr.read(reminderId);
        
        reminder.setDescription(newReminder.getDescription());
        reminder.setType(newReminder.getType());
        
        reminder = mgr.update(reminder);
        
        if (reminder == null) {
            throw new EntityNotFoundException();
        }
        
        Response.ResponseBuilder builder = Response.ok(reminder);
        return builder.build();
    }
        
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteReminder(@PathParam("id") int id) {
        Reminder reminder = mgr.read(id);
        
        Response.ResponseBuilder builder;
        if (mgr.delete(reminder)) {
            builder = Response.noContent();
        } else {
            builder = Response.serverError();
        }
        return builder.build();
    }
    
    
    private List<Reminder> clearAccounts (List<Reminder> rmdrs) {
        
        List<Reminder> reminders = new ArrayList<>();
        
        if (rmdrs.size() > 0) {
            rmdrs.stream().map((reminder) -> {
                reminder.getAccount().setReminders(null);
                return reminder;
            }).forEach((reminder) -> {
                reminders.add(reminder);
            });
        }
        
        return reminders;
    }

}
