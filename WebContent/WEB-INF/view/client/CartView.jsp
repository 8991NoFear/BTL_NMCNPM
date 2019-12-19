<jsp:include page="_header.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!-- ================ start banner area ================= -->	
  <section class="blog-banner-area" id="category">
    <div class="container h-100">
      <div class="blog-banner">
        <div class="text-center">
          <h1>Shopping Cart</h1>
          <nav aria-label="breadcrumb" class="banner-breadcrumb">
            <ol class="breadcrumb">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active" aria-current="page">Shopping Cart</li>
            </ol>
          </nav>
        </div>
      </div>
    </div>
  </section>
  <!-- ================ end banner area ================= -->
  
  

  <!--================Cart Area =================-->
  <section class="cart_area">
      <div class="container">
          <div class="cart_inner">
              <div class="table-responsive">
                  <table class="table">
                      <thead>
                          <tr>
                              <th scope="col" align="center">Product</th>
                              <th scope="col" align="center">Price</th>
                              <th scope="col" align="center">Delete</th>
                          </tr>
                      </thead>
                      <tbody>
                      	   <%! int index = 0; %>
                          <c:forEach items = "${NAME_CART.getListProduct() }" var = "product">
                          		<tr>
		                              <td>
		                                  <div class="media">
		                                      <div class="d-flex">
		                                          <img src="img/product/${product.getImage() }" alt="">
		                                      </div>
		                                      <div class="media-body">
		                                          <p>${product.getName() }</p>
		                                      </div>
		                                  </div>
		                              </td>
		                              <td>
		                                  <h5>${product.getPrice() }</h5>
		                              </td>
		                              <td>
			                              <div class="checkout_btn_inner d-flex align-items-center">
				                              	<a class="gray_btn" href="${pageContext.request.contextPath}/deleteProduct?index=<%=index %>">Delete</a>
				                              	<%index++; %>
			                              </div>
		                           	  </td>
                          		</tr>
                          </c:forEach>
                          <%index = 0; %>
                          <tr>
                          	  <td>
                          	  </td>
                              <td>
                                  <h5>Total: </h5>
                              </td>
                              <td>
                                  <h5>${NAME_CART.getTotalMoney() }</h5>
                              </td>
	                      </tr>
                          <tr class="out_button_area">
                              <td>
                              </td>
                              <td>
                              </td>
                              <td>
                                  <div class="checkout_btn_inner d-flex align-items-center">
                                      <a class="gray_btn" href="${pageContext.request.contextPath}/home"">Continue Shopping</a>
                                      <a class="primary-btn ml-2" href="${pageContext.request.contextPath}/checkout">Proceed to checkout</a>
                                  </div>
                              </td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
      </div>
  </section>
  <!--================End Cart Area =================-->


<jsp:include page="_footer.jsp"></jsp:include>