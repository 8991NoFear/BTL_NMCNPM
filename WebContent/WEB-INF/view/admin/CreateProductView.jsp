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
    <h1>Create New Product</h1>
    <form method="POST" action="${pageContext.request.contextPath}/admin/createProduct" enctype="multipart/form-data">
    	<table border="0">
            <tr>
               <td>Product ID</td>
               <td><input type="text" name="productID" value="${NAME_PRODUCT.getProductID() }" required/></td>
            </tr>
            <tr>
               <td>Category ID</td>
               <td><input type="text" name="categoryID" value="${NAME_PRODUCT.getCategoryID() }" required/></td>
            </tr>
            <tr>
               <td>Name Of Product</td>
               <td><input type="text" name="name" value="${NAME_PRODUCT.getName() }" required/></td>
            </tr>
            <tr>
               <td>Price</td>
               <td><input type="text" name="price" value="${NAME_PRODUCT.getPrice() }" required/></td>
            </tr>
            <tr>
               <td>Quantity</td>
               <td><input type="text" name="quantity" value="${NAME_PRODUCT.getQuantity() }" required/></td>
            </tr>
            <tr>
               <td>Description</td>
               <td><input type="text" name="description" value="${NAME_PRODUCT.getDescription() }"/></td>
            </tr>
            <tr>
               <td>image</td>
               <td><input type="file" name="image" required/></td>
            </tr>
            <tr>
               	<input type="checkbox" name="isTrending" value="YES" />
				<label>Is Trending?</label>
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