<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Skydash Admin</title>
<!-- 
	<!-- include libraries(jQuery, bootstrap)
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

include summernote css/js
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script> 
	-->


<!-- plugins:css -->
<link rel="stylesheet" href="Assets/Admin/vendors/feather/feather.css">
<link rel="stylesheet"
	href="Assets/Admin/vendors/ti-icons/css/themify-icons.css">
<link rel="stylesheet"
	href="Assets/Admin/vendors/css/vendor.bundle.base.css">
<!-- endinject -->
<!-- Plugin css for this page -->
<link rel="stylesheet"
	href="Assets/Admin/vendors/select2/select2.min.css">
<link rel="stylesheet"
	href="Assets/Admin/vendors/select2-bootstrap-theme/select2-bootstrap.min.css">
<!-- End plugin css for this page -->
<!-- inject:css -->
<link rel="stylesheet"
	href="Assets/Admin/css/vertical-layout-light/style.css">
<!-- endinject -->
<link rel="shortcut icon" href="Assets/Admin/images/favicon.png" />
<link rel="shortcut icon" href="Assets/Admin/images/favicon.png" />
<link rel="stylesheet" href="Assets/Admin/vendors/feather/feather.css">
<link rel="stylesheet"
	href="Assets/Admin/vendors/ti-icons/css/themify-icons.css">
<link rel="stylesheet"
	href="Assets/Admin/vendors/css/vendor.bundle.base.css">
<!-- endinject -->
<!-- Plugin css for this page -->
<link rel="stylesheet"
	href="Assets/Admin/vendors/mdi/css/materialdesignicons.min.css">
<!-- End plugin css for this page -->
<!-- inject:css -->
<link rel="stylesheet"
	href="Assets/Admin/css/vertical-layout-light/style.css">
<!-- endinject -->
<link rel="shortcut icon" href="Assets/Admin/images/favicon.png" />
<link rel="stylesheet" href="Assets/Admin/css/custom/style.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"
	integrity="sha512-U6K1YLIFUWcvuw5ucmMtT9HH4t0uz3M366qrF5y4vnyH6dgDzndlcGvH/Lz5k8NFh80SN95aJ5rqGZEdaQZ7ZQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>
	$(document)
			.ready(
					function() {
						$("#addFile")
								.click(
										function() {
											var fileIndex = $('#fileTable tr')
													.children().length - 1;
											console.log("fileIndex : "
													+ fileIndex);
											$('#fileTable')
													.append(
															'<tr><td>'
																	+ '<input type="file" name="files['+fileIndex+']" />'
																	+ '</td></tr>')
										})
					})
</script>


</head>
<body>

	<div class="container-scroller">
		<!-- partial:../../partials/_navbar.html -->
		<jsp:include page="../Shared/Header.jsp"></jsp:include>
		<!-- partial -->
		<div class="container-fluid page-body-wrapper">
			<!-- partial:../../partials/_settings-panel.html -->
			<jsp:include page="../Shared/SettingPanel.jsp"></jsp:include>
			<jsp:include page="../Shared/OptionPanel.jsp"></jsp:include>
			<jsp:include page="../Shared/Sidebar.jsp"></jsp:include>
			<div class="main-panel">
				<div class="content-wrapper">
					<div class="row">

						<div class="col-12 grid-margin stretch-card">
							<div class="card">
								<div class="card-body">
									<form method="post" enctype="multipart/form-data"
										action=AdminAddResourceController>
										<table id="fileTable">
											<tr>
												<td><input name="files[0]" type="file" /></td>
											</tr>

										</table>
										<br /> <input type="submit" value="Upload multile file" /> <input
											id="addFile" type="button" value="Add file field" />
									</form>

								</div>
								<!-- content-wrapper ends -->
								<!-- partial:../../partials/_footer.html -->
								<jsp:include page="../Shared/Footer.jsp"></jsp:include>
								<!-- partial -->
							</div>
							<!-- main-panel ends -->
						</div>
						<!-- page-body-wrapper ends -->
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="Assets/Admin/vendors/js/vendor.bundle.base.js"></script>
	<!-- endinject -->
	<!-- Plugin js for this page -->
	<script src="Assets/Admin/vendors/typeahead.js/typeahead.bundle.min.js"></script>
	<script src="Assets/Admin/vendors/select2/select2.min.js"></script>
	<!-- End plugin js for this page -->
	<!-- inject:js -->
	<script src="Assets/Admin/js/off-canvas.js"></script>
	<script src="Assets/Admin/js/hoverable-collapse.js"></script>
	<script src="Assets/Admin/js/template.js"></script>
	<script src="Assets/Admin/js/settings.js"></script>
	<script src="Assets/Admin/js/todolist.js"></script>
	<!-- endinject -->
	<!-- Custom js for this page-->
	<script src="Assets/Admin/js/file-upload.js"></script>
	<script src="Assets/Admin/js/typeahead.js"></script>
	<script src="Assets/Admin/js/select2.js"></script>
	<!-- End custom js for this page-->
</body>

</html>
