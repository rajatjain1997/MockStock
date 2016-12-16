/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Rajat
 */
public class PlayerLockedException extends Exception{ 
    public PlayerLockedException(String message) {
        super(message);
    }
    
}
