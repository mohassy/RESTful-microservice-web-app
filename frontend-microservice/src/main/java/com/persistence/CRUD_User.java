/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistence;

import com.helpers.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author student
 */
public class CRUD_User {
    private static Connection getCon() {
        Connection con = null;
        String connection = System.getenv("DB_URL");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://" + connection + "/FEMS?allowPublicKeyRetrieval=true&useSSL=false", "root", "student");
            System.out.println("Connection established!");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    public boolean createUser(User user) {
        boolean isCreated = false;
        Statement s;
        //create user in database
        try {
            Connection con = getCon();
            Buyer buyer = (Buyer) user;
            String newBuyer = "INSERT INTO Buyer (firstName, lastName, email, password, phoneNumber) "
                    + "VALUES('" + buyer.getFirstName() + "', '" + buyer.getLastName() + "', '" + buyer.getEmail() + "', '" + buyer.getPassword() + "', " + buyer.getPhoneNumber() + ")";

            s = con.createStatement();
            s.executeUpdate(newBuyer);
            isCreated = true;
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return isCreated;
    }

    public User readUser(String email, String password) {
        User user = null;
        int id;
        String firstName, lastName;
        long phoneNumber;
        //check if user is in database
        try {
            Connection con = getCon();

            String q = "SELECT * FROM Administrator WHERE email = " + " '" + email + "' ";

            PreparedStatement ps = con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("adminID");
                firstName = rs.getString("firstName");
                lastName = rs.getString("lastName");
                phoneNumber = rs.getLong("phoneNumber");
                if (password.equals(rs.getString("password"))) {
                    user = new Administrator(id, firstName, lastName, email, password, phoneNumber);
                }
            } else {
                q = "SELECT * FROM Buyer WHERE email = " + " '" + email + "' ";
                ps = con.prepareStatement(q);
                rs = ps.executeQuery();
                if (rs.next()) {
                    id = rs.getInt("buyerID");
                    firstName = rs.getString("firstName");
                    lastName = rs.getString("lastName");
                    phoneNumber = rs.getLong("phoneNumber");
                    if (password.equals(rs.getString("password"))) {
                        user = new Buyer(firstName, lastName, email, password, phoneNumber);
                        ((Buyer) user).setBuyerID(id);
                    }
                }
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return user;
    }

}
