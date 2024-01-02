<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<jsp:include page="./Shared/Header.jsp"></jsp:include>
	<!-- Navbar End -->
	<div class="wrapper-card">
		<div class="card-container">
			<span class="pro">PRO</span> <img
				src="img/profile/${userInfo.avatar }" alt="user" />
			<h3 class="card-username">${userInfo.username }</h3>
			<h6 class="card-fullname">${userInfo.fullName }</h6>
			<p class="card-subtitle">
				${userInfo.createAt} <br />
			</p>
			<div class="buttons">
				<button class="btn-upload-new-avatar" onclick="handleUploadAvatar()">Update
					your avatar</button>
				<form action="ProfileController" method="post" id="formUploadImg" enctype="multipart/form-data"
					class="form-upload-img">
					<div class="input-group mb-3">
						<div class="custom-file m-3">
							<input type="file" class="custom-file-input" name="avatar"
								id="inputGroupFile02"> <label class="custom-file-label"
								for="inputGroupFile02" aria-describedby="inputGroupFileAddon02">Choose
								file</label>
						</div>
						<div class="input-group-append"></div>
					</div>

					<button class="btn-submit" type="submit">Submit</button>
					<button class="btn-cancer-profile primary ghost" type="button">Cancer</button>
				</form>
			</div>
			<!-- <div class="skills">
				<h6>Skills</h6>
				<ul>
					<li>UI / UX</li>
					<li>Front End Development</li>
					<li>HTML</li>
					<li>CSS</li>
					<li>JavaScript</li>
					<li>React</li>
					<li>Node</li>
				</ul>
			</div> -->
		</div>
	</div>
	<!-- Footer Start -->
	<jsp:include page="Shared/Footer.jsp"></jsp:include>
	<!-- Footer End -->


	<!-- Back to Top -->
	<a href="#" class="btn btn-primary p-3 back-to-top"><i
		class="fa fa-angle-double-up"></i></a>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script>
		const formUploadImg = $('#formUploadImg');
		console.log('update: ', formUploadImg);
		const btnUploadImg = $('.btn-upload-new-avatar');
		const handleUploadAvatar = () => {
			console.log('UPDLOADS');
			formUploadImg.show();
			btnUploadImg.hide();	
		}
		$('.btn-cancer-profile').click(()=>{
			formUploadImg.hide();
			btnUploadImg.show();
		})	
	</script>
	<!-- JavaScript Libraries -->

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