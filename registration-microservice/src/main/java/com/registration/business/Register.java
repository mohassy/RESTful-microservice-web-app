/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.registration.business;
import com.registration.helpers.*;
import com.registration.persistence.CRUD_User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author student
 */
public class Register {
    
    public static void registerBuyer(Buyer buyer){
        CRUD_User cu = new CRUD_User();
        boolean success = cu.createUser(buyer);
        if(!success)
            return;
        String message = buyer.getBuyerID() + ":" + buyer.getFirstName() + ":" + buyer.getLastName() + ":"
                + buyer.getEmail() + ":"+ buyer.getPassword() + ":" + buyer.getPhoneNumber();
        try {
            Messaging.sendmessage(message);
        } catch (IOException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
