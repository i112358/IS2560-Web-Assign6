<%-- 
    Document   : checkYourFlight
    Created on : Nov 21, 2016, 3:48:06 PM
    Author     : Rachel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check your flights</title>
    </head>
    <body>
        <h1>Enter your passenger Id to check or cancel your flights!</h1>
        <form action="checkFlight" method="POST">
            Enter your passenger ID: <input type="text" name="passId">
            <br />
            <input type="submit" value="Check your flights">
        </form>
        <a href="index.jsp">Return to top page</a>
    </body>
</html>
