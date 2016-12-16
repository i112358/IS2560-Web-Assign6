<%-- 
    Document   : bookingForm
    Created on : Nov 19, 2016, 5:14:41 PM
    Author     : Rachel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book a flight</title>
    </head>
    <body>
        <h1>Booking flight <%= request.getParameter("flightNo") %></h1>
        <form action="bookFlight" method="POST">
            Enter Passenger ID: <input type="text" name="passengerId">
            <br />
            Enter flight date (YYYY-MM-DD): <input type="text" name="flightDate">
            <input type="hidden" name="flightNo" value=<%= request.getParameter("flightNo")%> >
            <br />
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
