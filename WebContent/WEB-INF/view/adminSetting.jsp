<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>JINLEE</title>
<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
<!-- Custom styles for this template-->
<link href="css/sb-admin-2.min.css" rel="stylesheet">
<link href="css/profile.css" rel="stylesheet">

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
							<h6 class="m-0 font-weight-bold text-primary">개인정보수정</h6>
						</div>
						<div class="card-body">
							<!-- 프로필 사진 미리보기 -->
							<div style="text-align: center;">
								<div style="margin-left: 150px; margin-bottom: 30px; width: 180px; height: 180px;">
									<c:if test="${!empty adminSetting.adminProfileUrl}">
										<img id="loadImg" src="http://localhost:8080/BLOG/upload/${adminSetting.adminProfileUrl}" class="photo-circle">
									</c:if>
									<c:if test="${empty adminSetting.adminProfileUrl}">
										<img id="loadImg" class="no-photo-circle">
									</c:if>
								</div>
							</div>
							<form class="form-horizontal" action="adminSettingModify.do" method="post" enctype="multipart/form-data">
								<input type="hidden" name="adminId" id="adminId" value="${authUser.id}">
								<table class="table table" id="dataTable" style="width: 60%; height: 100px;">
									<tbody>
										<!-- 이름 -->
										<tr>
											<td width="30%">이름</td>
											<td>${authUser.name}</td>
										</tr>
										<!-- 깃 허브 주소 -->
										<tr>
											<td>깃 허브 주소</td>
											<td>
												<input name="adminGithub" id="adminGithub" value="${adminSetting.adminGithub}">
											</td>
										</tr>
										<!-- 네이버 주소 -->
										<tr>
											<td>네이버 주소</td>
											<td>
												<input name="adminNaver" id="adminNaver" value="${adminSetting.adminNaver}">
											</td>
										</tr>
										<!-- 구글 주소 -->
										<tr>
											<td>구글 주소</td>
											<td>
												<input name="adminGoogle" id="adminGoogle" value="${adminSetting.adminGoogle}">
											</td>
										</tr>
										<!-- 프로필 사진 -->
										<tr>
											<td>프로필 사진</td>
											<td>
												<input type="file" onchange="previewFile()" accept="image/gif, image/jpeg, image/png" id="adminProfileUrl" name="adminProfileUrl" value="${adminSetting.adminProfileUrl}">
											</td>
										</tr>
									</tbody>
								</table>
								<div class="col-sm-offset-2 col-sm-10">
									<input type="submit" class="btn btn-warning" value="수정">
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 프로필 사진 미리보기 -->
	<script type="text/javascript">
		function previewFile() {
			var preview = document.querySelector('img');
			var file = document.querySelector('input[type=file]').files[0];
			var reader = new FileReader();

			reader.addEventListener("load", function() {
				preview.src = reader.result;
			}, false);

			if (file) {
				reader.readAsDataURL(file);
			}
		}
	</script>

	<!-- Bootstrap core JavaScript-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="js/sb-admin-2.min.js"></script>

	<!-- Page level plugins -->
	<script src="vendor/datatables/jquery.dataTables.min.js"></script>
	<script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

	<!-- Page level custom scripts -->
	<script src="js/demo/datatables-demo.js"></script>
</body>
</html>