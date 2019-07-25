<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>


<head>
<title>JINLEE</title>

<!-- Custom fonts for this template -->
<link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="../css/sb-admin-2.min.css" rel="stylesheet">

<!-- Custom styles for this page -->
<link href="../vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

<!-- <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.4.0.min.js"></script>
 -->
<!-- Bootstrap core JavaScript-->
<script src="../vendor/jquery/jquery.min.js"></script>
<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- 메뉴 -->
<link href="../css/profile.css" rel="stylesheet">

<style type="text/css">
#cate-css {
	width: 150px;
	height: 20px;
	background: #084B8A;
	color: #EFF5FB;
	font-size: 15px;
	text-align: center;
	border-radius: 10px;
}
</style>

</head>


<body id="page-top">

	<!-- Page Wrapper 시작 -->
	<div id="wrapper">

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">
				<jsp:include page="userHeader.jsp"></jsp:include>

				<!-- Begin Page Content -->
				<div class="container-fluid" style="width: 60%">

					<!-- DataTales Example -->
					<div class="row">
						<div class="col-xl-9 col-lg-8">
							<div class="card shadow mb-4">
								<div class="card-body">
									<c:if test="${param.postSearchText != null}">
										<h3>검색어 : ${param.postSearchText}</h3>
									</c:if>
									<c:if test="${param.tag != null}">
										<h3>태그명 : ${param.tag}</h3>
									</c:if>

									<div class="table-responsive">
										<table class="table table" id="dataTable" data-toggle="table" data-order="desc" data-sort-name="no">
											<!--  <table class="table table-bordered" data-toggle="table" data-sort-name="no" data-sort-order="desc"> -->
											<tbody>
												<c:if test="${postPage.hasNoArticles()}">
													<tr>
														<td colspan="4">게시글이 없습니다.</td>
													</tr>
												</c:if>

												<c:if test="${param.board ne '포트폴리오'}">
													<c:forEach var="post" items="${postPage.content}">
														<c:if test="${post.postVisibility==true}">
															<%-- onClick="location.href='adminRead.do?no=${post.postId}&pageNo=${postPage.currentPage}'"> --%>
															<tr onClick="location.href='adminRead.do?no=${post.postId}&pageNo=${postPage.currentPage}'" onmouseover="this.style.backgroundColor='#FAFAFA'" onmouseout="this.style.backgroundColor='#ffffff'">
																<td>
																	<div id="cate-css" style="margin-bottom: 10px;">카테고리 : ${post.cate.cateName}</div>
																	<div>
																		<h1 style="color: #2E2E2E">${post.postTitle}</h1>
																	</div>
																	<div>${post.postWriteDate}&nbsp,&nbsp${post.writer.writerNick}</div>
																</td>
																<td width="15px">
																	<img src="http://localhost:8080/BLOG/upload/${post.postImage}" onerror="this.style.display='none'" style="width: 150px; height: 150px;">
																<td width="15px"></td>
															</tr>
														</c:if>
													</c:forEach>
												</c:if>

												<c:if test="${param.board eq '포트폴리오'}">
													<c:forEach var="post" items="${postPage.content}">
														<c:if test="${post.postVisibility==true}">
															<%-- onClick="location.href='adminRead.do?no=${post.postId}&pageNo=${postPage.currentPage}'"> --%>
															<tr style="text-align: center;" onClick="location.href='adminRead.do?no=${post.postId}&pageNo=${postPage.currentPage}'" onmouseover="this.style.backgroundColor='#FAFAFA'" onmouseout="this.style.backgroundColor='#ffffff'">
																<td style="margin-left: auto; margin-right: auto;">
																	<c:if test="${post.cate.cateName eq '안드로이드'}">
																		<img src="http://localhost:8080/BLOG/upload/${post.postImage}" onerror="this.style.display='none'" style="width: 200px; height: 300px; margin-bottom: 10px;">
																	</c:if>
																	<c:if test="${post.cate.cateName eq '웹'}">
																		<img src="http://localhost:8080/BLOG/upload/${post.postImage}" onerror="this.style.display='none'" style="width: 700px; height: 300px; margin-bottom: 10px;">
																	</c:if>
																	<div id="cate-css" style="margin-bottom: 10px; margin-left: auto; margin-right: auto; background-color: #1ABC9C;">카테고리 : ${post.cate.cateName}</div>
																	<div>
																		<h3 style="color: #2E2E2E">${post.postTitle}</h3>
																	</div>
																</td>
															</tr>
														</c:if>
													</c:forEach>
												</c:if>

											</tbody>
										</table>
										<c:if test="${param.board ne '포트폴리오'}">
											<c:if test="${postPage.hasArticles()}">
												<ul class="pagination justify-content-center">
													<c:if test="${postPage.startPage > 5}">
														<li class="page-item"><a class="page-link" href="list.do?<c:if test="${param.board != null}">board=${param.board}</c:if><c:if test="${param.tag != null}">tag=${param.tag}</c:if><c:if test="${param.postSearchText != null}">postSearchText=${param.postSearchText}</c:if>&pageNo=${postPage.startPage - 5}">이전</a></li>
													</c:if>
													<c:forEach var="pNo" begin="${postPage.startPage}" end="${postPage.endPage}">
														<li class="page-item <c:if test="${postPage.currentPage eq pNo}">
                            active
                            </c:if>"><a class="page-link" href="list.do?<c:if test="${param.board != null}">board=${param.board}</c:if><c:if test="${param.tag != null}">tag=${param.tag}</c:if><c:if test="${param.postSearchText != null}">postSearchText=${param.postSearchText}</c:if>&pageNo=${pNo}">${pNo}</a></li>
													</c:forEach>
													<c:if test="${postPage.endPage < postPage.totalPages}">
														<li class="page-item"><a class="page-link" href="list.do?<c:if test="${param.board != null}">board=${param.board}</c:if><c:if test="${param.tag != null}">tag=${param.tag}</c:if><c:if test="${param.postSearchText != null}">postSearchText=${param.postSearchText}</c:if>&pageNo=${postPage.startPage + 5}">다음</a></li>
													</c:if>
												</ul>
											</c:if>
										</c:if>
									</div>
								</div>
							</div>
						</div>
						<c:if test="${param.board ne '포트폴리오'||param.board != null}">
							<div class="col-xl-3 col-lg-4">
								<div class="card shadow mb-4">
									<div class="card-body">
										<jsp:include page="userMenu.jsp"></jsp:include>
									</div>
								</div>
							</div>
						</c:if>
					</div>
				</div>

			</div>
		</div>
	</div>

	<!--  <script type="text/javascript">
  $('#dataTable td #postWriteDate').each(function() {
      var cellText = $(this).html();
      console.log(cellText);
  });
  </script> -->

	<!-- Core plugin JavaScript-->
	<script src="../vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="../js/sb-admin-2.min.js"></script>

	<!-- Page level plugins -->
	<script src="../vendor/datatables/jquery.dataTables.min.js"></script>
	<script src="../vendor/datatables/dataTables.bootstrap4.min.js"></script>

	<!-- Page level custom scripts -->
	<script src="../js/demo/datatables-demo.js"></script>

</body>
</html>