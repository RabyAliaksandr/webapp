package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.connectionpool.ConnectionPoolException;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.manager.MessageManager;
import com.epam.webapp.service.TrainingsService;

import javax.servlet.http.HttpServletRequest;

public class SetMarkCommand implements Command {

  private static final String TASK_ID = "taskId";
  private static final String STUDENT_ID = "studentId";
  private static final String MARK = "mark";
  private static final String TASK_PAGE = "path.page.topicForStudy";
  private static final String MESSAGE_ABOUT_CHANGES = "changesSavedMessage";
  private static final String MESSAGE_CHANGES_SAVED = "message.changesSaved";
  private static final String MESSAGE_CHANGES_ERROR = "message.changesError";
  @Override
  public String execute(HttpServletRequest request) throws CommandException, ConnectionPoolException {
    int taskId = Integer.parseInt(request.getParameter(TASK_ID));
    System.out.println(  "mark" + "ids" +  "tskid " + taskId);

    int studentId = Integer.parseInt(request.getParameter(STUDENT_ID));
    System.out.println(  "mark" + "ids" + studentId + "tskid " + taskId);

    int mark = Integer.parseInt(request.getParameter(MARK));
    System.out.println(mark + "mark" + "ids" + studentId + "tskid " + taskId);

    TrainingsService trainingsService = new TrainingsService();
    boolean done = trainingsService.gradeTask(studentId, taskId, mark);
    if (done) {
      request.getSession().setAttribute(MESSAGE_ABOUT_CHANGES, MessageManager.getProperty(MESSAGE_CHANGES_SAVED));
      return ConfigurationManager.getProperty(TASK_PAGE);
    }
    request.getSession().setAttribute(MESSAGE_ABOUT_CHANGES, MessageManager.getProperty(MESSAGE_CHANGES_ERROR));
    return ConfigurationManager.getProperty(TASK_PAGE);
  }
}
