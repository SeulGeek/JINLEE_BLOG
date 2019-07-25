<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<%-- <%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%> --%>

<!DOCTYPE html>
<html>
<head>
<!-- <title><tiles:getAsString name="title" /></title> -->
<!-- Custom fonts for this template -->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/sb-admin-2.min.css" rel="stylesheet">

<!-- Custom styles for this page -->
<link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

<!-- <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.4.0.min.js"></script>
 -->
<!-- Bootstrap core JavaScript-->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</head>


<body id="page-top">

	<!-- Page Wrapper 시작 -->
	<div id="wrapper">

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">
				<!-- 헤더 -->
				<!-- <tiles:insertAttribute name="header" /> -->

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- DataTales Example -->
					<div class="row">
						<!-- 내용 -->
						<!-- <tiles:insertAttribute name="body" /> -->
						<jsp:include page="post/list.do"></jsp:include>

						<div class="col-xl-2 col-lg-4">
							<div class="card shadow mb-4">
								<div class="card-body">
									<!-- 우측 메뉴 -->
									<!-- <tiles:insertAttribute name="menu" /> -->
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>

