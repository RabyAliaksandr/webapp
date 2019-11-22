<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 27.10.2019
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:bundle basename="local" prefix="label.">
    <jsp:useBean id="add" class="com.epam.tc.service.impl.TrainingsServiceImpl"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <html>
    <head><title>Trainings</title></head>
    </head>
    <body>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="controller?command=main_page">
                    Trainings Center
                </a>
            </div>
            <c:if test="${user.type != null}">
            <ul class="nav navbar-nav">
                <li>
                    <a href="controller?command=cabinet">
                        <fmt:message key="cabinet"/>
                    </a>
                </li>
                <li>
                    <a href="controller?command=log_out">
                        <fmt:message key="logout"/>
                    </a>
                </li>
                </c:if>
            </ul>

            <form id="xxx" method="post" action="controller">
                        <input type="hidden" name="command" value="set_local"/>
                        <input type="hidden" name="redirectTo" value="true"/>
                    <button form="xxx" name="local" value="${local == 'en' ? 'ru' : 'en'}"
                            class="btn-link" type="submit">
                            ${local == 'en' ? 'Ru' : 'En'}
                    </button>
                    </form>
        </div>
    </nav>

    <br/>
    <c:set var="idUser" value="${sessionScope.user.id}"/>
    <br/>
    <jsp:useBean id="trainingService" class="com.epam.tc.service.impl.TrainingsServiceImpl"/>
    <c:set var="count" value="1"/>
    <div class="container">
        <h2><fmt:message key="currentTrainings"/></h2>
            <%--            message about edit changes --%>
        <c:if test="${changesSavedMessage != null}">
            <div class="alert alert-danger" role="alert">
                    ${changesSavedMessage}
                <c:set var="changesSavedMessage" value="${null}"/>
            </div>
        </c:if>
        <c:if test="${deleteTraining != null}">
            <div class="alert alert-danger" role="alert">
                <label><font color="red">${deleteTraining}</font> </label>
                <c:set var="changesSavedMessage" value="${null}"/>
            </div>
        </c:if>
        <table class="table">
            <thead>
            <tr>
                <th>No</th>
                <th>Trainings name</th>
                <th>Status</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="training" items="${trainingService.findAllTrainings()}">
                <tr>
                    <td><c:out value="${count}"/></td>
                    <td>
                        <a href="controller?command=trainings_information_page&trainingId=${training.id}">
                                ${training.name}
                        </a>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${training.status ==  true}">
                                <fmt:message key="open"/>
                            </c:when>
                            <c:when test="${training.status == false}">
                                <fmt:message key="close"/>
                            </c:when>
                        </c:choose>
                    </td>
                        <%--    <c:out value="${alltrainings.name}"/> </td>--%>
                </tr>
                <c:set var="count" value="${count + 1}" scope="page"/>
            </c:forEach>
            </tbody>
        </table>
    </div>
    </body>
    </html>
</fmt:bundle>