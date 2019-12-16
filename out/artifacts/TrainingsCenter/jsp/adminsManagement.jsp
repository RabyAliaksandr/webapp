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
<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:bundle basename="local" prefix="label.">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script type="text/javascript" charset="utf8"
            src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>
    <script src="js/bootstrap-datetimepicker.js"></script>
    <script src="js/bootstrap-datetimepicker.min.js"></script>
    <link rel="stylesheet" href="style/bootstrap-datetimepicker.css"/>
    <link rel="stylesheet" href="style/bootstrap-datetimepicker.min.css"/>
    <html>

    <head><title><fmt:message key="administration"/></title></head>
    </head>
    <body>
    <nav class="navbar navbar-default">
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
            <input type="hidden" name="command" value="set_local_cabinet"/>
            <input type="hidden" name="redirectTo" value="true"/>
            <button form="xxx" name="local" value="${local == 'en' ? 'ru' : 'en'}"
                    class="btn-link" type="submit">
                    ${local == 'en' ? 'Ru' : 'En'}
            </button>
        </form>
    </nav>
    <br/>
    <div class="container-fluid">
            <%--        block Traininngs Management--%>
        <c:if test="${typeOperation == 'trainingManagement'}">
            <div class="col-xs-1">
                <jsp:useBean id="gettrainings"
                             class="com.epam.tc.service.impl.TrainingsServiceImpl"/>
                <c:set var="count" value="1"/>
                <div class="container">
                    <h2><fmt:message key="currentTrainings"/></h2>
                    <table>
                        <thead>
                        <tr>
                            <th>No</th>
                            <th><fmt:message key="name"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="training"
                                   items="${gettrainings.findAllTrainings()}">
                            <tr>
                                <td>${count}</td>
                                <td>
                                    <a href="controller?command=trainings_information_page&trainingId=${training.id}&editor=true">
                                            ${training.name}
                                    </a>
                                </td>
                            </tr>
                            <c:set var="count"
                                   value="${count + 1}" scope="page"/>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:if>
            <%--        block Users Management--%>
        <c:if test="${typeOperation == 'usersManagement'}">
            <div class="container-fluid">
                <jsp:useBean id="userService"
                             class="com.epam.tc.service.impl.UserServiceImpl"/>
                <h1><fmt:message key="usersManagement"/></h1>
                    <%--Message about saved changes--%>
                <c:if test="${changesSavedMessage != null}">
                    <div class="alert alert-danger" role="alert">
                            ${changesSavedMessage}
                        <c:set var="changesSavedMessage" value="${null}"/>
                    </div>
                </c:if>
                <c:if test="${messageDeleteUser != null}">
                    <div class="alert alert-danger" role="alert">
                            ${messageDeleteUser}
                        <c:set var="messageDeleteUser" value="${null}"/>
                    </div>
                </c:if>
                    <%--users table--%>
                <table>
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
                    <c:forEach var="users" items="${userService.allUser}">
                        <c:if test="${users.id != user.id}">
                            <tr>
                                <td>${count}</td>
                                <td>${users.name}</td>
                                <td>${users.surname}</td>
                                <td>${users.login}</td>
                                <td>${users.email}</td>
                                <td>${fn:toLowerCase(users.type)}</td>
                                    <%--form for changing users type--%>
                                <td>
                                    <form class="form-inline" method="post" action="controller">
                                        <input type="hidden" name="redirectTo" value="true"/>
                                        <input type="hidden" name="command" value="update_user_type"/>
                                        <input type="hidden" name="userId" value="${users.id}"/>
                                        <div class="form-group">
                                            <select id="company" class="form-control" name="type" required>
                                                <option>${fn:toLowerCase(users.type)}</option>
                                                <c:forEach var="type" items="${userService.usersType()}">
                                                    <option>${fn:toLowerCase(type)}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                            <%--                block change user status             --%>
                                </td>
                                <td>${users.status}</td>
                                <td>
                                    <div class="form-group">
                                        <select id="changeType" class="form-control" name="status">
                                            <option>${users.status}</option>
                                            <c:forEach var="status" items="${userService.userStatuses()}">
                                                <option>${fn:toLowerCase(status)}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                <td>
                                    <button type="submit" class="btn btn-warning">
                                        <fmt:message key="change"/>
                                    </button>
                                </td>
                                </form>
                                </td>
                            </tr>
                            <c:set var="count" value="${count + 1}"/>
                        </c:if>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
        <c:if test="${typeOperation == 'consultationManagement'}">
            <%--                    <jsp:useBean id="userService" class="UserServiceImpl"/>--%>

            <c:if test="${messageOfferSent != null}">
                <div class="alert alert-danger" role="alert">
                        ${messageOfferSent}
                    <c:set var="messageOfferSent" value="${null}"/>
                </div>
            </c:if>
            <label><fmt:message key="offerToMentor"/> </label>
            <br/>
            <form action="controller" method="post">
                <input type="hidden" name="redirectTo" value="true"/>
                <input type="hidden" name="command" value="offer_date"/>
                <p><fmt:message key="chooseDate"/>:
                    <input type="text" id="datepicker" name="date" required></p>
                <div class="container-fluid">
                    <jsp:useBean id="service"
                                 class="com.epam.tc.service.impl.UserServiceImpl"/>
                    <div class="form-group">
                        <fmt:message key="choose_mentor"/>
                        <select id="chooseMentor" class="form-control" name="trainingId" required>
                            <c:forEach var="mentor" items="${service.findMentorsAndTrainings()}">
                                <option value="${mentor.key.id}">${mentor.key.name} ${mentor.value.name} ${mentor.value.surname}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <label><fmt:message key="priceConsultation"/></label>
                <input type="text"
                       name="price"
                       pattern="(0\.((0[1-9]{1})|([1-9]{1}([0-9]{1})?)))|(([1-9]+[0-9]*)(\.([0-9]{1,2}))?)"
                       maxlength="6"
                       class="form-control" placeholder="100.00" required/>
                <button type="submit" class="btn-primary"><fmt:message key="send"/></button>
            </form>
        </c:if>
        <br/>

        <script>
            $(document).ready(function () {
                $('table').DataTable({
                    "sDom": '<"top"i>rt<"bottom"lp><"clear">',
                    "info": false
                });
            });
        </script>
        <script>
            $(function () {
                $("#datepicker").datepicker();
            });
        </script>
    </div>
    <div>
        <h5 align="center">
            <c:import url="footer.jsp"/>
        </h5>
    </div>
    </body>
    </html>
</fmt:bundle>
