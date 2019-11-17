package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandConst;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.service.impl.UserServiceImpl;
import com.google.protobuf.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class AddTrainingToStudentCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    int userId = Integer.parseInt(request.getParameter(CommandConst.USER_ID));
    int trainingId = Integer.parseInt(request.getParameter(CommandConst.TRAINING_ID));
    UserServiceImpl userService = new UserServiceImpl();
    try {
      userService.addTrainingToStudent(userId, trainingId);
    } catch (ServiceException e) {
      throw new CommandException("Error access service", e);
    }
    return ConfigurationManager.getProperty(CommandConst.INFORMATION_PAGE);
  }
}
