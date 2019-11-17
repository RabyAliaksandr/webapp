<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<link href="${pageContext.request.contextPath}/style/main.css" rel="stylesheet">
<%@ page import="com.epam.webapp.entity.User" %>
<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 27.10.2019
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
    <fmt:bundle basename="local" prefix="label.">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="ru" scope="session" />
<jsp:useBean id="add" class="com.epam.webapp.service.impl.TrainingsServiceImpl"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <html><head><title>Trainings</title></head>
        </head>
        <body>
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="controller?command=main_page">Trainings Center</a>
                </div>
                <c:if test="${user.type != null}">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="controller?command=cabinet"><fmt:message key="cabinet"/></a></li>
                    <li><a href="controller?command=log_out"><fmt:message key="logout"/></a></li>
                </ul>
                </c:if>
            </div>
        </nav>
        <br/>

    <c:set var="idUser" value="${sessionScope.user.id}"/>
    <br/>
        <jsp:useBean id="gettrainings" class="com.epam.webapp.service.impl.TrainingsServiceImpl"/>
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
            <table class="table">
                <thead>
                <tr>
                    <th>No</th>
                    <th>Trainings name</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="training" items="${gettrainings.findAllTrainings()}">
                    <tr>
                        <td><c:out value="${count}"/></td>
                        <td>
                            <a href="controller?command=trainings_information_page&trainingId=${training.id}">
                                    ${training.name}
                            </a>
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