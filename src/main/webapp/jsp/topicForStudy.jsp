<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 06.11.2019
  Time: 23:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:bundle basename="local" prefix="label.">
    <jsp:useBean id="trainingService" class="com.epam.webapp.service.TrainingsService"/>
    <html>
    <head>
        <title><fmt:message key="topics"/></title>
            ${topicName}
        <br/>
            ${trainingService.getTopic(trainingId, topicName)}
    </head>
    <body>

    </body>
    </html>
</fmt:bundle>