<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 09.11.2019
  Time: 22:11
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
    <script src="js/bootstrap-datetimepicker.js"></script>
    <script src="js/bootstrap-datetimepicker.min.js"></script>
    <link rel="stylesheet" href="style/bootstrap-datetimepicker.css"/>
    <link rel="stylesheet" href="style/bootstrap-datetimepicker.min.css"/>
    <html>

    <head><title>Mentor page</title></head>
    </head>
    <body>
    <nav class="navbar navbar-default">
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
    </nav>
    <br/>
    <div class="container-fluid">

                <%--        block Traininngs Management--%>

            <c:if test="${typeOperation == 'trainingManagement'}">
            <div class="col-xs-1">
                <jsp:useBean id="gettrainings" class="com.epam.webapp.service.TrainingsService"/>
                <c:set var="count" value="1"/>
                <div class="container">
                    <h2><fmt:message key="currentTrainings"/></h2>
                    <table class="table">
                        <thead>
                        <tr>
                            <th>No</th>
                            <th><fmt:message key="name"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="training" items="${gettrainings.allTrainings}">
                            <tr>
                                <td>${count}</td>
                                <td>
                                    <a href="controller?command=trainings_information_page&trainingId=${training.id}&editor=true">
                                            ${training.name}
                                    </a>
                                </td>
                            </tr>
                            <c:set var="count" value="${count + 1}" scope="page"/>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

            </div>
            </c:if>
                <%--        block Users Management--%>
            <c:if test="${typeOperation == 'usersManagement'}">
            <div class="container-fluid">
                <jsp:useBean id="userService" class="com.epam.webapp.service.UserService"/>
                <h1><fmt:message key="usersManagement"/></h1>
                    <%--Message about saved changes--%>
                <c:if test="${changesSavedMessage != null}">
                    <div class="alert alert-danger" role="alert">
                            ${changesSavedMessage}
                        <c:set var="changesSavedMessage" value="${null}"/>
                    </div>
                </c:if>
                    <%--users table--%>
                <table class="table">
                    <thead>
                    <tr>
                        <th>No</th>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Login</th>
                        <th>Email</th>
                        <th>Type</th>
                        <th></th>
                        <th>Status</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="count" value="${1}"/>
                    <c:forEach var="user" items="${userService.allUser}">
                        <tr>
                            <td>${count}</td>
                            <td>${user.name}</td>
                            <td>${user.surname}</td>
                            <td>${user.login}</td>
                            <td>${user.email}</td>
                            <td>${fn:toLowerCase(user.type)}</td>
                                <%--form for changing users type--%>
                            <td>
                                <form class="form-inline" method="post" action="controller">
                                    <input type="hidden" name="command" value="update_user_type"/>
                                    <input type="hidden" name="userId" value="${user.id}"/>
                                    <div class="form-group">
                                        <select id="company" class="form-control" name="type">
                                            <option>${fn:toLowerCase(user.type)}</option>
                                            <c:forEach var="type" items="${userService.usersType()}">
                                                <option>${fn:toLowerCase(type)}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                        <%--                block change user status             --%>
                            </td>
                            <td>${user.status}</td>
                            <td>
                                <div class="form-group">
                                    <select id="changeType" class="form-control" name="status">
                                        <option>${user.status}</option>
                                        <c:forEach var="status" items="${userService.userStatuses()}">
                                            <option>${fn:toLowerCase(status)}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            <td>
                                <button type="submit" class="btn btn-warning"><fmt:message key="change"/></button>
                            </td>
                            </form>
                            </td>
                        </tr>
                        <c:set var="count" value="${count + 1}"/>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            </c:if>
            <script>
                $('#toggleState').on('click', function () {
                    var toggleBtn = $('#toggle');
                    toggleBtn.button('toggle');
                    toggleBtn.hasClass('active') ? toggleBtn.text('Включено') : toggleBtn.text('Выключено');
                });
            </script>

            <c:if test="${typeOperation == 'consultationManagement'}">
                <%--                    <jsp:useBean id="userService" class="com.epam.webapp.service.UserService"/>--%>

            <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
            <link rel="stylesheet" href="/resources/demos/style.css">
            <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
            <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
            <script>
                $(function () {
                    $("#datepicker").datepicker();
                });
            </script>
                    <c:if test="${messageOfferSent != null}">
                    <div class="alert alert-danger" role="alert">
                            ${messageOfferSent}
                        <c:set var="messageOfferSent" value="${null}"/>
                    </div>
                    </c:if>
                Предложить консультацию для ментора
                <br/>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="offer_date"/>
                    <p><fmt:message key="chooseDate"/>: <input type="text" id="datepicker" name="date" required></p>
                    <div class="form-group">
                        <jsp:useBean id="service" class="com.epam.webapp.service.UserService"/>

                        <div class="form-group">
                            <fmt:message key="choose_mentor"/>
                            <select id="chooseMentor" class="form-control" name="trainingId">
                                <c:forEach  var="mentor" items="${service.getAllMentors()}">
                                    <option value="${mentor.key.id}">${mentor.key.name} ${mentor.value.name} ${mentor.value.surname}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <button type="submit" class="btn-primary" > DONE </button>
                    <form>
</c:if>
                        <br/>
            <fmt:bundle basename="local" prefix="footer.">
                <fmt:message key="copyright"/>
            </fmt:bundle>
    </body>


    </html>
</fmt:bundle>
