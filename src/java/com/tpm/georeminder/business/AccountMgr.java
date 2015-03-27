/*
 * Mike Schmidt
 * Regis MSSE692 Software Engineering Practicum
 * Spring 2015
 */
package com.tpm.georeminder.business;

import com.tpm.georeminder.domain.Account;
import java.util.List;

/**
 *
 * @author Mike
 */
public interface AccountMgr {
    
    public Account create(Account account);
    public Account read(int id);
    public List<Account> read(String userName);
    public List<Account> read();
    public Account update(Account account);
    public boolean delete(Account account);
}
