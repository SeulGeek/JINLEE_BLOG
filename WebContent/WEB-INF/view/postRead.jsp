<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>JINLEE</title>
<!-- Custom fonts for this template-->
<link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
<!-- Custom styles for this template-->
<link href="../css/sb-admin-2.min.css" rel="stylesheet">
<style type="text/css">
#cate-css, #tag-css {
	width: 110px;
	height: 15px;
	background: #084B8A;
	color: #EFF5FB;
	font-size: 11px;
	text-align: center;
	border-radius: 10px;
}
</style>
</head>


<body id="page-top">

	<!-- Page Wrapper 시작 -->
	<div id="wrapper">

		<u:isLogin>
			<jsp:include page="adminMenu.jsp"></jsp:include>
		</u:isLogin>
		<!-- Content Wrapper -->
		<div id="content-wrapper"  class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">
				<u:isLogin>
					<jsp:include page="adminHeader.jsp"></jsp:include>
				</u:isLogin>
				<u:notLogin>
					<jsp:include page="userHeader.jsp"></jsp:include>
				</u:notLogin>
				<!-- Begin Page Content -->
				<div class="container-fluid" <u:notLogin>style="width:60%;"</u:notLogin><u:isLogin>style="width:100%;"</u:isLogin>div>

					<div class="card shadow mb-4">
						<u:isLogin>
							<div class="card-header py-3">
								<h6 class="m-0 font-weight-bold text-primary">게시판 관리>게시글 상세보기</h6>
							</div>
						</u:isLogin>
						<div class="card-body">
							<!-- 카테고리 -->
							<div class="form-group">
								<div class="col-sm-3" id="cate-css">카테고리 : ${postData.post.cate.cateName}</div>
							</div>
							
							<!-- 제목 -->
							<div class="form-group">
								<div class="col-sm-5">
									<h1 style="color: #2E2E2E">${postData.post.postTitle}</h1>
								</div>
							</div>
							<!-- 작성일시, 작성자 -->
							<div class="form-group">
								<div class="col-sm-3">
									<p>${postData.post.postWriteDate}&nbsp;,&nbsp;${postData.post.writer.writerNick}</p>
								</div>
							</div>
							<hr>
							
							<!-- 내용 -->
							<div class="form-group">
								<div class="col-sm-10">${postData.post.postContent}</div>
							</div>

							<!-- 태그 -->
							<div class="form-group">
								태그 &nbsp;<c:forEach var="tag" items="${tag}">
									<span id="tag-css" style="background:#F4DD07">
										<a href="list.do?tag=${tag.tagName}">${tag.tagName}</a>
									</span>&nbsp;
								</c:forEach>
							</div>

							

							<div id="disqus_thread"></div>
							<script>
								/**
								 *  RECOMMENDED CONFIGURATION VARIABLES: EDIT AND UNCOMMENT THE SECTION BELOW TO INSERT DYNAMIC VALUES FROM YOUR PLATFORM OR CMS.
								 *  LEARN WHY DEFINING THESE VARIABLES IS IMPORTANT: https://disqus.com/admin/universalcode/#configuration-variables*/
								/*
								 var disqus_config = function () {
								 this.page.url = PAGE_URL;  // Replace PAGE_URL with your page's canonical URL variable
								 this.page.identifier = PAGEIdENTIFIER; // Replace PAGEIdENTIFIER with your page's unique identifier variable
								 };
								 */
								(function() { // DON'T EDIT BELOW THIS LINE
									var d = document, s = d
											.createElement('script');
									s.src = 'https://jinlee-blog-1.disqus.com/embed.js';
									s.setAttribute('data-timestamp',
											+new Date());
									(d.head || d.body).appendChild(s);
								})();
							</script>
							<noscript>
								Please enable JavaScript to view the
								<a href="https://disqus.com/?ref_noscript">comments powered by Disqus.</a>
							</noscript>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Bootstrap core JavaScript-->
	<script src="../vendor/jquery/jquery.min.js"></script>
	<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

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