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

</head>


<body id="page-top">

	<!-- Page Wrapper 시작 -->
	<div id="wrapper">

		<jsp:include page="adminMenu.jsp"></jsp:include>

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">
				<jsp:include page="adminHeader.jsp"></jsp:include>

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">게시판 관리</h6>
						</div>
						<div class="card-body">
							<a class="btn btn-warning" href="adminRegister.do" style="float: right; margin-bottom: 10px;">글쓰기</a>
							<div class="table-responsive">
								<table class="table table-bordered" id="dataTable" data-toggle="table" data-order="desc" data-sort-name="no">
									<!--  <table class="table table-bordered" data-toggle="table" data-sort-name="no" data-sort-order="desc"> -->

									<thead>
										<tr>
											<th data-field="no" data-sortable="true" width="5%">no</th>
											<th data-field="board" width="10%">게시판 명</th>
											<th data-field="cate" width="10%">카테고리 명</th>
											<th data-field="title" width="40%">제목</th>
											<th data-field="date" width="15%">작성 날짜</th>
											<th data-field="etc" width="20%">수정/삭제</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${postPage.hasNoArticles()}">
											<tr>
												<td colspan="4">게시글이 없습니다.</td>
											</tr>
										</c:if>
										<c:forEach var="post" items="${postPage.content}">
											<tr>
												<td>${post.postId}</td>
												<td>${post.board.boardName}</td>
												<td>${post.cate.cateName}</td>
												<td>
													<a href="adminRead.do?no=${post.postId}&pageNo=${postPage.currentPage}">
														<c:out value="${post.postTitle}" />
														<c:if test="${post.postVisibility==false}">
															<img src="../img/48px-Locked.svg.png" style="width: 20px; height: 20px;">
														</c:if>
													</a>
												</td>
												<td>
													<span id="postWriteDate">${post.postWriteDate}</span>
												</td>
												<td>
													<a class="btn btn-primary" href="adminModify.do?no=${post.postId}&board=${post.board.boardId}&cate=${post.cate.cateId}">수정</a>
													<a class="btn btn-danger" href="adminDelete.do?no=${post.postId}" onClick="return deleteConfirm()">삭제</a>
												</td>
											</tr>
										</c:forEach>

									</tbody>
								</table>
								<c:if test="${postPage.hasArticles()}">
									<%-- <tr>
										<td colspan="4">
											<c:if test="${postPage.startPage > 5}">
												<a href="adminList.do?pageNo=${postPage.startPage - 5}">[이전]</a>
											</c:if>
											<c:forEach var="pNo" begin="${postPage.startPage}" end="${postPage.endPage}">
												<a href="adminList.do?pageNo=${pNo}">[${pNo}]</a>
											</c:forEach>
											<c:if test="${postPage.endPage < postPage.totalPages}">
												<a href="adminList.do?pageNo=${postPage.startPage + 5}">[다음]</a>
											</c:if>
										</td>
									</tr> --%>
									<ul class="pagination justify-content-center">
										<c:if test="${postPage.startPage > 5}">
											<li class="page-item"><a class="page-link" href="adminList.do?pageNo=${postPage.startPage - 5}">이전</a></li>
										</c:if>
										<c:forEach var="pNo" begin="${postPage.startPage}" end="${postPage.endPage}">
											<li class="page-item <c:if test="${postPage.currentPage eq pNo}">
                            active
                            </c:if>"><a class="page-link" href="adminList.do?pageNo=${pNo}">${pNo}</a></li>
										</c:forEach>
										<c:if test="${postPage.endPage < postPage.totalPages}">
											<li class="page-item"><a class="page-link" href="adminList.do?pageNo=${postPage.startPage + 5}">다음</a></li>
										</c:if>
									</ul>
								</c:if>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

	<!-- 삭제버튼 클릭 이벤트 -->
	<script>
		function deleteConfirm() {
			var message = confirm("정말 삭제하시겠습니까?");
			if (message == true) {
				return true;
			} else
				return false;
		}
	</script>

	<!-- 	<script type="text/javascript">
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

