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
<fmt:bundle basename="local" prefix="label.">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}js/table_sort.js"></script>
    <html>
    <head><title><fmt:message key="button.studentsManagement"/></title></head>
    </head>
    <body>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="controller?command=main_page">Trainings Center</a>
            </div>
            <ul class="nav navbar-nav">
                <li><a href="controller?command=trainings_page"><fmt:message key="currentTrainings"/></a></li>
                <li><a href="controller?command=cabinet"><fmt:message key="cabinet"/></a></li>
                <li><a href="controller?command=log_out"><fmt:message key="logout"/></a></li>
                <c:if test="${typeOperation == 'trainingManagement'}">
                    <li><a href="controller?command=create_text&typeOperation=createTraining"><fmt:message
                            key="create_training"/></a></li>
                </c:if>
            </ul>
        </div>
    </nav>
    <br/>
    <div class="col-xs-1">
        <div class="container">
            <jsp:useBean id="userService" class="com.epam.webapp.service.UserService"/>
            <jsp:useBean id="trainingService" class="com.epam.webapp.service.TrainingsService"/>
            <h2><fmt:message key="button.studentsManagement"/></h2>
                <%--            table stusents registred on training--%>
            <table class="table table_sort">
                <c:set var="count" value="${1}"/>
                <thead>
                <tr>
                    <th>No<i class="fa fa-fw fa-sort"/></th>
                    <th><fmt:message key="userName"/><i class="fa fa-fw fa-sort"/></th>
                    <th><fmt:message key="userSurname"/><i class="fa fa-fw fa-sort"/></th>
                    <c:forEach var="task" items="${userService.findStudentsMarkForTrainingsTask(student.id, trainingId)}">
                        <th>
                            <a href="controller?command=task_page&studentId=${student.id}&taskId=${task.id}&showSolution=true" >
                                    ${task.name}
                            </a>
                        </th>
                    </c:forEach>
                    <th><fmt:message key="average"/><i class="fa fa-fw fa-sort"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="student" items="${userService.getStudentsForTraining(trainingId)}">
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
                        <c:forEach var="task" items="${userService.findStudentsMarkForTrainingsTask(student.id, trainingId)}">
                            <%--                </c:forEach>--%>
                            <th>
                                    ${task.mark}
                            </th>
                        </c:forEach>
                        <td>${trainingService.findAvgMarkForTasks(student.id, trainingId)}</td>
                    </tr>
                    <c:set var="count" value="${count + 1}"/>
                </c:forEach>
                </tbody>
            </table>
        </div>

    </div>


    </body>
    <script>
        document.addEventListener('DOMContentLoaded', () => {

            const getSort = ({target}) => {
                const order = (target.dataset.order = -(target.dataset.order || -1));
                const index = [...target.parentNode.cells].indexOf(target);
                const collator = new Intl.Collator(['en', 'ru'], {numeric: true});
                const comparator = (index, order) => (a, b) => order * collator.compare(
                    a.children[index].innerHTML,
                    b.children[index].innerHTML
                );

                for (const tBody of target.closest('table').tBodies)
                    tBody.append(...[...tBody.rows].sort(comparator(index, order)));

                for (const cell of target.parentNode.cells)
                    cell.classList.toggle('sorted', cell === target);
            };

            document.querySelectorAll('.table_sort thead').forEach(tableTH => tableTH.addEventListener('click', () => getSort(event)));

        });
    </script>
    </html>
</fmt:bundle>