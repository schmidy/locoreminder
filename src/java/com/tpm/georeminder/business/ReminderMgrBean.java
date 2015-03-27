/*
 * Mike Schmidt
 * Regis MSSE692 Software Engineering Practicum
 * Spring 2015
 */
package com.tpm.georeminder.business;

import com.tpm.georeminder.domain.Account;
import com.tpm.georeminder.domain.Reminder;
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
public class ReminderMgrBean implements ReminderMgr {
    
    @PersistenceContext(unitName = "GeoReminderPU")
    EntityManager em;
    static Logger log = Logger.getLogger(ReminderMgrBean.class.getName());
    
    @Override
    public Reminder create(Reminder reminder) {
        
        log.debug("Reminder Mgr Bean create reminder for: " + reminder.getDescription());
        try {
            em.persist(reminder);
        } catch (Exception ex) {
            log.fatal("Problem creating reminder: " +ex.getMessage());
            reminder = null;
        }
        
        return reminder;
    }

    @Override
    public Reminder read(int id) {
        
        log.debug("Reminder Mgr Bean read reminder for id: " + id);
        return em.find(Reminder.class, id);
    }

    @Override
    public Reminder update(Reminder reminder) {
        
        log.debug("Reminder Mgr Bean update reminder for: " + reminder.getId());
        return em.merge(reminder);
    }

    @Override
    public boolean delete(Reminder reminder) {
        boolean success = false;
        
        log.debug("Reminder Mgr Bean delete reminder for: " + reminder.getId());
        try {
            em.remove(em.merge(reminder));
            success = true;                    
        } catch (Exception ex) {
            log.error("Error deleting reminder: " + ex.getMessage());
        }
        
        return success;
    }

    @Override
    public List readForAcct(int acctid) {
        
        em.getTransaction().begin();
        
        List<Account> accounts = (List<Account>) em.createQuery("");
        
        return accounts;
    }

    @Override
    public List<Reminder> read() {
        log.debug("Reminder Mgr Bean read all reminders.");
        
        return em.createQuery("select rem from Reminder rem").getResultList();
    }
    
}
