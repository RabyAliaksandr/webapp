<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 22.10.2019
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <fmt:setLocale value="ru" scope="session" />
    <fmt:bundle basename="local" prefix = "label." >

    <html><head>
        <title><fmt:message key="title" /></title>
        <br/>
    </head>
    <body>
<c:import url="mainButtons.jsp"/>



    <fmt:bundle basename="local" prefix="footer.">
        <fmt:message key="copyright"/>
    </fmt:bundle>
    </body></html>
</fmt:bundle>
