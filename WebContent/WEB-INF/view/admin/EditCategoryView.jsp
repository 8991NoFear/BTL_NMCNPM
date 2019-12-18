<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Edit Category</h1>
       
      <p style="color: red;">${NAME_ERROR}</p>
       
      <form method="POST" action="${pageContext.request.contextPath}/admin/editCategory" enctype="multipart/form-data">
         <table border="0">
            <tr>
               <td>Category ID</td>
               <td><input type="text" name="categoryID" value="${NAME_CATEGORY.getCategoryID() }" required/></td>
            </tr>
            <tr>
               <td>Name Of Category</td>
               <td><input type="text" name="name" value="${NAME_CATEGORY.getName() }" required/></td>
            </tr>
            <tr>
               <td>Image</td>
               <td><input type="file" name="image" value="${NAME_CATEGORY.getImage() }" required/></td>
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