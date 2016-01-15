<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Login Failure Page</title>
</head>
<body>
	<h1>Login Failure Page</h1>
	<P>Sorry!! Username or password was not correct.</P>
	<br />
	<p>User name :</p>
	<p>${param.username}</p>
	<br />
	<p>Error detail is :</p>
	<p>${param.errordetail}</p>
	<br />
	<p>
		Please <a href="login">Click Here</a> to Re-Login
	</p>
</body>
</html>
