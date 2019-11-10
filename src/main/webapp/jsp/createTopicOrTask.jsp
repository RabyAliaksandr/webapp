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
    <head><title>Create</title></head>
    </head>
    <body>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="controller?command=main_page">Trainings Center</a>
            </div>
            <ul class="nav navbar-nav">
                <li><a href="controller?command=trainings_page"><fmt:message key="currentTrainings"/></a>
                <li class="active"><a href="controller?command=cabinet"><fmt:message key="cabinet"/></a></li>
                <li><a href="controller?command=log_out"><fmt:message key="logout"/></a></li>
                </li>
            </ul>
        </div>
    </nav>
    <br/>
    <hr/>
        <%--form for edit escription training--%>
    <jsp:useBean id="trainingService" class="com.epam.webapp.service.TrainingsService"/>
    <c:if test="${typeOperation == 'edit'}">
        <c:set var="training" value="${trainingService.getTrainingByIdTraining(trainingId)}" scope="session"/>
        <c:set var="text" value="${training.information}"/>
        <hr/>
        <form id="www" method="post" action="controller">
            <input type="hidden" name="command" value="update_information_about_training"/>
            <input type="hidden" name="trainingId" value="${trainingId}"/>
            <textarea id="editor" form="www" name="information">
                    ${text}
            </textarea>
            <input type="submit" value=<fmt:message key="send"/>/>
        </form>
    </c:if>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#editor").editor({
                uiLibrary: 'bootstrap'
            });
        });
    </script>

        <%--  form for add topic  --%>

    <c:if test="${typeOperation == 'addTopic'}">
        <hr/>
        <form id="addTopic" method="post" action="controller">
            <div class="form-group">
                <label for="form">Topics name:</label>
                <input type="text" class="form-control" id="form" name="topicsName" maxlength="70">
            </div>
            <input type="hidden" name="command" value="add_topic_for_training"/>
            <input type="hidden" name="trainingId" value="${trainingId}"/>
            <textarea id="editor1" form="addTopic" name="topicsText" maxlength="10000">
                    ${text}
            </textarea>
            <input type="submit" value=<fmt:message key="send"/>/>
        </form>
    </c:if>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#editor1").editor({
                uiLibrary: 'bootstrap'
            });
        });
    </script>

<%--    add task for trining--%>

    <c:if test="${typeOperation == 'addTask'}">
        <hr/>
        <form id="addTopic" method="post" action="controller">
            <div class="form-group">
                <label>Topics name:</label>
                <input type="text" class="form-control" name="taskName" maxlength="70">
            </div>
            <input type="hidden" name="command" value="add_task_for_training"/>
            <input type="hidden" name="trainingId" value="${trainingId}"/>
            <textarea id="editor2" form="addTopic" name="taskText" maxlength="10000">
                    ${text}
            </textarea>
            <input type="submit" value=<fmt:message key="send"/>>
        </form>
    </c:if>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#editor2").editor({
                uiLibrary: 'bootstrap'
            });
        });
    </script>

    <c:if test="${typeOperation == 'createTraining'}">
        <hr/>
        <form id="addTraining" method="post" action="controller">
            <input type="hidden" name="command" value="create_training"/>
            <div class="form-group">
                <label>Training Name:</label>
                <input type="text" class="form-control" name="trainingName" maxlength="70">
            </div>
            <textarea id="editor3" form="addTraining" name="description" maxlength="1000">
            </textarea>

            <div class="form-group">
                <jsp:useBean id="userService" class="com.epam.webapp.service.UserService"/>
                <label class="control-label col-sm-offset-2 col-sm-2" for="company"><fmt:message
                        key="choose_mentor"/></label>
                <div class="col-sm-6 col-md-4">

                    <select id="company" class="form-control" name="mentorId">
                            <%--                            <option>1</option>--%>
                        <c:forEach var="user" items="${userService.allMentors}">
                            <%--                            <option>2</option>--%>
                            <option value=${user.id}>${user.name} ${user.surname}</option>
                        </c:forEach>
                    </select>


                </div>
            </div>

            <input type="submit" value=<fmt:message key="send"/>>
        </form>
    </c:if>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#editor3").editor({
                uiLibrary: 'bootstrap'
            });
        });
    </script>

<%--    edit trainings topic --%>

    <c:if test="${typeOperation == 'editTopic'}">
        <hr/>
        <form id="editTopic" method="post" action="controller">
            <div class="form-group">
                <label><fmt:message key="topicsName"/></label>
                <input type="text" class="form-control" name="topicNewName" maxlength="70" value="${topicName}">
                <input type="hidden" name="topicName" value="${topicName}"/>
            </div>
            <input type="hidden" name="command" value="update_trainings_topic"/>
            <input type="hidden" name="trainingId" value="${trainingId}"/>
            <textarea id="editor4" form="editTopic" name="topic" maxlength="10000">
                    ${trainingService.getTopic(trainingId, topicName)}
            </textarea>
            <input type="submit" onclick="goBack()" value=<fmt:message key="send"/>>
        </form>
    </c:if>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#editor4").editor({
                uiLibrary: 'bootstrap'
            });
        });
    </script>

    </body>
    </html>
</fmt:bundle>