<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.webapp.entity.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %><%--&lt;%&ndash;--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<fmt:bundle basename="local" prefix="label.">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <html><head><title>Mentor page</title></head>
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
<div class="container-fluid">
    <form name="loginForm" method="POST" action="controller">
        <input type="hidden" name="command" value="log_in" />
        Login:<br/>
        <input type="text" name="login" value=""/>
        <br/>Password:<br/>
        <input type="password" name="password" value="" />
        <br/>
            ${errorLoginPassMessage}
        <br/>
            ${wrongAction}
        <br/>
            ${nullPage}
        <br/>
        <input type="submit" value="Log in"/>
        <br/>
    </form><hr/>
    <form name="redirectToRegister" method="POST" action="controller">
        <input type="hidden" name="command" value="registration_page" />
        <input type="submit" value="Sign Up"/>
        <br/>
    </form><hr/>
</div>
</body></html>
</fmt:bundle>