<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 01.12.2019
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
    <%@ taglib prefix="ctg" uri="/WEB-INF/tld/tableTag.tld"%><html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:bundle basename="local" prefix="label.">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script type="text/javascript" charset="utf8"
            src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>
    <script src="js/bootstrap-datetimepicker.js"></script>
    <script src="js/bootstrap-datetimepicker.min.js"></script>
    <link rel="stylesheet" href="style/bootstrap-datetimepicker.css"/>
    <link rel="stylesheet" href="style/bootstrap-datetimepicker.min.css"/>
    <html>

    <head><title><fmt:message key="administration"/></title></head>
    </head>
    <body>
    <nav class="navbar navbar-default">
        <div class="navbar-header">
            <a class="navbar-brand" href="controller?command=main_page">
                Trainings Center
            </a>
        </div>
        <ul class="nav navbar-nav">
            <li>
                <a href="controller?command=trainings_page">
                    <fmt:message key="currentTrainings"/>
                </a>
            </li>
            <li>
                <a href="controller?command=cabinet">
                    <fmt:message key="cabinet"/>
                </a>
            </li>
            <li>
                <a href="controller?command=log_out">
                    <fmt:message key="logout"/>
                </a>
            </li>
            <c:if test="${typeOperation == 'trainingManagement'}">
                <li>
                    <a href="controller?command=create_text&typeOperation=createTraining">
                        <fmt:message key="create_training"/>
                    </a>
                </li>
            </c:if>
        </ul>
        <form id="xxx" method="post" action="controller">
            <input type="hidden" name="command" value="set_local_cabinet"/>
            <input type="hidden" name="redirectTo" value="true"/>
            <button form="xxx" name="local" value="${local == 'en' ? 'ru' : 'en'}"
                    class="btn-link" type="submit">
                    ${local == 'en' ? 'Ru' : 'En'}
            </button>
        </form>
    </nav>
    <br/>
<div class="container-fluid">


    <ctg:tableTag local="${local}">

    </ctg:tableTag>

    <fmt:message key="hello"/>
    <fmt:message key="paymentDate"/>

</div>
    </body>
    <script>
        $(document).ready(function () {
            $('table').DataTable({
                "sDom": '<"top"i>rt<"bottom"lp><"clear">',
                "info": false
            });
        });
    </script>
<%--    <script>--%>
<%--        $(function () {--%>
<%--            $("#datepicker").datepicker();--%>
<%--        });--%>
<%--    </script>--%>
    </html>
</fmt:bundle>