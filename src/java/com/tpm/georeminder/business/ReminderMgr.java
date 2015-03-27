/*
 * Mike Schmidt
 * Regis MSSE692 Software Engineering Practicum
 * Spring 2015
 */
package com.tpm.georeminder.business;

import com.tpm.georeminder.domain.Reminder;
import java.util.List;

/**
 *
 * @author Mike
 */
public interface ReminderMgr {
    
    public Reminder create(Reminder reminder);
    public Reminder read(int id);
    public List<Reminder> read();
    public List     readForAcct(int acctid);
    public Reminder update(Reminder reminder);
    public boolean  delete(Reminder reminder);
}
