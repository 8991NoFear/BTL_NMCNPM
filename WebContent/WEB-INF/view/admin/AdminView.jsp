<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import = "bean.User" %>

<!DOCTYPE html>
<html>
<head>
<meta name='viewport' content='width=device-width, initial-scale=1'>
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<style>
* {
  box-sizing: border-box;
}

body {
  margin: 0;
  font-family: tohoma;}

::-webkit-scrollbar {
  width: 10px;
}

::-webkit-scrollbar-track {
  background: #ccc; 
}
 
::-webkit-scrollbar-thumb {
  background: #888; 
}

::-webkit-scrollbar-thumb:hover {
  background: #555; 
}

html, body {
  margin: 0;
  height: 100%
}

h1 {
  text-align: center;
  font-size: 20px;
  color: orange;
}

.row {  
  display: flex;
  flex-wrap: wrap;
  height: 100%;
}

.sidenav{
  margin-top: 0px;
  height: 100%;
  width: 256px;
  padding: 20px;
  border: 5px solid #ccc;
  background-color: #0040ff;
}

img {
  width: 100%;
}

.btn1, .btn2 {
  text-decoration: none;
  padding: 14px 16px;
  font-size: 16px;
  color: #ccc;
  display: block;
  border: 2px solid #666;
  border-top: 2px solid #333;
  border-bottom: 2px solid #999;
  width: 100%;
  text-align: left;
  outline: none;
}

.btn1 {
  background-color: #262626;
  margin-top: 10px;
}

.btn1:hover {
  color: white;
  background-color: #555;
}

.container {
  display: none;
}

.btn2 {
  background-color: #444;
  margin-top: 0;
}

.btn2:hover {
  background-color: #888;
  color: white;
}

.right{
  float: right;
}

.main{ 
  margin-top: 20px;
  flex: 75%;
  padding: 20px;
}

.footer {
  position: relative;
  bottom: 0;
  width: 100%;
  padding: 10px;
  min-height: 100px;
  background-color: #555;
  margin-top: 0px;
  color: #66ff66;
  text-align: center;
  display: block;
  clear: left;
}
</style>
</head>
<body>
  <div class="row">
    <div class="sidenav">
      <button class="tablinks btn1" onclick="openCity(event, 'Home')" id="defaultOpen">Home<i class="fa fa-home right"></i></button>
      <button class="tablinks btn1" onclick="openCity(event, 'User')">User<i class="fa fa-user right"></i></button>  
      <button class="tablinks btn1" onclick="openCity(event, 'Product')">Product<i class="fa fa-shopping-cart right"></i></button>
      <button class="tablinks btn1" onclick="openCity(event, 'Category')">Category<i class="fa fa-list right"></i></button>
      <button class="dropdown btn1">Manage Order<i class="fa fa-caret-down right"></i></button>
      <div class="container">
        <button class="tablinks btn2" onclick="openCity(event, 'NewOrder')">New Order<i class="fa fa-edit right"></i></button>
        <button class="tablinks btn2" onclick="openCity(event, 'AllOrder')">All Order<i class="fa fa-edit right"></i></button>
      </div>
      <button class="tablinks btn1" onclick="openCity(event, 'Setting')">Logout<i class="fa fa-undo right"></i></button>
    </div>

    <div class="main">
      <div id="Home" class="tabcontent">
      
        <!-- Giao diện bắt đầu sau khi đăng nhập admin -->
        
      </div>
      <div id="User" class="tabcontent">
<!-- Giao diện User -->
		
		
		
<a href="admin/createUser" method = "GET">Create New User</a>		
<table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>Username</th>
          <th>Password</th>
          <th>Email</th>
          <th>Admin</th>
          <th>Delete</th>
       </tr>
       <c:forEach items = "${NAME_LIST_USER}" var = "user">
          <tr>
             <td>${user.getUsername()}</td>
             <td>${user.getPassword()}</td>
             <td>${user.getEmail()}</td>
             <td>${user.isAdmin()}</td>
             <td>
                <a href="admin/deleteUser?username=${user.getUsername()}">Delete</a>
             </td>
          </tr>
       </c:forEach>
</table>
 
    
      </div>
      <div id="Product" class="tabcontent">
<!-- Giao diện Product -->
        
<a href="admin/createProduct">Create New Product</a>      
<table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>Product ID</th>
          <th>Category ID</th>
          <th>Name</th>
          <th>Quantity</th>
          <th>Price</th>
          <th>Description</th>
          <th>Image</th>
          <th>Trending</th>
          <th>Edit</th>
          <th>Delete</th>
       </tr>
       <c:forEach items = "${NAME_LIST_PRODUCT}" var = "product">
          <tr>
             <td>${product.getProductID()}</td>
             <td>${product.getCategoryID()}</td>
             <td>${product.getName()}</td>
             <td>${product.getQuantity()}</td>
             <td>${product.getPrice()}</td>
             <td>${product.getDescription()}</td>
             <td>${product.getImage()}</td>
             <td>${product.isTrending()}</td>
             <td>
                <a href="admin/editProduct?productID=${product.getProductID()}">Edit</a>
             </td>
             <td>
                <a href="admin/deleteProduct?productID=${product.getProductID()}">Delete</a>
             </td>
          </tr>
       </c:forEach>
</table>
        

      </div>
      <div id="Category" class="tabcontent">
<!-- Giao diện Category -->


<a href="createCategory">Create New Category</a>
<table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>Category ID</th>
          <th>Name</th>
          <th>Image</th>
          <th>Edit</th>
          <th>Delete</th>
       </tr>
       <c:forEach items = "${NAME_LIST_CATEGORY}" var = "category">
          <tr>
             <td>${newOrder.getCategoryID()}</td>
             <td>${newOrder.getName()}</td>
             <td>${newOrder.getImage()}</td>
             <td>${newOrder.getName()}</td>
             <td>
                <a href="editCategory?CategoryID=${categoryry.getCategoryID()}">Edit</a>
             </td>
             <td>
                <a href="deleteCategory?CategoryID=${category.getCategoryID()}">Delete</a>
             </td>
          </tr>
       </c:forEach>
</table>




      </div>
      <div id="NewOrder" class="tabcontent">
<!-- Giao diện New Order -->


<table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>Order ID</th>
          <th>Product ID</th>
          <th>Username</th>
          <th>Name</th>
          <th>Phone</th>
          <th>Address</th>
          <th>Quantity</th>
          <th>Time</th>
          <th>Confirm</th>
          <th>Delete</th>
       </tr>
       <c:forEach items = "${NAME_LIST_NEW_ORDER}" var = "newOrder">
          <tr>
             <td>${newOrder.getOrderID()}</td>
             <td>${newOrder.getProductID()}</td>
             <td>${newOrder.getUsername()}</td>
             <td>${newOrder.getName()}</td>
             <td>${newOrder.getPhone()}</td>
             <td>${newOrder.getAddress()}</td>
             <td>${newOrder.getQuantity()}</td>
             <td>${newOrder.getDateCreated()}</td>
             <td>
                <a href="confirmOrder?OrderID=${newOrder.getOrderID()}">Confirm</a>
             </td>
             <td>
                <a href="deleteOrder?OrderID=${newOrder.getOrderID()}">Delete</a>
             </td>
          </tr>
       </c:forEach>
</table>


      </div>
      <div id="AllOrder" class="tabcontent">
<!-- Giao diện All Order -->



<table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>Order ID</th>
          <th>Product ID</th>
          <th>Username</th>
          <th>Name</th>
          <th>Phone</th>
          <th>Address</th>
          <th>Quantity</th>
          <th>Time</th>
          <th>Confirm</th>
       </tr>
       <c:forEach items = "${NAME_LIST_ALL_ORDER}" var = "order">
          <tr>
             <td>${order.getOrderID()}</td>
             <td>${order.getProductID()}</td>
             <td>${order.getUsername()}</td>
             <td>${order.getName()}</td>
             <td>${order.getPhone()}</td>
             <td>${order.getAddress()}</td>
             <td>${order.getQuantity()}</td>
             <td>${order.getDateCreated()}</td>
             <td>${order.isConfirm()}</td>
          </tr>
       </c:forEach>
</table>



      </div>
  </div>
</div>

<div class="footer">
  <p>Được tạo bởi: Trần Tuấn Anh</p>
  <p>Email: anh.tt172944@sis.hust.edu.vn</p>
  <p>Điện Thoại: 0969185432</p>
</div>

<script>
function openCity(evt, cityName) {
  var i, tabcontent, tablinks;
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }
  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }
  document.getElementById(cityName).style.display = "block";
  evt.currentTarget.className += " active";
}
document.getElementById("defaultOpen").click();

/* ___________________________________________________________ */
var dropdown = document.getElementsByClassName("dropdown");
var i;
for (i = 0; i < dropdown.length; i++) {
  dropdown[i].addEventListener("click", function() {
    this.classList.toggle("active");
    var dropdownContent = this.nextElementSibling;
    if (dropdownContent.style.display === "block") {
      dropdownContent.style.display = "none";
    } else {
      dropdownContent.style.display = "block";
    }
  });
}

var myNodelist = document.getElementsByTagName("LI");
var i;
for (i = 0; i < myNodelist.length; i++) {
  var span = document.createElement("SPAN");
  var txt = document.createTextNode("\u00D7");
  span.className = "close";
  span.appendChild(txt);
  myNodelist[i].appendChild(span);
}

var close = document.getElementsByClassName("close");
var i;
for (i = 0; i < close.length; i++) {
  close[i].onclick = function() {
    var div = this.parentElement;
    div.style.display = "none";
  }
}

var list = document.querySelector('ul');
list.addEventListener('click', function(ev) {
  if (ev.target.tagName === 'LI') {
    ev.target.classList.toggle('checked');
  }
}, false);

function newElement() {
  var li = document.createElement("li");
  var inputValue = document.getElementById("myInput").value;
  var t = document.createTextNode(inputValue);
  li.appendChild(t);
  if (inputValue === '') {
    alert("You must write something!");
  } else {
    document.getElementById("myUL").appendChild(li);
  }
  document.getElementById("myInput").value = "";

  var span = document.createElement("SPAN");
  var txt = document.createTextNode("\u00D7");
  span.className = "close";
  span.appendChild(txt);
  li.appendChild(span);

  for (i = 0; i < close.length; i++) {
    close[i].onclick = function() {
      var div = this.parentElement;
      div.style.display = "none";
    }
  }
}
</script>

</body>
</html> 