<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 22.10.2019
  Time: 18:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="ru" scope="session"/>
<fmt:bundle basename="local" prefix="footer.">
    <html>
    <head><title>Footer</title></head>
    <body>
    <hr/>
    <fmt:message key="copyright"/>
    </body>

    </html>

</fmt:bundle>