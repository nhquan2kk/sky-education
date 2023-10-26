<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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




<script>
	console.log("id : ", "<%=request.getAttribute("grammarId")%>");
	function handleComment(){
		var xhttp;
		var memberId = "<%=session.getAttribute("sessionMemberId")%>";
		var username = "<%=session.getAttribute("sessionUser")%>";
		var grammarId = "<%=request.getAttribute("grammarId")%>";
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
	</div> -->
	<!-- Header End -->


	<!-- Detail Start -->
	<div class="container py-5">
		<div class="row pt-5">
			<div class="col-lg-12">
				<c:if test="${grammarData != null}">
					<div class="d-flex flex-column text-left mb-3">
						<!-- <p class="section-title pr-5"><span class="pr-2">Blog Detail Page</span></p> -->
						<h1 class="mb-3">${grammarData.name }</h1>
						<div class="d-flex">
							<p class="mr-3">
								<i class="fa fa-user text-primary"></i> Admin
							</p>
							<p class="mr-3">
								<i class="fa fa-folder text-primary"></i>Grammar
							</p>
							<p class="mr-3">
								<i class="fa fa-comments text-primary"></i> ${commentsMain }
							</p>
						</div>
					</div>
					<div class="mb-5">
						<img class="img-fluid rounded w-100 mb-4"
							src="img/grammar/${grammarData.image }" alt="Image" />
						<p>${grammarData.content }</p>
						<%--  <p>
										<c:set var="kq"
											value="${fn:replace(grammarData.content,characterDatabase, characterHTML)}" />
										<c:out value="${kq }" escapeXml="false" />
									</p> --%>
					</div>
				</c:if>
				<!-- Related Post -->
				<div class="mb-5 mx-n3">
					<h2 class="mb-4 ml-3">Related Post</h2>
					<div class="owl-carousel post-carousel position-relative">
						<c:forEach items="${ relatedGrammarList}" var="item">
							<div
								class="d-flex align-items-center bg-light shadow-sm rounded overflow-hidden mx-3">
								<img class="img-fluid" src="img/grammar/${item.image }"
									style="width: 80px; height: 80px;">
								<div class="pl-3">
									<h5 class="">${item.name }</h5>
									<div class="d-flex">
										<small class="mr-3"><i class="fa fa-user text-primary"></i>
											Admin</small> <small class="mr-3">
											Level : ${item.nameLevel }</small> <small
											class="mr-3"><i class="fa fa-comments text-primary"></i>
											${commentsMain }</small>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>

				<!-- Comment List -->
				<div class="mb-5">
					<h2 class="mb-4">Comments</h2>
					<div id="list-comments">
						<c:forEach items="${listCommentGrammar }" var="item">
							<div class="media mb-4">
								<img src="Assets/Main/img/person.jpg" alt="Image"
									class="img-fluid rounded-circle mr-3 mt-1" style="width: 45px;">
								<div class="media-body">
									<h6>
										${item.username } <small><i>01 Jan 2045 at 12:00pm</i></small>
									</h6>
									<p>${item.content }</p>
									<button class="btn btn-sm btn-light">Reply</button>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>

				<!-- Comment Form -->
				<%
				if (session.getAttribute("sessionUser") == null) {
				%>
				<h4>Please login to comment.</h4>
				<%
				} else {
				%>

			<!-- <form action="" name="commentForm">
					<textarea name="content" class="span8" rows="10"
						placeholder="Message"></textarea>
					<input type="button" value="submit" onclick="handleComment()" />

				</form>  -->

				<div class="bg-light p-5">
					<h2 class="mb-4">Leave a comment</h2>
					<form action="" name="commentForm">

						<div class="form-group">
							<label for="message">Comment *</label>
							<textarea name="content" id="message" cols="30" rows="5"
								class="form-control"></textarea>
						</div>
						<div class="form-group mb-0">
							<!-- 	<button type="button" onclick="handleComment()" >on submit</button>
							submit type of input tag similar to onSubmit() => don't need to use onsubmit()
						
						 -->
						 <input type="button" value="Leave Comment"
								onclick="handleComment();" class="btn btn-primary px-3" />
								
						</div>
					</form>
				</div>
				<%
				}
				%>
			</div>

			<!-- <div class="col-lg-4 mt-5 mt-lg-0">
				Author Bio
				<div
					class="d-flex flex-column text-center bg-primary rounded mb-5 py-5 px-4">
					<img src="img/user.jpg"
						class="img-fluid rounded-circle mx-auto mb-3"
						style="width: 100px;">
					<h3 class="text-secondary mb-3">John Doe</h3>
					<p class="text-white m-0">Conset elitr erat vero dolor ipsum et
						diam, eos dolor lorem ipsum, ipsum ipsum sit no ut est. Guber ea
						ipsum erat kasd amet est elitr ea sit.</p>
				</div>

				Search Form
				<div class="mb-5">
					<form action="">
						<div class="input-group">
							<input type="text" class="form-control form-control-lg"
								placeholder="Keyword">
							<div class="input-group-append">
								<span class="input-group-text bg-transparent text-primary"><i
									class="fa fa-search"></i></span>
							</div>
						</div>
					</form>
				</div>

				Category List
				<div class="mb-5">
					<h2 class="mb-4">Categories</h2>
					<ul class="list-group list-group-flush">
						<li
							class="list-group-item d-flex justify-content-between align-items-center px-0">
							<a href="">Web Design</a> <span
							class="badge badge-primary badge-pill">150</span>
						</li>
						<li
							class="list-group-item d-flex justify-content-between align-items-center px-0">
							<a href="">Web Development</a> <span
							class="badge badge-primary badge-pill">131</span>
						</li>
						<li
							class="list-group-item d-flex justify-content-between align-items-center px-0">
							<a href="">Online Marketing</a> <span
							class="badge badge-primary badge-pill">78</span>
						</li>
						<li
							class="list-group-item d-flex justify-content-between align-items-center px-0">
							<a href="">Keyword Research</a> <span
							class="badge badge-primary badge-pill">56</span>
						</li>
						<li
							class="list-group-item d-flex justify-content-between align-items-center px-0">
							<a href="">Email Marketing</a> <span
							class="badge badge-primary badge-pill">98</span>
						</li>
					</ul>
				</div>

				Single Image
				<div class="mb-5">
					<img src="img/blog-1.jpg" alt="" class="img-fluid rounded">
				</div>

				Recent Post
				<div class="mb-5">
					<h2 class="mb-4">Recent Post</h2>
					<div
						class="d-flex align-items-center bg-light shadow-sm rounded overflow-hidden mb-3">
						<img class="img-fluid" src="img/post-1.jpg"
							style="width: 80px; height: 80px;">
						<div class="pl-3">
							<h5 class="">Diam amet eos at no eos</h5>
							<div class="d-flex">
								<small class="mr-3"><i class="fa fa-user text-primary"></i>
									Admin</small> <small class="mr-3"><i
									class="fa fa-folder text-primary"></i> Web Design</small> <small
									class="mr-3"><i class="fa fa-comments text-primary"></i>
									15</small>
							</div>
						</div>
					</div>
					<div
						class="d-flex align-items-center bg-light shadow-sm rounded overflow-hidden mb-3">
						<img class="img-fluid" src="img/post-2.jpg"
							style="width: 80px; height: 80px;">
						<div class="pl-3">
							<h5 class="">Diam amet eos at no eos</h5>
							<div class="d-flex">
								<small class="mr-3"><i class="fa fa-user text-primary"></i>
									Admin</small> <small class="mr-3"><i
									class="fa fa-folder text-primary"></i> Web Design</small> <small
									class="mr-3"><i class="fa fa-comments text-primary"></i>
									15</small>
							</div>
						</div>
					</div>
					<div
						class="d-flex align-items-center bg-light shadow-sm rounded overflow-hidden mb-3">
						<img class="img-fluid" src="img/post-3.jpg"
							style="width: 80px; height: 80px;">
						<div class="pl-3">
							<h5 class="">Diam amet eos at no eos</h5>
							<div class="d-flex">
								<small class="mr-3"><i class="fa fa-user text-primary"></i>
									Admin</small> <small class="mr-3"><i
									class="fa fa-folder text-primary"></i> Web Design</small> <small
									class="mr-3"><i class="fa fa-comments text-primary"></i>
									15</small>
							</div>
						</div>
					</div>
				</div>

				Single Image
				<div class="mb-5">
					<img src="img/blog-2.jpg" alt="" class="img-fluid rounded">
				</div>

				Tag Cloud
				<div class="mb-5">
					<h2 class="mb-4">Tag Cloud</h2>
					<div class="d-flex flex-wrap m-n1">
						<a href="" class="btn btn-outline-primary m-1">Design</a> <a
							href="" class="btn btn-outline-primary m-1">Development</a> <a
							href="" class="btn btn-outline-primary m-1">Marketing</a> <a
							href="" class="btn btn-outline-primary m-1">SEO</a> <a href=""
							class="btn btn-outline-primary m-1">Writing</a> <a href=""
							class="btn btn-outline-primary m-1">Consulting</a>
					</div>
				</div>

				Single Image
				<div class="mb-5">
					<img src="img/blog-3.jpg" alt="" class="img-fluid rounded">
				</div>

				
			</div -->
		</div>
	</div>
	<!-- Detail End -->


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