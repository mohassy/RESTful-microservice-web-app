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
@XmlRootElement(name="device")
@XmlAccessorType(XmlAccessType.FIELD)
public class Device {
    private int deviceID;
    private double price;
    private String name;
    private String deviceType;
    private String imgurl;
    private String[] specs;

    public Device(int deviceID, double price, String name, String deviceType, String imgurl, String[] specs) {
        this.deviceID = deviceID;
        this.price = price;
        this.name = name;
        this.deviceType = deviceType;
        this.imgurl = imgurl;
        this.specs = specs;
    }
    
    public Device(){
        
    }
    
    

    public int getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(int deviceID) {
        this.deviceID = deviceID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String type) {
        this.deviceType = type;
    }
    
    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
    
    public String[] getSpecs() {
        return specs;
    }

    public void setSpecs(String[] specs) {
        this.specs = specs;
    }
      
}
