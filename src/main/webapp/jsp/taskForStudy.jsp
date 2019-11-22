<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 08.11.2019
  Time: 23:29
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:bundle basename="local" prefix="label.">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
    <link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css"/>
    <html>
    <head>
        <title><fmt:message key="taskForStudy"/></title>
    </head>
    <body>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="controller?command=main_page">
                    Trainings Center
                </a>
            </div>
            <ul class="nav navbar-nav">
                <li>
                    <a href="controller?command=trainings_page">
                        <fmt:message key="currentTrainings"/>
                    </a>
                </li>
                <c:if test="${user.type != null}">
                    <li class="active">
                        <a href="controller?command=cabinet">
                            <fmt:message key="cabinet"/>
                        </a>
                    </li>
                </c:if>
                    <%--                is user is admin or mentor, then he can edit topic and task--%>
                <c:if test="${(user.type == 'ADMIN' && editor == true && user.status == 'UNBLOCKED') ||
                 (user.type == 'MENTOR' && editor == true && user.status == 'UNBLOCKED')}">
                    <li>
                        <a href="controller?command=create_text&typeOperation=editTask&taskId=${taskId}">
                            <fmt:message key="button.editDescription"/>
                        </a>
                    </li>
                    <li>
                        <a href="controller?command=delete_task&taskId=${taskId}">
                            <fmt:message key="delete"/>
                        </a>
                    </li>
                </c:if>
                <li>
                    <a href="controller?command=log_out">
                        <fmt:message key="logout"/>
                    </a>
                </li>
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
    <jsp:useBean id="taskService" class="com.epam.tc.service.impl.TaskServiceImpl"/>
    <c:if test="${changesSavedMessage != null}">
        <div class="alert alert-danger" role="alert">
                ${changesSavedMessage}
            <c:set var="changesSavedMessage" value="${null}"/>
        </div>
    </c:if>
    <div class="container-fluid">
        <c:set var="task" value="${taskService.findTask(taskId)}"/>
        <h1>${task.name}</h1>
        <br/>
        <p>${task.task}</p>
    </div>
    <c:if test="${user.type == 'STUDENT'}">
        <%--                    message about sent task solution--%>
        <c:if test="${sendSolutionMessage != null}">
            <div class="alert alert-danger" role="alert">
                <fmt:bundle basename="local" prefix="message.">
                    <fmt:message key="solutionSend"/>
                </fmt:bundle>
            </div>
        </c:if>
        <%--check task status--%>
        <c:set var="checkMark" value="${taskService.checkTaskStatus(user.id, taskId)}"/>
        <%--            students form for send solution if check is false --%>
        <c:set var="task" value="${taskService.findTaskSolution(user.id, taskId)}"/>
        <c:set value="${task.answer}" var="solution"/>
        <c:set var="mark" value="${task.mark}"/>
        <c:choose>
            <c:when test="${mark == 0 && solution == null}">
                <form id="sendSolution" method="post" action="controller">
                    <input type="hidden" name="redirectTo" value="true"/>
                    <input type="hidden" name="command" value="send_solution"/>
                    <input type="hidden" name="taskId" value="${taskId}"/>
                    <input type="hidden" name="userId" value="${user.id}"/>
                    <textarea id="editor" form="sendSolution" name="solution" maxlength="10000" required>
            </textarea>
                    <button type="submit" class="btn btn-danger btn-lg btn-block" style="width: 95%;margin: 5px auto;">
                        <fmt:message key="send"/>
                    </button>
                </form>
                <script type="text/javascript">
                    $(document).ready(function () {
                        $("#editor").editor({
                            uiLibrary: 'bootstrap'
                        });
                    });
                </script>
            </c:when>
            <%--            if student sent already soltion then message about it--%>
            <c:when test="${mark > 0 && sendSolutionMessage == null}">
                <div class="alert alert-danger" role="alert">
                    <fmt:bundle basename="local" prefix="message.">
                        <c:if test="${mark > 0}">
                            <label><font color="red"><fmt:message key="yourMark"/>: ${checkMark} </font> </label>
                        </c:if>
                    </fmt:bundle>
                </div>
            </c:when>
            <c:when test="${mark == 0}">
                <label><font color="red"><fmt:message key="taskOnVerification"/></font> </label>
            </c:when>
        </c:choose>
    </c:if>
    <c:if test="${showSolution == true}">
        <c:set var="task" value="${taskService.findTaskSolution(studentId, taskId)}"/>
        <div class="container-fluid">
            <h4><fmt:message key="solution"/>:</h4>
            <br/>
                ${task.answer}
            <br/>
            <h5><fmt:message key="currentMark"/>:</h5> ${task.mark}
            <form name="setMark" method="post" action="controller">
                <input type="hidden" name="redirectTo" value="true"/>
                <input type="hidden" name="command" value="set_mark_for_task"/>
                <input type="hidden" name="studentId" value="${studentId}"/>
                <input type="hidden" name="taskId" value="${taskId}"/>
                <input type="number" name="mark" min="1" max="10"/>
                <button type="submit" class="btn-warning">
                    <fmt:message key="grade"/>
                </button>
            </form>
        </div>
    </c:if>
        <%--    nullify the message about sent solution --%>
    <c:set var="sendSolutionMessage" value="${null}"/>
    </body>
    </html>
</fmt:bundle>