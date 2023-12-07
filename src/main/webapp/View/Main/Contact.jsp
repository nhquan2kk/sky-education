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
<script type="text/javascript"
	src='https://cdn.tiny.cloud/1/qagffr3pkuv17a8on1afax661irst1hbr4e6tbv888sz91jc/tinymce/6/tinymce.min.js'
	referrerpolicy="origin">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</script>
<style>
	.bottom-0 {
		top: 0;
		right: 0;
	}
</style>
</head>

<body>
<div class="position-fixed bottom-0 start-0 p-3" style="z-index: 11">
				<div id="myToastEl" class="toast hide bg-primary" role="alert"
					aria-live="assertive" aria-atomic="true">
					<div class="toast-body text-white">${msgSuccess}</div>
				</div>
				</div>
	<!-- Navbar Start -->
	<jsp:include page="./Shared/Header.jsp"></jsp:include>

	<div class="container-fluid pt-5">
		<div class="container">
			
				<div class="text-center pb-2">
					<p class="section-title px-5">
						<span class="px-2">Get In Touch</span>
					</p>
					<h1 class="mb-4">Contact Us For Any Query</h1>
				</div>
				<div class="row">
					<div class="col-lg-7 mb-5">
						<div class="contact-form">
							<div id="msgSuccess" style="display: none;">${msgSuccess}</div>
							<form action="ContactController" novalidate="novalidate"
								method="post">
								<!--  <div class="control-group">
                                <input type="text" class="form-control" id="name" placeholder="Your Name" required="required" data-validation-required-message="Please enter your name" />
                                <p class="help-block text-danger"></p>
                            </div> -->
								<div class="control-group">
									<input type="email" class="form-control" id="email"
										placeholder="Your Email" required="required"
										data-validation-required-message="Please enter your email" />
									<p class="help-block text-danger"></p>
								</div>
								<div class="control-group">
									<input type="text" class="form-control" id="subject"
										name="subject" placeholder="Subject" required="required"
										data-validation-required-message="Please enter a subject" />
									<p class="help-block text-danger"></p>
								</div>
								<div class="control-group">
									<textarea id="summernote" name="content" required></textarea>
									<!-- 	<textarea class="form-control" rows="6" id="message" name="body"
									placeholder="Message" required="required"
									data-validation-required-message="Please enter your message"></textarea> -->
									<p class="help-block text-danger"></p>
								</div>
								<div>
									<button class="btn btn-primary py-2 px-4" type="submit"
										id="sendMessageButton">Send Message</button>
								</div>
							</form>
						</div>
					</div>
					<div class="col-lg-5 mb-5">
						<div class="d-flex">
							<i
								class="fa fa-map-marker-alt d-inline-flex align-items-center justify-content-center bg-primary text-secondary rounded-circle"
								style="width: 45px; height: 45px;"></i>
							<div class="pl-3">
								<h5>Address</h5>
								<p>182 Le Duan, Vinh</p>
							</div>
						</div>
						<div class="d-flex">
							<i
								class="fa fa-envelope d-inline-flex align-items-center justify-content-center bg-primary text-secondary rounded-circle"
								style="width: 45px; height: 45px;"></i>
							<div class="pl-3">
								<h5>Email</h5>
								<p>13team@gmail.com</p>
							</div>
						</div>
						<div class="d-flex">
							<i
								class="fa fa-phone-alt d-inline-flex align-items-center justify-content-center bg-primary text-secondary rounded-circle"
								style="width: 45px; height: 45px;"></i>
							<div class="pl-3">
								<h5>Phone</h5>
								<p>+84 123 456 789</p>
							</div>
						</div>
						<div class="d-flex">
							<i
								class="far fa-clock d-inline-flex align-items-center justify-content-center bg-primary text-secondary rounded-circle"
								style="width: 45px; height: 45px;"></i>
							<div class="pl-3">
								<h5>Opening Hours</h5>
								<strong>Sunday - Friday:</strong>
								<p class="m-0">08:00 AM - 05:00 PM</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


		<!-- Footer Start -->
		<jsp:include page="Shared/Footer.jsp"></jsp:include>
		<!-- Footer End -->


		<!-- Back to Top -->
		<a href="#" class="btn btn-primary p-3 back-to-top"><i
			class="fa fa-angle-double-up"></i></a>

		<script type="text/javascript">
			tinymce
					.init({
						selector : '#summernote',
						plugins : "image code",
						image_title : true,
						automatic_uploads : true,
						file_picker_types : 'image',
						file_picker_callback : function(cb, value, meta) {
							var input = document.createElement('input');
							input.setAttribute('type', 'file');
							input.setAttribute('accept', 'image/*');
							input.onchange = function() {
								var file = this.files[0];
								var reader = new FileReader();

								reader.onload = function() {
									var id = 'blobid' + (new Date()).getTime();
									var blobCache = tinymce.activeEditor.editorUpload.blobCache;
									var base64 = reader.result.split(',')[1];
									var blobInfo = blobCache.create(id, file,
											base64);
									blobCache.add(blobInfo);
									cb(blobInfo.blobUri(), {
										title : file.name
									});
								};
								reader.readAsDataURL(file);
							};
							input.click();
						}
					});
		</script>
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
			crossorigin="anonymous"></script>
		<script>
			var myToastEl = document.getElementById('myToastEl')
			var myToast = bootstrap.Toast.getOrCreateInstance(myToastEl) // Returns a Bootstrap toast instance
			console.log('MYTOAST: ', myToastEl);
			var content = document.getElementById('msgSuccess').innerHTML;
			console.log('content: ', content);
			if(content.length > 0 && content === 'Send email successfully!'){
				myToast.show();				
			}
			console.log('myTOast : ', myToast)
		</script>
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