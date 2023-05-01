<%-- 
    Document   : index
    Created on : 28 mars 2023, 02:08:00
    Author     : itu
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Dept" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/insertion.css">
    <title>Bienvenue</title>
</head>
<body>
    <h1>FRAMEWORK JENNY</h1>
    <p>Mety le izy</p>
     <p><%= request.getAttribute("test1") %></p>
     <p>Do you know that:</p>
      <% String a=(String)request.getAttribute("test2"); %>
    <h3><%out.print(a);%></h3>
</body>
</html>