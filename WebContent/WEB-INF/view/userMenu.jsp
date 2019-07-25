<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>

<style type="text/css">
#logo-css {
	width: 32px;
	height: 32px;
	text-align: center;
	border-radius: 50px;
	margin-top: 30px;
	margin-left: auto;
	margin-right: auto;
	margin-left: auto
}
</style>

${ctxPath = pageContext.request.contextPath ; ''}
<!-- Sidebar -->
<dl class="navbar-nav bg-gradient-default sidebar-light" id="accordionSidebar" style="padding-right: 20px;">

	<!-- Divider -->
	<hr class="sidebar-divider my-0">
	<!-- Nav Item - Dashboard -->

	<!-- 관리자 프로필 사진 -->
	<div style="text-align: center;">
		<div style="max-width: 180px; width: 100%; height: 100%; margin-left: auto; margin-right: auto;">
			<c:if test="${!empty admin.adminProfileUrl}">
				<img id="loadImg" src="http://localhost:8080/BLOG/upload/${admin.adminProfileUrl}" class="photo-circle">
			</c:if>
			<c:if test="${empty admin.adminProfileUrl}">
				<img id="loadImg" class="no-photo-circle">
			</c:if>
		</div>
	</div>

	<!-- 관리자 주소 -->
	
	<div>
		<%-- <p style="text-align: center;"><a href="${admin.adminGithub}" target="_blank">
				<img alt="github" id="logo-css" src="../img/32px-github_logo.png">
			</a> <a href="mailto:${admin.adminNaver}">
				<img alt="naver" id="logo-css" src="../img/200px-naver_logo.png">
			</a> <a href="mailto:${admin.adminGoogle}">
				<img alt="google" id="logo-css" src="../img/240px-google_logo.png">
			</a></p> --%>
		<p style="text-align: center;"><a href="${admin.adminGithub}" target="_blank">github</a>&nbsp|&nbsp <a href="mailto:${admin.adminNaver}">naver</a>&nbsp|&nbsp <a href="mailto:${admin.adminGoogle}">google</a></p>
	</div>
	<!-- 검색 -->
	<div>
		<!-- class=	"d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search" -->
		<form action="list.do" method="get">

			<input type="checkbox" id="postTitle" name="postTitle" checked />제목 <input type="checkbox" id="postContent" name="postContent" />본문
			<div class="input-group">
				<input type="text" id="postSearchText" name="postSearchText" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="search" aria-describedby="basic-addon2"> <input type="submit" class="btn btn-warning" value="검색">
				<!-- <div class="input-group-append">
					<button class="btn btn-primary" type="button">
						<i class="fas fa-search fa-sm"></i>
					</button>
				</div> -->
			</div>
		</form>
	</div>

	<hr class="sidebar-divider">

	<!-- 카테고리  -->
	<dt class="nav-item">카테고리
	<dd>
		<ul>
			<c:if test ="${param.board == '개발노트'}">
				<c:forEach var="cateList" items="${boardCate[2].cateList}">
					<li><a href="list.do?board=${param.board}&cate=${cateList.cateName}">${cateList.cateName}</a></li>
				</c:forEach>
			</c:if>
			<c:if test ="${param.board == '일상'}">
        <c:forEach var="cateList" items="${boardCate[0].cateList}">
          <li><a href="list.do?board=${param.board}&cate=${cateList.cateName}">${cateList.cateName}</a></li>
        </c:forEach>
      </c:if>
		</ul>
	</dd>
	</dt>

	<hr class="sidebar-divider">

	<dt class="nav-item">TOP5
	<dd>
		<ol>
			<c:forEach var="post" items="${postTop5}">
				<c:if test="${post.postVisibility==true}">
					<li><a href="adminRead.do?no=${post.postId}">${post.postTitle}</a></li>
				</c:if>
			</c:forEach>
		</ol>
	</dd>
	</dt>

	<hr class="sidebar-divider">

	<dt class="nav-item">
		TAG
		<div>
			<c:forEach var="tag" items="${tag}">
				<span>
					<a href="list.do?tag=${tag.tagName}">${tag.tagName}&nbsp;</a>
				</span>
			</c:forEach>
		</div>
	</dt>
	<!-- Divider -->
	<hr class="sidebar-divider">

</dl>
<!-- End of Sidebar -->
