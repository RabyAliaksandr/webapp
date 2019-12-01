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
<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:bundle basename="local" prefix="label.">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
    <link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css"/>
    <html>
    <head><title>Create</title></head>
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
                <li class="active">
                    <a href="controller?command=cabinet">
                        <fmt:message key="cabinet"/></a>
                </li>
                <li>
                    <a href="controller?command=log_out">
                        <fmt:message key="logout"/>
                    </a>
                </li>
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
    <hr/>
        <%--form for edit description training--%>
    <jsp:useBean id="trainingService" class="com.epam.tc.service.impl.TrainingsServiceImpl"/>
    <div class="container-fluid">
        <c:if test="${changesSavedMessage != null}">
            <div class="alert alert-danger" role="alert">
                    ${changesSavedMessage}
                <c:set var="changesSavedMessage" value="${null}"/>
            </div>
        </c:if>
        <c:if test="${typeOperation == 'edit'}">
            <c:set var="training"
                   value="${trainingService.findTrainingByIdTraining(trainingId)}"
                   scope="session"/>
            <c:set var="text" value="${training.information}"/>
            <c:set var="trainingName" value="${training.name}"/>
            <hr/>
            <form id="www" method="post" action="controller">
                <input type="hidden" name="command" value="update_information_about_training"
                       id="name" required/>
                <input type="text" name="trainingName" value="${name != null ? name : trainingName}" required maxlength="70"/>
                <input type="hidden" name="pageName" value="trainings_information_page&trainingId=${trainingId}&editor=true">
                <input type="hidden" name="trainingId" value="${trainingId}"/>
                <textarea id="qweqwe" required maxlength="1000" form="www"
                          name="information">
                        ${information != null ? information : text}
                </textarea>
                <input type="submit" value=<fmt:message key="send"/>/>
            </form>
            <c:set var="information" value="${null}"/>
            <c:set var="name" value="${null}"/>
            <div id="counter"></div>
        </c:if>
        <c:if test="${typeOperation == 'addTopic'}">
            <hr/>
            <form id="addTopic" method="post" action="controller">
                <div class="form-group">
                    <label for="form"><fmt:message key="topicsName"/></label>
<%--                    <input type="hidden" name="redirectTo" value="true"/>--%>
                    <input type="hidden" name="pageName" value="trainings_information_page&trainingId=${trainingId}&editor=true">
                    <input type="text" class="form-control" id="form"
                           placeholder="min 5 max 70" name="topicsName" maxlength="70"
                           value="${name}" required>
                </div>
                <input type="hidden" name="command" value="add_topic_for_training"/>
                <input type="hidden" name="trainingId" value="${trainingId}" required/>
                <textarea id="editor1" form="addTopic" name="topicsText" placeholder="min 50 max 1000" maxlength="1000">
                        ${text}
                </textarea>
                <input type="submit" value=<fmt:message key="send"/>>
            </form>
        </c:if>
        <c:if test="${typeOperation == 'addTask'}">
            <hr/>
            <form id="addTopic" method="post" action="controller">
<%--                <input type="hidden" name="redirectTo" value="true"/>--%>
    <input type="hidden" name="pageName" value="trainings_information_page&trainingId=${trainingId}&editor=true">
    <div class="form-group">
                    <label><fmt:message key="topicsName"/></label>
                    <input type="text" placeholder="min - 5 max - 70" class="form-control" name="taskName"
                          value="${name}" maxlength="70" required>
                </div>
                <input type="hidden" name="command" value="add_task_for_training"/>
                <input type="hidden" name="trainingId" value="${trainingId}"/>
                <textarea id="editor2" form="addTopic" name="taskText" maxlength="1000" required>
                        ${text}
                </textarea>
                <input type="submit" value=<fmt:message key="send"/>>
            </form>
        </c:if>
        <c:if test="${typeOperation == 'createTraining'}">
            <hr/>
            <form id="addTraining" method="post" action="controller">
                <input type="hidden" name="command" value="create_training"/>
                <input type="hidden" name="pageName" value="trainings_information_page&trainingId=${trainingId}&editor=true">
                <div class="form-group">
                    <label><fmt:message key="name"/> </label>
                    <input type="text" class="form-control" name="trainingName"
                           required maxlength="70" value="${name}">
                </div>
                <textarea id="editor3" form="addTraining" name="description" maxlength="1000" required>
                    ${text}
            </textarea>
                <div class="form-group">
                    <jsp:useBean id="userService" class="com.epam.tc.service.impl.UserServiceImpl"/>
                    <label class="control-label col-sm-offset-2 col-sm-2" for="company"><fmt:message
                            key="choose_mentor"/></label>
                    <div class="col-sm-6 col-md-4">

                        <select id="company" class="form-control" name="mentorId" required>
                            <c:forEach var="mentor" items="${userService.findAllMentors()}">
                                <option value=${mentor.id}>${mentor.name} ${mentor.surname}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <input type="submit" value=<fmt:message key="send"/>>
            </form>
        </c:if>
        <jsp:useBean id="topicService" class="com.epam.tc.service.impl.TopicServiceImpl"/>
        <c:if test="${typeOperation == 'editTopic'}">
            <c:set var="topic" value="${topicService.findTopic(topicId)}"/>
            <hr/>
            <form id="editTopic" method="post" action="controller">
                <input type="hidden" name="topicId" value="${topicId}"/>
                <input type="hidden" name="pageName" value="trainings_information_page&trainingId=${trainingId}&editor=true">
                <div class="form-group">
                    <label><fmt:message key="topicsName"/></label>
                    <input type="text" class="form-control" name="topicName"
                           maxlength="70" value="${name == null ? topic.name : name}"
                           required>
                </div>
                <input type="hidden" name="command" value="update_trainings_topic"/>
                <textarea id="editor4" form="editTopic" name="topic" maxlength="10000" required>
                        ${text == null ? topic.topic : text}
                </textarea>
                <input type="submit" value=<fmt:message key="send"/>>
            </form>
        </c:if>
            <%--edit task for training--%>
        <c:if test="${typeOperation == 'editTask'}">
            <jsp:useBean id="taskService" class="com.epam.tc.service.impl.TaskServiceImpl"/>
            <c:set var="task" value="${taskService.findTask(taskId)}"/>
            <hr/>
            <form id="editTask" method="post" action="controller">
                <input type="hidden" name="pageName" value="trainings_information_page&trainingId=${trainingId}&editor=true">
ta                <div class="form-group">
                    <label><fmt:message key="tasksName"/></label>
                    <input type="text" class="form-control" name="taskName" maxlength="70"
                           value="${name == null ? task.name : name}"
                           required>
                    <input type="hidden" name="taskId" value="${taskId}"/>
                </div>
                <input type="hidden" name="command" value="update_trainings_task"/>
                <textarea id="editor5" form="editTask" name="task" maxlength="10000" required>
                        ${text == null ? task : text}
                </textarea>
                <input type="submit" value=<fmt:message key="send"/>>
            </form>
        </c:if>
        <div class="container-fluid">
            <c:if test="${typeOperation == 'giveFeedback'}">
                <hr/>
                <form method="post" action="controller">
                    <input type="hidden" name="command" value="give_feedback"/>
                    <textarea id="editor6" name="feedback" maxlength="1000" required>
            </textarea>
                    <input type="submit" value=<fmt:message key="send"/>>
                </form>
            </c:if>
        </div>
    </div>
    </body>
            <script type="text/javascript">
                $(document).ready(function () {
                    $('textarea').editor({
                        uiLibrary: 'bootstrap'
                    });
                });
            </script>
    </html>
</fmt:bundle>