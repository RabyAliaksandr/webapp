<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 22.10.2019
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <li><a href="controller?command=trainings_page"><fmt:message key="currentTrainings"/></a></li>
        </ul>
    </div>
</nav>
<br/>
    <fmt:bundle basename="local" prefix="footer.">
        <fmt:message key="copyright"/>
    </fmt:bundle>
    </body></html>
</fmt:bundle>

