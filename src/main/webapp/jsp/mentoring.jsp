<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 13.11.2019
  Time: 23:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:bundle basename="local" prefix="label.">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8"
            src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>
    <html>
    <head><title>Mentor page</title></head>
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
    <jsp:useBean id="userService" class="com.epam.tc.service.impl.UserServiceImpl"/>
    <c:set var="count" value="${0}"/>
    <div class="container-fluid">
        <h2><fmt:message key="trainingManagement"/></h2>
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
                                <c:forEach var="task"
                                           items="${userService.findStudentsMarkForTrainingsTask(studentId, trainingId)}">
                                    <th>
                                        <a href="controller?command=task_page&studentId=${studentId}&taskId=${task.id}&showSolution=true">
                                                ${task.name}
                                        </a>
                                    </th>
                                </c:forEach>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <c:forEach var="task"
                                           items="${userService.findStudentsMarkForTrainingsTask(studentId, trainingId)}">
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
            <jsp:useBean id="trainingService" class="com.epam.tc.service.impl.TrainingsServiceImpl"/>
            <c:set var="finalGrade" value="${trainingService.findFinalGrade(studentId, trainingId)}"/>
            <c:choose>
                <c:when test="${finalGrade > 0}">
                    <h4><fmt:message key="finalGrade"/>: ${finalGrade}</h4>
                </c:when>
                <c:when test="${finalGrade == 0}">
                    <h3><fmt:message key="setFinalGrade"/></h3>
                    <form name="transfer" method="POST" action="controller">
                        <input type="hidden" name="redirectTo" value="true"/>
                        <input type="hidden" name="command" value="set_final_grade"/>
                        <input type="hidden" name="studentId" value="${studentId}"/>
                        <input type="hidden" name="trainingId" value="${trainingId}"/>
                        <input type="number" required min="1" max="10" name="grade"/>
                        <button type="submit" class="btn-warning"><fmt:message key="grade"/></button>
                    </form>
                </c:when>
            </c:choose>

        </div>
        <script>
            $(document).ready( function () {
                $('table').DataTable({
                    "sDom": '<"top"i>rt<"bottom"lp><"clear">',
                    "info":false
                });
            } );
        </script>
            <%--        </div>--%>
    </body>
    </html>
</fmt:bundle>