/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.business;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.io.IOUtils;
import com.helpers.*;
import com.persistence.CRUD_User;
import javax.xml.bind.Marshaller;
/**
 *
 * @author student
 */
public class Business {
    
    public static User Authenticate( String email, String password) {
        User user;
        CRUD_User cu = new CRUD_User();
        user = cu.readUser(email, password);
        return user; 
    }
    
    
    public static Cart getCart(Buyer buyer, String token) throws IOException{
        String cartservice = System.getenv("cartservice");
        Client sc = ClientBuilder.newClient();
        WebTarget swt = sc.target("http://"+ cartservice +"/CartManagement/webresources/cart");
        InputStream is = swt.path(buyer.getBuyerID()+"").request(MediaType.APPLICATION_XML).get(InputStream.class);
        String xml = IOUtils.toString(is, "utf-8");
        System.out.println(xml);
        Cart cart = xmlToCart(xml);
        return cart;
    }
    
    public static boolean buyDevice(Device device, String token) throws IOException{
        String cartservice = System.getenv("cartservice");
        Client sc = ClientBuilder.newClient();
        WebTarget target = sc.target("http://"+ cartservice +"/CartManagement/webresources/cart");
        Response response = target.path(device.getDeviceID()+"").request().delete();
        int statusCode = response.getStatus();
        if(statusCode == 200){
            return true;
        }else{
            return false;
        }
    }
    
    public static boolean register(User user, String token) throws IOException{
        String registerservice = System.getenv("registerservice");
        //Build the JAX-RS client
        Client client = ClientBuilder.newClient();
        //Build web target
        WebTarget target = client.target("http://"+ registerservice +"/Registration/webresources/register");
        //create user xml string
        String buyerXml = buyerToXml((Buyer)user);
        System.out.println(buyerXml);
        Response response = target.request().put(javax.ws.rs.client.Entity.entity(buyerXml, MediaType.APPLICATION_XML));
        int statusCode = response.getStatus();
        System.out.println("Status Code: " + statusCode);
        if(statusCode == 200){
            System.out.println("Succesfull Registration");
            return true;
        }else {
            System.out.println("Registration Error");
            return false;
        }
        
    }

    
    private static Cart xmlToCart(String xml){
        JAXBContext jaxbContext;
        try{
            jaxbContext = JAXBContext.newInstance(Cart.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Cart cart = (Cart) jaxbUnmarshaller.unmarshal(new StringReader(xml));
            return cart;
        } catch(JAXBException e){
            System.out.println(e);
        }
        return null;
    }
    private static String buyerToXml(Buyer buyer){
        JAXBContext jaxbContext;
        try{
            jaxbContext = JAXBContext.newInstance(Buyer.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            StringWriter writer = new StringWriter();
            marshaller.marshal(buyer, writer);
            return writer.toString();
        } catch(JAXBException e){
            System.out.println(e);
        }
        return null;
    }
    

}
