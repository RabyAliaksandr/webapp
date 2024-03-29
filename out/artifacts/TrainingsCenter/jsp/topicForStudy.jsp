<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 06.11.2019
  Time: 23:05
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:bundle basename="local" prefix="label.">
    <jsp:useBean id="topicService" class="com.epam.tc.service.impl.TopicServiceImpl"/>
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
                <c:if test="${user.type != null}">
                    <li class="active">
                        <a href="controller?command=cabinet">
                            <fmt:message key="cabinet"/>
                        </a>
                    </li>

                </c:if>
                    <%--                is user is admin or mentor, then he can edit topic and task--%>
                <c:if test="${(user.type == 'ADMIN' && editor == true && user.status == 'UNBLOCKED') ||
                 (user.type == 'MENTOR' && editor == true)}">
                    <li>
                        <a href="controller?command=create_text&typeOperation=editTopic&topicId=${topicId}">
                            <fmt:message key="button.editDescription"/>
                        </a>
                    </li>
                    <li>
                        <a href="controller?command=delete_topic&topicId=${topicId}">
                            <fmt:message key="delete"/>
                        </a>
                    </li>
                </c:if>
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
        <%--            message about edit changes --%>
    <c:if test="${changesSavedMessage != null}">
        <div class="alert alert-danger" role="alert">
                ${changesSavedMessage}
            <c:set var="changesSavedMessage" value="${null}"/>
        </div>
    </c:if>
    <div class="container-fluid">
        <c:set var="topic" value="${topicService.findTopic(topicId)}"/>
        <h1>${topic.name}</h1>
        <br/>
        <p>${topic.topic}</p>
    </div>
        <%--    button learned topic--%>
    <c:if test="${user.type == 'STUDENT'}">
        <%--            message about done mark learned topic--%>
        <c:if test="${markDoneMessage != null}">
            <div class="alert alert-danger" role="alert">
                    ${markDoneMessage}
            </div>
        </c:if>
        <%--check status topic--%>
        <c:set var="checkMark" value="${topicService.checkTopicStatus(user.id, topicId)}"/>

        <c:choose>
            <c:when test="${checkMark == false}">
                <div class="container-fluid">
                    <form class="form-inline" method="post" action="controller">
                        <input type="hidden" name="redirectTo" value="true"/>
                        <input type="hidden" name="userId" value="${user.id}"/>
                        <input type="hidden" name="topicId" value="${topicId}"/>
                        <input type="hidden" name="command" value="mark_topic"/>
                        <button type="submit" class="btn btn-danger btn-lg btn-block"
                                style="width: 95%;margin: 5px auto;">
                            <fmt:message key="learned"/>
                        </button>
                    </form>
                </div>
            </c:when>

            <c:when test="${checkMark == true && markDoneMessage == null}">
                <div class="alert alert-danger" role="alert">
                    <fmt:bundle basename="local" prefix="message.">
                        <fmt:message key="markDone"/>
                    </fmt:bundle>
                </div>
            </c:when>
        </c:choose>
    </c:if>
    <c:set var="markDoneMessage" value="${null}"/>
    <div>
        <h5 align="center">
            <c:import url="footer.jsp"/>
        </h5>
    </div>
    </body>
    </html>
</fmt:bundle>