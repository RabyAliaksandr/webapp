<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 27.10.2019
  Time: 22:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="ru" scope="session"/>
<jsp:useBean id="trainingService" class="com.epam.webapp.service.TrainingsService"/>
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
                <li><a href="controller?command=log_out"><fmt:message key="logout"/></a></li>
                <li><a href="controller?command=consultation_for_mentor"><fmt:message key="consultationManagement"/></a></li>
            </ul>
        </div>
    </nav>
    <br/>
    <c:set var="user" value="${sessionScope.user}"/>
    <c:set var="count" value="1"/>
        <div class="container-fluid">
            <div class="jumbotron">
                <fmt:message key="trainingManagement"/>
            </div>

            <div class="table-bordered">
                <table class="table-striped">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col"><fmt:message key="name"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="training" items="${trainingService.getTrainingForMentor(user.id)}">
                        <tr>
                            <th scope="row">${count}</th>
                            <td>
                                    <%--                           <a href="controller?command=trainings_information_page&trainingId=${training.id}">--%>
                                    <%--                                   ${training.name}--%>
                                    <%--                           </a>--%>
                                <a href="controller?command=trainings_information_page&trainingId=${training.id}&editor=true">
                                        ${training.name}
                                </a>
                            </td>
                        </tr>
                        <c:set var="count" value="${count + 1}"/>
                    </c:forEach>
                    </tbody>

                </table>
            </div>
            <%--        <table border="2">--%>
            <%--            <c:forEach var="training" items="${trainingService.getTrainingForMentor(user.id)}">--%>
            <%--            <tr>--%>
            <%--                <td>--%>
            <%--                    <c:out value="${count}"/>--%>
            <%--                </td>--%>
            <%--                <td>--%>
            <%--                    <a href="controller?command=trainings_information_page&trainingId=${training.id}&editor=true">--%>
            <%--                            ${training.name}--%>
            <%--                    </a>--%>
            <%--                </td>--%>
            <%--                <c:set var="count" value="${count + 1}"/>--%>
            <%--                </c:forEach>--%>
            <%--            </tr>--%>
            <%--        </table>--%>
    </div>
    <c:import url="footer.jsp"/></body>
    </html>
</fmt:bundle>