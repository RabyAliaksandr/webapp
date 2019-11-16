<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
<link rel="stylesheet" href="build.css">
<script src="bootstrap-checkbox.min.js" defer></script>

<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 16.11.2019
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:bundle basename="local" prefix="label.">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="js/bootstrap-datetimepicker.js"></script>
    <script src="js/bootstrap-datetimepicker.min.js"></script>
    <link rel="stylesheet" href="style/bootstrap-datetimepicker.css"/>
    <link rel="stylesheet" href="style/bootstrap-datetimepicker.min.css"/>
    <link rel="stylesheet" href="style/bootstrap-datetimepicker.min.css"/>
    <script src="https://snipp.ru/cdn/jquery/2.1.1/jquery.min.js"></script>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/css/bootstrap-select.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/bootstrap-select.min.js"></script>

    <!-- (Optional) Latest compiled and minified JavaScript translation files -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/i18n/defaults-*.min.js"></script>
    <html>
    <head>
        <nav class="navbar navbar-default">
            <div class="navbar-header">
                <a class="navbar-brand" href="controller?command=main_page">Trainings Center</a>
            </div>
            <ul class="nav navbar-nav">
                <li><a href="controller?command=trainings_page"><fmt:message key="currentTrainings"/></a></li>
                <li><a href="controller?command=cabinet"><fmt:message key="cabinet"/></a></li>
                <li><a href="controller?command=log_out"><fmt:message key="logout"/></a></li>
            </ul>
        </nav>
        <br/>
        <title><fmt:message key="orderPage"/></title>
    </head>
    <body>
    <jsp:useBean id="trainingService" class="com.epam.webapp.service.TrainingsService"/>
    <div class="container-fluid">
        <div class="row">
            <fmt:message key="availableConsultation"/>
            <div class="row">

                        <%--Message about saved changes--%>
                    <c:if test="${orderSentMessage != null}">
                        <div class="alert alert-danger" role="alert">
                                ${orderSentMessage}
                            <c:set var="orderSentMessage" value="${null}"/>
                        </div>
                    </c:if>
            </div>
            <form id="chooseTasksForConsultation" onsubmit="return sub()" method="post" action="controller">
                <input type="hidden" name="command" value="send_order_consultation"/>
                <input type="hidden" name="studentId" value="${user.id}"/>
            </form>

            <c:set var="checkConsultation" value="${0}"/>
            <c:set var="checkTask" value="${0}"/>
            <c:set var="checkTopick" value="${0}"/>
            <div class="form-group">
                <select class="selectpicker" name="consultationId" form="chooseTasksForConsultation" required>
                    <c:forEach var="consultation" items="${trainingService.findConsultationsForTraining(trainingId)}">
                        <option value="${consultation.key}">${consultation.value} ${consultation.key}</option>
                        <c:set var="checkConsultation" value="${1}"/>
                    </c:forEach>
                </select>
            </div>

        </div>
        <div class="col-sm-6">
            <h3><fmt:message key="completedTasks"/></h3>
            <table class="table">
                <c:set var="count" value="${0}"/>
                <tr>
                    <th>No</th>
                    <th><fmt:message key="nameTask"/></th>
                    <th></th>
                </tr>
                <tr>
                    <c:forEach var="task" items="${trainingService.findCompletedTasks(trainingId, studentId)}">
                <tr>
                    <td>${count}</td>
                    <td>${task.name}</td>
                <form id="q">

                    <td>
                        <input type="checkbox"
                               class="checkTask"
                               name="taskId"
                               value="${task.id}" form="chooseTasksForConsultation"/>
                    </td>
                </form>
                </tr>
                <c:set var="count" value="${count + 1}"/>
                <c:set var="checkTask" value="${1}"/>
                </c:forEach>
                </tr>
            </table>
        </div>
        <div class="col-sm-6">
            <h3><fmt:message key="learnedTopics"/></h3>
            <table class="table">
                <c:set var="count" value="${0}"/>
                <tr>
                    <th>No</th>
                    <th><fmt:message key="nameTopic"/></th>
                    <th></th>
                </tr>
                <tr>
                    <c:forEach var="topic" items="${trainingService.findLearnedTopics(studentId, trainingId)}">
                <tr>
                    <td>${count}</td>
                    <td>${topic.name}</td>
                    <td>
                        <input type="checkbox" name="topicId"
                               class="checkTopic"
                               value="${topic.id}" form="chooseTasksForConsultation"/>
                    </td>
                </tr>
                <c:set var="count" value="${count + 1}"/>
                <c:set var="checkTopick" value="${1}"/>
                </c:forEach>
                </tr>
            </table>

            <c:if test="${checkTask == 0}">
                <h3><fmt:message key="solveTask"/></h3>
            </c:if>
            <c:if test="${checkConsultation == 0}">
                <h3><fmt:message key="noConsultation"/> </h3>
            </c:if>
            <c:if test="${checkTopick == 0}">
                <h3><fmt:message key="learnTopic"/> </h3>
            </c:if>
            <c:if test="${checkTopick != 0 && checkConsultation != 0 && checkTask != 0}">
                <button  type="submit" class="btn-warning"
                         form="chooseTasksForConsultation">Отправить</button>
            </c:if>
        </div>

        <script>
            function sub() {
            var a = !!document.querySelector(".checkTask:checked");
            a || alert("Выберите хотя бы одну задачу");

            var b = !!document.querySelector(".checkTopic:checked");
                b || alert("Выберите хотя бы одну тему") ;
                if (a == false || b == false) {
                    return false;
                }
            return true;
            };
        </script>
    </div>
    </body>
    </html>
</fmt:bundle>