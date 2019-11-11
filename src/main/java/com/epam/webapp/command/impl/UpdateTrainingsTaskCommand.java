package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.connectionpool.exception.ConnectionPoolException;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.manager.MessageManager;
import com.epam.webapp.service.TrainingsService;

import javax.servlet.http.HttpServletRequest;

public class UpdateTrainingsTaskCommand implements Command {

  private static final String TASK_ID =  "taskId";
  private static final String TASK_NAME = "taskName";
  private static final String TASK = "task";
  private static final String TASK_PAGE = "path.page.taskPage";
  private static final String MESSAGE_ABOUT_CHANGES = "changesSavedMessage";
  private static final String MESSAGE_CHANGES_SAVED = "message.changesSaved";
  private static final String MESSAGE_CHANGES_ERROR = "message.changesError";

  @Override
  public String execute(HttpServletRequest request) throws CommandException, CommandException, ConnectionPoolException {

    TrainingsService trainingsService = new TrainingsService();
    int taskId = Integer.parseInt(request.getParameter(TASK_ID));
    String taskName = request.getParameter(TASK_NAME);
    String task = request.getParameter(TASK);
    boolean done = trainingsService.updateTask(taskId, taskName, task);
    if (done) {
      request.getSession().setAttribute(MESSAGE_ABOUT_CHANGES, MessageManager.getProperty(MESSAGE_CHANGES_SAVED));
      return ConfigurationManager.getProperty(TASK_PAGE);
    }
    request.getSession().setAttribute(MESSAGE_ABOUT_CHANGES, MessageManager.getProperty(MESSAGE_CHANGES_ERROR));
    return ConfigurationManager.getProperty(TASK_PAGE);
  }
}
