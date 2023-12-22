<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

		<!DOCTYPE html>
		<html lang="en">

		<head>
			<!-- Required meta tags -->
			<meta charset="utf-8">
			<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
			<title>Skydash Admin</title>
			<!-- plugins:css -->
			<link rel="stylesheet" href="Assets/Admin/vendors/feather/feather.css">
			<link rel="stylesheet" href="Assets/Admin/vendors/ti-icons/css/themify-icons.css">
			<link rel="stylesheet" href="Assets/Admin/vendors/css/vendor.bundle.base.css">
			<!-- endinject -->
			<!-- Plugin css for this page -->
			<link rel="stylesheet" href="Assets/Admin/vendors/mdi/css/materialdesignicons.min.css">
			<!-- End plugin css for this page -->
			<!-- inject:css -->
			<link rel="stylesheet" href="Assets/Admin/css/vertical-layout-light/style.css">
			<!-- endinject -->
			<link rel="shortcut icon" href="Assets/Admin/images/favicon.png" />
			<link rel="stylesheet" href="Assets/Admin/css/custom/style.css">
			<style type="text/css">
				.table td img,
				.jsgrid .jsgrid-table td img {
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
								<a class="my-4 text-black" href="AdminCreateListeningController">Create
									new listening</a>


								<div class="col-lg-12 grid-margin stretch-card">
									<div class="card">
										<div class="card-body">
											<div class="table-responsive pt-3">
												<table class="table table-bordered">
													<thead>
														<tr>
															<th>#</th>
															<th>Name</th>
															<th>Image</th>
															<th>Action</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${ listeningList}" var="item" varStatus="index">
															<tr>
																<td>${index.count}</td>
																<td><a href="DetailListeningController?listenId=${item.listenId }"
																		class="">${item.name }</a></td>
																<td><img src="img/listening/${item.image}" /></td>
																<td class="d-flex align-items-center"><a
																		href="AdminUpdateListeningController?listenId=${item.listenId }">
																		<i class="mdi mdi-table-edit btn-icon"></i>
																	</a>
																	<form id="deleteForm" method="post"
																		action="AdminDeleteListeningController">
																		<input type="hidden" name="listenId"
																			value="${item.listenId }" />
																		<button type="button" class="btn "
																			data-toggle="modal"
																			data-target="#exampleModal-${item.listenId }">
																			<i style="font-size: 30px;"
																				class="mdi mdi-delete-forever btn-icon "></i>
																		</button>
																		<div class="modal fade"
																			id="exampleModal-${item.listenId }"
																			tabindex="-1"
																			aria-labelledby="exampleModalLabel"
																			aria-hidden="true">
																			<div class="modal-dialog">
																				<div class="modal-content">
																					<div class="modal-header">
																						<h5 class="modal-title"
																							id="exampleModalLabel">
																							Notification</h5>
																						<button type="button"
																							class="close"
																							data-dismiss="modal"
																							aria-label="Close">
																							<span
																								aria-hidden="true">&times;</span>
																						</button>
																					</div>
																					<div class="modal-body">Do you want
																						to delete
																						${item.name } ?</div>
																					<div class="modal-footer">
																						<button type="button"
																							class="btn btn-secondary"
																							data-dismiss="modal">Cancer</button>
																						<button type="submit"
																							class="btn btn-primary">Delete</button>
																					</div>
																				</div>
																			</div>
																		</div>
																	</form>
																</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</div>
									</div>

								</div>
								<c:if test="${numberPage == 1 }">
									<div class="btn-group" role="group" aria-label="Basic example">
										<button type="button" class="btn btn-outline-secondary">Prev</button>
										<button type="button" class="btn btn-outline-secondary">
											<a href="AdminListeningController?pageId=1">1</a>
										</button>
										<button type="button" class="btn btn-outline-secondary">
											<a href="AdminListeningController?pageId=2">2</a>
										</button>
										<button type="button" class="btn btn-outline-secondary">
											<a href="AdminListeningController?pageId=3">3</a>
										</button>
										<button type="button" class="btn btn-outline-secondary">
											<a href="AdminListeningController?pageId=${numberPage +1}">Next</a>
										</button>
									</div>
								</c:if>
								<c:if test="${numberPage == maxPageId }">
									<div class="btn-group" role="group" aria-label="Basic example">
										<button type="button" class="btn btn-outline-secondary">
											<a href="AdminListeningController?pageId=${numberPage - 1 }">Prev</a>
										</button>
										<button type="button" class="btn btn-outline-secondary">
											<a href="AdminListeningController?pageId=1">1</a>
										</button>
										<button type="button" class="btn btn-outline-secondary">
											<a href="AdminListeningController?pageId=2">2</a>
										</button>
										<button type="button" class="btn btn-outline-secondary">
											<a href="AdminListeningController?pageId=3">3</a>
										</button>
										<button type="button" class="btn btn-outline-secondary">
											Next</button>
									</div>

								</c:if>

								<c:if test="${numberPage != 1 && numberPage < maxPageId }">
									<div class="btn-group" role="group" aria-label="Basic example">
										<button type="button" class="btn btn-outline-secondary">
											<a href="AdminListeningController?pageId=${numberPage - 1 }">Prev</a>
										</button>
										<button type="button" class="btn btn-outline-secondary">
											<a href="AdminListeningController?pageId=1">1</a>
										</button>
										<button type="button" class="btn btn-outline-secondary">
											<a href="AdminListeningController?pageId=2">2</a>
										</button>
										<button type="button" class="btn btn-outline-secondary">
											<a href="AdminListeningController?pageId=3">3</a>
										</button>
										<button type="button" class="btn btn-outline-secondary">
											Next</button>
									</div>
								</c:if>
							</div>
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