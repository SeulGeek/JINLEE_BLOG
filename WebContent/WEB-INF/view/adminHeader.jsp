<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>

<%--컴퓨터, 핸드폰 어떤 곳에서 접속하더라도 각 해상도에 맞게 바꿔 줄 수 있는 코드--%>
<meta name="viewport" content="width=device-width" , initial-scale="1">

${ctxPath = pageContext.request.contextPath; ''}
<header>
	<%-- 	<!-- 	<a class="navbar-brand" href="index.jsp">JINLEE</a>관리자모드 -->
	<nav class="navbar navbar-expand-lg n999999*avbar-light bg-light">
		<a class="navbar-brand" href="index.jsp">JINLEE</a>
		관리자모드
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor03" aria-controls="navbarColor03" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse " id="navbarColor03">
			<ul class="navbar-nav mr-auto">
			
			</ul>
		</div>
		<div class="collapse navbar-collapse">
			<ul class="navbar-nav mr-auto">
				<u:isLogin>
					<li class="nav-item">${authUser.name}님,안녕하세요.<a class="btn btn-outline-secondary btn-sm" href="${ctxPath}/adminLogout.do" role="button">로그아웃</a> <a href="${ctxPath}/adminLogout.do">[로그아웃]</a>
					</li>
				</u:isLogin>
			</ul>
		</div>
	</nav> --%>

	<!-- Topbar -->
	<nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

		<!-- Sidebar Toggle (Topbar) -->
		<button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
			<i class="fa fa-bars"></i>
		</button>

		<u:notLogin>
			<a class="navbar-brand" href="${ctxPath}/adminTopList.do"><h2>JINLEE</h2></a>관리자모드
      </u:notLogin>

		<!-- Topbar Navbar -->
		<ul class="navbar-nav ml-auto">

			<!-- <div class="topbar-divider d-none d-sm-block"></div> -->

			<!-- Nav Item - User Information -->
			<u:isLogin>
				<li class="nav-item dropdown no-arrow"><a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<span class="mr-2 d-none d-lg-inline text-gray-600 small">${authUser.name}님,안녕하세요.</span>>
					</a> <!-- Dropdown - User Information -->
					<div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
						<a class="dropdown-item" href="${ctxPath}/adminLogout.do">
							<i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i> 로그아웃
						</a>
					</div></li>
			</u:isLogin>

		</ul>

	</nav>
	<!-- End of Topbar -->

</header>