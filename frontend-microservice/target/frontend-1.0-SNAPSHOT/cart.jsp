<%-- 
    Document   : cart
    Created on : Feb 11, 2023, 3:08:32 PM
    Author     : student
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.helpers.*"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
            crossorigin="anonymous"
            />
        <title>Cart</title>
    </head>

    <body>
        <div class="container">

            <div class="row my-2">
                <h1 class="text-center display-3 fw-bold"><%= ((Buyer)session.getAttribute("buyer")).getFirstName()%>'s Cart</h1>
            </div>
            <div class="row col-8 mx-auto">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col" >Category</th>
                            <th scope="col" class="col-2 m-auto">Image</th>
                            <th scope="col">Details</th>
                            <th scope="col">Price</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                        Cart cart = (Cart)(session.getAttribute("cart"));
                        if (cart != null){
                            for (Device d : cart.getDevices()) {%>
                        <tr>
                            <td scope="row"><%= d.getDeviceType() %></td>
                            <td>
                                <div class="container-fluid">
                                    <img
                                        src="<%=d.getImgurl()%>"
                                        class="img-thumbnail"
                                        />
                                </div>
                            </td>
                            <td>
                                <% int i = 0;
                                    String[] details = d.getSpecs();%>
                                <%=d.getName()%><br/>
                                <ul>
                                    <li><%=details[i++]%></li>
                                    <li><%=details[i++]%></li>
                                    <li><%=details[i]%></li>
                                </ul>
                            </td>
                            <td><%= d.getPrice()%></td>
                            <td>
                                <form action="FrontEnd" method="post">
                                    <input type="hidden" name="pageName" value="buy">
                                    <button name="buyDevice" value="<%= d.getDeviceID() %>" class="btn btn-warning">Buy Now</button>
                                </form>
                            </td>
                        </tr>
                            <% }
                        }%>
                    </tbody>
                </table>   
            </div>



        </div>

        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
            crossorigin="anonymous"
        ></script>
    </body>
</html>
