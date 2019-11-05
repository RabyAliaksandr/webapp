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
        <jsp:useBean id="service" class="com.epam.webapp.service.TrainingsService"/>
        <fmt:message key="informationAboutTraining"/>
        <br/>
        <table border="3">
            <tr>
                <td>
                    <c:set var="training" value="${service.getTrainingByIdTraining(trainingId)}" scope="session"/>
                    <c:out value="${training.information}"/>
                </td>
            </tr>
        </table>
        <br/>
        <c:choose>
            <c:when test="${sessionScope.user.type == 'student'}">
                <form name="addTrainingToStudent" method="POST" action="controller">
                    <input type="hidden" name="command" value="add_training_to_student"/>
                    <input type="hidden" name="userId" value="${user.id}"/>
                    <input type="hidden" name="trainingId" value="${trainingId}"/>
                    <c:out value="${user.id}  it is here"/>
                    <c:out value="${trainingId} it is trainingid"/>
                    <input type="submit" value=<fmt:message key="addTraining"></fmt:message>>
                </form>            </c:when>
        </c:choose>
        <c:choose>
            <c:when test="${sessionScope.user.type == 'mentor'}">
                <fmt:message key="trainedStudents"/>
                <jsp:useBean id="studentList" class="com.epam.webapp.service.TrainingsService"/>
                <table border="3">
                    <c:forEach var="student" items="${studentList.getStudentsByIdTraining(trainingId)}">
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
                                    <input type="text" name="grade" value=""/>
                                    <input type="hidden" name="trainingId" value="${trainingId}"/>
                                    <input type="hidden" name="userId" value="${student.id}"/>
                                    <c:out value="${trainingId}"/>
                                    <input type="submit" value=<fmt:message key="grade"></fmt:message>/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
        </c:choose>
    </head>
    <body>

    </body>
    </html>
</fmt:bundle>