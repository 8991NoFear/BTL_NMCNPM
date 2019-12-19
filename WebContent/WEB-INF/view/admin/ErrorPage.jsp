<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Insert title here</title>
</head>
<body style="background-color: #ffdd99; padding: 200px;">
<h1 style="color: #ff1a1a;">
  <i class="fa fa-warning"></i> 
  OPP! There are some error occurred, you may be concerned about that:
  <i class="fa fa-warning"></i>
</h1>
<h2 style="color: #ff1a1a;">${NAME_ERROR }</h2>
<a style="float:right; font-size: 20px;" href="${pageContext.request.contextPath}/admin">Go back to admin page</a>
</body>
</html>