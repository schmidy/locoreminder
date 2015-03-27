/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpm.georeminder.business;

import com.tpm.georeminder.domain.Authentication;

/**
 *
 * @author Mike
 */
public interface AuthenticationMgr {
    
    public Authentication create(Authentication authentication);
    public Authentication read(int id);
    public Authentication update(Authentication authentication);
    public boolean        delete(Authentication authentication);
    
}
