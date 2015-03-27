/*
 * Mike Schmidt
 * Regis MSSE692 Software Engineering Practicum
 * Spring 2015
 */
package com.tpm.georeminder.service;

import com.tpm.georeminder.exceptions.InvalidArgumentException;
import com.tpm.georeminder.business.AccountMgr;
import com.tpm.georeminder.business.ReminderMgr;
import com.tpm.georeminder.domain.Account;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.Consumes;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;

/**
 *
 * @author Mike
 */
@Consumes("text/*")
@Path("/account")
@RequestScoped
public class AccountResource {
    static Logger log = Logger.getLogger(AccountResource.class);
    
    @Inject
    private AccountMgr accountMgr;
    
    @Inject
    private ReminderMgr reminderMgr;
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Response getAccount(@PathParam("id") String accountId) 
            throws EntityNotFoundException, InvalidArgumentException {
        
        log.debug("Get account for: " + accountId);
        
        Integer id;
        
        try {
            id = Integer.parseInt(accountId);
                    
        } catch (Exception ex) {
            if (accountId.equals("all")) {
                id = 0;
            } else {
                throw new InvalidArgumentException(ex.getMessage(), ex);
            }
        }
        Response.ResponseBuilder builder = null;
        
        if (id == 0) {
            List<Account> accounts = accountMgr.read();
            accounts.stream().forEach((acct) -> {
                acct.getReminders().stream().forEach((reminder) -> {
                    reminder.setAccount(null);
                });
            });
            GenericEntity<List<Account>> entity = new GenericEntity<List<Account>>(accounts){};
        
            builder = Response.ok(entity);
            
        } else {
            Account account = accountMgr.read(id);

            if (account == null) {
                throw new EntityNotFoundException();
            }

            account.getReminders().stream().forEach((reminder) -> {
                reminder.setAccount(null);
            });

            GenericEntity<Account> entity = new GenericEntity<Account>(account){};

            builder = Response.ok(entity);
        }
    
        return builder.build();   
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAccount() 
            throws EntityNotFoundException, InvalidArgumentException {
        
        log.debug("Get all accounts");
        
        List<Account> accounts = accountMgr.read();
        accounts.stream().forEach((acct) -> {
            acct.getReminders().stream().forEach((reminder) -> {
                reminder.setAccount(null);
            });
        });
        GenericEntity<List<Account>> entity = new GenericEntity<List<Account>>(accounts){};

        Response.ResponseBuilder builder = Response.ok(entity);
    
        return builder.build();   
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(final Account newAccount)
    {
        
        log.debug("Create Account");
        Account account = new Account();
        account.setFullName(newAccount.getFullName());
        account.setAddress(newAccount.getAddress());
        account.setEmailAddress(newAccount.getEmailAddress());
        account.setUserName(newAccount.getUserName());
        
        account = accountMgr.create(account);
        Response.ResponseBuilder builder = Response.ok(account);
        return builder.build();
    }
    
    @POST
    @Consumes("text/xml")
    @Produces(MediaType.APPLICATION_XML)
    public Response createXMLAccount(Account account) {
        log.debug("Create XML Account");
        
        account = accountMgr.create(account);
        Response.ResponseBuilder builder = Response.ok(account);
        return builder.build();
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAccount (@PathParam("id") int accountId,
            final Account account)

            throws InvalidArgumentException {
        
        log.debug("Update Account for: " + account.getFullName());
        
        if (account.getFullName().isEmpty()) {
            throw new InvalidArgumentException("Name cannot be empty");
        }
        
        Account newAccount = accountMgr.read(accountId);
        newAccount.setFullName(account.getFullName());
        newAccount.setUserName(account.getUserName());
        newAccount.setAddress(account.getAddress());
        newAccount.setEmailAddress(account.getEmailAddress());
        
        //Account account = new Account(fullName, address, email, userName, id);

        newAccount = accountMgr.update(newAccount);
        
        if (newAccount == null) {
            throw new EntityNotFoundException();
        }

        return Response.ok(newAccount).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response deleteAccount(@PathParam("id") int accountId) {
        
        Account account = accountMgr.read(accountId);

        log.debug("Deleteing reminders for account id: " + accountId + " first.");
        
        if (account.getReminders().size() > 0) {
            account.getReminders().stream().forEach((reminder) -> {
                reminderMgr.delete(reminder);
            });
        }

        log.debug("Deleteing account for id: " + accountId );

        Response.ResponseBuilder builder;
        if (accountMgr.delete(account)) {
            builder = Response.noContent();
        } else {
            builder = Response.serverError();
        }
        return builder.build();
    } 
}
