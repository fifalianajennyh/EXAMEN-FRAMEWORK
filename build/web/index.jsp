<%-- 
    Document   : index
    Created on : 3 avr. 2023, 20:41:08
    Author     : itu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/insertion.css">
    <title>Page  |Insertion</title>
</head>
<body>
    <h1>FRAMEWORK</h1>
    <form action="http://localhost:8080/Web_dynamique/FrontServlet" method="get">
    <p>Entrer votre nom= <input type="text" name="nom"></p>
    <p>Entrer votre age= <input type="number" name="age"></p>
    <input type="submit" value="ok">
    </form>
    <p><a href ="http://localhost:8080/Web_dynamique/FrontServlet?etu=2090">Test1</a></p>
</body>
</html>