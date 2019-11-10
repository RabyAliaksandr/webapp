package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.connectionpool.exception.ConnectionPoolException;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.service.TrainingsService;

import javax.servlet.http.HttpServletRequest;

public class AddTopicForTrainingCommand implements Command {


  private static final String TRAINING_ID = "trainingId";
  private static final String TOPICS_NAME = "topicsName";
  private static final String TOPICS_TEXT = "topicsText";

  @Override
  public String execute(HttpServletRequest request) throws CommandException, CommandException, ConnectionPoolException {
    TrainingsService trainingsService = new TrainingsService();
    System.out.println("i'm here");
    int trainingId = Integer.parseInt(request.getParameter(TRAINING_ID));
    System.out.println("trId = " + trainingId);
    String topicsName = request.getParameter(TOPICS_NAME);
    System.out.println("name = " + topicsName);
    String topicsText = request.getParameter(TOPICS_TEXT);
    System.out.println("text = " + topicsText);
    trainingsService.addTopicForTraining(trainingId, topicsName, topicsText);
//    return ConfigurationManager.
    return null;
  }
}
