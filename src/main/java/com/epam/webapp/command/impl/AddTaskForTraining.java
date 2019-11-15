package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.connectionpool.ConnectionPoolException;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.manager.MessageManager;
import com.epam.webapp.service.TrainingsService;

import javax.servlet.http.HttpServletRequest;

public class AddTaskForTraining implements Command {

  private static final String TRAINING_ID = "trainingId";
  private static final String TASK_NAME = "taskName";
  private static final String TASK_TEXT = "taskText";
  private static final String TRAINING_PAGE = "path.page.trainingsInformation";
  private static final String MESSAGE_ABOUT_CHANGES = "changesSavedMessage";
  private static final String MESSAGE_CHANGES_SAVED = "message.changesSaved";
  private static final String MESSAGE_CHANGES_ERROR = "message.changesError";
  @Override
  public String execute(HttpServletRequest request) throws CommandException, CommandException, ConnectionPoolException {
    TrainingsService trainingsService = new TrainingsService();
    int trainingId = Integer.parseInt(request.getParameter(TRAINING_ID));
    String taskName = request.getParameter(TASK_NAME);
    String taskText = request.getParameter(TASK_TEXT);
    boolean done = trainingsService.addTaskForTraining(trainingId, taskName, taskText);
    if (done) {
      request.getSession().setAttribute(MESSAGE_ABOUT_CHANGES, MessageManager.getProperty(MESSAGE_CHANGES_SAVED));
      return ConfigurationManager.getProperty(TRAINING_PAGE);
    }
    request.getSession().setAttribute(MESSAGE_ABOUT_CHANGES, MessageManager.getProperty(MESSAGE_CHANGES_ERROR));
    return ConfigurationManager.getProperty(TRAINING_PAGE);
  }
}
