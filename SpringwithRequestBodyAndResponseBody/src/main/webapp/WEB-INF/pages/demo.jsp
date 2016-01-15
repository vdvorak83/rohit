<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
    <head>
        <title>Demo</title>
        <script
        src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script type="text/javascript" language="javascript">
            function sendrequest() {
                try {
                    $.ajax({
                        url : 'SpringwithRequestBodyAndResponseBody/submitform',
                        type : 'post',
                        data : JSON.stringify({
                            "id" : $('#id').val(),
                            "fname" : $('#fname').val(),
                            "lname" : $('#lname').val()
                        }),
                        contentType : "application/json",
                        success : function(response) {
                            $('#statuscode').html(response.statuscode);
                            $('#message').html(response.message);
                            $('#description').html(response.description);
                        },
                        failure : function(response) {
                            $('#statuscode').html(response.statuscode);
                            $('#message').html(response.message);
                            $('#description').html(response.description);
                        }
                    });
                } catch (ex) {
                    alert(ex);
                }
            }
        </script>

    </head>
    <body>
        <h1>Please Fill This Form</h1>
        <form method="post" id="form1" action="submitform">
            <div>
                <label>Id</label><input id="id" name="id" />
            </div>
            <div>
                <label>First Name</label><input id="fname" name="fname" />
            </div>
            <div>
                <label>Last Name</label><input id="lname" name="lname" />
            </div>
            <input type="button" value="Submit this form using jquery ajax"
                   onclick="sendrequest();" /> <br />
            <div>
                Status Code : <label id="statuscode"></label><br /> Message : <label
                    id="message"></label><br /> Description : <label id="description"></label>
            </div>
        </form>
    </body>
</html>