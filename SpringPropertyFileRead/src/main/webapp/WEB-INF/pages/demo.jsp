<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Demo Page</title>
</head>
<body>
	<h1>Actual use of property file is to read and use data in
		configuration. I am displaying data on this view just for demo
		purpose.</h1>
	<br />
	<h1>These messages are from property files</h1>

	<span>URL:</span>
	<span>${url}</span>
	<br />
	<span>User name:</span>
	<span>${username}</span>
</body>
</html>
