/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.registration.persistence;
import com.registration.helpers.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
/**
 *
 * @author student
 */
public class CRUD_User {
    
    private static Connection getCon(){
        Connection con = null;
        String connection = System.getenv("DB_URL");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://"+ connection +"/RMS?allowPublicKeyRetrieval=true&useSSL=false", "root", "student");
            System.out.println("Connection established!");
        }catch(Exception e){
            System.out.println(e);
        }
        return con;
    }
    
    public boolean createUser(User user){
        boolean isCreated = false;
        Statement s;
        //create user in database
        try{
          Connection con = getCon();
          Buyer buyer = (Buyer)user;
          String newBuyer = "INSERT INTO Buyer (firstName, lastName, email, password, phoneNumber) "
                       + "VALUES('"+ buyer.getFirstName() +"', '"+ buyer.getLastName() +"', '"+ buyer.getEmail() +"', '"+ buyer.getPassword() +"', "+ buyer.getPhoneNumber() +")";
          
          s = con.createStatement();
          s.executeUpdate(newBuyer);
          isCreated = true;
          con.close();
        }catch(Exception e){
            System.out.println(e);
        }

        return isCreated;
    }
    
    
}
