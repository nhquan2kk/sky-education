<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<title>Sky Education</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="Free HTML Templates" name="keywords">
<meta content="Free HTML Templates" name="description">

<!-- Favicon -->
<link href="img/favicon.ico" rel="icon">

<!-- Google Web Fonts -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Handlee&family=Nunito&display=swap"
	rel="stylesheet">

<!-- Font Awesome -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
	rel="stylesheet">

<!-- Flaticon Font -->
<link href="Assets/Main/lib/flaticon/font/flaticon.css" rel="stylesheet">

<!-- Libraries Stylesheet -->
<link href="Assets/Main/lib/owlcarousel/assets/owl.carousel.min.css"
	rel="stylesheet">
<link href="Assets/Main/lib/lightbox/css/lightbox.min.css"
	rel="stylesheet">

<!-- Customized Bootstrap Stylesheet -->
<link href="Assets/Main/css/style.css" rel="stylesheet">
</head>

<body>
	<!-- Navbar Start -->
	<jsp:include page="../Shared/Header.jsp"></jsp:include>
	<!-- Navbar End -->


	<!-- Header Start -->
	<!-- <div class="container-fluid bg-primary mb-5">
		<div
			class="d-flex flex-column align-items-center justify-content-center"
			style="min-height: 400px">
			<h3 class="display-3 font-weight-bold text-white">Grammar</h3>
			<div class="d-inline-flex text-white">
				<p class="m-0">
					<a class="text-white" href="HomeController">Home</a>
				</p>
				<p class="m-0 px-2">/</p>
				<p class="m-0">Grammar</p>
			</div>
		</div>
	</div> -->
	<!-- Header End -->


	<!-- Blog Start -->
	<div class="container-fluid pt-5">
		<div class="container">
			<div class="text-center pb-2">
				<p class="section-title px-5">
					<span class="px-2">Grammar</span>
				</p>
				<!-- <h1 class="mb-4">Latest Articles</h1> -->
			</div>
			<c:choose>
				<c:when test="${msg == 'NOT FOUND' }">
					<h1>No Found Any Product.</h1>
				</c:when>
				<c:otherwise>
					<div class="row pb-3">
						<c:forEach items="${listGrammar }" var="item">
							<div class="col-lg-4 mb-4">
								<div class="card border-0 shadow-sm mb-2">
									<img class="card-img-top mb-2" src="img/grammar/${item.image }"
										alt="" style="height: 200px">
									<div class="card-body bg-light text-center p-4">
										<h4 class="">${item.name }</h4>
										<div class="d-flex justify-content-center mb-3">
											<small class="mr-3"><i
												class="fa fa-user text-primary"></i> Admin</small> <small
												class="mr-3">Level : ${item.nameLevel}</small> <small
												class="mr-3"><i class="fa fa-comments text-primary"></i>
												${item.countComment }</small>
										</div>
										<!-- 	<p>short content</p> -->
										<a href="DetailGrammarController?grammarId=${item.grammarId }"
											class="btn btn-primary px-4 mx-auto my-2">Read More</a>
									</div>
								</div>
							</div>
						</c:forEach>
						<div class="col-md-12 mb-4">
							<nav aria-label="Page navigation">
								<ul class="pagination justify-content-center mb-0">
									<c:if test="${numberPage != 1 }">
										<li class="page-item "><a class="page-link"
											href="GrammarController?pageId=${numberPage - 1 }"
											aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Previous</span>
										</a></li>
									</c:if>
									<c:forEach begin="1" end="${maxPageId}" var="i">
										<c:choose>
											<c:when test="${numberPage eq i }">
												<li class="page-item active"><a class="page-link">${i}</a></li>
											</c:when>
											<c:otherwise>
												<c:if
													test="${nummberPage != i and (numberPage - 2 <= i and i < numberPage) or (numberPage + 2 >= i and i > numberPage)   }">
													<li class="page-item"><a class="page-link"
														href="GrammarController?pageId=${i }">${i }</a></li>
												</c:if>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<c:if test="${numberPage lt maxPageId }">
										<li class="page-item"><a class="page-link"
											href="GrammarController?pageId=${numberPage + 1 }"
											aria-label="Next"> <span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Next</span>
										</a></li>
									</c:if>
								</ul>
							</nav>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<!-- Blog End -->


	<!-- Footer Start -->
	<jsp:include page="../Shared/Footer.jsp"></jsp:include>
	<!-- Footer End -->


	<!-- Back to Top -->
	<a href="#" class="btn btn-primary p-3 back-to-top"><i
		class="fa fa-angle-double-up"></i></a>


	<!-- JavaScript Libraries -->
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
	<script src="Assets/Main/lib/easing/easing.min.js"></script>
	<script src="Assets/Main/lib/owlcarousel/owl.carousel.min.js"></script>
	<script src="Assets/Main/lib/isotope/isotope.pkgd.min.js"></script>
	<script src="Assets/Main/lib/lightbox/js/lightbox.min.js"></script>
	<!-- Contact Assets/Main/Javascript File -->
	<script src="Assets/Main/mail/jqBootstrapValidation.min.js"></script>
	<script src="Assets/Main/mail/contact.js"></script>
	<!-- TemplateAssets/Main/ Javascript -->
	<script src="Assets/Main/js/main.js"></script>
</body>

</html>
