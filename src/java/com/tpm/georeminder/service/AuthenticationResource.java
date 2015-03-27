/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpm.georeminder.service;

import com.tpm.georeminder.business.AccountMgr;
import com.tpm.georeminder.business.AuthenticationMgr;
import com.tpm.georeminder.domain.Account;
import com.tpm.georeminder.domain.Authentication;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;

/**
 *
 * @author Mike
 */
@Consumes("text/*")
@Path("/auth")
@RequestScoped
public class AuthenticationResource {
    static Logger log = Logger.getLogger(AuthenticationResource.class);
    
    @Inject 
    private AccountMgr acctMgr;
        
    @Inject
    private AuthenticationMgr authMgr;
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    // url/auth/?userName=user&password=password
    public Response login(@QueryParam("userName") String userName, 
            @QueryParam("password") String password) {
        
        log.debug("Login for userName: " + userName);
        
        List<Account> accts = acctMgr.read(userName);
        Response.ResponseBuilder builder;
        
        //Only going to use the first account in the list
//        Authentication auth = authMgr.read(accts.get(0).getId());
//        
//        if (password.equals(auth.getPassword())) {
//            builder = Response.ok();
//        } else {
//            builder = Response.status(Response.Status.BAD_REQUEST);
//        }
        
        builder = Response.ok();
        
        return builder.build();
                
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(
            @FormParam("password") String Password,
            @FormParam("acctId") String accountId){
        
        log.debug("Create Password");
        Authentication auth = new Authentication(Password, 
                Integer.parseInt(accountId));
        
        auth = authMgr.create(auth);
        
        Response.ResponseBuilder builder = Response.ok(auth);
        return builder.build();
    }
    
}
