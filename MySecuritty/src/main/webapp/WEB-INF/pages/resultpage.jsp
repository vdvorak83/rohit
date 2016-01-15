<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	Result :
	<h2>${result}</h2>
	<br /> Source :
	<h2>${source}</h2>
	<br /> Role :
	<h2>${role}</h2>
	<br /> Username :
	<h2>${username}</h2>

	<br /> Admin Posts :
	<h2>${adminposts}</h2>
	<br /> Personal Posts :
	<h2>${personalposts}</h2>
	<br /> Public Posts :
	<h2>${publicposts}</h2>
</body>
</html>
