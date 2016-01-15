<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
	function sendajaxrequest() {
		$.ajax({
			url : '../getajaxrequest',
			type : 'get',
			data : {
				name : 'Rohit'
			},
			success : function(response) {
				$('#lbl1').html(response);
			}
		});
	}
</script>
</head>
<body>
	<P>
		Message is <br /> ${message}
	</P>
	<div>

		<input type="button" value="Click here to Send Ajax Request"
			onclick="sendajaxrequest();" /> <label id="lbl1"></label>
	</div>
</body>
</html>
