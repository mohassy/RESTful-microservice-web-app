/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontend;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.AbstractMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.business.Business;
import com.helpers.Administrator;
import com.helpers.Buyer;
import com.helpers.Cart;
import com.helpers.Device;
import com.helpers.User;
import java.util.Map.Entry;
import javax.servlet.http.HttpSession;

/**
 *
 * @author student
 */
@WebServlet(name = "FrontEnd", urlPatterns = {"/FrontEnd"})
public class FrontEnd extends HttpServlet {

    Authenticate autho;

    public FrontEnd() {
        autho = new Authenticate();
    }
    private final String authenticationCookieName = "login_token";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private Map.Entry<String, String> isAuthenticated(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String token = "";
        
        System.out.println("TOKEN IS");
        try {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName());
                if (cookie.getName().equals(authenticationCookieName)) {
                    token = cookie.getValue();
                }
            }
        } catch (Exception e) {

        }
        if (!token.isEmpty())
           try {
            if (this.autho.verify(token).getKey()) {
                  Map.Entry entry= new  AbstractMap.SimpleEntry<String, String>
                             (token,this.autho.verify(token).getValue());
            return entry;

            } else {
                 Map.Entry entry= new  AbstractMap.SimpleEntry<String, String>("","");
            return entry;
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FrontEnd.class.getName()).log(Level.SEVERE, null, ex);
        }

       Map.Entry entry= new  AbstractMap.SimpleEntry<String, String>("","");
            return entry;

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session;
        Entry entry;
        String token = isAuthenticated(request).getKey();
        String uname = isAuthenticated(request).getValue();
        String hiddenParam = request.getParameter("pageName");
        User user = null;
        boolean success;
        switch (hiddenParam) {
            case "login":
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                user = Business.Authenticate(email, password);
                token = autho.createJWT("FrontEnd", email, 100000);
                Cookie newCookie = new Cookie(authenticationCookieName, token);
                response.addCookie(newCookie);
                if(user instanceof Administrator) {
                    session = request.getSession();
                    session.setAttribute("admin", (Administrator)user);
                    RequestDispatcher rd = request.getRequestDispatcher("register.html");
                    rd.forward(request, response);
                }else if(user instanceof Buyer){
                    //get cart from cart microservice
                    Cart cart = Business.getCart((Buyer)user, token);
                    session = request.getSession();
                    session.setAttribute("cart", cart);
                    session.setAttribute("buyer", (Buyer)user);
                    RequestDispatcher rd = request.getRequestDispatcher("cart.jsp");
                    rd.forward(request, response);
                }else{
                    RequestDispatcher rd = request.getRequestDispatcher("index.html");
                    rd.forward(request, response);
                }
                break;
            case "buy":
                entry = autho.verify(token);
                success = (boolean) entry.getKey();
                if(!success){
                    break;
                }
                Device boughtDevice;
                int deviceID = Integer.parseInt((String) request.getParameter("buyDevice"));
                session = request.getSession();
                Cart cart = (Cart) session.getAttribute("cart");
                //find device in cart, delete + disptach if found.
                for (Device device : cart.getDevices()) {
                    if (device.getDeviceID() == deviceID) {
                        boughtDevice = device;
                        Business business = new Business();
                        if (business.buyDevice(boughtDevice, token)) {
                            session.setAttribute("boughtDevice", (Device) (boughtDevice));
                            RequestDispatcher rd = request.getRequestDispatcher("checkout.jsp");
                            rd.forward(request, response);
                        } else {
                            RequestDispatcher rd = request.getRequestDispatcher("index.html");
                            rd.forward(request, response);
                        }
                        break;
                    }
                }
                break;
            case "register":
                //create a user and marshall into xml
                entry = autho.verify(token);
                success = (boolean) entry.getKey();
                if(!success){
                    break;
                }
                String firstName, lastName;
                long phoneNumber;
                firstName = (String) request.getParameter("firstName");
                lastName = (String) request.getParameter("lastName");
                email = (String) request.getParameter("email");
                password = (String) request.getParameter("password");
                phoneNumber =  Long.parseLong((String)request.getParameter("phoneNumber"));
                //create buyer
                User buyer = new Buyer(firstName, lastName, email, password, phoneNumber);
                //Register user in the Register microservice
                success = Business.register(buyer, token);
                if(success){
                    RequestDispatcher rd = request.getRequestDispatcher("index.html");
                    rd.forward(request, response);
                }else{
                    RequestDispatcher rd = request.getRequestDispatcher("register.html");
                    rd.forward(request, response);
                }
                break;
            case "logout":
                RequestDispatcher rd = request.getRequestDispatcher("index.html");
                rd.forward(request, response);
                break;
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    

}
