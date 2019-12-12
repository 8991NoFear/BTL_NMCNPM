<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>User Info</title>
 </head>
 <body>
 
    <jsp:include page="_header.jsp"></jsp:include>
 
    <h3>Hello: ${user.username}</h3>
 
    Username: <b>${user.username}</b>
    <br />
    Password: <b>${user.password}</b>
    <br />
    Email:<b> ${user.email}</b>
 
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>