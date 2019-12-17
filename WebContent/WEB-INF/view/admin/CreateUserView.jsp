<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Create New User</h1>
       
      <p style="color: red;">${NAME_ERROR}</p>
       
      <form method="POST" action="${pageContext.request.contextPath}/admin/createUser">
         <table border="0">
            <tr>
               <td>Username</td>
               <td><input type="text" name="username" value="${NAME_USER.getUsername() }" required/></td>
            </tr>
            <tr>
               <td>Email</td>
               <td><input type="text" name="email" value="${NAME_USER.getEmail() }" required/></td>
            </tr>
            <tr>
               <td>Password</td>
               <td><input type="text" name="password" value="${NAME_USER.getPassword() }" required/></td>
            </tr>
            <tr>
               <td>Confirm Password</td>
               <td><input type="text" name="confirmPassword" value="" required/></td>
            </tr>
            <tr>
               <td colspan="2">                   
                   <input type="submit" value="Submit" />
                   <a href="${pageContext.request.contextPath}/admin">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
</body>
</html>