<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.epam.webapp.entity.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %><%--&lt;%&ndash;--%>
<%--  Created by IntelliJ IDEA.--%>
<%--  User: alex--%>
<%--  Date: 22.10.2019--%>
<%--  Time: 18:23--%>
<%--  To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head><title>Login</title></head>--%>
<%--<body>--%>
<%--<form name="loginForm" method="POST" action="controller">--%>
<%--    <input type="hidden" name="command" value="login"/>--%>
<%--    Login:<br/>--%>
<%--    <input type="text" name="login" value=""/>--%>
<%--    <br/>Password:<br/>--%>
<%--    <input type="password" name="password" value=""/>--%>
<%--    <br/>--%>
<%--    ${errorLoginPassMessage}--%>
<%--    <br/>--%>
<%--    ${wrongAction}--%>
<%--    <br/>--%>
<%--    ${nullPage}--%>
<%--    <br/>--%>
<%--    <input type="submit" value="Log in"/>--%>
<%--</form>--%>
<%--<form name="registration" method="POST" action="controller">   <input type="submit" name="command" value="registration"/>--%>
<%--</form>--%>
<%--<hr/>--%>
<%--&lt;%&ndash;<c:set var="admin" value="Raby" scope="request"/>&ndash;%&gt;--%>
<%--&lt;%&ndash;<c:import url="header.jsp"/>&ndash;%&gt;--%>
<%--&lt;%&ndash;<form action="${ pageContext.request.contextPath }/index.jsp">&ndash;%&gt;--%>
<%--&lt;%&ndash;    Content admin page<br/>&ndash;%&gt;--%>
<%--&lt;%&ndash;    <input type="submit" value="to Index">&ndash;%&gt;--%>
<%--&lt;%&ndash;</form>&ndash;%&gt;--%>
<%--&lt;%&ndash;<c:import url="footer.jsp"/>&ndash;%&gt;--%>
<%--</body>--%>
<%--</html>--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html><head><title>Login</title></head>
<body>
<form name="loginForm" method="POST" action="controller">
    <input type="hidden" name="command" value="log_in" />
    Login:<br/>
    <input type="text" name="login" value=""/>
    <br/>Password:<br/>
    <input type="password" name="password" value="" />
    <br/>
    ${errorLoginPassMessage}
    <br/>
    ${wrongAction}
    <br/>
    ${nullPage}
    <br/>
    <input type="submit" value="Log in"/>
    <br/>
</form><hr/>
<form name="redirectToRegister" method="POST" action="controller">
    <input type="hidden" name="command" value="registration_page" />
    <input type="submit" value="Sign Up"/>
    <br/>
</form><hr/>
<c:import url="mainButtons.jsp"/>
</body></html>
    </body></html>