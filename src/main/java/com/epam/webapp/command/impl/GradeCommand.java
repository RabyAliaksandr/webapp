package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.connectionpool.exception.ConnectionPoolException;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class GradeCommand implements Command {

  private final static String USER_ID = "userId";
  private final static String TRAINING_ID = "trainingId";
  private final static String GRADE = "grade";


  @Override
  public String execute(HttpServletRequest request) throws CommandException, CommandException, ConnectionPoolException {

    UserService userService = new UserService();
    int userId = Integer.parseInt(request.getParameter(USER_ID));
    int trainingId = Integer.parseInt(request.getParameter(TRAINING_ID));
    int grade = Integer.parseInt(request.getParameter(GRADE));
    userService.grade(grade, userId, trainingId);
    return ConfigurationManager.getProperty("path.page.trainingsInformation");
  }
}
