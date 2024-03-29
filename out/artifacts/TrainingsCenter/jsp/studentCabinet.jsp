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
    <head><title>Student cabinet</title></head>
    </head>
    <body>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="controller?command=main_page">Trainings Center</a>
            </div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="controller?command=cabinet"><fmt:message key="cabinet"/></a></li>
                <li><a href="controller?command=trainings_page"><fmt:message key="currentTrainings"/></a></li>
                </li>
                <li>
                    <a href="controller?command=card_management">
                        <fmt:message key="cardManagement"/>
                    </a>
                </li>
                <li><a href="controller?command=log_out"><fmt:message key="logout"/></a></li>
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
    <jsp:useBean id="gettrainings" class="com.epam.tc.service.impl.TrainingsServiceImpl"/>
    <jsp:useBean id="getCompletedTrinings" class="com.epam.tc.service.impl.TrainingsServiceImpl"/>
    <c:set var="count" value="1"/>
    <div class="container">
            <%--        list of trainings for which the student is registered --%>
        <h1><fmt:message key="studentstrainings"/></h1>
        <table class="table">
            <thead>
            <tr>
                <th>No</th>
                <th><fmt:message key="name"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="training" items="${gettrainings.findCompletedTrainingForStudent(user.id)}">
                <c:choose>
                    <c:when test="${training.grade == 0 }">
                        <tr>
                            <td>${count}</td>
                            <td>
                                <a href="controller?command=trainings_information_page&trainingId=${training.id}">
                                        ${training.name}
                                </a>
                            </td>
                        </tr>
                        <c:set var="count" value="${count + 1}" scope="page"/>
                    </c:when>
                </c:choose>
            </c:forEach>
                <%--            if there are no completed trainings--%>
            <c:if test="${count == 1 }">
                <tr>
                    <td>
                        <fmt:message key="noEnrolledTraining"/>
                    </td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>

        <%--    if there are no trainings for which is registered--%>
    <div class="container">
        <c:set var="count" value="1"/>
        <h1><fmt:message key="completedTraining"/></h1>
            <%--        list of completed trainings--%>
        <table class="table">
            <thead>
            <tr>
                <th>No</th>
                <th><fmt:message key="name"/></th>
                <th><fmt:message key="assessment"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="training" items="${gettrainings.findCompletedTrainingForStudent(user.id)}">
                <c:choose>
                    <c:when test="${training.grade > 0 }">
                        <tr>
                            <td>${count}"</td>
                            <td>
                                <a href="controller?command=trainings_information_page&trainingId=${training.id}">
                                        ${training.name}
                                </a>
                            </td>
                            <td>${training.grade}</td>
                            <td><a href="controller?command=create_text&typeOperation=giveFeedback&trainingId=${trainingId}">
                                <fmt:message key="giveFeedBack"/>
                            </a> </td>
                        </tr>
                        <c:set var="count" value="${count + 1}" scope="page"/>
                    </c:when>
                </c:choose>
            </c:forEach>
                <%--            if there are no completed trainings--%>
            <c:if test="${count == 1 }">
                <tr>
                    <td>
                        <fmt:message key="noCompletedTraining"/>
                    </td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
    <br/>
    <div>
        <h5 align="center">
            <c:import url="footer.jsp"/>
        </h5>
    </div>
    </body>
    </html>
</fmt:bundle>