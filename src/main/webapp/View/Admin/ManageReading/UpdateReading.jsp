<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>add text to text area and submit</title>
<!-- Boostrap version 4 and jQuery version 3.5 -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script>
<!-- include summernote css/js-->
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
<body>

	<script>
		$(document).ready(function() {
			$('#summernote').summernote();

		});
	</script>
	<div style="height: 100vh"
		class="container d-flex align-items-center justify-content-center">
		<div id="contentSummernote" style="display: none">${readingData.content }</div>
		<div class="row align-items-center p-5 mt-5">
			<div class="col-12">
				<!-- use method post, because method get has limits of the max length -->
				<form class="forms-sample" enctype="multipart/form-data"
					method="post" action="AdminUpdateReadingController" id="myForm">
					<input type="hidden" value="${readingData.readingId }"
						name="readingId" />
					<div class="form-group">
						<label for="name">Name</label> <input name="name" type="text"
							class="form-control" id="exampleInputName1"
							value="${readingData.name }" placeholder="Name reading">
					</div>
					<div class="form-group">
						<label>File upload</label> <input type="file" name="imgFile" required
							class="form-control-file">

					</div>
					<div class="form-group">
						<label>File upload</label> <input type="file" name="excelFile" required
							class="form-control-file">

					</div>
					<div class="form-group">
						<label for="name">Content</label>
						<textarea id="summernote" name="content"></textarea>
					</div>
					<input type="submit" />
				</form>

			</div>
		</div>
	</div>
	<script>
		$('#summernote').summernote({
			tabsize : 2,
			height : 450
		});
	</script>
	<script>
		$(document).ready(function() {
			var content = $("#contentSummernote").html();
			console.log("content : ", content);

			var text = `${content}`;
			$('#summernote').summernote('code', content);
		});
	</script>
</body>