<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>

<%--컴퓨터, 핸드폰 어떤 곳에서 접속하더라도 각 해상도에 맞게 바꿔 줄 수 있는 코드--%>
<meta name="viewport" content="width=device-width" , initial-scale="1">

${ctxPath = pageContext.request.contextPath; ''}
<header>

	<!-- Topbar -->
	<nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

		<!-- Sidebar Toggle (Topbar) -->
		<button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
			<i class="fa fa-bars"></i>
		</button>

			<a class="navbar-brand" href="${ctxPath}/post/list.do?board=개발노트">
				<h2>JINLEE</h2>
			</a>

		<!-- Topbar Navbar -->

		<div class="topbar-divider d-none d-sm-block"></div>

		<!-- 네비바 우측 -->
		<ul class="navbar-nav ml-auto">

			<c:forEach var="board" items="${boardList}">
				<!-- Nav Item - Alerts -->
				<li class="nav-item"><a class="nav-link" href="${ctxPath}/post/list.do?board=${board.boardName}">${board.boardName}</a> <!-- Dropdown - Alerts -->
					<div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="alertsDropdown"></div></li>

				<div class="topbar-divider d-none d-sm-block"></div>
				<!-- Nav Item - User Information -->
				</li>
			</c:forEach>
		</ul>
		<!-- 네비바 우측 끝 -->
		<!-- Nav Item - User Information -->
		</ul>
	</nav>
	<!-- End of Topbar -->

</header>