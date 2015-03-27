/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpm.georeminder.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mike
 */
@XmlRootElement
public class JsonReminder {
    
    private String description;
    private String type;
    private int accountId;
    
    public JsonReminder() {}
     
    public JsonReminder (String description, String type, int id) {
        this.description = description;
        this.type = type;
        accountId = id;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param Description the description to set
     */
    public void setDescription(String Description) {
        this.description = Description;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param Type the type to set
     */
    public void setType(String Type) {
        this.type = Type;
    }

    /**
     * @return the accountId
     */
    public int getAccountId() {
        return accountId;
    }

    /**
     * @param AccountId the accountId to set
     */
    public void setAccountId(int AccountId) {
        this.accountId = AccountId;
    }
    
    
}
