/*
 * Mike Schmidt
 * Regis MSSE692 Software Engineering Practicum
 * Spring 2015
 */
package com.tpm.georeminder.domain;

import java.io.Serializable;
import java.util.List;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mike
 */
@XmlRootElement(name = "Account")
@Entity
@Table(name="ACCOUNT")
public class Account implements Serializable {
    
    @Id
    @GeneratedValue
    @Column(name="ACCOUNT_ID")
    int account_id;

    private String FullName;    
    private String EmailAddress;
    private String Address;
    private String UserName;
    
    @OneToMany(cascade=ALL, mappedBy = "account", fetch = FetchType.EAGER)
    private List<Reminder> Reminders;
    
    public Account () {}
    
    public Account (String fullName, String address, String emailAddress, 
            String userName, int id) {
        FullName = fullName;
        Address = address;
        EmailAddress = emailAddress; 
        UserName = userName;
        this.account_id = id;
    }
    
    public int getId() {
        return account_id;
    }

    public void setId(int id) {
        this.account_id = id;
    }

    /**
     * @return the FullName
     */
    public String getFullName() {
        return FullName;
    }

    /**
     * @param FullName the FullName to set
     */
    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    /**
     * @return the Address
     */
    public String getAddress() {
        return Address;
    }

    /**
     * @param Address the Address to set
     */
    public void setAddress(String Address) {
        this.Address = Address;
    }

    /**
     * @return the EmailAddress
     */
    public String getEmailAddress() {
        return EmailAddress;
    }

    /**
     * @param EmailAddress the EmailAddress to set
     */
    public void setEmailAddress(String EmailAddress) {
        this.EmailAddress = EmailAddress;
    }
    
    /**
     * @return the Reminders
     */
    public List<Reminder> getReminders() {
        return Reminders;
    }

    /**
     * @param Reminders the Reminders to set
     */
    public void setReminders(List<Reminder> Reminders) {
        this.Reminders = Reminders;
    }    
    
    public void addReminder(Reminder reminder) {
        this.Reminders.add(reminder);
//        if (reminder.getAccount() != this) {
//            reminder.setAccount(this);
//        }
    }

    /**
     * @return the UserName
     */
    public String getUserName() {
        return UserName;
    }

    /**
     * @param userName the UserName to set
     */
    public void setUserName(String userName) {
        this.UserName = userName;
    }
}
