<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 06.11.2019
  Time: 23:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:bundle basename="local" prefix="label.">
    <jsp:useBean id="trainingService" class="com.epam.webapp.service.TrainingsService"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <html>
    <head><title>Mentor page</title></head>
    </head>
    <body>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="controller?command=main_page">Trainings Center</a>
            </div>
            <ul class="nav navbar-nav">
                <li><a href="controller?command=trainings_page"><fmt:message key="currentTrainings"/></a></li>
                <c:if test="${user.type != null}">
                    <li class="active"><a href="controller?command=cabinet"><fmt:message key="cabinet"/></a></li>
                    <li><a href="controller?command=log_out"><fmt:message key="logout"/></a></li>
                </c:if>
                <c:if test="${user.type == 'admin'}">
                    <li><a href="controller?command=create_text&typeOperation=editTopic&trainingId=${trainingId}&
                            topicName=${topicName}"><fmt:message key="button.editDescription"/></a></li>
                </c:if>
            </ul>
        </div>
    </nav>
    <br/>
    <div class="container-fluid">
            <c:if test="${changesSavedMessage != null}">
                <div class="alert alert-danger" role="alert">
                        ${changesSavedMessage}
                    <c:set var="changesSavedMessage" value="${null}"/>
                </div>
            </c:if>
        <h1>${topicName}</h1>
        <br/>
        <p> ${trainingService.getTopic(trainingId, topicName)}</p>
    </div>
    </body>
    </html>
</fmt:bundle>