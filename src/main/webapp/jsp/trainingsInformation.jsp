<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 29.10.2019
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:bundle basename="local" prefix="label.">
    <html>
    <head>
        <title>Information about Training</title>
        <c:set var="trainingId" value="${sessionScope.trainingId}"/>
        <c:import url="mainButtons.jsp"/>
        <br/>
        <jsp:useBean id="trainingService" class="com.epam.webapp.service.TrainingsService"/>
        <fmt:message key="informationAboutTraining"/>
        <br/>
        <br/>
        <table border="3">
            <tr>
                <td>
                    <c:set var="training" value="${trainingService.getTrainingByIdTraining(trainingId)}"
                           scope="session"/>
                    <c:out value="${training.information}"/>
                </td>
            </tr>
        </table>
        <br/>
        <jsp:useBean id="check" class="com.epam.webapp.service.UserService"/>
        <c:if test="${sessionScope.user.type == 'student'}">
            <c:choose>
                <c:when test="${check.checkEnrolled(user.id, trainingId) == false}">
                    <form name="addTrainingToStudent" method="POST" action="controller">
                        <input type="hidden" name="command" value="add_training_to_student"/>
                        <input type="hidden" name="userId" value="${user.id}"/>
                        <input type="hidden" name="trainingId" value="${trainingId}"/>
                        <input type="submit" value=<fmt:message key="addTraining"></fmt:message>/>
                    </form>
                </c:when>
                <c:when test="${check.checkEnrolled(user.id, trainingId) == true}">
                    <fmt:message key="enrolledTraining"/>
                </c:when>
            </c:choose>
        </c:if>
        <c:choose>
            <c:when test="${sessionScope.user.type == 'mentor'}">
                <fmt:message key="trainedStudents"/>
                <table border="3">
                    <c:forEach var="student" items="${trainingService.getStudentsByIdTraining(trainingId)}">
                        <tr>
                            <td>
                                <c:out value="${student.name}"/>
                            </td>
                            <td>
                                <c:out value="${student.surname}"/>
                            </td>
                            <td>
                                <form name="GradeForTraining" method="post" action="controller">
                                    <input type="hidden" name="command" value="grade"/>
                                    <input type="number" name="grade" value="" min="1" max="10"/>
                                    <input type="hidden" name="trainingId" value="${trainingId}"/>
                                    <input type="hidden" name="userId" value="${student.id}"/>
                                    <input type="submit" value=<fmt:message key="grade"/>>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
        </c:choose>
        <br/>
        <fmt:message key="listTopics"/>
        <br/>
        <table border="1">
            <c:set var="count" value="1"/>
            <c:forEach var="mapTopics" items="${trainingService.getTopicsForTraining(trainingId)}">
                <tr>
                    <td>${count}</td>
                    <td><a href="controller?command=topic_page&trainingId=${trainingId}&topicName=${mapTopics.key}">${mapTopics.key}</a></td>
                </tr>
                <c:set var="count" value="${count + 1}"/>
            </c:forEach>
        </table>
        <a href="${pageContext.session.servletContext.contextPath}/controller?command=delete_offer&id_offer=${currentOffer.key.idOffer}">dfghj</a>
    </head>
    <body>
    </body>
    </html>
</fmt:bundle>