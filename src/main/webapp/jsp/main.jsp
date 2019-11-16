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
    <style>
        body {
            background-image: url('./images/background2.jpg');
            background-position: center center;
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-size: cover;
        }
    </style>
    <head><title>Welcome</title></head>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="controller?command=main_page">Trainings Center</a>
            </div>
            <ul class="nav navbar-nav">
                <c:if test="${user.type == null}">
                    <li><a href="controller?command=log_in_page"><fmt:message key="login"/></a></li>
                    <li><a href="controller?command=registration_page"><fmt:message key="signUp"/></a></li>
                </c:if>
                <li><a href="controller?command=trainings_page"><fmt:message key="currentTrainings"/></a>
                    <c:if test="${user.type != null}">
                <li class="active"><a href="controller?command=cabinet"><fmt:message key="cabinet"/></a></li>
                <li><a href="controller?command=log_out"><fmt:message key="logout"/></a></li>
                </c:if>
                </li>
            </ul>
        </div>
    </nav>
    <br/>
    <body>
<%--        <jsp:useBean id="t" class="com.epam.webapp.service.UserService"/>--%>

<%--        <div class="form-group">--%>
<%--            <fmt:message key="choose_mentor"/>--%>
<%--            <select id="chooseMentor" class="form-control" name="status">--%>
<%--                <c:forEach  var="mentor" items="${t.getAllMentors()}">--%>
<%--                    <option>     ${mentor.name}</option>--%>
<%--                </c:forEach>--%>
<%--            </select>--%>
<%--        </div>--%>


    </body>

    <html></html>
</fmt:bundle>