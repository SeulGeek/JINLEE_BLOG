<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>JINLEE</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

<!-- Custom styles for this template-->
<link href="css/sb-admin-2.min.css" rel="stylesheet">


</head>
<body>
	<jsp:include page="adminHeader.jsp" />

	<%-- <div style="width: 400px; margin: 0 auto; padding-top: 50px">
		<form action="adminLogin.do" method="post">
			<div class="form-group">
				<label for="email">이메일</label> <input type="email" class="form-control" id="email" name="email" placeholder="Enter email" value="${param.email}">
				<small id="emailHelp" class="form-text text-muted"><c:if test="${errors.email}">이메일을 입력하세요.</c:if></small>
			</div>
			<div class="form-group">
				<label for="password">비밀번호</label> <input type="password" class="form-control" id="password" name="password" placeholder="Password" value="${param.password}">
				<small id="emailHelp" class="form-text text-muted"><c:if test="${errors.password}">암호를 입력하세요.</c:if></small>
				<small id="emailHelp" class="form-text text-muted"><c:if test="${errors.emailOrPwNotMatch}">이메일과 암호가 일치하지 않습니다.</c:if></small>
			</div>
			<input class="btn btn-primary btn-lg btn-block" type="submit" value="로그인">
		</form>
	</div>
 --%>
	<div class="container">

		<!-- Outer Row -->
		<div class="row justify-content-center">

			<div class="col-lg-6 col-lg-6 col-lg-6">

				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="card-body p-0">
						<!-- Nested Row within Card Body -->
						<div class="row">
							<div class="col-lg-12">
								<div class="p-5">
									<div class="text-center">
										<h1 class="h4 text-gray-900 mb-4">로그인</h1>
									</div>
									<form class="user" action="adminLogin.do" method="post">
										<div class="form-group">
											<input type="email" class="form-control form-control-user" id="email" name="email" placeholder="Enter Email" value="${param.email}">
												<c:if test="${errors.email}"><small id="emailHelp" class="form-text text-muted">이메일을 입력하세요.</small></c:if>
										</div>
										<div class="form-group">
											<input type="password" class="form-control form-control-user" id="password" name="password" placeholder="Password" value="${param.password}">
												<c:if test="${errors.password}"><small id="emailHelp" class="form-text text-muted">암호를 입력하세요.</small></c:if>
												<c:if test="${errors.emailOrPwNotMatch}"><small id="emailHelp" class="form-text text-muted">이메일과 암호가 일치하지 않습니다.</small></c:if>
										</div>
										<input class="btn btn-primary btn-user btn-block" type="submit" value="로그인">
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>

		</div>

	</div>


	<!-- Bootstrap core JavaScript-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="js/sb-admin-2.min.js"></script>
</body>
</html>