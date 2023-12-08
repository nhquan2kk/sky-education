<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Skydash Admin</title>
<!-- plugins:css -->
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
<script type="text/javascript"
	src='https://cdn.tiny.cloud/1/qagffr3pkuv17a8on1afax661irst1hbr4e6tbv888sz91jc/tinymce/6/tinymce.min.js'
	referrerpolicy="origin">
	
</script>
<style type="text/css">
.table td img, .jsgrid .jsgrid-table td img {
	width: 45%;
	border-radius: 0;
	height: 50px;
}

.btn-icon {
	display: inline-block;
	cursor: pointer;
	font-size: 30px;
	margin-right: 5px;
	width: 40px;
	text-align: left;
	color: #4B49AC;
}
</style>
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
						<div class="col-lg-12 grid-margin stretch-card">
							<div class="card">
								<div class="card-body">
								<div id="grammarContent" style="display: none">${grammarData.content }</div>
									<form class="forms-sample" enctype="multipart/form-data"
										method="post" action="AdminCreateGrammarController"
										id="myForm" novalidate>
										<div class="form-group">
											<label for="name">Name</label> <input name="name" type="text"
												required class="form-control" id="exampleInputName1"
												value="${grammarData.name }" placeholder="Name grammar">
										</div>
										<div class="form-group">
											<label>File upload</label> <input type="file" name="file"
												required class="form-control-file">

										</div>

										<div class="form-group">
											<label>Level</label> <select class="form-control w-25"
												name="level">
												<c:forEach items="${ listLevel}" var="item">
													<option
														${grammarData.levelId == item.idLevel ? 'selected' : ''}
														value="${item.idLevel }">${item.name }</option>
												</c:forEach>
											</select>

										</div>

										<div class="form-group">
											<label for="name">Content</label>
											<textarea id="summernote" name="content" required></textarea>
										</div>
										<input type="submit" class="btn btn-primary" />
									</form>
								</div>
							</div>
						</div>

					</div>
					<!-- content-wrapper ends -->
					<!-- partial:../../partials/_footer.html -->
					<jsp:include page="../Shared/Footer.jsp"></jsp:include>
					<!-- partial -->
				</div>
			</div>
			<!-- main-panel ends -->
		</div>
		<!-- page-body-wrapper ends -->
	</div>
	<script type="text/javascript">
		var content = document.getElementById('grammarContent').innerHTML;
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
					},
					 setup: function (editor) {
					      editor.on('init', function (e) {
					        editor.setContent(content);
					      });
					}
				});
	</script>
	<!-- container-scroller -->
	<!-- plugins:js -->
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
	<!-- endinject -->
	<!-- Custom js for this page-->
	<!-- End custom js for this page-->
</body>

</html>