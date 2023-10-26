<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Grammar</title>
<!-- include libraries(jQuery, bootstrap) -->
<!-- <link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css"
	rel="stylesheet">
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script>
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> -->


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
<!-- <link
	href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.css"
	rel="stylesheet">
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.js"></script> -->
	
	  <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
<body>

	<script>
		$(document).ready(function() {
			$('#summernote').summernote();
		});
	</script>

	<div style="height: 100vh"
		class="container mt-2">
		<div id="contentSummernote" style="display: none">${grammarData.content }</div>
			<div class="col-12 ">
				<form class="forms-sample" enctype="multipart/form-data"
					method="post" action="AdminUpdateGrammarController" id="myForm">
					<input type="hidden" name="grammarId" value="${grammarId }" />
					<div class="form-group">
						<label for="name">Name</label> <input name="name" type="text"
							value="${grammarData.name }" class="form-control"
							id="exampleInputName1" placeholder="Name grammar">
					</div>
					<div class="form-group">
						<label>Level</label>
						 <select class="form-control w-25" name="level">
							<c:forEach items="${ listLevel}" var="item" >
								<option 
								${grammarData.levelId == item.idLevel ? 'selected' : ''}
								 value="${item.idLevel }">${item.name }</option>
							</c:forEach>
						</select>

					</div>
					<div class="form-group">
						<label>File upload</label> <input type="file" name="file"
							class="form-control-file">

					</div>
					<div class="form-group">
						<label for="name">Content</label>
						<textarea id="summernote" name="content"></textarea>
					</div>
					<input type="submit" class="btn btn-primary"/>
				</form>

			</div>
		</div>
	</div>
	<script>
	 $('#summernote').summernote({
	        tabsize: 2,
	        height: 450
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