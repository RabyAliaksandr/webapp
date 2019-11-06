<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 25.10.2019
  Time: 0:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form name="registration" method="POST" action="controller">
    <input type="hidden" name="command" value="registration"/>

    First Name:<br/>
    <input type="text" name="name" value="" required maxlength="50"/><br/>
    <br/>
    Last Name:<br/>
    <input type="text" name="surname" value="" required/><br/>
    <br/>
    Email:
    <input type="email" name="email" value="" required maxlength="50" /><br/>
    <br/>
    Login:
    <input type="number" name="login" value="" min="1" max="10"/><br/>
    <br/>
    Password:
    <input type="password" name="password" value=""  pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$"/><br/>
<br/>
    Type:
    <input type="text" name="type" value="student"/>
<br/>
    <br/>
    <input type="submit" value="Sign Up"/>
    <br/>
    ${errorLoginPassMessage}
    <br/>
    ${wrongAction}
    <br/>
    ${nullPage}
    <br/>

</form>
</form>
<c:import url="mainButtons.jsp"/>
</body>
</html>
