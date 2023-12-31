
<div class="container-fluid bg-light position-relative shadow">
	<nav
		class="navbar navbar-expand-lg bg-light navbar-light py-3 py-lg-0 px-0 px-lg-5">
		<a href="HomeController"
			class="navbar-brand font-weight-bold text-secondary"
			style="font-size: 50px;"> <!--  <i class="flaticon-043-teddy-bear"></i> -->
			<img alt="" style="margin-bottom: 12px"
			src="Assets/Main/img/online-learning.png"> <span
			class="text-primary">Sky Education</span>
		</a>
		<button type="button" class="navbar-toggler" data-toggle="collapse"
			data-target="#navbarCollapse">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse justify-content-around"
			id="navbarCollapse">
			<div class="navbar-nav font-weight-bold py-0">
				<a href="HomeController" class="nav-item nav-link active">Home</a> <a
					href="GrammarController?pageId=1" class="nav-item nav-link">Grammar</a>
				<a href="ExaminationController?pageId=1" class="nav-item nav-link">Examination</a>
				<a href="VocabularyController?pageId=1" class="nav-item nav-link">Vocabulary</a>
				<a href="ListeningController?pageId=1" class="nav-item nav-link">Listening</a>

				<!--    <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Pages</a>
                        <div class="dropdown-menu rounded-0 m-0">
                            <a href="ReadingController?pageId=1" class="dropdown-item">Reading</a>
                            <a href="single.html" class="dropdown-item">Blog Detail</a>
                        </div>
                    </div> -->
				<a href="ReadingController?pageId=1" class="nav-item nav-link">Reading</a>
				<a href="ContactController" class="nav-item nav-link">Contact</a>
				<!--      <a href="contact.html" class="nav-item nav-link">Contact</a> -->
				<a href="ChatGPTController" class="nav-item nav-link">ChatAI</a>
			</div>
			<%
			if (session.getAttribute("sessionUser") == null) {
			%>
			<a href="LoginController" class="btn btn-primary px-4">Login</a>
			<%
			} else {
			%>
			<div class="wrapper-profile nav-item nav-link">
				<div class="nav-item dropdown">
					<div class="avatar-user" class="nav-link dropdown-toggle"
						data-toggle="dropdown"> <img
						src="img/profile/${MEMBERAVATAR }" />
					</div>
					<div class="dropdown-menu rounded-0 m-0">
						<a href="ProfileController" class="dropdown-item">Profile</a> <a
							href="LogoutController" class="dropdown-item">Logout</a>
					</div>
				</div>
			</div>
			<%
			}
			%>
		</div>
	</nav>
</div>
<link href="Assets/Main/css/profile.css" rel="stylesheet">