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




<script type="text/javascript">
	console.log("id : ", "<%=request.getAttribute("grammarId")%>");
	function handleComment(){
		console.log('click')
		var xhttp;
		var memberId = "<%=session.getAttribute("sessionId")%>";
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
	<div class="container mt-4">
		<div class="row ">
			<div class="col-lg-12">
				<h2>${listeningData.name }</h2>
				<img alt="" class="w-50" src="img/listening/${listeningData.image }">
				<br>
				
					<br>
						<br>

			<audio  controls="controls">
				<source src="audio/listening/${listeningData.audioMp3 }">
							</audio>
				<%-- <p>
					<c:set var="kq"
						value="${fn:replace(listeningData.content,characterDatabase, characterHTML)}" />
					<c:out value="${kq }" escapeXml="false" />
				</p> --%>
				<form action="DetailListeningController?listenId=${listenId }"
					method="post">
					<c:forEach items="${ listeningQuizData}" var="item">
						<h3>${item.ordinalNum }</h3>

						<p>${item.question }</p>
						<input type="radio" name="ans[${item.ordinalNum }]" value="A">
						<span> ${item.option1 }</span>
						<br>
						<input type="radio" name="ans[${item.ordinalNum }]" value="B" />
						<span>${item.option2 }</span>
						<br>
						<input type="radio" name="ans[${item.ordinalNum }]" value="C" />
						<span> ${item.option3 }</span>
						<br>
						<input type="radio" name="ans[${item.ordinalNum }]" value="D" />
						<span>  ${item.option4 }</span>
						<br>
						<br>
					</c:forEach>
				<button type="button" class="btn btn-primary px-4 mx-auto my-2"
						data-toggle="modal" data-target="#exampleModal-${listenId}">
						Submit</button>
					<div class="modal fade" id="exampleModal-${listenId}"
						tabindex="-1" aria-labelledby="exampleModalLabel"
						aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel">Notification</h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">Do you want to finish your
									listening?</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-dismiss="modal">Cancer</button>
									<button type="submit" class="btn btn-primary">Ok</button>
								</div>
							</div>
						</div>
					</div>
				</form>

			</div>


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