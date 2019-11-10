<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 09.11.2019
  Time: 22:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:bundle basename="local" prefix="label.">
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
    <div class="row">
        <div class="col-xs-1">
            <jsp:useBean id="gettrainings" class="com.epam.webapp.service.TrainingsService"/>
            <c:set var="count" value="1"/>
            <div class="container">
                <h2><fmt:message key="currentTrainings"/></h2>
                <table class="table">
                    <thead>
                    <tr>
                        <th>No</th>
                        <th><fmt:message key="name"/> </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="training" items="${gettrainings.allTrainings}">
                        <tr>
                            <td>${count}</td>
                            <td>
                                <a href="controller?command=trainings_information_page&trainingId=${training.id}&editor=true">
                                        ${training.name}
                                </a>
                            </td>
                        </tr>
                        <c:set var="count" value="${count + 1}" scope="page"/>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

        </div>
    </div>
    <fmt:bundle basename="local" prefix="footer.">
        <fmt:message key="copyright"/>
    </fmt:bundle>
    </body>
    </html>
</fmt:bundle>
