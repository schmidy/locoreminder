/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpm.georeminder.exceptions;

/**
 *
 * @author schmim7
 */
public class InvalidArgumentException extends Exception {
    
    public InvalidArgumentException() { super(); }
    public InvalidArgumentException(String msg) {
        super(msg);
    }
    public InvalidArgumentException(String msg, Throwable cause) {
        super(msg, cause);
    }
    public InvalidArgumentException(Throwable cause) {
        super(cause);
    }
}