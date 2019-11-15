<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 15.11.2019
  Time: 9:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:bundle basename="local" prefix="label.">
    <html>
    <head>
        <title><fmt:message key="consultationManagement"/></title>
    </head>
    <body>
        <div class="container-fluid">
            Предложить консультацию для ментора
            <br/>
            <form name="offerConsultation" method="post" action="controller">
                <div class="input-append date form_datetime" data-date="2012-12-21T15:25:00Z">
                    <input size="16" type="text" value="" readonly name="offerConsultation">
                    <span class="add-on"><i class="icon-remove"></i></span>
                    <span class="add-on"><i class="icon-th"></i></span>
                </div>
                <input type="text" id="mirror_field" value="" readonly />
            </form>
        <script type="text/javascript">
            $(".form_datetime").datetimepicker({
                format: "dd MM yyyy - hh:ii",
                linkField: "mirror_field",
                linkFormat: "yyyy-mm-dd hh:ii"
            });
        </script>
    </body>
    </html>
</fmt:bundle>