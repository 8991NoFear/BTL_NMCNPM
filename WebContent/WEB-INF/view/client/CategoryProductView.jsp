<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page = "_header.jsp"></jsp:include>

<!-- ================ start banner area ================= -->	
	<section class="blog-banner-area" id="category">
		<div class="container h-100">
			<div class="blog-banner">
				<div class="text-center">
					<h1>Items Of ${NAME_CATEGORY.getName()}</h1>
					<nav aria-label="breadcrumb" class="banner-breadcrumb">
            <ol class="breadcrumb">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active" aria-current="page">category items</li>
            </ol>
          </nav>
				</div>
			</div>
    </div>
	</section>
	<!-- ================ end banner area ================= -->
  
  

  <!--================Item Area =================-->
  
  <section class="section-margin calc-60px">
    <div class="container">
      <div class="section-intro pb-60px">
        <p>Products of category ${NAME_CATEGORY.getName()} in market</p>
        <h2>All <span class="section-intro__style">Product</span></h2>
      </div>
      <div class="row">
        <c:forEach items = "${NAME_LIST_CATEGORY_PRODUCT }" var = "product" >
          <div class="col-md-6 col-lg-4 col-xl-3">
          <div class="card text-center card-product">
            <div class="card-product__img">
              <img class="card-img" src="img/product/${product.getImage() }" alt="">
              <ul class="card-product__imgOverlay">
                <li><button onclick="location.href='${pageContext.request.contextPath}/product?productID=${product.getProductID()}'" type="button"><i class="ti-search"></i></button></li>
                <li><button onclick="location.href='${pageContext.request.contextPath}/addToCart?productID=${product.getProductID()}'" type="button"><i class="ti-shopping-cart"></i></button></li>
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

  <!--================End Item Area =================-->


<jsp:include page="_footer.jsp"></jsp:include>