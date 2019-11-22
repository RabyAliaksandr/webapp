package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandException;
import com.epam.webapp.constant.ConstName;
import com.epam.webapp.constant.ConstPage;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.service.impl.UserServiceImpl;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddTrainingToStudentCommand implements Command {

  private static final Logger logger = LogManager.getLogger(AddTrainingToStudentCommand.class);


  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    int userId = Integer.parseInt(request.getParameter(ConstName.USER_ID));
    int trainingId = Integer.parseInt(request.getParameter(ConstName.TRAINING_ID));
    UserServiceImpl userService = new UserServiceImpl();
    try {
      userService.addTrainingToStudent(userId, trainingId);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException("Error access service", e);
    }
    return ConfigurationManager.getProperty(ConstPage.INFORMATION_PAGE);
  }
}
