package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.CommandException;
import com.epam.tc.command.RequestVariableName;
import com.epam.tc.command.PageName;
import com.epam.tc.manager.ConfigurationManager;
import com.epam.tc.service.ServiceException;
import com.epam.tc.service.ServiceFactory;
import com.epam.tc.service.TrainingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddTrainingToStudentCommand implements Command {

  private static final Logger logger = LogManager.getLogger(AddTrainingToStudentCommand.class);

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    int userId = Integer.parseInt(request.getParameter(RequestVariableName.USER_ID));
    int trainingId = Integer.parseInt(request.getParameter(RequestVariableName.TRAINING_ID));
    TrainingService trainingService = ServiceFactory.getTrainingService();
    try {
      trainingService.addTrainingToStudent(userId, trainingId);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException("Error access service", e);
    }
    return ConfigurationManager.getProperty(PageName.INFORMATION_PAGE);
  }
}
