package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.connectionpool.exception.ConnectionPoolException;
import com.epam.webapp.service.TrainingsService;

import javax.servlet.http.HttpServletRequest;

public class UpdateInformationAboutTrainingCommand implements Command {
  private static final String TRAINING_ID = "trainingId";
  private static final String INFORMATION = "information";
  @Override
  public String execute(HttpServletRequest request) throws CommandException, CommandException, ConnectionPoolException {
    TrainingsService trainingsService = new TrainingsService();
    System.out.println(" i am here");
    int trainingId = Integer.parseInt(request.getParameter(TRAINING_ID));
    String information = request.getParameter(INFORMATION);

    System.out.println("id = " + trainingId + "inf = " + information);
    boolean update = trainingsService.updateTrainingsInformation(trainingId, information);
    return null;
  }
}
