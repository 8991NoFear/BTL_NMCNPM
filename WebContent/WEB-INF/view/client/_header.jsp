<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "model.Cart" %>
<%@ page import = "bean.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Aroma Shop</title>
	<link rel="icon" href="img/Fevicon.png" type="image/png">

  <link rel="stylesheet" href="vendors/bootstrap/bootstrap.min.css">
  <link rel="stylesheet" href="vendors/fontawesome/css/all.min.css">
    <link rel="stylesheet" href="vendors/themify-icons/themify-icons.css">
    <link rel="stylesheet" href="vendors/linericon/style.css">
  <link rel="stylesheet" href="vendors/owl-carousel/owl.theme.default.min.css">
  <link rel="stylesheet" href="vendors/owl-carousel/owl.carousel.min.css">
  <link rel="stylesheet" href="vendors/nice-select/nice-select.css">
  <link rel="stylesheet" href="vendors/nouislider/nouislider.min.css">

  <link rel="stylesheet" href="css/style.css">
</head>
<body>
	<!--================ Start Header Menu Area =================-->
	<header class="header_area">
    <div class="main_menu">
      <nav class="navbar navbar-expand-lg navbar-light">
        <div class="container">
          <a class="navbar-brand logo_h" href="${pageContext.request.contextPath}/home"><img src="img/logo.png" alt=""></a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <div class="collapse navbar-collapse offset" id="navbarSupportedContent">
            <ul class="nav navbar-nav menu_nav ml-auto mr-auto">
              <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/home">Home</a></li>
              <li class="nav-item submenu dropdown">
                <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                  aria-expanded="false">Shop</a>
                <ul class="dropdown-menu">
                  <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/home">Shop Category</a></li>
                  <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/home">All Product</a></li>
                  <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/home">Confirmation</a></li>
                  <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/home">Shopping Cart</a></li>
                </ul>
				</li>
      
				<li class="nav-item active submenu dropdown">
                <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                  aria-expanded="false">User</a>
                <ul class="dropdown-menu">
                  <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a></li>
                  <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a></li>
                  <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/register">Register</a></li>
                </ul>
              </li>
            </ul>

            <ul class="nav-shop">
              <li class="nav-item"><button onclick="location.href='${pageContext.request.contextPath}/cart'" type="button"><i class="ti-shopping-cart"></i><span class="nav-shop__circle">
					<jsp:scriptlet>
						Cart cart = (Cart) session.getAttribute("NAME_CART");
						int amountProduct = 0;
						if(cart != null){
							amountProduct = cart.getAmountProduct();
						}
						out.print(amountProduct);
					</jsp:scriptlet>
              </span></button> </li>
              <li class="nav-item"><a class="button button-header" href="${pageContext.request.contextPath}/userInfo">
              Xin Chào, 
              <jsp:scriptlet>
              	User user = (User) session.getAttribute("KEY_LOGINED_USER");
              	String username;
              	if(user != null){
              		username = user.getUsername();
              	} else {
              		username = "Khách Hàng";
              	}
              	out.print(username + "!");
              </jsp:scriptlet>
              </a></li>
            </ul>
          </div>
        </div>
      </nav>
    </div>
  </header>
	<!--================ End Header Menu Area =================-->