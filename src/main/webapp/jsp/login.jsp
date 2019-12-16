<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:bundle basename="local" prefix="label.">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <html>
    <head><title><fmt:message key="login"/> </title></head>
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
    <div class="container-fluid">
        <form name="loginForm" method="POST" action="controller">
            <input type="hidden" name="command" value="log_in"/>
            <fmt:message key="userLogin"/> :<br/>
            <input type="text" name="login" value=""/>
            <br/><fmt:message key="userPassword"/> :<br/>
            <input type="password" name="password" value=""/>
            <br/>
              <label><font color="red">${errorLoginPassMessage}</font> </label>
            <br/>
<%--            <label><font color="red"> ${wrongAction}</font> </label>--%>
            <br/>
            <c:if test="${wrongAction != null}">
            <label><font color="red"><fmt:message key="hack"/></font> </label>
            </c:if>
            <br/>
            <input type="submit" value=<fmt:message key="login"/>>
            <br/>
        </form>
        <hr/>
    </div>
    <div>
        <h5 align="center">
            <c:import url="footer.jsp"/>
        </h5>
    </div>
    </body>
    </html>
</fmt:bundle>