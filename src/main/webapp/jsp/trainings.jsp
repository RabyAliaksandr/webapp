<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page import="com.epam.webapp.entity.User" %>
<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 27.10.2019
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="ru" scope="session" />
<fmt:bundle basename="local" prefix = "label." >
<jsp:useBean id="add" class="com.epam.webapp.service.TrainingsService"/>
<html>
<head>
    <title>Trainings</title>
</head>
<body>

    <span>${sessionScope.user.surname} ${sessionScope.user.name}</span>
    <c:set var="idUser" value="${sessionScope.user.id}"/>
    <br/>
    <jsp:useBean id="gettrainings" class="com.epam.webapp.service.TrainingsService"/>
    <c:import url="mainButtons.jsp"/>
    <fmt:message key="currenttrainings"/>
    <c:set var="count" value="1"/>

    <table border="3">
    <c:forEach var="alltrainings" items="${gettrainings.allTrainings}">
        <tr>
        <td> <c:out value="${count}"/> </td>
    <td> <c:out value="${alltrainings.name}"/> </td>
    </tr>
        <c:set var="count" value="${count + 1}" scope="page"/>
    </c:forEach>
    </table>
</body>
</html>
</fmt:bundle>