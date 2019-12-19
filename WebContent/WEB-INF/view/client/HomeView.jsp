<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="bean.Category" %>
<jsp:include page="_header.jsp"></jsp:include>
  <main class="site-main">

    <!--================ Hero banner start =================-->
    <section class="hero-banner">
      <div class="container">
        <div class="row no-gutters align-items-center pt-60px">
          <div class="col-5 d-none d-sm-block">
            <div class="hero-banner__img">
              <img class="img-fluid" src="img/hero-banner.png" alt="">
            </div>
          </div>
          <div class="col-sm-7 col-lg-6 offset-lg-1 pl-4 pl-md-5 pl-lg-0">
            <div class="hero-banner__content">
              <h4>Shop is fun</h4>
              <h1>Browse Our Premium Product</h1>
              <p>Us which over of signs divide dominion deep fill bring they're meat beho upon own earth without morning over third. Their male dry. They are great appear whose land fly grass.</p>
              <a class="button button-hero" href="#">Browse Now</a>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!--================ Hero banner start =================-->

    <!--================ Hero Carousel start =================-->
    <section class="section-margin mt-0">
      <div class="owl-carousel owl-theme hero-carousel">
        <c:forEach items = "${NAME_LIST_CATEGORY }" var = "category">
        	<div class="hero-carousel__slide">
	          <img src="img/product/${category.getImage() }" alt="" class="img-fluid">
	          <a href="#" class="hero-carousel__slideOverlay">
	            <h3>${category.getName() }</h3>
	            <p>Category Item</p>
	          </a>
        	</div>
        </c:forEach>
      </div>
    </section>
    <!--================ Hero Carousel end =================-->

    <!-- ================ trending product section start ================= -->  
    <section class="section-margin calc-60px">
		<div class="container">
			<div class="section-intro pb-60px">
			  <p>Popular Item in the market</p>
			  <h2>Trending <span class="section-intro__style">Product</span></h2>
			</div>
			<div class="row">
				<c:forEach items = "${NAME_LIST_TRENDING_PRODUCT }" var = "trendingProduct" >
					<div class="col-md-6 col-lg-4 col-xl-3">
					<div class="card text-center card-product">
						<div class="card-product__img">
							<img class="card-img" src="img/product/${trendingProduct.getImage() }" alt="">
							<ul class="card-product__imgOverlay">
							  <li><button onclick="location.href='${pageContext.request.contextPath}/product?productID=${trendingProduct.getProductID()}'" type="button"><i class="ti-search"></i></button></li>
							  <li><button><i class="ti-shopping-cart"></i></button></li>
							  <li><button><i class="ti-heart"></i></button></li>
							</ul>
						</div>
						<div class="card-body">
							<h4 class="card-product__title"><a href="single-product.html">${trendingProduct.getName() }</a></h4>
							<p class="card-product__price">$${trendingProduct.getPrice() }</p>
						</div>
					</div>
				</div>
				</c:forEach>
			</div>
		 </div>
    </section>
    <!-- ================ trending product section end ================= -->  

	<!-- ================ all product section start ================= --> 
	<section class="section-margin calc-60px">
		<div class="container">
			<div class="section-intro pb-60px">
			  <p>All Product in the market</p>
			  <h2>All <span class="section-intro__style">Product</span></h2>
			</div>
			<div class="row">
				<c:forEach items = "${NAME_LIST_ALL_PRODUCT }" var = "product" >
					<div class="col-md-6 col-lg-4 col-xl-3">
					<div class="card text-center card-product">
						<div class="card-product__img">
							<img class="card-img" src="img/product/${product.getImage() }" alt="">
							<ul class="card-product__imgOverlay">
							  <li><button onclick="location.href='${pageContext.request.contextPath}/product?productID=${product.getProductID()}'" type="button"><i class="ti-search"></i></button></li>
							  <li><button><i class="ti-shopping-cart"></i></button></li>
							  <li><button><i class="ti-heart"></i></button></li>
							</ul>
						</div>
						<div class="card-body">
							<h4 class="card-product__title"><a href="single-product.html">${product.getName() }</a></h4>
							<p class="card-product__price">$${product.getPrice() }</p>
						</div>
					</div>
				</div>
				</c:forEach>
			</div>
		 </div>
    </section>
	<!-- ================ all product section end ================= -->
  </main>


<jsp:include page="_footer.jsp"></jsp:include>