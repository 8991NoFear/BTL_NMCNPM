<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:include page = "_header.jsp"></jsp:include>

  <!--================View Box Area =================-->
	<section class="login_box_area section-margin">
		<div class="container">
			<div class="row">
				<div class="col-lg-6">
					<div class="login_box_img">
						<div class="hover">
						<img src = "img/product/${NAME_PRODUCT.getImage() }" />
						</div>
					</div>
				</div>
				<div class="col-lg-6">
					<div class="login_form_inner">
						<h3>${NAME_PRODUCT.getName() }</h3>
						<br />
						<form class="row login_form" action="login" method="POST" id="contactForm" >
							<p>${NAME_PRODUCT.getDescription() }</p>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--================End View Box Area =================-->
	
<jsp:include page = "_footer.jsp"></jsp:include>
