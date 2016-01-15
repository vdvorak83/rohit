<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Not Logged in</title>
</head>
<body>
	<P>
		Please <a href="login">login</a> to access this page <br /> Error
		Detail is:${requestScope.SPRING_SECURITY_LAST_EXCEPTION}
	</P>
</body>
</html>
