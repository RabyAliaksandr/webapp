package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.connectionpool.exception.ConnectionPoolException;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.manager.MessageManager;
import com.epam.webapp.service.TrainingsService;

import javax.servlet.http.HttpServletRequest;

public class UpdateTrainingsTopicCommand implements Command {

  private static final String TOPIC_NAME = "topicName";
  private static final String TOPIC_NEW_NAME = "topicNewName";
  private static final String TOPIC = "topic";
  private static final String TRAINING_ID = "trainingId";
  private static final String TOPIC_PAGE = "path.page.topicForStudy";
  private static final String MESSAGE_ABOUT_CHANGES = "changesSavedMessage";
  private static final String MESSAGE_CHANGES_SAVED = "message.changesSaved";
  private static final String MESSAGE_CHANGES_ERROR = "message.changesError";


  @Override
  public String execute(HttpServletRequest request) throws CommandException, CommandException, ConnectionPoolException {
    TrainingsService trainingsService = new TrainingsService();
    String topicName = request.getParameter(TOPIC_NAME);
    String topicNewName = request.getParameter(TOPIC_NEW_NAME);
    String topic = request.getParameter(TOPIC);
    int trainingId = Integer.parseInt(request.getParameter(TRAINING_ID));
    boolean done = trainingsService.updateTrainingsTopic(topicName, topicNewName, topic, trainingId);
    if (done) {
      request.getSession().setAttribute(MESSAGE_ABOUT_CHANGES, MessageManager.getProperty(MESSAGE_CHANGES_SAVED));
      return ConfigurationManager.getProperty(TOPIC_PAGE);
    }
    request.getSession().setAttribute(MESSAGE_ABOUT_CHANGES, MessageManager.getProperty(MESSAGE_CHANGES_ERROR));
    return ConfigurationManager.getProperty(TOPIC_PAGE);
  }
}
