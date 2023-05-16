/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cartmanagement.helpers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author student
 */
@XmlRootElement(name = "buyer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Buyer extends User{
    private int buyerID;
    
    public Buyer(String firstName, String lastName, String email, String password, long phoneNumber) {
        super(firstName, lastName, email, password, phoneNumber);
        this.buyerID = buyerID;
    }
    public Buyer(){
        
    }

    public int getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(int buyerID) {
        this.buyerID = buyerID;
    }

    
}
