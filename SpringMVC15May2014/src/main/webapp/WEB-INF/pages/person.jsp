<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	trimDirectiveWhitespaces="true" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" language="javascript">
	function sendrequest() {
		try {
			$.ajax({
				url : 'addperson',
				type : 'post',
				//dataType : "json",
				data : JSON.stringify({
					"fname" : "Rohit"
				}),
				contentType : "application/json",
				success : function(response) {
					alert(response.fname);
				}
			});
		} catch (ex) {
			alert(ex);
		}
	}
</script>

</head>
<body>

	<form:form modelAttribute="person" id="form1">
		<table>
			<tr>
				<td><label><spring:message code="person.fname"></spring:message>
				</label></td>
				<td><form:input path="fname" /> <label><form:errors
							path="fname"></form:errors> </label></td>
			</tr>
			<tr>
				<td><label><spring:message code="person.lname"></spring:message>
				</label></td>
				<td><form:input path="lname" /></td>
			</tr>
			<tr>
				<td><label><spring:message code="person.age"></spring:message>
				</label></td>
				<td><form:input path="age" /></td>
			</tr>
			<tr>
				<td><label><spring:message code="person.salary"></spring:message>
				</label></td>
				<td><form:input path="salary" /></td>
			</tr>
			<tr>
				<td><label><spring:message code="person.dob"></spring:message>
				</label></td>
				<td><form:input path="dob" /> <label><form:errors
							path="dob"></form:errors> </label></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit" />&nbsp;<input
					type="button" value="Submit Ajax" onclick="sendrequest();" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>
