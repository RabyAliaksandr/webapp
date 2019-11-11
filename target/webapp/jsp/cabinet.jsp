<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.epam.webapp.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 28.10.2019
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>

<body>
<c:choose>
    <c:when test="${user.type == 'ADMIN'}">
        <jsp:forward page="adminCabinet.jsp"></jsp:forward>
    </c:when>
    <c:when test="${user.type == 'STUDENT'}">
        <jsp:forward page="studentCabinet.jsp"></jsp:forward>
    </c:when>
    <c:when test="${user.type == 'MENTOR'}">
        <jsp:forward page="mentorCabinet.jsp"></jsp:forward>
    </c:when>
</c:choose>
</body>
</html>
