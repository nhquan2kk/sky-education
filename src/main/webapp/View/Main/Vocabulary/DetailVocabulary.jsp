<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<title>KidKinder - Kindergarten Website Template</title>
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




<script type="text/javascript">
	console.log("id : ", "<%=request.getAttribute("grammarId")%>");
	function handleComment(){
		console.log('click')
		var xhttp;
		var memberId = "<%=session.getAttribute("sessionId")%>";
		var username = "<%=session.getAttribute("sessionUser")%>";
		var grammarId = "<%=request.getAttribute("grammarId")%>
	";
		console.log('memberId : ', memberId, 'username : ', username,
				'grammarId : ', grammarId);
		event.preventDefault();

		var content = document.commentForm.content.value;
		var url = "CommentController?content=" + content + "&userId="
				+ memberId + "&grammarId=" + grammarId;

		if (window.XMLHttpRequest) {
			xhttp = new XMLHttpRequest();
		} else {
			xhttp = newActiveXObject("Microsoft.XMLHTTP");
		}
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4) {
				var data = xhttp.responseText;
				console.log('data : ', data);
				document.getElementById("list-comments").innerHTML = data;
			}
		}
		xhttp.open("POST", url, true);
		xhttp.send();
	}
</script>

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
			<h3 class="display-3 font-weight-bold text-white">Gramar Detail</h3>
			<div class="d-inline-flex text-white">
				<p class="m-0">
					<a class="text-white" href="HomeController">Home</a>
				</p>
				<p class="m-0 px-2">/</p>
				<p class="m-0">Grammar Detail</p>
			</div>
		</div>
	</div -->
	<!-- Header End -->


	<!-- Detail Start -->
	<div class="container py-5">
		<div class="row pt-5">
			<div class="col-lg-12">
				<c:if test="${vocabularyData != null}">
					<div class="d-flex flex-column text-left mb-3">
						<!-- <p class="section-title pr-5"><span class="pr-2">Blog Detail Page</span></p> -->
						<h1 class="mb-3">${vocabularyData.name }</h1>
						<div class="d-flex">
							<p class="mr-3">
								<i class="fa fa-user text-primary"></i> Admin
							</p>
							<!-- <p class="mr-3">
								<i class="fa fa-folder text-primary"></i>Grammar
							</p>
							<p class="mr-3">
								<i class="fa fa-comments text-primary"></i> 15
							</p> -->
						</div>
					</div>
					<div class="mb-5">
						<img class="img-fluid rounded w-100 mb-4"
							src="img/vocabulary/${vocabularyData.image }" alt="Image" />

					</div>
					<c:forEach items="${vocabularyWordsList }" var="item">
						<div class="my-4">
							<h5>${item.name }</h5>
							<audio controls="controls">
								<source src="resource/${item.audioMp3 }">
							</audio>
							<c:if test="${item.image == '' }">
								<p>${item.mean }</p>
							</c:if>
							<c:if test="${item.image != '' }">
								<div class="row">
									<div class="col-3">
										<div class="w-100">
											<img class="w-100" src="resource/${item.image }" />
										</div>
									</div>
									<div class="col-9">
										<p>${item.mean }</p>
									</div>
								</div>

							</c:if>
						</div>

					</c:forEach>
				</c:if>
				<!-- Related Post -->
			

				<!-- Comment List -->
				<%-- <div class="mb-5">
					<h2 class="mb-4">Comments</h2>
					<div id="list-comments">
						<c:forEach items="${listCommentGrammar }" var="item">
							<div class="media mb-4">
								<img src="img/" alt="Image"
									class="img-fluid rounded-circle mr-3 mt-1" style="width: 45px;">
								<div class="media-body">
									<h6>
										${item.username } <small><i>01 Jan 2045 at 12:00pm</i></small>
									</h6>
									<p>
										${item.content }	
									</p>
									<button class="btn btn-sm btn-light">Reply</button>
								</div>
							</div>
						</c:forEach>
					</div>
				</div> --%>

				<!-- Comment Form -->
				

			
		</div>
	</div>
	<!-- Detail End -->

</div>
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

	<!-- Contact Javascript File -->
	<script src="Assets/Main/mail/jqBootstrapValidation.min.js"></script>
	<script src="Assets/Main/mail/contact.js"></script>

	<!-- Template Javascript -->
	<script src="Assets/Main/js/main.js"></script>
</body>

</html>