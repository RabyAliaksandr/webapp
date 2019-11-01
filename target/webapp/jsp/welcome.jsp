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
    <% User user = (User) session.getAttribute("user");
    if (user.getType().equals("admin")) {
      %>
    <jsp:forward page="adminPage.jsp"></jsp:forward>
    <%
    }
    if (user.getType().equals("student")) {
    %>
    <jsp:forward page="studentPage.jsp"></jsp:forward>
    <% } %>
    <%
        if (user.getType().equals("mentor"))  {
    %>  <jsp:forward page="mentorPage.jsp"></jsp:forward>
    <% } %>

</head>
<body>

</body>
</html>
