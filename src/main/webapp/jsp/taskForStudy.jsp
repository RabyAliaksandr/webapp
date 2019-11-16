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
                <a class="navbar-brand" href="controller?command=main_page">Trainings Center</a>
            </div>
            <ul class="nav navbar-nav">
                <li><a href="controller?command=trainings_page"><fmt:message key="currentTrainings"/></a></li>
                <c:if test="${user.type != null}">
                    <li class="active"><a href="controller?command=cabinet"><fmt:message key="cabinet"/></a></li>
                    <li><a href="controller?command=log_out"><fmt:message key="logout"/></a></li>
                </c:if>
<%--                is user is admin or mentor, then he can edit topic and task--%>
                <c:if test="${(user.type == 'ADMIN' && editor == true) || (user.type == 'MENTOR' && editor == true)}">
                    <li><a href="controller?command=create_text&typeOperation=editTask&
                            taskId=${taskId}"><fmt:message key="button.editDescription"/></a></li>
                </c:if>
            </ul>
        </div>
    </nav>
    <br/>
    <jsp:useBean id="trainingServie" class="com.epam.webapp.service.TrainingsService"/>
    <c:if test="${changesSavedMessage != null}">
        <div class="alert alert-danger" role="alert">
                ${changesSavedMessage}
            <c:set var="changesSavedMessage" value="${null}"/>
        </div>
    </c:if>
    <div class="container-fluid">
        <c:set var="task" value="${trainingServie.getTask(taskId)}"/>
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
        <c:set var="check" value="${trainingServie.checkTaskStatus(user.id, taskId)}"/>
        <%--            students form for send solution if check is false --%>
        <c:choose>
            <c:when test="${check == false}">
                <form id="sendSolution" method="post" action="controller">
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
            <c:when test="${check == true && sendSolutionMessage == null}">
                <div class="alert alert-danger" role="alert">
                    <fmt:bundle basename="local" prefix="message.">
                        <fmt:message key="solutionSendAlready"/>
                    </fmt:bundle>
                </div>
            </c:when>
        </c:choose>
    </c:if>
    <c:if test="${showSolution == true}">
    <c:set var="map" value="${trainingServie.findTaskSolution(studentId, taskId)}"/>
       <div class="container-fluid">
      <c:forEach var="solution" items="${map}">
          <h4><fmt:message key="solution"/>:</h4>
          <br/>
          ${solution.key}
          <br/>
          <jsp:useBean id="trainingService" class="com.epam.webapp.service.TrainingsService"/>
          <h5> <fmt:message key="currentMark"/>:</h5> ${solution.value}
      </c:forEach>
           <form name="setMark"  method="post" action="controller">
               <input type="hidden" name="command" value="set_mark_for_task"/>
               <input type="hidden" name="studentId" value="${studentId}"/>
               <input type="hidden" name="taskId" value="${taskId}"/>
               <input type="number" name="mark"  min="1" max="10"/>
               <button type="submit" class="btn-warning" >
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