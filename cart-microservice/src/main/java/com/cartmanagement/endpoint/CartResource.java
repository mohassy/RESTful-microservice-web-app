/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cartmanagement.endpoint;

import com.cartmanagement.business.CartManager;
import com.cartmanagement.helpers.Cart;
import com.cartmanagement.persistence.CRUD_Device;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import static javax.ws.rs.client.Entity.xml;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * REST Web Service
 *
 * @author student
 */
@Path("cart/{id}")
public class CartResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CartResource
     */
    public CartResource() {
    }

    /**
     * Retrieves representation of an instance of com.cartmanagement.endpoint.CartResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public InputStream getXml(@PathParam("id") String buyerID) {
        //TODO return proper representation object
        Cart cart = CartManager.getCart(buyerID);
        System.out.println(cart.getCartID());
        System.out.println(buyerID);
        String xml = cartToXml(cart);
        System.out.println(xml);
        InputStream inputStream = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
        return inputStream;
    }
    
    /**
     * Deletes device from database
     */
    @DELETE
    public Response deleteDevice(@PathParam("id") int id){
        CartManager cm = new CartManager();
        boolean success = cm.deleteDevice(id);
        if(success){
            return Response.status(Response.Status.OK).build();
        }else{
            return Response.status((Response.Status.EXPECTATION_FAILED)).build();
        }
    }

    /**
     * PUT method for updating or creating an instance of CartResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
        
    }
    
    public String cartToXml(Cart cart){
        
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(Cart.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            StringWriter writer = new StringWriter();
            marshaller.marshal(cart, writer);
            return writer.toString();
        } catch(JAXBException e){
            System.out.println(e);
            return null;
        }
    }
    
}
