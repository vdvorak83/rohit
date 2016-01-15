<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
<link type="text/css" href="mycss.css">
</head>
<body>
	<h1>Hello world!</h1>
	${message} ${message1}
	<br />
	<h1>Hello ${pageContext.request.remoteUser}</h1>
	<spring:message code="name"></spring:message>
	<form:form modelAttribute="myperson" method="post">
		<table>
			<tr>
				<td><form:label path="name">Name : </form:label></td>
				<td><form:input path="name" /></td>
			</tr>

			<tr>
				<td><form:label path="age">Age : </form:label></td>
				<td><form:input path="age" /></td>
			</tr>


			<tr>
				<td><form:label path="salary">Salary : </form:label></td>
				<td><form:input path="salary" /></td>
			</tr>

			<tr>
				<td><form:label path="dob">Date of Birth : </form:label></td>
				<td><form:input path="dob" /></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Submit" /></td>
			</tr>
		</table>


	</form:form>

</body>
</html>
