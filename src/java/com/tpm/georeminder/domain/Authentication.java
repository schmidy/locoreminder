/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mike
 */
@XmlRootElement(name = "Authentication")
@Entity
@Table(name="AUTHENTICATION")
public class Authentication implements Serializable {
    
    @Id
    @GeneratedValue
    @Column(name="ID")
    private int authenticationId;
    
    private String Password;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ACCOUNT_ID", nullable=false)
    @JsonIgnore
    @JsonBackReference
    private Account account;
    
    public Authentication () {}
    
    public Authentication(String Password, int id) {
        
        this.Password = Password;
        authenticationId = id;
        
    }

    /**
     * @return the authenticationId
     */
    public int getAuthentication_id() {
        return authenticationId;
    }

    /**
     * @param authentication_id the authenticationId to set
     */
    public void setAuthentication_id(int authentication_id) {
        this.authenticationId = authentication_id;
    }

    /**
     * @return the Password
     */
    public String getPassword() {
        return Password;
    }

    /**
     * @param Password the Password to set
     */
    public void setPassword(String Password) {
        this.Password = Password;
    }

    /**
     * @return the account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(Account account) {
        this.account = account;
    }
    
            
}
