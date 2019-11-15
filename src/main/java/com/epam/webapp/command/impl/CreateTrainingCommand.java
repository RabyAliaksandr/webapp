package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.connectionpool.ConnectionPoolException;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.manager.MessageManager;
import com.epam.webapp.service.TrainingsService;

import javax.servlet.http.HttpServletRequest;

public class CreateTrainingCommand implements Command {

  private static final String TRAINING_NAME = "trainingName";
  private static final String MENTOR_ID = "mentorId";
  private static final String DESCRIPTION = "description";
  private static final String TRAINING_PAGE = "path.page.trainings_page";
  private static final String MESSAGE_ABOUT_CHANGES = "changesSavedMessage";
  private static final String MESSAGE_CHANGES_SAVED = "message.changesSaved";
  private static final String MESSAGE_CHANGES_ERROR = "message.changesError";


  @Override
  public String execute(HttpServletRequest request) throws CommandException, CommandException, ConnectionPoolException {
    TrainingsService trainingsService = new TrainingsService();
    String trainingName = request.getParameter(TRAINING_NAME);
    System.out.println("training name = " + trainingName);
    int mentorId = Integer.parseInt(request.getParameter(MENTOR_ID));
    System.out.println("id mentor = " + mentorId);
    String trainingDescription = request.getParameter(DESCRIPTION);
    System.out.println("description = " + trainingDescription);
    boolean done = trainingsService.createTraining(trainingName, mentorId, trainingDescription);
    if (done) {
      request.getSession().setAttribute(MESSAGE_ABOUT_CHANGES, MessageManager.getProperty(MESSAGE_CHANGES_SAVED));
      return ConfigurationManager.getProperty(TRAINING_PAGE);
    }
    request.getSession().setAttribute(MESSAGE_ABOUT_CHANGES, MessageManager.getProperty(MESSAGE_CHANGES_ERROR));
    return ConfigurationManager.getProperty(TRAINING_PAGE);
  }
}
