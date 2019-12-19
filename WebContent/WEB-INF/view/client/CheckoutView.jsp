<jsp:include page="_header.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- ================ start banner area ================= --> 
  <section class="blog-banner-area" id="category">
    <div class="container h-100">
      <div class="blog-banner">
        <div class="text-center">
          <h1>Product Checkout</h1>
          <nav aria-label="breadcrumb" class="banner-breadcrumb">
            <ol class="breadcrumb">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active" aria-current="page">Checkout</li>
            </ol>
          </nav>
        </div>
      </div>
    </div>
  </section>
  <!-- ================ end banner area ================= -->
  
  
  <!--================Checkout Area =================-->
  <section class="checkout_area section-margin--small">
    <div class="container">
        <div class="billing_details">
            <div class="row">
                <div class="col-lg-8">
                    <h3>Billing Details</h3>
                    <form id="form" class="row contact_form" action="checkout" method="post" novalidate="novalidate">
                        <div class="col-md-12 form-group">
                            <input type="text" class="form-control" id="email" name="name" placeholder="name" required>
                        </div>
                        <div class="col-md-12 form-group">
                            <input type="text" class="form-control" id="email" name="phone" placeholder="phone" required>
                        </div>
                        <div class="col-md-12 form-group">
                            <input type="text" class="form-control" id="email" name="address" placeholder="address" required>
                        </div>
                    </form>
                </div>
                <div class="col-lg-4">
                    <div class="order_box">
                        <h2>Your Order</h2>
                        <ul class="list">
                            <li><a href="#"><h4>Product <span>Total</span></h4></a></li>
                            <c:forEach items = "${NAME_CART.getListProduct()}" var = "product">
                                <li><a href="#">${product.getName()}<span class="middle">x ${product.getQuantity() }</span> <span class="last">${product.getPrice()}</span></a></li>
                            </c:forEach>
                        </ul>
                        <ul class="list list_2">
                            
                            <li><a href="#">Total <span>$${NAME_CART.getTotalMoney()}</span></a></li>
                        </ul>
                        <div class="text-center">
                          <button class="button button-paypal" form="form" type="submit">Proceed to Paypal</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
  </section>
  <!--================End Checkout Area =================-->

<jsp:include page="_footer.jsp"></jsp:include>