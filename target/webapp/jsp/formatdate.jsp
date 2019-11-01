<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 23.10.2019
  Time: 21:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html><head><title>Формат даты</title></head>
<body>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:setLocale value="en-EN"/>
Вывод даты в формате English<br/>
Сегодня: <fmt:formatDate value="${now}" /><br/>
<fmt:setLocale value="ru-RU"/>
Вывод даты в формате Russian<br/>
Сегодня: <fmt:formatDate value="${now}" /><br/>
Стиль времени:
(short): <fmt:formatDate value="${now}" type="time" timeStyle="short" /><br/>
(medium):<fmt:formatDate value="${now}" type="time" timeStyle="medium" /><br/>
(long): <fmt:formatDate value="${now}" type="time" timeStyle="long" /><br/>
</body></html>