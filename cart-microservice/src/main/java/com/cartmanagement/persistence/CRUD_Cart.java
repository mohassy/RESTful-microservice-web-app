/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cartmanagement.persistence;
import com.cartmanagement.helpers.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author student
 */
public class CRUD_Cart {
    private static Connection getCon(){
        String connection = System.getenv("DB_URL");
        Connection con = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://"+ connection +"/CMS?allowPublicKeyRetrieval=true&useSSL=false", "root", "student");
            System.out.println("Connection established!");
        }catch(Exception e){
            System.out.println(e);
        }
        
        return con;
    }
    
    
    public void addToCart(Buyer buyer, Device device){
        //create cart in database
        int cartID, buyerID, deviceID;
        // search DB for cart
        try{
            Connection con = getCon();
        
            String newDevice =  "INSERT INTO Cart (buyerID, DeviceID) "
                                + "VALUES(" + buyer.getBuyerID() + ", " + device.getDeviceID() + ")";

              Statement statement = con.createStatement();
              statement.executeUpdate(newDevice);

              con.close(); 
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public Cart readCart(String buyerID){
        Cart cart = null;
        int cartID, deviceID;
        double price;
        String deviceName, deviceType, imgurl;
        String []specs;
        // search DB for cart
        try{
          Connection con = getCon();
        
            String q = "SELECT * FROM Cart WHERE buyerID = " + buyerID;
            
            PreparedStatement ps = con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();
            int i = 0;
            while(rs.next()){
                if(i == 0){
                    cartID = rs.getInt("cartID");
                    buyerID = rs.getInt("buyerID") + "";
                    cart = new Cart(cartID, Integer.parseInt(buyerID)); 
                    i++;
                }
                deviceID = rs.getInt("deviceID");
                q =  "SELECT * FROM Device WHERE deviceID = " + deviceID;
                ps = con.prepareStatement(q);
                ResultSet drs = ps.executeQuery();
                if(drs.next()){
                    deviceID = drs.getInt("deviceID");
                    price = drs.getDouble("price");
                    deviceName = drs.getString("deviceName");
                    deviceType = drs.getString("deviceType");
                    imgurl = drs.getString("imgurl");
                    specs = drs.getString("specs").split(";");
                    cart.addDevice(new Device(deviceID, price, deviceName, deviceType, imgurl, specs));
                }
            }
            con.close(); 
        }catch(Exception e){
            System.out.println(e);
        }
        
        return cart;
    }

}
