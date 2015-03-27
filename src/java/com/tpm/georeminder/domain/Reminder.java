/*
 * Mike Schmidt
 * Regis MSSE692 Software Engineering Practicum
 * Spring 2015
 */
package com.tpm.georeminder.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mike
 */
@XmlRootElement(name = "Reminder")
@Entity
@Table(name="REMINDER")
public class Reminder implements Serializable {

    @Id
    @GeneratedValue
    @Column(name="REMINDER_ID")
    private int reminderId;        
    @XmlElement
    private String Description;
    @XmlElement
    private String Type;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ACCOUNT_ID", nullable=false)
    @JsonIgnore
    @JsonBackReference
    private Account account;
    
    public Reminder () {}
    
    public Reminder (String description, String type, int id) {
        
        Description = description;
        Type = type;
        this.reminderId = id;        
    }
    
    public int getId() {
        return reminderId;
    }
    
    public void setId(int id) {
        this.reminderId = id;
    }

    /**
     * @return the Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * @param Description the Description to set
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /**
     * @return the Type
     */
    public String getType() {
        return Type;
    }

    /**
     * @param Type the Type to set
     */
    public void setType(String Type) {
        this.Type = Type;
    }

    /**
     * @return the Account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * @param account the Account to set
     */
    public void setAccount(Account account) {
        this.account = account;
//        if (!account.getReminders().contains(this)) {
//            account.getReminders().add(this);
//        }
    }
}
