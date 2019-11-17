<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 22.10.2019
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Error Page</title></head>
<body>
<ul>
    <li>Exception type: <c:out value="${requestScope['javax.servlet.error.exception_type']}" /></li>
    <li>Exception message: <c:out value="${requestScope['javax.servlet.error.message']}" /></li>
    <li>Request URI: <c:out value="${requestScope['javax.servlet.error.request_uri']}" /></li>
    <li>Servlet name: <c:out value="${requestScope['javax.servlet.error.servlet_name']}" /></li>
    <li>Status code: <c:out value="${requestScope['javax.servlet.error.status_code']}" /></li>

    <c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>

    <c:if test="${exception != null}">
        <h4>${exception}</h4>
        <c:forEach var="stackTraceElem" items="${exception.stackTrace}">
            <c:out value="${stackTraceElem}"/><br/>
        </c:forEach>
    </c:if>
</ul>
</body>
</html>