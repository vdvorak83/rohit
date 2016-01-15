<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Spring With Session Listener Example</title>
</head>
<body>
	<P>Total No of sessions:</P>${noofsession}
	<br />
	<a href="destroysession">Destroy this session.</a>
</body>
</html>
