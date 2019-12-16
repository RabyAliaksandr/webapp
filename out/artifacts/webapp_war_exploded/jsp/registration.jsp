<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 25.10.2019
  Time: 0:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:bundle basename="local" prefix="label.">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <html>
    <head><title>Registration</title></head>
    </head>
    <body>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="controller?command=main_page">Trainings Center</a>
            </div>
            <ul class="nav navbar-nav">
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
        <form name="registration" method="POST" action="controller">
            <input name="command" type="hidden" value="registration"/>
<%--            <input type="hidden" name="redirectToPage" value="cabinet"/>--%>

            <div class="col-md-4 mb-3">
                <label><fmt:message key="userName"/> </label>
                <div class="input-group">
                    <input type="text" value="${userFields.name}" name="name" class="form-control"
                           required pattern="^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$"
                           placeholder=
                        <fmt:message key="userName"/>>
                    <div class="invalid-feedback">
                        <p><font color="red">${messageValidationName}</font></p>
                    </div>
                </div>

                <label><fmt:message key="userSurname"/> </label>
                <div class="input-group">
                    <input type="text" name="surname" value="${userFields.surname}" class="form-control" required
                           pattern="^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$"
                           placeholder=
                        <fmt:message key="userSurname"/>>
                    <div class="invalid-feedback">
                        <p><font color="red"> ${messageValidationSurName}</font></p>
                    </div>
                </div>

                <label><fmt:message key="userLogin"/> </label>
                <div class="input-group">
                    <input type="text" name="login" value="${userFields.login}" class="form-control"
                           pattern="^([A-Z]?[a-z]{6,23})$"
                           required placeholder=
                        <fmt:message key="userLogin"/>>
                    <div class="invalid-feedback">
                        <p><font color="red">${messageValidationLogin}</font></p>
                        <p><font color="red">${messageLoginIsExist}</font></p>
                    </div>
                </div>
                <br/>
                <label><fmt:message key="userPassword"/> </label>
                <label><fmt:message key="correctPassword"/> </label>

                <div class="input-group">
                    <input type="password" name="password" class="form-control" required
                           pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9])\S{8,15}"
                           placeholder=
                        <fmt:message key="userPassword"/>>
                </div>
                <div class="invalid-feedback">
                        <p><font color="red"> ${messageValidationPassword}</font> </p>
                    <p><font color="red"> ${messageEmailIsExist}</font> </p>
                </div>
                <div class="input-group">
                    <input type="password" class="form-control" required name="repeatPassword"
                           pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9])\S{8,15}"
                           placeholder=
                        <fmt:message key="repeatPassword"/>>
                </div>
                <div class="invalid-feedback">
                        <p><font color="red"> ${messageRepeatPassword}</font> </p>
                </div>
                <label>Email</label>
                <div class="input-group">
                    <input type="text" name="email" value="${userFields.email}" class="form-control" required
                           pattern="^[-\w.]{6,30}+@([A-z0-9][-A-z0-9]+\.)+[A-z]{2,4}$"
                           placeholder="example@example.ex">
                    <div class="invalid-feedback">
                            <p><font color="red"> ${messageValidationEmail}</font> </p>
                    </div>
                </div>
                <input type="checkbox" class="check-box-table-cell" required>
                    <fmt:message key="termsOfUse"/>
                <button type="submit" class="btn-success"><fmt:message key="send"/></button>
        </form>
    </div>
    </div>
    <div>
        <h5 align="center">
            <c:import url="footer.jsp"/>
        </h5>
    </div>
    </body>
    </html>
</fmt:bundle>