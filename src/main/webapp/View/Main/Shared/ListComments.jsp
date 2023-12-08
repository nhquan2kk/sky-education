<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List comments</title>
</head>
<body>
	<c:forEach items="${listCommentGrammar }" var="item">
		<div class="media mb-4">
			<img src="img/" alt="Image"
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
</body>
</html>