
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<%-- <%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%> --%>

<!DOCTYPE html PUBLIC>
<html>
<head>
<title>JINLEE</title>
</head>
<body>
	<u:isLogin>
		<script>
			location = "adminTopList.do"
		</script>
	</u:isLogin>
	<u:notLogin>
		<script>
			/* location = "home.do" */
			/* location = "post/list.do" */
			/* location = "userLayout.do" */
			location = "post/list.do?board=개발노트"
		</script>
	</u:notLogin>
	<%-- <tiles:insert definition="post/list" flush="true" /> --%>
	<%-- <tiles:insertDefinition name="userLayout"/> --%>
</body>
</html>