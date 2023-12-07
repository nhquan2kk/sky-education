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
<!-- Add from DASHBOARD -->
<!-- <link rel="stylesheet" href="Assets/Admin/vendors/feather/feather.css">
<link rel="stylesheet"
	href="Assets/Admin/vendors/ti-icons/css/themify-icons.css">
<link rel="stylesheet"
	href="Assets/Admin/vendors/css/vendor.bundle.base.css">
endinject
Plugin css for this page
<link rel="stylesheet"
	href="Assets/Admin/vendors/mdi/css/materialdesignicons.min.css">
End plugin css for this page
inject:css
<link rel="stylesheet"
	href="Assets/Admin/css/vertical-layout-light/style.css">
endinject
<link rel="shortcut icon" href="Assets/Admin/images/favicon.png" />
<link rel="stylesheet" href="Assets/Admin/css/custom/style.css"> -->


<script type="text/javascript">
	function autoSubmit(){
		console.log('Time out', document.getElementById('form'));
		setTimeout(()=>{
			document.getElementById('form').submit();
		}, 360000);
	}
	
</script>

</head>

<body onload="autoSubmit()">
	<!-- Navbar Start -->
	<jsp:include page="../Shared/Header.jsp"></jsp:include>
	<!-- Navbar End -->

	<%
	if (session.getAttribute("sessionUser") == null) {
	%>
	<div class="container mt-5">
		<div class="row ">
			<h4>Please login to do examination.</h4>
		</div>
	</div>
	<%
	} else {
	%>
	<%-- <c:if test="${session.getAttribute('sessionUser') == null}">
		<h1>TESTING IN C IF BLOCK</h1>
	</c:if>
	<c:choose>
		<c:when test="${session.getAttribute('sessionUser') == null}">
			<h1>TESTING IN CHATGPT</h1>
		</c:when>
		<c:otherwise>
			<!-- Xử lý khi sessionUser không bằng null -->
		</c:otherwise>
	</c:choose>
	<c:if test="${sessionScope:userId != null}" > 
   session value present......
  <h1>CONINTINET TESTIN</h1>
</c:if--%>
	<div class="container-fluid bg-primary mb-5">
		<div
			class="d-flex flex-column align-items-center justify-content-center"
			style="min-height: 400px">
			<h3 class="display-3 font-weight-bold text-white" id="time">60 :
				00</h3>
			<div class="d-inline-flex text-white">
				<p class="m-0">
					<a class="text-white" href="HomeController">Home</a>
				</p>
				<p class="m-0 px-2">/</p>
				<p class="m-0">Examination Detail</p>
			</div>
		</div>
	</div>


	<!-- Detail Start -->
	<div class="container mt-5">
		<div class="row ">
			<div class="col-lg-12">


				<form id="form"
					action="DetailExaminationController?examinationId=${examinationId }"
					method="post">
					<c:forEach items="${ examinationQuizs}" var="item">
						<h3>${item.num }</h3>
						<c:if test="${item.image != ''}">
							<img alt="" src="resource/${item.image }">
						</c:if>
						<br />
						<br />
						<c:if test="${item.audioMP3 != ''}">
							<audio ${item.audioMP3 == '' ? 'style="display : none"' : ''}
								controls="controls">
								<source src="resource/${item.audioMP3 }">
							</audio>
						</c:if>

						<c:if test="${item.paragraph != ''}">
							<p>${item.paragraph }</p>
						</c:if>
						<c:if test="${item.question != ''}">
							<p>${item.question }</p>
						</c:if>

						<input type="radio" name="ans[${item.num }]" value="A">
						<span> ${item.option1 }</span>
						<br>
						<input type="radio" name="ans[${item.num }]" value="B" />
						<span> ${item.option2 }</span>
						<br>
						<input type="radio" name="ans[${item.num }]" value="C" />
						<span> ${item.option3 }</span>
						<br>
						<input type="radio" name="ans[${item.num }]" value="D" />
						<span> ${item.option4 }</span>
						<br>
						<br>
					</c:forEach>
					<button type="button" 	class="btn btn-primary px-4 mx-auto my-2" data-toggle="modal"
						data-target="#exampleModal-${examinationId}">
						Submit
					</button>
					<div class="modal fade" id="exampleModal-${examinationId}"
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
									examination?</div>
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
	<%
	}
	%>
	<!-- Detail End -->


	<!-- Footer Start -->
	<jsp:include page="../Shared/Footer.jsp"></jsp:include>
	<!-- Footer End -->


	<!-- Back to Top -->
	<a href="#" class="btn btn-primary p-3 back-to-top"><i
		class="fa fa-angle-double-up"></i></a>


	<!-- JavaScript Libraries -->
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script>
	function startTimer(duration, display) {
	    var timer = duration,minutes, seconds;
	    setInterval(function () {
	        minutes = parseInt(timer / 60, 10)
	        seconds = parseInt(timer % 60, 10);

	        minutes = minutes < 10 ? "0" + minutes : minutes;
	        seconds = seconds < 10 ? "0" + seconds : seconds;
			
	        display.innerText = minutes + ":" + seconds;

	        if (--timer < 0) {
	            timer = duration;
	        }
	    }, 1000);
	}

	 window.onload = function () { 
	    var fiveMinutes = 60 * 60,
	        display = document.querySelector('#time');
	    startTimer(fiveMinutes, display);
	    console.log('display : ', display)
	 }; 
	</script>
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
	
	<!--  Add from Dashboard-->
	<script src="Assets/Admin/vendors/js/vendor.bundle.base.js"></script>
	<!-- endinject -->
	<!-- Plugin js for this page -->
	<!-- End plugin js for this page -->
	<!-- inject:js -->
	<script src="Assets/Admin/js/off-canvas.js"></script>
	<script src="Assets/Admin/js/hoverable-collapse.js"></script>
	<script src="Assets/Admin/js/template.js"></script>
	<script src="Assets/Admin/js/settings.js"></script>
	<script src="Assets/Admin/js/todolist.js"></script>
</body>

</html>