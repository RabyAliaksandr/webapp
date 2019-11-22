package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.CommandException;
import com.epam.tc.command.MessageName;
import com.epam.tc.command.RequestVariableName;
import com.epam.tc.command.PageName;
import com.epam.tc.manager.ConfigurationManager;
import com.epam.tc.manager.MessageManager;
import com.epam.tc.service.ServiceFactory;
import com.epam.tc.service.TrainingService;
import com.epam.tc.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UpdateInformationAboutTrainingCommand implements Command {

  private static final Logger logger = LogManager.getLogger(UpdateInformationAboutTrainingCommand.class);

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    TrainingService trainingService = ServiceFactory.getTrainingService();
    int trainingId = Integer.parseInt(request.getParameter(RequestVariableName.TRAINING_ID));
    String information = request.getParameter(RequestVariableName.INFORMATION);
    String trainingName = request.getParameter(RequestVariableName.TRAINING_NAME);
       try {
      trainingService.updateTrainingsInformation(trainingId, trainingName, information);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException("Error access service", e);
    }
      request.getSession().setAttribute(MessageName.MESSAGE_ABOUT_CHANGES,
              MessageManager.getProperty(MessageName.MESSAGE_CHANGES_SAVED));
      return ConfigurationManager.getProperty(PageName.PATH_PAGE_TRAININGS_INFORMATION);
  }
}
