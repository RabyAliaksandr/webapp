<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="ru" scope="session"/>
<fmt:bundle basename="local" prefix="label.">
    <html>
    <head>
        <title>Student Page</title>
    </head>
    <body>
    <fmt:message key="hello" var="hello"/>
    <c:set var="user" value="${sessionScope.user}"/>
    <c:out value=" ${hello} ${user.surname} ${user.name}"/>
    <br/>
    <jsp:useBean id="gettrainings" class="com.epam.webapp.service.TrainingsService"/>
    <jsp:useBean id="getCompletedTrinings" class="com.epam.webapp.service.TrainingsService"/>
    <c:set var="count" value="1" scope="page"/>
    <c:import url="mainButtons.jsp"/>

    <table border="1">
        <fmt:message key="studentstrainings"/>
        <c:forEach var="training" items="${gettrainings.getCompletedTrainingForStudent(user.id)}">
            <c:choose>
                <c:when test="${training.grade == 0 }">
                    <tr>
                        <td><c:out value="${count}"></c:out></td>
                        <td><c:out value="${training.name}"/></td>
                    </tr>
                    <c:set var="count" value="${count + 1}" scope="page"/>
                </c:when>
            </c:choose>
        </c:forEach>
        <c:if test="${count == 1 }">
            <tr>
                <td>
                    <fmt:message key="noEnrolledTraining"/>
                </td>
            </tr>
        </c:if>
    </table>
    <br/>
    <table border="1"/>
    <fmt:message key="completedTraining"/>
    <c:set var="count" value="1" scope="page"/>
    <c:forEach var="training" items="${getCompletedTrinings.getCompletedTrainingForStudent(user.id)}">
        <c:choose>
            <c:when test="${training.grade > 0}">
                <tr>
                    <td><c:out value="${count}"></c:out></td>
                    <td><c:out value="${training.name}"/></td>
                    <td><c:out value="${training.grade}"/></td>
                </tr>
                <c:set var="count" value="${count + 1}" scope="page"/>
            </c:when>
        </c:choose>
    </c:forEach>
    <c:if test="${count == 1 }">
        <tr>
            <td>
                <fmt:message key="noCompletedTraining"/>
            </td>
        </tr>
    </c:if>
    </table>
    <br/>
    <c:import url="footer.jsp"/></body>
    </html>
</fmt:bundle>