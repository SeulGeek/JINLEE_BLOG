<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>JINLEE</title>
</head>
<body>
  <c:if test="${success==true}">
    <script>
      alert("등록되었습니다.");
      location = "adminList.do"
    </script>
  </c:if>
  <c:if test="${success==false}">
    <script>
      alert("등록하지 못했습니다.");
      location = "adminList.do"
    </script>
  </c:if>
</body>
</html>