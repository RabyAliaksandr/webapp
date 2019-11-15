package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.connectionpool.ConnectionPoolException;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.manager.MessageManager;
import com.epam.webapp.service.TrainingsService;

import javax.servlet.http.HttpServletRequest;

public class AddTopicForTrainingCommand implements Command {


  private static final String TRAINING_ID = "trainingId";
  private static final String TOPICS_NAME = "topicsName";
  private static final String TOPICS_TEXT = "topicsText";
  private static final String TASK_PAGE = "path.page.trainingsInformation";
  private static final String MESSAGE_ABOUT_CHANGES = "changesSavedMessage";
  private static final String MESSAGE_CHANGES_SAVED = "message.changesSaved";
  private static final String MESSAGE_CHANGES_ERROR = "message.changesError";

  @Override
  public String execute(HttpServletRequest request) throws CommandException, CommandException, ConnectionPoolException {
    TrainingsService trainingsService = new TrainingsService();
    int trainingId = Integer.parseInt(request.getParameter(TRAINING_ID));
    String topicsName = request.getParameter(TOPICS_NAME);
    String topicsText = request.getParameter(TOPICS_TEXT);
    boolean done = trainingsService.addTopicForTraining(trainingId, topicsName, topicsText);
    if (done) {
      request.getSession().setAttribute(MESSAGE_ABOUT_CHANGES, MessageManager.getProperty(MESSAGE_CHANGES_SAVED));
      return ConfigurationManager.getProperty(TASK_PAGE);
    }
    request.getSession().setAttribute(MESSAGE_ABOUT_CHANGES, MessageManager.getProperty(MESSAGE_CHANGES_ERROR));
    return ConfigurationManager.getProperty(TASK_PAGE);
  }
}
