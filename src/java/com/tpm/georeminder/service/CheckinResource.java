/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpm.georeminder.service;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;

/**
 *
 * @author Mike
 */
@Consumes("text/*")
@Path("/checkin")
@RequestScoped
public class CheckinResource {
    
    final String GOOGLEAPIKEY = "AIzaSyAoSaq67s3usb7XOFihxPVSLnAwU3ADJWo";
    
}
