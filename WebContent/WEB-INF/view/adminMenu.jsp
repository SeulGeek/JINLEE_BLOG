<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>

${ctxPath = pageContext.request.contextPath ; ''}
<u:isLogin>
	<!-- Sidebar -->
	<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

		<!-- Sidebar - Brand -->
		<a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.jsp">
			<div class="sidebar-brand-icon rotate-n-15"></div>
			<div class="sidebar-brand-text mx-3">JINLEE</div>
		</a>

		<!-- Divider -->
		<hr class="sidebar-divider d-none d-md-block">


		<!-- 대시보드 화면 -->
		<li class="nav-item"><a class="nav-link" href="${ctxPath}/adminTopList.do">
				<i class="fas fa-fw fa-home"></i>
				<span>대시보드</span>
			</a></li>

		<!-- Divider -->
		<hr class="sidebar-divider d-none d-md-block">

		<!-- 개인정보수정 화면  -->
		<li class="nav-item"><a class="nav-link" href="${ctxPath}/adminSettingModify.do?no=${authUser.id}">

				<i class="fas fa-fw fa-user"></i>
				<span>개인정보수정</span>
			</a></li>

		<!-- Divider -->
		<hr class="sidebar-divider d-none d-md-block">

		<!-- 게시판 관리 화면 -->
		<li class="nav-item"><a class="nav-link" href="${ctxPath}/post/adminList.do">
				<i class="fas fa-fw fa-table"></i>
				<span>게시판 관리</span>
			</a></li>

		<!-- Divider -->
		<hr class="sidebar-divider d-none d-md-block">

		<!-- Nav Item - Tables -->
		<!-- <li class="nav-item"><a class="nav-link" href="#comment">
				<i class="fas fa-fw fa-comments"></i>
				<span>댓글 관리</span>
			</a></li> -->


		<!-- Sidebar Toggler (Sidebar) -->
		<div class="text-center d-none d-md-inline">
			<button class="rounded-circle border-0" id="sidebarToggle"></button>
		</div>

	</ul>
	<!-- End of Sidebar -->
</u:isLogin>