<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false"%>
<html>
    <head>
        <title>Home</title>
        <link rel="stylesheet" href="<spring:theme code="css"/>" type="text/css" />
    </head>
    <body>
        <form action="" id="form1">
            <p>
                Select other theme: <select name="theme" onchange="form1.submit();">
                    <option value="default">default</option>
                    <option value="black">black</option>
                    <option value="blue">blue</option>
                    <option value="green">green</option>
                </select> <br />
        </form>
        <div class="mystyle">A Simple Div</div>
    </body>
</html>
