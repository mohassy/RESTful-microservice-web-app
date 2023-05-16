/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.registration.endpoint;

import com.registration.business.Register;
import com.registration.helpers.Buyer;
import java.io.StringReader;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * REST Web Service
 *
 * @author student
 */
@Path("register")
public class RegisterResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RegisterResource
     */
    public RegisterResource() {
    }

    /**
     * Retrieves representation of an instance of com.registration.endpoint.RegisterResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of RegisterResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String buyerXml) {
        //unmarshall xml into a Buyer
        Buyer buyer = null;
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(Buyer.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            buyer = (Buyer) unmarshaller.unmarshal( new StringReader(buyerXml));
        }catch (JAXBException e){
            System.out.println(e);
        }
        
        //Register buyer in business componenet
        Register.registerBuyer(buyer);
    }
    
}
