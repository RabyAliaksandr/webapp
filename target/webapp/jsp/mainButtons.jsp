<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 28.10.2019
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="ru" scope="session" />
<fmt:bundle basename="local" prefix = "label." >

    <html><head>
    </head>
    <body>
    <script>
        function goBack()
        {
            window.history.back();
        }
    </script>
    <input type="submit" onclick="goBack()" value=<fmt:message key="buttonBack"/>>
    <br/>
    <br/>
    <form name="redirectToMainPage" method="POST" action="controller">
        <input type="hidden" name="command" value="tomain"/>
        <input type="submit" value=<fmt:message key="tomain"></fmt:message>>
    </form>
    <form name="alltrainings" method="post" action="controller">
        <input type="hidden" name="command" value="trainings"/>
        <input type="submit" value="All training"/>
    </form>
    <body>
    <form name="Logout" method="POST" action="controller">
        <input type="hidden" name="command" value="logout"/>
        <input type="submit" value=<fmt:message key="logout"></fmt:message>>
    </form>

</fmt:bundle>
