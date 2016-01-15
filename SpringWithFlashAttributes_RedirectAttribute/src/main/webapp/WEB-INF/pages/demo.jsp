<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Demo</title>
</head>
<body>
	<h1>Person List</h1>
	<c:choose>
		<c:when test="${empty personlist}">
			<h2>No Person Data Available</h2>
			<br />
			<a href="list">Click here to get personlist</a>
		</c:when>
		<c:otherwise>
			<table>
				<tr>
					<th>id</th>
					<th>Fname</th>
					<th>Lname</th>
				</tr>
				<c:forEach items="${personlist}" var="item">
					<tr>
						<td>${item.id}</td>
						<td>${item.fname}</td>
						<td>${item.lname}</td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>

</body>
</html>
