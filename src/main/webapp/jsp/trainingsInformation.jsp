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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <html>
    <head><title>Information</title></head>
    </head>
    <body>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="controller?command=main_page">Trainings Center</a>
            </div>
            <ul class="nav navbar-nav">
                <c:if test="${user.type != null}">
                    <li class="active"><a href="controller?command=cabinet"><fmt:message key="cabinet"/></a></li>
                </c:if>
                <li><a href="controller?command=trainings_page"><fmt:message key="currentTrainings"/></a></li>
                <c:if test="${user.type == 'mentor'}">
                    <li><a href="controller?command=create_text"> Add describe </a></li>
                    <li><a href="controller?command=create_text"> Add task </a></li>
                </c:if>
            </ul>
        </div>
    </nav>
    <br/>
    <title>Information about Training</title>

    <c:set var="trainingId" value="${sessionScope.trainingId}"/>
    <br/>
    <jsp:useBean id="trainingService" class="com.epam.webapp.service.TrainingsService"/>
    <c:set var="training" value="${trainingService.getTrainingByIdTraining(trainingId)}" scope="session"/>
    <br/>
    <br/>
    <jsp:useBean id="check" class="com.epam.webapp.service.UserService"/>

    <section class="a">
        <div class="container-fluid">
            <fmt:message key="informationAboutTraining"/>
            <br/>

            <c:out value="${training.information}"/>

        <br/>
            <br/>
        <c:if test="${sessionScope.user.type == 'student'}">
               <c:if test="${check.checkEnrolled(user.id, trainingId) == false}">
                <form name="addTrainingToStudent" method="POST" action="controller">
                    <input type="hidden" name="command" value="add_training_to_student"/>
                    <input type="hidden" name="userId" value="${user.id}"/>
                    <input type="hidden" name="trainingId" value="${trainingId}"/>
                    <input type="submit" value=<fmt:message key="addTraining"></fmt:message>/>
                </form>
               </c:if>
                <c:if test="${check.checkEnrolled(user.id, trainingId) == true}">
                    <fmt:message key="enrolledTraining"/>
                </c:if>
        </c:if>
        </div>
    </section>

    <br/>

<%--    <c:choose>--%>
<%--        <c:when test="${sessionScope.user.type == 'mentor'}">--%>
<%--            <fmt:message key="trainedStudents"/>--%>
<%--            <table border="3">--%>
<%--                <c:forEach var="student" items="${trainingService.getStudentsByIdTraining(trainingId)}">--%>
<%--                    <tr>--%>
<%--                        <td>--%>
<%--                            <c:out value="${student.name}"/>--%>
<%--                        </td>--%>
<%--                        <td>--%>
<%--                            <c:out value="${student.surname}"/>--%>
<%--                        </td>--%>
<%--                        <td>--%>
<%--                            <form name="GradeForTraining" method="post" action="controller">--%>
<%--                                <input type="hidden" name="command" value="grade"/>--%>
<%--                                <input type="number" name="grade" value="" min="1" max="10"/>--%>
<%--                                <input type="hidden" name="trainingId" value="${trainingId}"/>--%>
<%--                                <input type="hidden" name="userId" value="${student.id}"/>--%>
<%--                                <input type="submit" value=<fmt:message key="grade"/>>--%>
<%--                            </form>--%>
<%--                        </td>--%>
<%--                    </tr>--%>
<%--                </c:forEach>--%>
<%--            </table>--%>
<%--        </c:when>--%>
<%--    </c:choose>--%>
    <br/>

<section class="b">
    <div class="col-lg-6 col-md-6">
        <div class="container-fluid">
            <fmt:message key="listTopics"/>
            <br/>
            <table border="1">
                <c:set var="count" value="1"/>
                <c:forEach var="mapTopics" items="${trainingService.getTopicsForTraining(trainingId)}">
                    <tr>
                        <td>${count}</td>
                        <td>
                            <a href="controller?command=topic_page&trainingId=${trainingId}&topicName=${mapTopics.key}">${mapTopics.key}</a>
                        </td>
                    </tr>
                    <c:set var="count" value="${count + 1}"/>
                </c:forEach>
            </table>
        </div>
    </div>
    <div class="col-lg-6 col-md-6">
        <div class="container-fluid">
            <table border="1">
                <tr>
                    <td>
                Here will be tasks list
                    </td>
                </tr>
            </table>
        </div>
    </div>
</section>
    </head>
    </body>
    </html>
</fmt:bundle>