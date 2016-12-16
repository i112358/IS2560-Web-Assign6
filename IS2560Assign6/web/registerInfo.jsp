<%-- 
    Document   : registerInfo
    Created on : Nov 21, 2016, 12:42:44 PM
    Author     : Rachel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <h1>Hello New User! Register to become a passenger at Something Airlines!</h1>
        <form action="register" method="POST">
            Enter Your First Name: <input type="text" name="firstName" required><br/>
            Enter Your Last Name: <input type="text" name="lastName" required><br/>
            Enter Your Birth Date (YYYY-MM-DD): <input type="text" name="birthDate" required><br/>
            Enter Your Gender (F/M): <input type="text" name="gender" maxlength="1" required><br/> 
            Enter Your Address (Optional): <input type="text" name="address"><br/> 
            <input type="submit" value="Register">
        </form>
        <a href="index.jsp">Cancel registration</a>
    </body>
</html>
