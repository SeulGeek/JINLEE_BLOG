<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>

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
				<div class="container-fluid"></div>
				<jsp:include page="${param.BODY_PATH}"></jsp:include>

			</div>
		</div>
	</div>
</body>
</html>