<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Person List</title>
</head>
<body>
	<h1>Person List</h1>

	<table>
		<tr>
			<th>id</th>
			<th>first name</th>
			<th>last name</th>
		</tr>
		<c:forEach items="${personlist}" var="item">
			<tr>
				<td>${item.id}</td>
				<td>${item.fname}</td>
				<td>${item.lname}</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<a href="personlistintxtfile">Export this file in notepad format</a>
	</div>
</body>
</html>