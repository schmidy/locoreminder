/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpm.georeminder.business;

import com.tpm.georeminder.domain.Authentication;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;

/**
 *
 * @author Mike
 */
public class AuthenticationMgrBean implements AuthenticationMgr{
    
    @PersistenceContext(unitName = "GeoReminderPU")
    EntityManager em;
    static Logger log = Logger.getLogger(AuthenticationMgr.class.getName());

    @Override
    public Authentication create(Authentication authentication) {
        log.debug("Authentication Mgr Bean create password for id: " + authentication.getAccount().getId());
        
        try {
            em.persist(authentication);
        } catch (Exception ex) {
            log.fatal("Problem creating investor: " +ex.getMessage());
            authentication = null;
        }
         
        return authentication;
        
    }

    @Override
    public Authentication read(int id) {
        log.debug("Authentication Mgr Bean read password for id: " + id );
        
        return em.find(Authentication.class, id);
        
    }

    @Override
    public Authentication update(Authentication authentication) {
        log.debug("Authentication Mgr Bean update password for id: " + authentication.getAccount().getId());
        
        return em.merge(authentication);
    }

    @Override
    public boolean delete(Authentication authentication) {
        log.debug("Authentication Mgr Bean delete password for id: " + authentication.getAccount().getId());
        
        boolean success = false;
                
        try {
            em.remove(em.merge(authentication));
            success = true;
        } catch (Exception ex) {
            log.error("Error deleting account for account id " + authentication.getAccount().getId());
            log.error(ex.getMessage());
        }
        
        return success;
    }
    
}
