<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page import="com.epam.webapp.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 27.10.2019
  Time: 22:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="ru" scope="session"/>
<jsp:useBean id="data" class="com.epam.webapp.service.TrainingsService"/>
<fmt:bundle basename="local" prefix="label.">
    <html>
    <head>
        <title>Mentor Page</title>
    </head>
    <body>
    <c:set var="user" value="${sessionScope.user}"/>

    <c:import url="main.jsp"/>

    <c:set var="count" value="1"/>
    <table border="2">

        <c:forEach var="allStudents" items="${data.getTrainingForMentor(user.id)}">
        <tr>
            <td>
                <c:out value="${count}"/>
            </td>
            <td>
                <form name="informationAboutTraining" method="post" action="controller" >
                    <c:set var="trainingid" value="${allStudents.id}" scope="request"/>
                    <input type="hidden" name="command" value="trainingpageformentor"/>
                    <input type="hidden" name="trainingid" value="${allStudents.id}"/>
                    <input type="submit" value="сделатьНЕкнопкой"/>
                </form>
<%-- это не работает почему не знаю. --%>
<%--               ё <a href="jsp/trainingformentor.jsp?trainingid=${allStudents.id}"><c:out value="${allStudents.name}"/></a>--%>
            </td>
            <c:set var="count" value="${count + 1}"/>
            </c:forEach>
        </tr>
    </table>
    <c:import url="footer.jsp"/></body>
    </html>
</fmt:bundle>