package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.connectionpool.exception.ConnectionPoolException;
import com.epam.webapp.service.TrainingsService;

import javax.servlet.http.HttpServletRequest;

public class CreateTrainingCommand implements Command {

  private static final String TRAINING_NAME = "trainingName";
  private static final String MENTOR_ID = "mentorId";
  private static final String DESCRIPTION = "description";

  @Override
  public String execute(HttpServletRequest request) throws CommandException, CommandException, ConnectionPoolException {
    TrainingsService trainingsService = new TrainingsService();
    String trainingName = request.getParameter(TRAINING_NAME);
    System.out.println("training name = " + trainingName);
    int mentorId = Integer.parseInt(request.getParameter(MENTOR_ID));
    System.out.println("id mentor = " + mentorId);
    String trainingDescription = request.getParameter(DESCRIPTION);
    System.out.println("description = " + trainingDescription);
    trainingsService.createTraining(trainingName, mentorId, trainingDescription);
    return null;
  }
}
