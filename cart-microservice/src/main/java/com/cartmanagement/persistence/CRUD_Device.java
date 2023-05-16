/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cartmanagement.persistence;

import com.cartmanagement.helpers.Device;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author student
 */
public class CRUD_Device {
    
    
    private static Connection getCon(){
        Connection con = null;
        String connection = System.getenv("DB_URL");
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://"+ connection +"/CMS?allowPublicKeyRetrieval=true&useSSL=false", "root", "student");
            System.out.println("Connection established!");
        }catch(Exception e){
            System.out.println(e);
        }
        return con;
    }
    
    
    public void createDevice(){
        //create new device
    }
    
    
    public boolean deleteDevice(int deviceID){
        //delete device from database
        boolean isDeleted = false;
        Connection con;
        Statement st;
        try{
            
            con = getCon();
            st = con.createStatement();
            String delete = "DELETE FROM Device WHERE deviceID = " + deviceID;
            st.executeUpdate(delete);
            isDeleted = true;
            con.close(); 
        }catch(Exception e){
            System.out.println(e);
        }
        return isDeleted;
    }
}
