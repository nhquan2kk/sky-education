<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
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
<link rel="stylesheet"
	href="Assets/Admin/vendors/mdi/css/materialdesignicons.min.css">

<link rel="stylesheet" href="Assets/Admin/css/custom/style.css">
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
<!-- File upload toàn mắc lỗi get ra một cột bằng null bởi vì cột hiện tại đang rỗng, file có row = 9( do java đọc) nhưng thực tế file chỉ có row = 5 ????<!--  -->
						<div class="col-12 grid-margin stretch-card">
							<div class="card">
								<div class="card-body">
									<form class="forms-sample" enctype="multipart/form-data"
										method="post" action="AdminUpdateVocabularyController">
											<input type="hidden" value="${vocabularyData.vocabularyId }" name="vocabularyId"/>
										<div class="form-group">
											<label for="name">Name</label> <input name="name" type="text"
												class="form-control" id="exampleInputName1" value="${vocabularyData.name }" 
												placeholder="Name examination">
										</div>
										<div class="form-group">
											<label>File upload</label> <input type="file" name="imgFile"
												class="file-upload-default">
											<div class="input-group col-xs-12">
												<input type="text" class="form-control file-upload-info"
													disabled placeholder="Upload Image"> <span
													class="input-group-append">
													<button class="file-upload-browse btn btn-primary"
														type="button">Upload</button>
												</span>
											</div>
										</div>
										<div class="form-group">
											<label>Upload data</label> <input type="file" name="excelFile"
												class="file-upload-default">
											<div class="input-group col-xs-12">
												<input type="text" class="form-control file-upload-info"
													disabled placeholder="Upload Excel"> <span
													class="input-group-append">
													<button class="file-upload-browse btn btn-primary"
														type="button">Upload</button>
												</span>
											</div>
										</div>
										<div class="form-group">
											<label for="exampleTextarea1">Content</label>
											<!-- <textarea class="form-control" id="exampleTextarea1" name="content" rows="4"></textarea> -->
											<textarea id="summernote" class="form-control" name="content"></textarea>
										</div>
										<button class="btn btn-primary mr-2">Submit</button>
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
