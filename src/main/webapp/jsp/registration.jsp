<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 25.10.2019
  Time: 0:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<html><head><title>Registration</title></head>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="controller?command=main_page">Trainings Center</a>
        </div>
        <ul class="nav navbar-nav">
        </ul>
    </div>
</nav>
<br/>
<form name="registration" method="POST" action="controller">
    <input type="hidden" name="redirectTo" value="true"/>
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
</body>
</html>
