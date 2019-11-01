<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 29.10.2019
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:bundle basename="local" prefix="label.">

<html>
<head>
    <title>Information about Training</title>



<%--    <% int id = Integer.parseInt(request.getParameter("trainingId"));    %>--%>
    <c:set var="trainingId" value="${requestScope.trainingid}" />
    <c:import url="mainButtons.jsp"/>
    <c:out value="${requestScope.trainingid}"/>

    <br/>
    <jsp:useBean id="service" class="com.epam.webapp.service.TrainingsService"/>
    <fmt:message key="informationAboutTraining"/>

    <br/>
    <c:out value="${trainingid}"/>
    <table border="3">
        <tr>
            <td>

        <c:set var="count" value="0" />
        <c:set var="training" value="${service.getTrainingByIdTraining(trainingid)}" scope="session"/>
            <c:out value="${training.information}"/>
            </td>
        </tr>
    </table>
    <br/>
    <fmt:message key="trainedStudents"/>
    <jsp:useBean id="stedentList" class="com.epam.webapp.service.TrainingsService"/>
    <table border="3">
       <c:forEach var="student" items="${stedentList.getStudentsByIdTraining(trainingId)}">
           <tr>
               <td>
                   <c:out value="${student.name}"/>
               </td>
               <td>
                   <c:out value="${student.surname}"/>
               </td>
               <td>
                   <c:out value="${student.grade}"/>
                   <form name="alltrainings" method="post" action="controller">
                       <input type="hidden" name="command" value="trainings"/>
                       <input type="text" value=""/>
                       <input type="submit" value=<fmt:message key="rate"></fmt:message>/>
                   </form>
               </td>
           </tr>
       </c:forEach>
    </table>
</head>
<body>

</body>
</html>
</fmt:bundle>