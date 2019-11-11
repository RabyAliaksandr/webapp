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
    <c:set var="trainingId" value="${sessionScope.trainingId}"/>
    <jsp:useBean id="trainingService" class="com.epam.webapp.service.TrainingsService"/>
    <jsp:useBean id="userService" class="com.epam.webapp.service.UserService"/>
    <c:set var="training" value="${trainingService.getTrainingByIdTraining(trainingId)}" scope="session"/>
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
                <c:if test="${editor == true}">
                    <li>
                        <a href="controller?command=create_text&typeOperation=edit&trainingId=${trainingId}"><fmt:message
                                key="button.editDescription"/></a></li>
                    <li>
                        <a href="controller?command=create_text&typeOperation=addTopic&trainingId=${trainingId}"><fmt:message
                                key="button.addTopic"/></a></li>
                    <li>
                        <a href="controller?command=create_text&typeOperation=addTask&trainingId=${trainingId}"><fmt:message
                                key="button.addTask"/></a></li>
                </c:if>
                <c:if test="${user.type != null}">
                    <li><a href="controller?command=log_out"><fmt:message key="logout"/></a></li>
                </c:if>
            </ul>
        </div>
    </nav>
    <br/>
    <title>Information about Training</title>
    <br/>
    <br/>
    <br/>
    <section class="a">
        <div class="container-fluid">
            <h1><fmt:message key="informationAboutTraining"/></h1>
            <br/>
                ${training.information}
            <br/>
            <br/>
            <c:if test="${sessionScope.user.type == 'STUDENT'}">
                <c:if test="${userService.checkEnrolled(user.id, trainingId) == false}">
                    <form name="addTrainingToStudent" method="POST" action="controller">
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
    <section class="b">
        <div class="col-lg-6 col-md-6">
            <div class="container-fluid">

                <c:set var="check" value="${trainingService.checkTrainingStatusForStudent(user.id, trainingId)}"/>
                    <%--        if student on training or admin or mentor--%>
                <c:if test="${check == true || user.type == 'ADMIN' || user.type == 'MENTOR'}">
                <h3>
                    <fmt:message key="listTopics"/>
                </h3>
                <br/>

                    <%--table topics for training--%>


                    <table class="table">
                        <thead>
                        <tr>
                            <th>No</th>
                            <th><fmt:message key="topicsName"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:set var="count" value="1"/>
                        <c:forEach var="topic" items="${trainingService.getTopicsForTraining(trainingId)}">
                            <tr>
                                <td>${count}</td>
                                <td>
                                    <a href="controller?command=topic_page&trainingId=${trainingId}&topicId=${topic.id}">${topic.name}</a>
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
        <c:if test="${check == true || user.type == 'ADMIN' || user.type == 'MENTOR'}">
        <div class="col-lg-6 col-md-6">
            <div class="container-fluid">
                <h3>
                    <fmt:message key="listTask"/>
                </h3>
                <br/>
                    <%--table taks for training--%>
                   <table class="table">
                       <thead>
                       <tr>
                           <th>No</th>
                           <th><fmt:message key="tasksName"/></th>
                       </tr>
                       </thead>
                       <tbody>
                       <c:set var="count" value="1"/>
                       <c:forEach var="task" items="${trainingService.getTasksListForTraining(trainingId)}">
                           <tr>
                               <td>${count}</td>
                               <td>
                                   <a href="controller?command=topic_page&trainingId=${trainingId}&taskId=${task.id}">${task.name}</a>
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
    </section>
    </head>
    </body>
    </html>
</fmt:bundle>


