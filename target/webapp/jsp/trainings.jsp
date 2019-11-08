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
<jsp:useBean id="add" class="com.epam.webapp.service.TrainingsService"/>
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
                </ul>
                </c:if>
            </div>
        </nav>
        <br/>

    <span>${sessionScope.user.surname} ${sessionScope.user.name}</span>
    <c:set var="idUser" value="${sessionScope.user.id}"/>
    <br/>
    <jsp:useBean id="gettrainings" class="com.epam.webapp.service.TrainingsService"/>
    <fmt:message key="currentTrainings"/>
    <c:set var="count" value="1"/>
    <table border="3">
    <c:forEach var="training" items="${gettrainings.allTrainings}">
        <tr>
        <td> <c:out value="${count}"/> </td>
            <td>
            <a href="controller?command=trainings_information_page&trainingId=${training.id}">${training.name}</a>
            </td>
<%--    <c:out value="${alltrainings.name}"/> </td>--%>
    </tr>
        <c:set var="count" value="${count + 1}" scope="page"/>
    </c:forEach>
    </table>
</body>
</html>
</fmt:bundle>




<%--    <%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--    <fmt:bundle basename="local" prefix="label.">--%>

<%--        <html>--%>
<%--        <head>--%>
<%--            <title>Information about Training</title>--%>



<%--                &lt;%&ndash;    <% int id = Integer.parseInt(request.getParameter("trainingId"));    %>&ndash;%&gt;--%>
<%--            <c:set var="trainingId" value="${requestScope.trainingid}" />--%>
<%--            <c:import url="mainButtons.jsp"/>--%>
<%--            <c:out value="${requestScope.trainingid}"/>--%>

<%--            <br/>--%>
<%--            <jsp:useBean id="service" class="com.epam.webapp.service.TrainingsService"/>--%>
<%--            <fmt:message key="informationAboutTraining"/>--%>

<%--            <br/>--%>
<%--            <c:out value="${trainingid}"/>--%>
<%--            <table border="3">--%>
<%--                <tr>--%>
<%--                    <td>--%>

<%--                        <c:set var="count" value="0" />--%>
<%--                        <c:set var="training" value="${service.getTrainingByIdTraining(trainingid)}" scope="session"/>--%>
<%--                        <c:out value="${training.information}"/>--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--            </table>--%>
<%--            <br/>--%>
<%--            <fmt:message key="trainedStudents"/>--%>
<%--            <jsp:useBean id="stedentList" class="com.epam.webapp.service.TrainingsService"/>--%>
<%--            <table border="3">--%>
<%--                <c:forEach var="student" items="${stedentList.getStudentsByIdTraining(trainingId)}">--%>
<%--                    <tr>--%>
<%--                        <td>--%>
<%--                            <c:out value="${student.name}"/>--%>
<%--                        </td>--%>
<%--                        <td>--%>
<%--                            <c:out value="${student.surname}"/>--%>
<%--                        </td>--%>
<%--                        <td>--%>
<%--                            <c:out value="${student.grade}"/>--%>
<%--                            <form name="alltrainings" method="post" action="controller">--%>
<%--                                <input type="hidden" name="command" value="trainings"/>--%>
<%--                                <input type="text" value=""/>--%>
<%--                                <input type="submit" value=<fmt:message key="rate"></fmt:message>/>--%>
<%--                            </form>--%>
<%--                        </td>--%>
<%--                    </tr>--%>
<%--                </c:forEach>--%>
<%--            </table>--%>
<%--        </head>--%>
<%--        <body>--%>

<%--        </body>--%>
<%--        </html>--%>
<%--    </fmt:bundle>--%>