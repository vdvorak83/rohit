<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
	function sendajaxrequest() {
		$.ajax({
			url : 'jsonhome.json',
			type : 'get',
			contentType : 'application/json',
			// 			data : {
			// 				name : 'Rohit'
			// 			},
			success : function(response) {
				alert(response);
			}
		});
	}
</script>

</head>
<body>

	<h1>Hello world!</h1>
	${message} ${message1}
	<br />
	<h1>Hello ${pageContext.request.remoteUser}</h1>
	<link rel="stylesheet" href="<spring:theme  />" type=" text/css" />
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
	<input type="button" value="Send Ajax Command"
		onclick="sendajaxrequest();" />
	<form action="createuser" method="get">
		<input type="submit" value="Create User" />
	</form>

	<span>------------------ERRORS
		DETAIL------------------------------</span>
	<spring:bind path="myperson">
		<c:forEach var="error" items="${status.errorMessages}"
			varStatus="status">
        ${error}
        <c:if test="${!status.last}">,</c:if>
		</c:forEach>
	</spring:bind>

</body>
</html>
