<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div style="padding:5px; color:red;font-style:italic;">${NAME_ERROR}</div>
    <h1>Upload Image Of New Product</h1>
    <form method="POST" action="${pageContext.request.contextPath}/admin/createProduct" enctype="multipart/form-data">
    	<table border="0">
            <tr>
               <td>image</td>
               <td><input type="file" name="image" required/></td>
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