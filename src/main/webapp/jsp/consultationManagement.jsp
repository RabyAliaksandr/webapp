<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 15.11.2019
  Time: 9:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:bundle basename="local" prefix="label.">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="js/bootstrap-datetimepicker.js"></script>
    <script src="js/bootstrap-datetimepicker.min.js"></script>
    <link rel="stylesheet" href="style/bootstrap-datetimepicker.css"/>
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="style/bootstrap-datetimepicker.min.css"/>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/css/bootstrap-select.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/bootstrap-select.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/i18n/defaults-*.min.js"></script>
    <html>
    <head><title>Mentor page</title></head>
    </head>
    <body>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="controller?command=main_page">Trainings Center</a>
            </div>
            <ul class="nav navbar-nav">
                <li>
                    <a href="controller?command=trainings_page">
                        <fmt:message key="currentTrainings"/>
                    </a>
                </li>
                <li>
                    <a href="controller?command=log_out">
                        <fmt:message key="logout"/>
                    </a>
                </li>
                <li>
                    <a href="controller?command=consultation_for_mentor">
                        <fmt:message key="consultationManagement"/>
                    </a>
                </li>
            </ul>
            <form id="xxx" method="post" action="controller">
                <input type="hidden" name="command" value="set_local"/>
                <input type="hidden" name="redirectTo" value="true"/>
                <button form="xxx" name="local" value="${local == 'en' ? 'ru' : 'en'}"
                        class="btn-link" type="submit">
                        ${local == 'en' ? 'Ru' : 'En'}
                </button>
            </form>
        </div>
    </nav>
    <br/>
    <div class="container-fluid">
        <jsp:useBean id="consultationService"
                     class="com.epam.tc.service.impl.ConsultationServiceImpl"/>
        <c:if test="${messageAgreement != null}">
            <div class="alert alert-danger" role="alert">
                    ${messageAgreement}
                <c:set var="messageAgreement" value="${null}"/>
            </div>
        </c:if>
        <br/>

        <table class="table table_sort">
            <thead>
            <tr>
                    <%--               <th  class="fa fa-fw fa-sort">No</th>--%>
                <th><i class="fa fa-fw fa-sort"/>Name</th>
                <th><i class="fa fa-fw fa-sort"></i>Date</th>

            </tr>
            </thead>
            <tbody>
            <c:set var="count" value="${1}"/>
            <c:forEach var="training" items="${consultationService.findConsultationsOffer(user.id)}">
                <tr>
                    <td>${training.key.name}</td>
                    <td>${training.value}</td>
                    <td>
                        <form class="form-inline" method="post" action="controller">
                            <input type="hidden" name="redirectTo" value="true"/>
                            <input type="hidden" name="command" value="send_agreement"/>
                            <input type="hidden" name="trainingId" value="${training.key.id}"/>
                            <input type="hidden" name="date" value="${training.value}"/>
                            <div class="form-group">
                                <select id="agreement" class="selectpicker" name="agreement">
                                    <option value="true"><fmt:message key="confirm"/></option>
                                    <option value="false"><fmt:message key="refuse"/></option>
                                </select>
                            </div>
                                <%--                block change user status             --%>
                    </td>
                    <td>
                        <button type="submit" class="btn btn-warning"><fmt:message key="send"/></button>
                    </td>
                    </form>
                    </td>
                </tr>
                <c:set var="count" value="${count + 1}"/>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <script>
        document.addEventListener('DOMContentLoaded', () => {
            const getSort = ({target}) => {
                const order = (target.dataset.order = -(target.dataset.order || -1));
                const index = [...target.parentNode.cells].indexOf(target);
                const collator = new Intl.Collator(['en', 'ru'], {numeric: true});
                const comparator = (index, order) => (a, b) => order * collator.compare(
                    a.children[index].innerHTML,
                    b.children[index].innerHTML
                );
                for (const tBody of target.closest('table').tBodies)
                    tBody.append(...[...tBody.rows].sort(comparator(index, order)));
                for (const cell of target.parentNode.cells)
                    cell.classList.toggle('sorted', cell === target);
            };
            document.querySelectorAll('.table_sort thead').forEach(tableTH => tableTH.addEventListener('click', () => getSort(event)));
        });
    </script>
    </body>
    </html>
</fmt:bundle>