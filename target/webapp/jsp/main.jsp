<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
Created by IntelliJ IDEA.
User: alex
Date: 22.10.2019
Time: 18:24
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:bundle basename="local" prefix="label.">
<html><head><title>Welcome</title></head>
<body>
<form name="toLogInPage" method="post" action="controller">
    <input type="hidden" name="command" value="log_in_page"/>
    <input type="submit" value=<fmt:message key="login"/> >
</form>
<form name="toSignUpPage" method="post" action="controller">
    <input type="hidden" name="command" value="registration_page"/>
    <input type="submit" value=<fmt:message key="signUp"/> >
</form>
<form name="toSignUpPage" method="post" action="controller">
    <input type="hidden" name="command" value="trainings_information_page"/>
    <input type="submit" value=<fmt:message key="currentTrainings"/> >
</form>
<br/>
<hr/>
Here will be information about this resource
<hr/>
</body></html>
</fmt:bundle>