<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet"
	href='<c:url value="../css/styles.css"></c:url>'></link>
<title>Insert title here</title>
</head>
<body>
	<span style="float: right"> <a href="?l	ang=en">en</a> | <a
		href="?lang=de">de</a>
	</span> ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}


	<form action='<c:url value="j_spring_security_check"/>' method="post">
		<table>
			<tr>
				<td>
					<%-- 				<spring:message code="username"></spring:message> --%>
					Enter Username Please:
				</td>
				<td><input type="text" id="j_username" name="j_username"></input></td>
			</tr>
			<tr>
				<td>
					<%-- 				<spring:message code="password"></spring:message> --%>
					Enter Password Please:
				</td>
				<td><input type="text" id="j_password" name="j_password"></input></td>
			</tr>


			<tr>
				<td>Remember Me:</td>
				<td><input type="checkbox" id="_spring_security_remember_me"
					name="_spring_security_remember_me"></input></td>
			</tr>
			<td colspan="2"><input type="submit" /></td>
			</tr>
		</table>
	</form>
</body>
</html>