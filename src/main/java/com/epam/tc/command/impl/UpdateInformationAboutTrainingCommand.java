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
import com.epam.tc.validator.InputDataValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UpdateInformationAboutTrainingCommand implements Command {

  private static final Logger logger = LogManager.getLogger(UpdateInformationAboutTrainingCommand.class);

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    TrainingService trainingService = ServiceFactory.getTrainingService();
    InputDataValidation validation = new InputDataValidation();
    int trainingId = Integer.parseInt(request.getParameter(RequestVariableName.TRAINING_ID));
    String information = request.getParameter(RequestVariableName.INFORMATION);
    information = validation.stripXSS(information);
    information = validation.deleteExcessiveSpace(information);
    String trainingName = request.getParameter(RequestVariableName.TRAINING_NAME);
    trainingName = validation.stripXSS(trainingName);
    boolean checkTrainingName = validation.checkSizeTextArea(trainingName, 2, 70);
    boolean checkInformation = validation.checkSizeTextArea(information, 50, 1000);
    if (!checkInformation) {
      request.getSession().setAttribute(RequestVariableName.NAME, trainingName);
      request.getSession().setAttribute(RequestVariableName.INFORMATION, information);
      request.getSession().setAttribute(MessageName.MESSAGE_ABOUT_CHANGES,
              MessageManager.getProperty(MessageName.MESSAGE_TEXTAREA_SIZE));
      return ConfigurationManager.getProperty(PageName.CREATE_TEXT_PAGE);
    }
    if (!checkTrainingName) {
      request.getSession().setAttribute(RequestVariableName.NAME, trainingName);
      request.getSession().setAttribute(RequestVariableName.INFORMATION, information);
      request.getSession().setAttribute(MessageName.MESSAGE_ABOUT_CHANGES,
              MessageManager.getProperty(MessageName.MESSAGE_TEXTAREA_NAME_SIZE));
      return ConfigurationManager.getProperty(PageName.CREATE_TEXT_PAGE);
    }
       try {
      trainingService.updateTrainingsInformation(trainingId, trainingName, information);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException("Error access service", e);
    }
       request.getSession().setAttribute(RequestVariableName.REDIRECT_TO, true);
      request.getSession().setAttribute(MessageName.MESSAGE_ABOUT_CHANGES,
              MessageManager.getProperty(MessageName.MESSAGE_CHANGES_SAVED));
      return ConfigurationManager.getProperty(PageName.PATH_PAGE_TRAININGS_INFORMATION);
  }
}
