/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpers;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author student
 */
@XmlRootElement(name="cart")
@XmlAccessorType(XmlAccessType.FIELD)
public class Cart {
    private int cartID;
    private int buyerID;
    @XmlElementWrapper(name="devices")
    @XmlElement(name="device")
    private ArrayList<Device> devices;

    public Cart(int cartID, int buyerID) {
        this.cartID = cartID;
        this.buyerID = buyerID;
        devices = new ArrayList();
    }
   
    public Cart(){
        
    }
    

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public int getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(int buyerID) {
        this.buyerID = buyerID;
    }

    public ArrayList<Device> getDevices() {
        return devices;
    }

    public void setDevices(ArrayList<Device> devices) {
        this.devices = devices;
    }
    
    
    //add item
    public void addDevice(Device device){
        devices.add(device);
    }
    
    
    //remove item
    public void removeDevice(Device device){
        devices.remove(device);
    }
    
}
