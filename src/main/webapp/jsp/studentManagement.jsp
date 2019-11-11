<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 11.11.2019
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:bundle basename="local" prefix="label.">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <html>
    <head><title><fmt:message key="button.studentsManagement"/></title></head>
    </head>
    <body>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="controller?command=main_page">Trainings Center</a>
            </div>
            <ul class="nav navbar-nav">
                <li><a href="controller?command=trainings_page"><fmt:message key="currentTrainings"/></a></li>
                <li><a href="controller?command=cabinet"><fmt:message key="cabinet"/></a></li>
                <li><a href="controller?command=log_out"><fmt:message key="logout"/></a></li>
                <c:if test="${typeOperation == 'trainingManagement'}">
                    <li><a href="controller?command=create_text&typeOperation=createTraining"><fmt:message
                            key="create_training"/></a></li>
                </c:if>
            </ul>
        </div>
    </nav>
    <br/>
    <div class="col-xs-1">
        <div class="container">
            <jsp:useBean id="userService" class="com.epam.webapp.service.UserService"/>
            <h2><fmt:message key="button.studentsManagement"/></h2>
<%--            table stusents registred on training--%>
            <table class="table">
                <thead>
                <tr>
                    <th>No</th>
                    <th ><fmt:message key="userName"/></th>
                    <th><fmt:message key="userSurname"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="student" items="${userService.getStudentsForTraining(trainingId)}">
                    <tr>
                        <td>${count}</td>
                        <td>${student.name}</td>
                        <td>${student.surname}</td>
                    </tr>
                    <c:set var="count" value="${count + 1}" scope="page"/>
                </c:forEach>
                </tbody>
            </table>
        </div>

    </div>





    </body>
    </html>
</fmt:bundle>