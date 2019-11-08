<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:bundle basename="local" prefix="label.">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
    <link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />
    <html>
    <head><title>Welcome</title></head>
    </head>
    <body>

    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="controller?command=main_page">Trainings Center</a>
            </div>
            <ul class="nav navbar-nav">
                <li><a href="controller?command=log_in_page"><fmt:message key="login"/></a></li>
                <li><a href="controller?command=registration_page"><fmt:message key="signUp"/></a></li>
                <li><a href="controller?command=trainings_page"><fmt:message key="currentTrainings"/></a>
                </li>
            </ul>
        </div>
    </nav>
    <br/>
    <hr/>
    Here will be information about this resource
    <hr/>
<%--    <form id="www" method="post" action="controller">--%>
<%--        <input type="hidden" name="command" value="get_text"/>--%>

<%--    <textarea id="editor" form="www" name="text_topic">--%>
<%--    </textarea>--%>
<%--        <input type="submit" value=<fmt:message key="send"/>/>--%>
<%--    </form>--%>
<%--    <script type="text/javascript">--%>
<%--        $(document).ready(function () {--%>
<%--            $("#editor").editor({--%>
<%--                uiLibrary: 'bootstrap'--%>
<%--            });--%>
<%--        });--%>
<%--    </script>--%>
    </body>
    </html>
</fmt:bundle>