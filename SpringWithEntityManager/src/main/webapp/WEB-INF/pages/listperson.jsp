<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Test</title>
</head>
<body>
	<a href="${pageContext.request.contextPath}/add">Click here to add
		new record</a>
	<h1>All Records</h1>
	<div>
		<c:choose>
			<c:when test="${fn:length(personlist)>0}">
				<table id="records">
					<thead>
						<tr>
							<th>Edit</th>
							<th>Delete</th>
							<th>ID</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Age</th>
						</tr>

						<c:forEach items="${personlist}" var="p">
							<tr>
								<td><a href="<c:url value="/edit/${p.id}"/>">EDIT</a></td>
								<td><a href="<c:url value="/delete/${p.id}"/>">DELETE</a></td>
								<td><c:out value="${p.id}"></c:out></td>
								<td><c:out value="${p.firstname}"></c:out></td>
								<td><c:out value="${p.lastname}"></c:out></td>
								<td><c:out value="${p.age}"></c:out></td>
							</tr>
						</c:forEach>
					</thead>
				</table>
			</c:when>
			<c:otherwise>
				<label>Sorry! No Record To Display</label>
			</c:otherwise>
		</c:choose>
	</div>

</body>

</html>