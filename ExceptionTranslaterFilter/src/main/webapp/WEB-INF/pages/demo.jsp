<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>A Demo page</title>

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript">
	function protectedpageusingajax() {
		$.ajax({
			url : 'protectedpage',
			type : 'get',
			success : function(response) {
				alert(response);
			},
			failure : function(response) {
				alert(response);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert(jqXHR.responseText);
			}
		});
	}
</script>

</head>
<body>

	<P>You arer successfully logged in, Your role is "ROLE_USER"</P>
	<p>
		Please <a href="protectedpage">click here</a> to access a page, which
		needs to "ROLE_ADMIN" role
	</p>
	<p>
		OR<br /> <a href="#" onclick="protectedpageusingajax()">click
			here</a> to access a page, which needs to "ROLE_ADMIN" role using ajax
		request
	</p>
</body>
</html>
