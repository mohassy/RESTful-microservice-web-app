/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cartmanagement.business;

import com.cartmanagement.helpers.Cart;
import com.cartmanagement.persistence.CRUD_Cart;
import com.cartmanagement.persistence.CRUD_Device;
import javax.ws.rs.core.Response;

/**
 *
 * @author student
 */
public class CartManager {
    
    public static Cart getCart(String buyerID){
        CRUD_Cart cc = new CRUD_Cart();
        Cart cart =  cc.readCart(buyerID);
        return cart;
    }
    
    public boolean deleteDevice(int id){
        CRUD_Device cd = new CRUD_Device();
        boolean success = cd.deleteDevice(id);
        return success;
    }
}
