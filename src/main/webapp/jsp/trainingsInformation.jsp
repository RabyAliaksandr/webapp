<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 29.10.2019
  Time: 22:13
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
    <head><title><fmt:message key="informationAboutTraining"/></title></head>
    </head>
    <body>
    <c:set var="trainingId" value="${sessionScope.trainingId}"/>
    <jsp:useBean id="trainingService" class="com.epam.webapp.service.impl.TrainingsServiceImpl"/>
    <jsp:useBean id="userService" class="com.epam.webapp.service.impl.UserServiceImpl"/>
    <c:set var="training" value="${trainingService.findTrainingByIdTraining(trainingId)}" scope="session"/>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="controller?command=main_page">
                    Trainings Center
                </a>
            </div>
            <ul class="nav navbar-nav">
                    <%--                link cabinet only registred users--%>
                <c:if test="${user.type != null}">
                    <li class="active">
                        <a href="controller?command=cabinet">
                            <fmt:message key="cabinet"/>
                        </a>
                    </li>
                </c:if>
                <li>
                    <a href="controller?command=trainings_page">
                        <fmt:message key="currentTrainings"/>
                    </a>
                </li>
                    <%--    if mark editor is true then user can edit training--%>
                <c:if test="${editor == true}">
                    <li>
                        <a href="controller?command=create_text&typeOperation=edit&trainingId=${trainingId}">
                            <fmt:message key="button.editDescription"/>
                        </a>
                    </li>
                    <li>
                        <a href="controller?command=create_text&typeOperation=addTopic&trainingId=${trainingId}">
                            <fmt:message key="button.addTopic"/>
                        </a>
                    </li>
                    <li>
                        <a href="controller?command=create_text&typeOperation=addTask&trainingId=${trainingId}">
                            <fmt:message key="button.addTask"/>
                        </a>
                    </li>
                    <c:if test="${user.type == 'ADMIN'}">
                        <li>
                            <a href="controller?command=close_reception&trainingId=${trainingId}">
                                <fmt:message key="closeReception"/>
                            </a>
                        </li>
                        <li>
                            <a href="controller?command=delete_training&trainingId=${trainingId}">
                                <fmt:message key="deleteTraining"/>
                            </a>
                        </li>
                    </c:if>
                </c:if>
                    <%--                    if user is mentor then he can manage students--%>
                <c:if test="${user.type == 'MENTOR' && user.status == 'UNBLOCKED'}">
                    <li>
                        <a href="controller?command=student_management&trainingId=${trainingId}">
                            <fmt:message key="button.studentsManagement"/>
                        </a>
                    </li>
                </c:if>
                <c:if test="${user.type == 'STUDENT' && user.status == 'UNBLOCKED'}">
                    <li>
                        <a href="controller?command=order_consultation&trainingId=${trainingId}&studentId=${user.id}">
                            <fmt:message key="orderConsultation"/>
                        </a>
                    </li>
                </c:if>
                <c:if test="${user.type != null}">
                    <li>
                        <a href="controller?command=log_out">
                            <fmt:message key="logout"/>
                        </a>
                    </li>
                </c:if>
                <form id="xxx" method="post" action="controller">
                    <input type="hidden" name="command" value="set_local"/>
                    <input type="hidden" name="redirectTo" value="true"/>
                    <li>
                        <button form="xxx" name="local" value="${local == 'en' ? 'ru' : 'en'}"
                                class="btn-link" type="submit">
                                ${local == 'en' ? 'Ru' : 'En'}
                        </button>
                    </li>
                </form>
            </ul>
        </div>
    </nav>
    <br/>
    <title><fmt:message key="informationAboutTraining"/></title>
    <br/>
    <br/>
    <br/>
    <c:if test="${messageCloseReception != null}">
        <div class="alert alert-danger" role="alert">
                ${messageCloseReception}
            <c:set var="changesSavedMessage" value="${null}"/>
        </div>
    </c:if>

    <c:if test="${messageFeedback != null}">
        <div class="alert alert-danger" role="alert">
                ${messageFeedback}
            <c:set var="messageFeedback" value="${null}"/>
        </div>
    </c:if>

        <%--            message about edit changes --%>

    <c:if test="${changesSavedMessage != null}">
        <div class="alert alert-danger" role="alert">
                ${changesSavedMessage}
            <c:set var="changesSavedMessage" value="${null}"/>
        </div>
    </c:if>
    <section class="a">
        <div class="container-fluid">
            <h1><fmt:message key="informationAboutTraining"/></h1>
            <br/>
                ${training.information}
            <br/>
            <c:if test="${training.status == false}">
                <label><font color="red"><fmt:message key="receptionIsClose"/></font></label>
            </c:if>
            <br/>
            <c:if test="${sessionScope.user.type == 'STUDENT' && training.status == true && user.status == 'UNBLOCKED'}">
                <c:if test="${userService.checkEnrolled(user.id, trainingId) == false}">
                    <form name="addTrainingToStudent" method="POST" action="controller">
                        <input type="hidden" name="redirectTo" value="true"/>
                        <input type="hidden" name="command" value="add_training_to_student"/>
                        <input type="hidden" name="userId" value="${user.id}"/>
                        <input type="hidden" name="trainingId" value="${trainingId}"/>
                        <input type="submit" value=<fmt:message key="addTraining"></fmt:message>/>
                    </form>
                </c:if>
                <c:if test="${userService.checkEnrolled(user.id, trainingId) == true}">
                    <fmt:message key="enrolledTraining"/>
                </c:if>
            </c:if>
        </div>
    </section>
    <br/>
    <section class="b">
        <div class="col-lg-6 col-md-6">
            <div class="container-fluid">
                <c:set var="checkMark" value="${trainingService.checkTrainingStatusForStudent(user.id, trainingId)}"/>
                    <%--        if student on training or admin or mentor--%>
                <c:if test="${checkMark == true && user.status == 'UNBLOCKED' ||
                user.type == 'ADMIN' && user.status == 'UNBLOCKED' ||
                user.type == 'MENTOR' && user.status == 'UNBLOCKED'}">
                    <h3>
                        <fmt:message key="listTopics"/>
                    </h3>
                    <br/>
                    <%--table topics for training--%>
                    <select name="state" id="topicRows">
                        <option value="5000">All</option>
                        <option value="5">5</option>
                        <option value="10">10</option>
                    </select>
                    <table class="table" id="topics">
                        <thead>
                        <tr>
                            <th>No</th>
                            <th><fmt:message key="topicsName"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:set var="count" value="1"/>
                        <c:forEach var="topic" items="${trainingService.findTopicsForTraining(trainingId)}">
                            <tr>
                                <td>${count}</td>
                                <td>
                                    <a href="controller?command=topic_page&trainingId=${trainingId}&topicId=${topic.id}">
                                            ${topic.name}
                                    </a>
                                </td>
                            </tr>
                            <c:set var="count" value="${count + 1}"/>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class='pagination-container'>
                        <nav>
                            <ul class="pagination">

                                <li data-page="prev">
                                    <span> < <span class="sr-only">(current)</span></span>
                                </li>
                                <li data-page="next" id="prevTopic">
                                    <span> > <span class="sr-only">(current)</span></span>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </c:if>
            </div>
        </div>
        </div>
            <%--        if student on training or admin or mentor--%>
        <c:if test="${checkMark == true && user.status == 'UNBLOCKED'||
        user.type == 'ADMIN' && user.status == 'UNBLOCKED' ||
         user.type == 'MENTOR' && user.status == 'UNBLOCKED'}">
        <div class="col-lg-6 col-md-6">
            <div class="container-fluid">
                <h3>
                    <fmt:message key="listTask"/>
                </h3>
                <br/>
                    <%--table taks for training--%>
                <div class="container">

                        <%--                    <div class="form-group"> 	<!--		Show Numbers Of Rows 		-->--%>
                    <select name="state" id="maxRows">
                        <option value="5000">All</option>
                        <option value="5">5</option>
                        <option value="10">10</option>
                    </select>
                    <table class="table" id="tasks">
                        <thead>
                        <tr>
                            <th>No</th>
                            <th><fmt:message key="tasksName"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:set var="count" value="1"/>
                        <c:forEach var="task" items="${trainingService.findTasksListForTraining(trainingId)}">
                            <tr>
                                <td>${count}</td>
                                <td>
                                    <a href="controller?command=task_page&trainingId=${trainingId}&taskId=${task.id}">
                                            ${task.name}
                                    </a>
                                </td>
                            </tr>
                            <c:set var="count" value="${count + 1}"/>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class='pagination-container'>
                        <nav>
                            <ul class="pagination">
                                <li data-page="prev">
                                    <span> < <span class="sr-only">(current)</span></span>
                                </li>
                                <li data-page="next" id="prev">
                                    <span> > <span class="sr-only">(current)</span></span>
                                </li>
                            </ul>
                        </nav>
                    </div>
                    </c:if>
                    <script>
                        getPagination('#tasks');
                        // getPagination('.pagan');
                        //getPagination('table');
                        function getPagination(table) {
                            var lastPage = 1;
                            $('#maxRows').on('change', function (evt) {
                                //$('.paginationprev').html('');						// reset pagination
                                lastPage = 1;
                                $('.pagination').find("li").slice(1, -1).remove();
                                var trnum = 0;									// reset tr counter
                                var maxRows = parseInt($(this).val());			// get Max Rows from select option

                                if (maxRows == 5000) {

                                    $('.pagination').hide();
                                } else {

                                    $('.pagination').show();
                                }

                                var totalRows = $(table + ' tbody tr').length;		// numbers of rows
                                $(table + ' tr:gt(0)').each(function () {			// each TR in  table and not the header
                                    trnum++;									// Start Counter
                                    if (trnum > maxRows) {						// if tr number gt maxRows

                                        $(this).hide();							// fade it out
                                    }
                                    if (trnum <= maxRows) {
                                        $(this).show();
                                    }// else fade in Important in case if it ..
                                });											//  was fade out to fade it in
                                if (totalRows > maxRows) {						// if tr total rows gt max rows option
                                    var pagenum = Math.ceil(totalRows / maxRows);	// ceil total(rows/maxrows) to get ..
                                    //	numbers of pages
                                    for (var i = 1; i <= pagenum;) {			// for each page append pagination li
                                        $('.pagination #prev').before('<li data-page="' + i + '">\
								      <span>' + i++ + '<span class="sr-only">(current)</span></span>\
								    </li>').show();
                                    }											// end for i
                                } 												// end if row count > max rows
                                $('.pagination [data-page="1"]').addClass('active'); // add active class to the first li
                                $('.pagination li').on('click', function (evt) {		// on click each page
                                    evt.stopImmediatePropagation();
                                    evt.preventDefault();
                                    var pageNum = $(this).attr('data-page');	// get it's number
                                    var maxRows = parseInt($('#maxRows').val());			// get Max Rows from select option
                                    if (pageNum == "prev") {
                                        if (lastPage == 1) {
                                            return;
                                        }
                                        pageNum = --lastPage;
                                    }
                                    if (pageNum == "next") {
                                        if (lastPage == ($('.pagination li').length - 2)) {
                                            return;
                                        }
                                        pageNum = ++lastPage;
                                    }
                                    lastPage = pageNum;
                                    var trIndex = 0;							// reset tr counter
                                    $('.pagination li').removeClass('active');	// remove active class from all li
                                    $('.pagination [data-page="' + lastPage + '"]').addClass('active');// add active class to the clicked
                                    // $(this).addClass('active');					// add active class to the clicked
                                    $(table + ' tr:gt(0)').each(function () {		// each tr in table not the header
                                        trIndex++;								// tr index counter
                                        // if tr index gt maxRows*pageNum or lt maxRows*pageNum-maxRows fade if out
                                        if (trIndex > (maxRows * pageNum) || trIndex <= ((maxRows * pageNum) - maxRows)) {
                                            $(this).hide();
                                        } else {
                                            $(this).show();
                                        } 				//else fade in
                                    }); 										// end of for each tr in table
                                });										// end of on click pagination list

                            }).val(5).change();
                        }
                    </script>

                            <script>
                                getPagination('#topics');
                                // getPagination('.pagan');
                                //getPagination('table');
                                function getPagination(table) {
                                    var lastPage = 1;
                                    $('#topicRows').on('change', function (evt) {
                                        //$('.paginationprev').html('');						// reset pagination
                                        lastPage = 1;
                                        $('.pagination').find("li").slice(1, -1).remove();
                                        var trnum = 0;									// reset tr counter
                                        var maxRows = parseInt($(this).val());			// get Max Rows from select option

                                        if (maxRows == 5000) {

                                            $('.pagination').hide();
                                        } else {

                                            $('.pagination').show();
                                        }

                                        var totalRows = $(table + ' tbody tr').length;		// numbers of rows
                                        $(table + ' tr:gt(0)').each(function () {			// each TR in  table and not the header
                                            trnum++;									// Start Counter
                                            if (trnum > maxRows) {						// if tr number gt maxRows

                                                $(this).hide();							// fade it out
                                            }
                                            if (trnum <= maxRows) {
                                                $(this).show();
                                            }// else fade in Important in case if it ..
                                        });											//  was fade out to fade it in
                                        if (totalRows > maxRows) {						// if tr total rows gt max rows option
                                            var pagenum = Math.ceil(totalRows / maxRows);	// ceil total(rows/maxrows) to get ..
                                            //	numbers of pages
                                            for (var i = 1; i <= pagenum;) {			// for each page append pagination li
                                                $('.pagination #prevTopic').before('<li data-page="' + i + '">\
								      <span>' + i++ + '<span class="sr-only">(current)</span></span>\
								    </li>').show();
                                            }											// end for i
                                        } 												// end if row count > max rows
                                        $('.pagination [data-page="1"]').addClass('active'); // add active class to the first li
                                        $('.pagination li').on('click', function (evt) {		// on click each page
                                            evt.stopImmediatePropagation();
                                            evt.preventDefault();
                                            var pageNum = $(this).attr('data-page');	// get it's number
                                            var maxRows = parseInt($('#topicRows').val());			// get Max Rows from select option
                                            if (pageNum == "prev") {
                                                if (lastPage == 1) {
                                                    return;
                                                }
                                                pageNum = --lastPage;
                                            }
                                            if (pageNum == "next") {
                                                if (lastPage == ($('.pagination li').length - 2)) {
                                                    return;
                                                }
                                                pageNum = ++lastPage;
                                            }
                                            lastPage = pageNum;
                                            var trIndex = 0;							// reset tr counter
                                            $('.pagination li').removeClass('active');	// remove active class from all li
                                            $('.pagination [data-page="' + lastPage + '"]').addClass('active');// add active class to the clicked
                                            // $(this).addClass('active');					// add active class to the clicked
                                            $(table + ' tr:gt(0)').each(function () {		// each tr in table not the header
                                                trIndex++;								// tr index counter
                                                // if tr index gt maxRows*pageNum or lt maxRows*pageNum-maxRows fade if out
                                                if (trIndex > (maxRows * pageNum) || trIndex <= ((maxRows * pageNum) - maxRows)) {
                                                    $(this).hide();
                                                } else {
                                                    $(this).show();
                                                } 				//else fade in
                                            }); 										// end of for each tr in table
                                        });										// end of on click pagination list

                                    }).val(5).change();
                                }
                            </script>
                </div>
            </div>
        </div>
    </section>
    <c:set var="markDoneMessage" value="${null}"/>
    </body>
    </html>
</fmt:bundle>


