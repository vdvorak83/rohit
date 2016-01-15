<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Access Denied</title>
</head>
<body>
	<P>
		Please <a href="login">go to login page</a> and re-login using admin
		credentials. <br />Only admin has authority to view this page <br />
		Error Detail is:${requestScope.SPRING_SECURITY_403_EXCEPTION}
	</P>
</body>
</html>
