<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Demo Page</title>
</head>
<body>
	<c:if test="${not empty name}">
		<p>Hello Mr. ${name}</p>
	</c:if>
	<form method="post">
		<label>Please enter your name:</label> <input type="text"
			name="personname" id="personname" /> <input type="submit" />
	</form>
</body>
</html>