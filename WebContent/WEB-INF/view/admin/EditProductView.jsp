<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <style type="text/css">
* {
  box-sizing: border-box;
}

body {
  margin: 0;
  background-color: #555;
  font-family: tohoma;}

.card {
	display: block;
	border: 5px solid #ccc;
	width: 1000px;
	color: white;
	background-color: #4CAF50;
	margin: auto;
	margin-top: 20px;
	padding: 20px;
	min-height: 250px;
}

.card h2, .card h1 {
	text-align: center;
	color: #ffc14d;
}

.card span {
	font-weight: bold;
	color: #ffc14d;
}

.card ul li a {
	text-decoration: none;
	color: white;
}

.card ul li a:hover {
	color: orange;
}

input[type=text], input[type=file] {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  resize: vertical;
}

input[type=text]:focus, input[type=file]:focus {
	border: 1px solid orange;
	background-color: #e6e6e6;
}

.a1, .a2{
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  float: right;
  margin: 10px 10px 0 0;
}

.a1{
  background-color: orange;
}

.a2{
  background-color: red;
}

.a1:hover, .a2:hover {
	opacity: 0.8;
}

.a1 a, .a2 a{
	text-decoration: none;
	color: white;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}

.col1 {
  color: #ffc14d;
  float: left;
  width: 20%;
  margin-top: 6px;
  font-size: 20px;
}

.col2 {
  float: left;
  width: 80%;
  margin-top: 6px;
}

.info:after {
  content: "";
  display: table;
  clear: both;
}
  </style>
</head>
<body>
		<div class="card">
			<h1>Edit Product</h1>
			<p style="color: red;">${NAME_ERROR}</p>
			<form method="POST" action="${pageContext.request.contextPath}/admin/editProduct" enctype="multipart/form-data">
				<div class="info">
					<div class="col1">
						<label>Product ID:</label>
					</div>
					<div class="col2">
						<input type="text" name="productID" value="${NAME_PRODUCT.getProductID() }" required/>
					</div>
				</div>
				<div class="info">
					<div class="col1">
						<label>Category ID:</label>
					</div>
					<div class="col2">
						<input type="text" name="categoryID" value="${NAME_PRODUCT.getCategoryID() }" required/>
					</div>
				</div>
				<div class="info">
					<div class="col1">
						<label>Name Of Product:</label>
					</div>
					<div class="col2">
						<input type="text" name="name" value="${NAME_PRODUCT.getName() }" required/>
					</div>
				</div><div class="info">
					<div class="col1">
						<label>Price:</label>
					</div>
					<div class="col2">
						<input type="text" name="price" value="${NAME_PRODUCT.getPrice() }" required/>
					</div>
				</div>
				<div class="info">
					<div class="col1">
						<label>Quantity:</label>
					</div>
					<div class="col2">
						<input type="text" name="quantity" value="${NAME_PRODUCT.getQuantity() }" required/>
					</div>
				</div>
				<div class="info">
					<div class="col1">
						<label>Description:</label>
					</div>
					<div class="col2">
						<input type="text" name="description" value="${NAME_PRODUCT.getDescription() }"/>
					</div>
				</div>
				<div class="info">
					<div class="col1">
						<label>Image:</label>
					</div>
					<div class="col2">
						<input type="file" name="image" required/>
					</div>
				</div>
				<div class="info">
					<div class="col1">
					</div>
					<div class="col2">
						<div style="float:right;">
							<input type="checkbox" name="isTrending" value="YES" /><label>Is Trending?</label>
						</div>
					</div>
				</div>
				<div class="info">
					<input type="submit" value="Submit" class="a1" />
					<button class="a2"><a href="${pageContext.request.contextPath}/admin">Cancel</a></button>
				</div>
			</form>
		</div>
</body>
</html>