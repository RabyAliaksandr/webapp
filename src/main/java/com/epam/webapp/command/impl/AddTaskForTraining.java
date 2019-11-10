package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.connectionpool.exception.ConnectionPoolException;
import com.epam.webapp.service.TrainingsService;

import javax.servlet.http.HttpServletRequest;

public class AddTaskForTraining implements Command {

  private static final String TRAINING_ID = "trainingId";
  private static final String TASK_NAME = "taskName";
  private static final String TASK_TEXT = "taskText";
  @Override
  public String execute(HttpServletRequest request) throws CommandException, CommandException, ConnectionPoolException {
    TrainingsService trainingsService = new TrainingsService();
    int trainingId = Integer.parseInt(request.getParameter(TRAINING_ID));
    System.out.println(trainingId + " = trainingId");
    String taskName = request.getParameter(TASK_NAME);
    System.out.println("tsskName = " + taskName);
    String taskText = request.getParameter(TASK_TEXT);
    System.out.println("taskText = " + taskText);
    trainingsService.addTaskForTraining(trainingId, taskName, taskText);
    return null;
  }
}
