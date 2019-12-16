<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3>Create New User</h3>
       
      <p style="color: red;">${ERROR}</p>
       
      <form method="POST" action="${pageContext.request.contextPath}/admin/editUser">
         <table border="0">
            <tr>
               <td>Username</td>
               <td><input type="text" name="code" value="" required/></td>
            </tr>
            <tr>
               <td>Email</td>
               <td><input type="text" name="name" value="" required/></td>
            </tr>
            <tr>
               <td>Password</td>
               <td><input type="text" name="price" value="" required/></td>
            </tr>
            <tr>
               <td>Confirm Password</td>
               <td><input type="text" name="price" value="" required/></td>
            </tr>
            <tr>
               <td colspan="2">                   
                   <input type="submit" value="Submit" />
                   <a href="productList">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
</body>
</html>