<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 27.10.2019
  Time: 22:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.local}" scope="session"/>
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
                <a class="navbar-brand" href="controller?command=main_page">
                    Trainings Center
                </a>
            </div>
            <ul class="nav navbar-nav">
                <li>
                    <a href="controller?command=trainings_page">
                        <fmt:message key="currentTrainings"/>
                    </a>
                </li>
                <li>
                    <a href="controller?command=consultation_for_mentor">
                        <fmt:message key="consultationManagement"/>
                    </a>
                </li>
                <li>
                    <a href="controller?command=log_out">
                        <fmt:message key="logout"/>
                    </a>
                </li>
            </ul>
            <form id="xxx" method="post" action="controller">
                <input type="hidden" name="command" value="set_local_cabinet"/>
                <button form="xxx" name="local" value="${local == 'en' ? 'ru' : 'en'}"
                        class="btn-link" type="submit">
                        ${local == 'en' ? 'Ru' : 'En'}
                </button>
            </form>
        </div>
    </nav>
    <br/>
    <c:set var="user" value="${sessionScope.user}"/>
    <c:set var="count" value="1"/>
    <div class="container-fluid">
            <fmt:message key="trainingManagement"/>

        <table class="table">
            <thead>
            <tr>
                <th>No</th>
                <th><fmt:message key="name"/></th>
            </tr>
            </thead>
            <jsp:useBean id="trainingService"
                         class="com.epam.webapp.service.impl.TrainingsServiceImpl"/>
            <tbody>
            <c:forEach var="training"
                       items="${trainingService.findTrainingForMentor(user.id)}">
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
                <%--            if there are no completed trainings--%>
            <c:if test="${count == 1 }">
                <tr>
                    <td>
                        <fmt:message key="noMentoringTraining"/>
                    </td>
                </tr>
            </c:if>
            </tbody>
        </table>





<%--        <div class="table-bordered">--%>
<%--            <table class="table-striped">--%>
<%--                <thead>--%>
<%--                <tr>--%>
<%--                    <th scope="col">#</th>--%>
<%--                    <th scope="col"><fmt:message key="name"/></th>--%>
<%--                </tr>--%>
<%--                </thead>--%>
<%--                <tbody>--%>
<%--                <c:forEach var="training" items="${trainingService.findTrainingForMentor(user.id)}">--%>
<%--                    <tr>--%>
<%--                        <th scope="row">${count}</th>--%>
<%--                        <td>--%>
<%--                                &lt;%&ndash;                           <a href="controller?command=trainings_information_page&trainingId=${training.id}">&ndash;%&gt;--%>
<%--                                &lt;%&ndash;                                   ${training.name}&ndash;%&gt;--%>
<%--                                &lt;%&ndash;                           </a>&ndash;%&gt;--%>
<%--                            <a href="controller?command=trainings_information_page&--%>
<%--                                trainingId=${training.id}&editor=true">--%>
<%--                                    ${training.name}--%>
<%--                            </a>--%>
<%--                        </td>--%>
<%--                    </tr>--%>
<%--                    <c:set var="count" value="${count + 1}"/>--%>
<%--                </c:forEach>--%>
<%--                </tbody>--%>

<%--            </table>--%>
        </div>
    </div>
    <c:import url="footer.jsp"/></body>
    </html>
</fmt:bundle>