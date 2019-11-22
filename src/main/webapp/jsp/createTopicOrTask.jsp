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
    <jsp:useBean id="trainingService" class="com.epam.webapp.service.impl.TrainingsServiceImpl"/>

    <div class="container-fluid">

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
                <input type="text" name="trainingName" value="${trainingName}" required maxlength="70"/>
                <input type="hidden" name="trainingId" value="${trainingId}"/>

                <textarea id="qweqwe" form="www"
                          name="information">
                        ${text}
                </textarea>

                <div class="last_later">Left <span id="remain">100</span> symbols</div>
                <input type="submit" value=<fmt:message key="send"/>/>
            </form>

            <div id="counter"></div>
        </c:if>

        <script>
            $(function () {
                $(document).ready(function () {
                    var $textarea = '#qweqwe';
                    var $counter = '#counter';
                    $($textarea).on('blur, keyup', function () {
                        var $max = 10; // Максимальное кол-во символов
                        var $val = $(this).val();
                        $(this).attr('maxlength', $max); // maxlength=""
                        if ($val.length <= 0) {
                            $($counter).html(0);
                        } else {
                            if ($max < parseInt($val.length)) {
                                $this.val($val.substring(0, $max));
                            }
                            $($counter).html($(this).val().length);
                        }
                    });
                });
            });
        </script>

<%--        <script type="text/javascript">--%>
<%--            $(document).ready(function () {--%>
<%--                $('textarea').editor({--%>
<%--                    uiLibrary: 'bootstrap'--%>
<%--                });--%>

<%--            });--%>
<%--        </script>--%>
            <%--  form for add topic  --%>
        <c:if test="${typeOperation == 'addTopic'}">
            <hr/>
            <form id="addTopic" method="post" action="controller">

                <div class="form-group">
                    <label for="form">Topics name:</label>
                    <input type="text" class="form-control" id="form" name="topicsName" maxlength="70" required>
                </div>
                <input type="hidden" name="command" value="add_topic_for_training"/>
                <input type="hidden" name="trainingId" value="${trainingId}" required/>
                <textarea id="editor1" form="addTopic" name="topicsText" maxlength="10000">
                        ${text}
                </textarea>
                <input type="submit" value=<fmt:message key="send"/>>
            </form>
        </c:if>
            <%--    <script type="text/javascript">--%>
            <%--        $(document).ready(function () {--%>
            <%--            $("#editor1").editor({--%>
            <%--                uiLibrary: 'bootstrap'--%>
            <%--            });--%>
            <%--        });--%>
            <%--    </script>--%>
            <%--    add task for trining--%>
        <c:if test="${typeOperation == 'addTask'}">
            <hr/>
            <form id="addTopic" method="post" action="controller">

                <div class="form-group">
                    <label>Topics name:</label>
                    <input type="text" class="form-control" name="taskName" maxlength="70" required>
                </div>
                <input type="hidden" name="command" value="add_task_for_training"/>
                <input type="hidden" name="trainingId" value="${trainingId}"/>
                <textarea id="editor2" pa form="addTopic" name="taskText" maxlength="1000" required>
                        ${text}
                </textarea>
                <input type="submit" value=<fmt:message key="send"/>>
            </form>
        </c:if>

            <%--    <script type="text/javascript">--%>
            <%--        $(document).ready(function () {--%>
            <%--            $("#editor2").editor({--%>
            <%--                uiLibrary: 'bootstrap'--%>
            <%--            });--%>
            <%--        });--%>
            <%--    </script>--%>

        <c:if test="${typeOperation == 'createTraining'}">
            <hr/>
            <form id="addTraining" method="post" action="controller">
                <input type="hidden" name="command" value="create_training"/>

                <div class="form-group">
                    <label>Training Name:</label>
                    <input type="text" class="form-control" name="trainingName" maxlength="70">
                </div>
                <textarea id="editor3" form="addTraining" name="description" maxlength="1000" required>
            </textarea>

                <div class="form-group">
                    <jsp:useBean id="userService" class="com.epam.webapp.service.impl.UserServiceImpl"/>
                    <label class="control-label col-sm-offset-2 col-sm-2" for="company"><fmt:message
                            key="choose_mentor"/></label>
                    <div class="col-sm-6 col-md-4">

                        <select id="company" class="form-control" name="mentorId">
                            <c:forEach var="user" items="${userService.findAllMentors}">
                                <option value=${user.id}>${user.name} ${user.surname}</option>
                            </c:forEach>
                        </select>


                    </div>
                </div>

                <input type="submit" value=<fmt:message key="send"/>>
            </form>
        </c:if>
            <%--    <script type="text/javascript">--%>
            <%--        $(document).ready(function () {--%>
            <%--            $("#editor3").editor({--%>
            <%--                uiLibrary: 'bootstrap'--%>
            <%--            });--%>
            <%--        });--%>
            <%--    </script>--%>

            <%--    edit trainings topic --%>

        <c:if test="${typeOperation == 'editTopic'}">
            <c:set var="topic" value="${trainingService.findTopic(topicId)}"/>
            <hr/>
            <c:out value="${topicId} fuck "/>
            <form id="editTopic" method="post" action="controller">
                <input type="hidden" name="topicId" value="${topicId}"/>

                <div class="form-group">
                    <label><fmt:message key="topicsName"/></label>
                    <input type="text" class="form-control" name="topicName" maxlength="70" value="${topic.name}"
                           required>
                </div>
                <input type="hidden" name="command" value="update_trainings_topic"/>
                    <%--            <input type="hidden" name="trainingId" value="${trainingId}"/>--%>
                <textarea id="editor4" form="editTopic" name="topic" maxlength="10000" required>
                        ${topic.topic}
                </textarea>
                <input type="submit" value=<fmt:message key="send"/>>
            </form>
        </c:if>

            <%--    <script type="text/javascript">--%>
            <%--        $(document).ready(function () {--%>
            <%--            $("#editor4").editor({--%>
            <%--                uiLibrary: 'bootstrap'--%>
            <%--            });--%>
            <%--        });--%>
            <%--    </script>--%>

            <%--edit task for training--%>
        <c:if test="${typeOperation == 'editTask'}">
            <c:set var="task" value="${trainingService.findTask(taskId)}"/>
            <hr/>
            <form id="editTask" method="post" action="controller">

                <div class="form-group">
                    <label><fmt:message key="tasksName"/></label>
                    <input type="text" class="form-control" name="taskName" maxlength="70" value="${task.name}"
                           required>
                    <input type="hidden" name="taskId" value="${taskId}"/>
                </div>
                <input type="hidden" name="command" value="update_trainings_task"/>
                <textarea id="editor5" form="editTask" name="task" maxlength="10000" required>
                        ${task.task}
                </textarea>
                <input type="submit" value=<fmt:message key="send"/>>
            </form>
        </c:if>
            <%--    <script type="text/javascript">--%>
            <%--        $(document).ready(function () {--%>
            <%--            $("#editor5").editor({--%>
            <%--                uiLibrary: 'bootstrap'--%>
            <%--            });--%>
            <%--        });--%>
            <%--    </script>--%>

        <div class="container-fluid">
                <%--        <c:if test="${messageFeedback != null}">--%>
                <%--            <div class="alert alert-danger" role="alert">--%>
                <%--                    ${messageFeedback}--%>
                <%--                <c:set var="messageFeedback" value="${null}"/>--%>
                <%--            </div>--%>
                <%--        </c:if>--%>
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
            <%--    <script type="text/javascript">--%>
            <%--        $(document).ready(function () {--%>
            <%--            $("#editor6").editor({--%>
            <%--                uiLibrary: 'bootstrap'--%>
            <%--            });--%>
            <%--        });--%>
            <%--    </script>--%>
    </div>
    </body>
    </html>
</fmt:bundle>