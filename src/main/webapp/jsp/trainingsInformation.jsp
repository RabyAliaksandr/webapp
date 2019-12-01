<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 29.10.2019
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:bundle basename="local" prefix="label.">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8"
            src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>

    <html>
    <head><title><fmt:message key="informationAboutTraining"/></title></head>
    </head>
    <body>
    <c:set var="trainingId" value="${trainingId}"/>
    <jsp:useBean id="trainingService" class="com.epam.tc.service.impl.TrainingsServiceImpl"/>
    <jsp:useBean id="userService" class="com.epam.tc.service.impl.UserServiceImpl"/>
    <c:set var="training" value="${trainingService.findTrainingByIdTraining(trainingId)}" scope="session"/>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="controller?command=main_page">
                    Trainings Center
                </a>
            </div>
            <ul class="nav navbar-nav">
                    <%--                link cabinet only registred users--%>
                <c:if test="${user.type != null}">
                    <li class="active">
                        <a href="controller?command=cabinet">
                            <fmt:message key="cabinet"/>
                        </a>
                    </li>
                </c:if>
                <li>
                    <a href="controller?command=trainings_page">
                        <fmt:message key="currentTrainings"/>
                    </a>
                </li>
                    <%--    if mark editor is true then user can edit training--%>
                <c:if test="${editor == true}">
                    <li>
                        <a href="controller?command=create_text&typeOperation=edit&trainingId=${trainingId}">
                            <fmt:message key="button.editDescription"/>
                        </a>
                    </li>
                    <li>
                        <a href="controller?command=create_text&typeOperation=addTopic&trainingId=${trainingId}">
                            <fmt:message key="button.addTopic"/>
                        </a>
                    </li>
                    <li>
                        <a href="controller?command=create_text&typeOperation=addTask&trainingId=${trainingId}">
                            <fmt:message key="button.addTask"/>
                        </a>
                    </li>
                    <c:if test="${user.type == 'ADMIN'}">
                        <li>
                            <a href="controller?command=close_reception&trainingId=${trainingId}">
                                <fmt:message key="closeReception"/>
                            </a>
                        </li>
                        <li>
                            <a href="controller?command=delete_training&trainingId=${trainingId}">
                                <fmt:message key="deleteTraining"/>
                            </a>
                        </li>
                    </c:if>
                </c:if>
                    <%--                    if user is mentor then he can manage students--%>
                <c:if test="${user.type == 'MENTOR' && user.status == 'UNBLOCKED'}">
                    <li>
                        <a href="controller?command=student_management&trainingId=${trainingId}">
                            <fmt:message key="button.studentsManagement"/>
                        </a>
                    </li>
                </c:if>
                <c:if test="${user.type == 'STUDENT' && user.status == 'UNBLOCKED'}">
                    <li>
                        <a href="controller?command=order_consultation&trainingId=${trainingId}&studentId=${user.id}">
                            <fmt:message key="orderConsultation"/>
                        </a>
                    </li>
                </c:if>
                <c:if test="${user.type != null}">
                    <li>
                        <a href="controller?command=log_out">
                            <fmt:message key="logout"/>
                        </a>
                    </li>
                </c:if>
            </ul>
            <form id="xxx" method="post" action="controller">
                <input type="hidden" name="command" value="set_local"/>
                <input type="hidden" name="redirectTo" value="true"/>
                <button form="xxx" name="local" value="${local == 'en' ? 'ru' : 'en'}"
                        class="btn-link" type="submit">
                        ${local == 'en' ? 'Ru' : 'En'}
                </button>
            </form>
        </div>
    </nav>
    <br/>
    <title><fmt:message key="informationAboutTraining"/></title>
    <br/>
    <br/>
    <br/>
    <div class="container-fluid">
        <c:if test="${messageCloseReception != null}">
            <div class="alert alert-danger" role="alert">
                    ${messageCloseReception}
                <c:set var="changesSavedMessage" value="${null}"/>
            </div>
        </c:if>

        <c:if test="${messageFeedback != null}">
            <div class="alert alert-danger" role="alert">
                    ${messageFeedback}
                <c:set var="messageFeedback" value="${null}"/>
            </div>
        </c:if>

            <%--            message about edit changes --%>


        <c:if test="${changesSavedMessage != null}">
            <div class="alert alert-danger" role="alert">
                    ${changesSavedMessage}
                <c:set var="changesSavedMessage" value="${null}"/>
            </div>
        </c:if>
        <section class="a">
            <div class="container-fluid">
                <h1><fmt:message key="informationAboutTraining"/></h1>
                <br/>
                    ${training.information}
                <br/>
                <c:if test="${training.status == false}">
                    <label><font color="red"><fmt:message key="receptionIsClose"/></font></label>
                </c:if>
                <br/>
                <c:if test="${sessionScope.user.type == 'STUDENT' && training.status == true && user.status == 'UNBLOCKED'}">
                    <c:if test="${userService.checkEnrolled(user.id, trainingId) == false}">
                        <form name="addTrainingToStudent" method="POST" action="controller">
                            <input type="hidden" name="redirectTo" value="true"/>
                            <input type="hidden" name="command" value="add_training_to_student"/>
                            <input type="hidden" name="userId" value="${user.id}"/>
                            <input type="hidden" name="trainingId" value="${trainingId}"/>
                            <input type="submit" value=<fmt:message key="addTraining"></fmt:message>/>
                        </form>
                    </c:if>
                    <c:if test="${userService.checkEnrolled(user.id, trainingId) == true}">
                        <fmt:message key="enrolledTraining"/>
                    </c:if>
                </c:if>
            </div>
        </section>
        <br/>
    </div>
    <div class="col-lg-6 col-md-6">

        <div class="container-fluid">
            <c:set var="checkMark" value="${trainingService.checkTrainingStatusForStudent(user.id, trainingId)}"/>
            <jsp:useBean id="topicService" class="com.epam.tc.service.impl.TopicServiceImpl"/>
                <%--        if student on training or admin or mentor--%>
            <c:if test="${checkMark == true && user.status == 'UNBLOCKED' ||
                user.type == 'ADMIN' && user.status == 'UNBLOCKED' ||
                user.type == 'MENTOR' && user.status == 'UNBLOCKED'}">
                <h3>
                    <fmt:message key="listTopics"/>
                </h3>
                <br/>
                <%--table topics for training--%>
                <table class="table" id="topics">
                    <thead>
                    <tr>
                        <th>No</th>
                        <th><fmt:message key="topicsName"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="count" value="1"/>
                    <c:forEach var="topic" items="${topicService.findTopicsForTraining(trainingId)}">
                        <tr>
                            <td>${count}</td>
                            <td>
                                <a href="controller?command=topic_page&trainingId=${trainingId}&topicId=${topic.id}">
                                        ${topic.name}
                                </a>
                            </td>
                        </tr>
                        <c:set var="count" value="${count + 1}"/>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
    </div>
    </div>
        <%--        if student on training or admin or mentor--%>
    <c:if test="${checkMark == true && user.status == 'UNBLOCKED'||
        user.type == 'ADMIN' && user.status == 'UNBLOCKED' ||
         user.type == 'MENTOR' && user.status == 'UNBLOCKED'}">
    <div class="col-lg-6 col-md-6">
        <div class="container-fluid">
            <h3>
                <fmt:message key="listTask"/>
            </h3>
            <br/>
                <%--table taks for training--%>

                <%--                    <div class="form-group"> 	<!--		Show Numbers Of Rows 		-->--%>

            <table class="table" id="tasks">
                <thead>
                <tr>
                    <th>No</th>
                    <th><fmt:message key="tasksName"/></th>
                </tr>
                </thead>
                <tbody>
                <c:set var="count" value="1"/>
                <jsp:useBean id="taskService" class="com.epam.tc.service.impl.TaskServiceImpl"/>
                <c:forEach var="task" items="${taskService.findTasksListForTraining(trainingId)}">
                    <tr>
                        <td>${count}</td>
                        <td>
                            <a href="controller?command=task_page&trainingId=${trainingId}&taskId=${task.id}">
                                    ${task.name}
                            </a>
                        </td>
                    </tr>
                    <c:set var="count" value="${count + 1}"/>
                </c:forEach>
                </tbody>
            </table>

            </c:if>
        </div>
    </div>
    <c:set var="markDoneMessage" value="${null}"/>
    </body>
    <script>
        $(document).ready(function () {
            $('table').DataTable({
                "sDom": '<"top"i>rt<"bottom"lp><"clear">',
                "info": false
            });
        });
    </script>
    </html>
</fmt:bundle>


