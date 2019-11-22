<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 11.11.2019
  Time: 18:03
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
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8"
            src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>
    <script src="${pageContext.request.contextPath}js/table_sort.js"></script>
    <html>
    <head><title><fmt:message key="button.studentsManagement"/></title></head>
    </head>
    <body>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="controller?command=main_page">
                    <fmt:message key="button.studentsManagement"/>
                </a>
            </div>
            <ul class="nav navbar-nav">
                <li>
                    <a href="controller?command=trainings_page">
                        <fmt:message key="currentTrainings"/>
                    </a>
                </li>
                <li>
                    <a href="controller?command=cabinet">
                        <fmt:message key="cabinet"/>
                    </a>
                </li>

                <li>
                    <a href="controller?command=log_out">
                        <fmt:message key="logout"/>
                    </a>
                </li>
                <c:if test="${typeOperation == 'trainingManagement'}">
                    <li>
                        <a href="controller?command=create_text&typeOperation=createTraining">
                            <fmt:message key="create_training"/>
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
    <div class="col-xs-1">
        <div class="container">
            <jsp:useBean id="userService" class="com.epam.webapp.service.impl.UserServiceImpl"/>
            <jsp:useBean id="trainingService" class="com.epam.webapp.service.impl.TrainingsServiceImpl"/>
            <h2><fmt:message key="button.studentsManagement"/></h2>
                <%--            table stusents registred on training--%>
            <table id="table_id" class="table table_sort" cellpadding="0" width="100%">
                <c:set var="count" value="${1}"/>
                <thead>
                <tr>
                    <th>No<i class="fa fa-fw fa-sort"/></th>
                    <th><fmt:message key="userName"/><i class="fa fa-fw fa-sort"/></th>
                    <th><fmt:message key="userSurname"/><i class="fa fa-fw fa-sort"/></th>
                    <th><fmt:message key="average"/><i class="fa fa-fw fa-sort"/></th>
                    <c:forEach var="task" items="${trainingService.findTasksListForTraining(trainingId)}">
                        <th>
                            <a href="controller?command=task_page&studentId=${studentId}&taskId=${task.id}&showSolution=true">
                                    ${task.name}
                            </a>
                        </th>
                    </c:forEach>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="student" items="${userService.findStudentsForTraining(trainingId)}">
                   <c:if test="${student.grade == 0}">
                       <tr>
                           <td>
                               <a href="controller?command=mentoring&studentId=${student.id}&trainingId=${trainingId}">
                                       ${count}
                               </a>
                           </td>
                           <td>
                               <a href="controller?command=mentoring&studentId=${student.id}&trainingId=${trainingId}">
                                       ${student.name}
                               </a>
                           </td>
                           <td>
                               <a href="controller?command=mentoring&studentId=${student.id}&trainingId=${trainingId}">
                                       ${student.surname}
                               </a>
                           </td>
                           <td>${trainingService.findAvgMarkForTasks(student.id, trainingId)}</td>
                           <c:forEach var="task"
                                      items="${userService.findStudentsMarkForTrainingsTask(student.id, trainingId)}">
                               <%--                </c:forEach>--%>
                               <td>
                                       ${task.mark}
                               </td>
                           </c:forEach>
                       </tr>
                       <c:set var="count" value="${count + 1}"/>
                   </c:if>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    </body>
    <script>
        $(document).ready(function () {
            $('#table_id').DataTable();
        });
    </script>
    <script>
        var table = $('#table_id').dataTable({
            sScrollX: "100%",
            sScrollXInner: "150%",
            bScrollCollapse: true
        });
        new $.fn.dataTable.FixedColumns(table);
    </script>
    </html>
</fmt:bundle>