/*
 * Mike Schmidt
 * Regis MSSE692 Software Engineering Practicum
 * Spring 2015
 */
package com.tpm.georeminder.business;

import com.tpm.georeminder.domain.Account;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;

/**
 *
 * @author Mike
 */
@Stateless
public class AccountMgrBean implements AccountMgr {
    
    @PersistenceContext(unitName = "GeoReminderPU")
    EntityManager em;
    static Logger log = Logger.getLogger(AccountMgrBean.class.getName());

    @Override
    public Account create(Account account) {
        
        log.debug("Account Mgr Bean create account for id: " + account.getId());
        try {
            em.persist(account);
        } catch (Exception ex) {
            log.fatal("Problem creating investor: " +ex.getMessage());
            account = null;
        }
         
        return account;
    }

    @Override
    public Account read(int id) {
        
        log.debug("Account Mgr Bean read account for id: " + id);
        
        Account acct = em.find(Account.class, id);
        
        return acct;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Account> read() {
        log.debug("Account Mgr Bean read account for all.");
        
        return em.createQuery("select acct from Account acct").getResultList();
    
    }
    
    @Override
    public List<Account> read(String username) {
        log.debug("Account Mgr Bean read account for username: " + username);
        
        return em.createQuery("select acct from Account acct where acct.UserName LIKE :userName")
                .setParameter("userName", username)
                .getResultList();
        
    }

    @Override
    public Account update(Account account) {
        
        log.debug("Account Mgr Bean update account for id: " + account.getId());
        
        return em.merge(account);
    }

    @Override
    public boolean delete(Account account) {
        
        boolean success = false;
        
        log.debug("Account Mgr Bean delete account for id: " + account.getId());
        
        try {
            em.remove(em.merge(account));
            success = true;
        } catch (Exception ex) {
            log.error("Error deleting account for account id " + account.getId());
            log.error(ex.getMessage());
        }
        
        return success;
    }
    
}
