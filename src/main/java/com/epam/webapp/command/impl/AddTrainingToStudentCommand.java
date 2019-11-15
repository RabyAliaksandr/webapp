package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.connectionpool.ConnectionPoolException;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class AddTrainingToStudentCommand implements Command {
  @Override
  public String execute(HttpServletRequest request) throws CommandException, CommandException, ConnectionPoolException {
    int userId = Integer.parseInt(request.getParameter("userId"));
    int trainingId = Integer.parseInt(request.getParameter("trainingId"));

    UserService userService = new UserService();
    userService.addTrainingToStudent(userId, trainingId);

    return ConfigurationManager.getProperty("path.page.trainingsInformation");

  }
}
