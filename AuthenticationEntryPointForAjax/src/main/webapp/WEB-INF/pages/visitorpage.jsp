<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Visitor Page</title>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript">
	function sendajaxcommand() {
		$.ajax({
			url : 'getpersonname',
			type : 'get',
			success : function(response) {
				$('#lblresponse').html(response);
			},
			failure : function(response) {
				$('#lblresponse').html(response);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				$('#lblresponse').html(jqXHR.responseText);
			}
		});
	}
</script>
</head>
<body>
	<P>Please Click on this button to get person name</P>
	<br />
	<input type="button" onclick="sendajaxcommand();" value="Click Here" />
	<br />
	<label>Response :</label>
	<label id="lblresponse"></label>
</body>
</html>
