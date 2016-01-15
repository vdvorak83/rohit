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
<title>Add/Edit Record</title>
</head>
<body>
	<form:form commandName="person" action="/save" method="post">
		<table>
			<tr>
				<td><label>First Name : </label></td>
				<td><form:hidden path="id" /> <form:input path="firstname" /></td>
			</tr>
			<tr>
				<td><label>Last Name : </label></td>
				<td><form:input path="lastname" /></td>
			</tr>
			<tr>
				<td><label>Age : </label></td>
				<td><form:input path="age" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Save"></input></td>
			</tr>
		</table>
	</form:form>
</body>
</html>