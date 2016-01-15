<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Enter person's last name and click on submit button</title>
</head>
<body>
	<c:choose>
		<c:when test="${empty person.lname}">
			<h1>Enter person's last name and click on submit button</h1>
			<form:form modelAttribute="person" method="post">
				<label>Enter Last name</label>
				<form:input path="lname" />
				<br />
				<input type="submit" value="Save" />
			</form:form>
		</c:when>
		<c:otherwise>
			<label>Person's detail is as follow:</label>
			<br />
			<label>Id : </label>
			<label>${person.id} </label>

			<br />
			<label>First Name : </label>
			<label>${person.fname} </label>

			<br />
			<label>Last Name : </label>
			<label>${person.lname} </label>


		</c:otherwise>
	</c:choose>
</body>
</html>
