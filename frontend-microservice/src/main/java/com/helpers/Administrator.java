/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author student
 */
@XmlRootElement(name="administrator")
@XmlAccessorType(XmlAccessType.FIELD)
public class Administrator extends User{
    private int adminID;

    public Administrator(int adminID, String firstName, String lastName,String email, String password, long phoneNumber) {
        super(firstName, lastName, email, password, phoneNumber);
        this.adminID = adminID;
    }
    
    public Administrator(){
        
    }
   

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

   
    
    
}
