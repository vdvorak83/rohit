<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 
<h1>Logged-in Users</h1><table>
<tr>
<td width="100">Username</td>
<td width="150">Authorities</td>
<td width="170">IsAccountNonExpired</td>
<td width="190">IsCredentialsNonExpired</td>
<td width="150">IsAccountNonLocked</td>
</tr>
<c:forEach items="${users}" var="user">
<tr>
<td><c:out value="${user.username}" /></td>
<td><c:out value="${user.authorities}" /></td>
<td><c:out value="${user.accountNonExpired}" /></td>
<td><c:out value="${user.credentialsNonExpired}" /></td>
<td><c:out value="${user.accountNonLocked}" /></td>
</tr>
</c:forEach>
</table>
<p>Total users: ${total}</p>
</body>
</html>