<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
	<!-- Add this block to prevent back button after logout  -->
<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Skydash Admin</title>

<link rel="stylesheet" href="Assets/Admin/vendors/feather/feather.css">
<link rel="stylesheet"
	href="Assets/Admin/vendors/ti-icons/css/themify-icons.css">
<link rel="stylesheet"
	href="Assets/Admin/vendors/css/vendor.bundle.base.css">

<link rel="stylesheet"
	href="Assets/Admin/vendors/datatables.net-bs4/dataTables.bootstrap4.css">
<link rel="stylesheet"
	href="Assets/Admin/vendors/ti-icons/css/themify-icons.css">
<link rel="stylesheet" type="text/css"
	href="Assets/Admin/js/select.dataTables.min.css">

<link rel="stylesheet"
	href="Assets/Admin/vendors/mdi/css/materialdesignicons.min.css">

<link rel="stylesheet"
	href="Assets/Admin/css/vertical-layout-light/style.css">

<link rel="stylesheet" href="Assets/Admin/css/custom/style.css">

<link rel="shortcut icon" href="Assets/Admin/images/favicon.png" />
</head>
<body>
	<div class="container-scroller">
		<jsp:include page="./Shared/Header.jsp"></jsp:include>
		<div class="container-fluid page-body-wrapper">
			<jsp:include page="./Shared/SettingPanel.jsp"></jsp:include>
			<jsp:include page="./Shared/OptionPanel.jsp"></jsp:include>
			<jsp:include page="./Shared/Sidebar.jsp"></jsp:include>
			<div class="main-panel">
				<div class="content-wrapper">
					<div class="row">
						<div class="col-md-12 grid-margin">
							<div class="row">
								<div class="col-12 col-xl-8 mb-4 mb-xl-0">
									<h3 class="font-weight-bold">Welcome Admin</h3>
									<h6 class="font-weight-normal mb-0">All systems are
										running smoothly!</h6>
								</div>
								<div class="col-12 col-xl-4">
									<div class="justify-content-end d-flex">
										<div class=" flex-md-grow-1 flex-xl-grow-0">
											<p
												class="bg-white 	rounded-pill px-2 py-2 d-flex align-items-center">
												<i class="mdi mdi-calendar"></i>Today (${currentDay })
											</p>

											<%-- <button class="btn btn-sm btn-light bg-white dropdown-toggle"
												type="button" id="dropdownMenuDate2" data-toggle="dropdown"
												aria-haspopup="true" aria-expanded="true">
												<i class="mdi mdi-calendar"></i>Today (${currentDay })
											</button> --%>
											<!-- <div class="dropdown-menu dropdown-menu-right"
												aria-labelledby="dropdownMenuDate2">
												<a class="dropdown-item" href="Assets/Admin/#">January -
													March</a> <a class="dropdown-item" href="Assets/Admin/#">March
													- June</a> <a class="dropdown-item" href="Assets/Admin/#">June
													- August</a> <a class="dropdown-item" href="Assets/Admin/#">August
													- November</a>
											</div> -->
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<!-- 	<div class="col-md-6 grid-margin stretch-card">
							<div class="card tale-bg">
								<div class="card-people mt-auto">
									<img src="Assets/Admin/images/dashboard/people.svg"
										alt="people">
									<div class="weather-info">
										<div class="d-flex">
											<div>
												<h2 class="mb-0 font-weight-normal">
													<i class="icon-sun mr-2"></i>31<sup>C</sup>
												</h2>
											</div>
											<div class="ml-2">
												<h4 class="location font-weight-normal">Bangalore</h4>
												<h6 class="font-weight-normal">India</h6>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div> -->
						<div class="col-12 grid-margin transparent">
							<div class="row">

								<div class="col-md-2 mb-4 stretch-card transparent">
									<div class="card card-tale">
										<div class="card-body">
											<p class="mb-4">Total Grammar</p>
											<p class="fs-30 mb-2">${grammarTotal }</p>
										</div>
									</div>
								</div>
								
								
								
								<div class="col-md-2 mb-4 stretch-card transparent">
									<div class="card card-dark-blue">
										<div class="card-body">
											<p class="mb-4">Total Examination</p>
											<p class="fs-30 mb-2">${examinationTotal }</p>
										</div>
									</div>
								</div>
								<div class="col-md-2 mb-4 stretch-card transparent">
									<div class="card card-light-blue">
										<div class="card-body">
											<p class="mb-4">Total Vocabulary</p>
											<p class="fs-30 mb-2">${vocabularyTotal }</p>
										</div>
									</div>
								</div>
								<div class="col-md-2 mb-4 stretch-card transparent">
									<div class="card card-light-danger">
										<div class="card-body">
											<p class="mb-4">Total Reading</p>
											<p class="fs-30 mb-2">${readingTotal }</p>
										</div>
									</div>
								</div>
								<div class="col-md-2 mb-4 stretch-card transparent">
									<div class="card card-tale">
										<div class="card-body">
											<p class="mb-4">Total Listening</p>
											<p class="fs-30 mb-2">${listeningTotal }</p>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6 grid-margin stretch-card">
							<div class="card">
								<div class="card-body">
									<p class="card-title">New Member Report</p>
									<p class="font-weight-500 mb-3">The total number of new member
										within the date range. That's the time that users register for
										an account every day</p>
									<canvas id="lineChart"></canvas>
								</div>
							</div>
						</div>
						<div class="col-md-6 grid-margin stretch-card">
							<div class="card">
								<div class="card-body">
									<div class="d-flex justify-content-between">
										<p class="card-title">Feature Report</p>
									<!-- 	<a href="Assets/Admin/#" class="text-info">View all</a> -->
									</div>
									<p class="font-weight-500 mb-3">The total number of every feature
										</p>
									<div id="sales-legend" class="chartjs-legend mt-4 mb-2"></div>
									<canvas id="pieChart"></canvas>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 grid-margin stretch-card">
							<div class="card position-relative">
								<div class="card-body">
									<div id="detailedReports"
										class="carousel slide detailed-report-carousel position-static pt-2"
										data-ride="carousel">
										<div class="carousel-inner">
											<div class="carousel-item active">
												<div class="row">
													<div
														class="col-md-12 col-xl-3 d-flex flex-column justify-content-start">
														<div class="ml-xl-4 mt-3">
															<p class="card-title">Number Of Examination Reports</p>
														<!-- 	<h1 class="text-primary">$34040</h1> -->
															<!-- `<h3 class="font-weight-500 mb-xl-4 text	-primary">North
																America</h3> -->
															<p class="mb-2 mb-xl-0">The total number of examination by level.</p>
														</div>
													</div>
													<div class="col-md-12 col-xl-9">
														<div class="row">
															<div class="col-12 ">
																<canvas id="horizontalBarChart"></canvas>
															</div>
															`
														</div>
													</div>
												</div>
											</div>
											<!-- <div class="carousel-item">
												<div class="row">
													<div
														class="col-md-12 col-xl-3 d-flex flex-column justify-content-start">
														<div class="ml-xl-4 mt-3">
															<p class="card-title">Detailed Reports</p>
															<h1 class="text-primary">$34040</h1>
															<h3 class="font-weight-500 mb-xl-4 text-primary">North
																America</h3>
															<p class="mb-2 mb-xl-0">The total number of sessions
																within the date range. It is the period time a user is
																actively engaged with your website, page or app, etc</p>
														</div>
													</div>
													<div class="col-md-12 col-xl-9">
														<div class="row">
															<div class="col-md-6 border-right">
																<div class="table-responsive mb-3 mb-md-0 mt-3">
																	<table class="table table-borderless report-table">
																		<tr>
																			<td class="text-muted">Illinois</td>
																			<td class="w-100 px-0">
																				<div class="progress progress-md mx-4">
																					<div class="progress-bar bg-primary"
																						role="progressbar" style="width: 70%"
																						aria-valuenow="70" aria-valuemin="0"
																						aria-valuemax="100"></div>
																				</div>
																			</td>
																			<td><h5 class="font-weight-bold mb-0">713</h5></td>
																		</tr>
																		<tr>
																			<td class="text-muted">Washington</td>
																			<td class="w-100 px-0">
																				<div class="progress progress-md mx-4">
																					<div class="progress-bar bg-warning"
																						role="progressbar" style="width: 30%"
																						aria-valuenow="30" aria-valuemin="0"
																						aria-valuemax="100"></div>
																				</div>
																			</td>
																			<td><h5 class="font-weight-bold mb-0">583</h5></td>
																		</tr>
																		<tr>
																			<td class="text-muted">Mississippi</td>
																			<td class="w-100 px-0">
																				<div class="progress progress-md mx-4">
																					<div class="progress-bar bg-danger"
																						role="progressbar" style="width: 95%"
																						aria-valuenow="95" aria-valuemin="0"
																						aria-valuemax="100"></div>
																				</div>
																			</td>
																			<td><h5 class="font-weight-bold mb-0">924</h5></td>
																		</tr>
																		<tr>
																			<td class="text-muted">California</td>
																			<td class="w-100 px-0">
																				<div class="progress progress-md mx-4">
																					<div class="progress-bar bg-info"
																						role="progressbar" style="width: 60%"
																						aria-valuenow="60" aria-valuemin="0"
																						aria-valuemax="100"></div>
																				</div>
																			</td>
																			<td><h5 class="font-weight-bold mb-0">664</h5></td>
																		</tr>
																		<tr>
																			<td class="text-muted">Maryland</td>
																			<td class="w-100 px-0">
																				<div class="progress progress-md mx-4">
																					<div class="progress-bar bg-primary"
																						role="progressbar" style="width: 40%"
																						aria-valuenow="40" aria-valuemin="0"
																						aria-valuemax="100"></div>
																				</div>
																			</td>
																			<td><h5 class="font-weight-bold mb-0">560</h5></td>
																		</tr>
																		<tr>
																			<td class="text-muted">Alaska</td>
																			<td class="w-100 px-0">
																				<div class="progress progress-md mx-4">
																					<div class="progress-bar bg-danger"
																						role="progressbar" style="width: 75%"
																						aria-valuenow="75" aria-valuemin="0"
																						aria-valuemax="100"></div>
																				</div>
																			</td>
																			<td><h5 class="font-weight-bold mb-0">793</h5></td>
																		</tr>
																	</table>
																</div>
															</div>
															<div class="col-md-6 mt-3">
																<canvas id="south-america-chart"></canvas>
																<div id="south-america-legend"></div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<a class="carousel-control-prev"
											href="Assets/Admin/#detailedReports" role="button"
											data-slide="prev"> <span
											class="carousel-control-prev-icon" aria-hidden="true"></span>
											<span class="sr-only">Previous</span>
										</a> <a class="carousel-control-next"
											href="Assets/Admin/#detailedReports" role="button"
											data-slide="next"> <span
											class="carousel-control-next-icon" aria-hidden="true"></span>
											<span class="sr-only">Next</span>
										</a>
									</div> -->
										</div>
									</div>
								</div>
							</div>

						</div>
						<jsp:include page="./Shared/Footer.jsp"></jsp:include>
					</div>
				</div>
			</div>

			<!-- plugins:js -->
			<script src="Assets/Admin/vendors/js/vendor.bundle.base.js"></script>
			<!-- endinject -->
			<!-- Plugin js for this page -->
			<script src="Assets/Admin/vendors/chart.js/Chart.min.js"></script>
			<script
				src="Assets/Admin/vendors/datatables.net/jquery.dataTables.js"></script>
			<script
				src="Assets/Admin/vendors/datatables.net-bs4/dataTables.bootstrap4.js"></script>
			<script src="Assets/Admin/js/dataTables.select.min.js"></script>

			<!-- End plugin js for this page -->
			<!-- inject:js -->
			<script src="Assets/Admin/js/off-canvas.js"></script>
			<script src="Assets/Admin/js/hoverable-collapse.js"></script>
			<script src="Assets/Admin/js/template.js"></script>
			<script src="Assets/Admin/js/settings.js"></script>
			<script src="Assets/Admin/js/todolist.js"></script>
			<!-- endinject -->
			<!-- Custom js for this page-->
			<!--   <script src="Assets/Admin/js/dashboard.js"></script> -->
			<script src="Assets/Admin/js/Chart.roundedBarCharts.js"></script>

			<!-- Render All Chart -->
			<script src="Assets/Admin/js/RenderChart.js"></script>
			<!-- <script>
	 
	</script> -->
			<!-- End custom js for this page-->
</body>

</html>

