<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 13.11.2019
  Time: 23:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="ru" scope="session"/>
<jsp:useBean id="trainingService" class="com.epam.webapp.service.TrainingsService"/>
<fmt:bundle basename="local" prefix="label.">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <html>
    <head><title>Mentor page</title></head>
    </head>
    <body>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="controller?command=main_page">Trainings Center</a>
            </div>
            <ul class="nav navbar-nav">
                <li><a href="controller?command=trainings_page"><fmt:message key="currentTrainings"/></a></li>
                <li><a href="controller?command=log_out"><fmt:message key="logout"/></a></li>
            </ul>
        </div>
    </nav>
    <br/>
    <jsp:useBean id="userService" class="com.epam.webapp.service.UserService"/>
    <c:set var="count" value="${0}"/>
    <div class="container-fluid">
        <div class="jumbotron">
            <fmt:message key="trainingManagement"/>
        </div>
    <div class="container-fluid">
        <div class="item mx-5 mt-2" style="overflow: hidden">
<%--            ЗДЕСЬ БУДЕТ ЗАГОЛОВОК КОТОРЫЙ НЕ ПЕРЕМАТЫВАЕТСЯ                                             --%>
<%--            <h2 class="p-2 bg-primary text-white">Inventory</h2>--%>
<%--            <div class="text-center">--%>
<%--                <h3 style="border-bottom: 1px solid #000">Time Chart</h3>--%>
<%--            </div>--%>
            <div class="d-flex p-4">
                <div class="table-responsive">
                    <table class="table table-bordered text-center">
                        <thead>
                        <tr>
                            <c:forEach var="task" items="${userService.findStudentsMarkForTrainingsTask(studentId, trainingId)}">
                                <th>
                                        <a href="controller?command=task_page&studentId=${studentId}&taskId=${task.id}&showSolution=true" >
                                                ${task.name}
                                        </a>
                                </th>
                            </c:forEach>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <c:forEach var="task" items="${userService.findStudentsMarkForTrainingsTask(studentId, trainingId)}">
                                <%--                </c:forEach>--%>
                                <th>
                                        ${task.mark}
                                </th>
                            </c:forEach>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>


<%--        </div>--%>
    </body>
    </html>
</fmt:bundle>