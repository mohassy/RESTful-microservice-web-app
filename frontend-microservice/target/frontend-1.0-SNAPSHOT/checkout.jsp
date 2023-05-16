<%-- 
    Document   : checkout
    Created on : Feb 11, 2023, 4:59:44 PM
    Author     : student
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.helpers.*"%>
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
        <title>Checkout Page</title>
    </head>
    <body>
        <% Device device = (Device)session.getAttribute("boughtDevice");%>
        <h1 class="text-center">Your total is <%= device.getPrice() + "!" %></h1>
        <form action="FrontEnd" method="post">
            <input type="hidden" name="pageName" value="logout">
            <button class="btn btn-primary" value="FrontEnd">Logout</button>
        </form>
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
            crossorigin="anonymous"
        ></script>
    </body>
</html>
