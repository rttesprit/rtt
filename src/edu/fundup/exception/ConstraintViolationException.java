/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fundup.exception;

/**
 *
 * @author hhamzaoui
 */
public class ConstraintViolationException extends Exception{

    /**
     *
     * @param message
     */
    public ConstraintViolationException(String message) {
        super(message);
    }
    

}
