package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.CommandException;
import com.epam.tc.command.PageName;
import com.epam.tc.command.VariableName;
import com.epam.tc.service.ServiceException;
import com.epam.tc.service.TrainingService;
import com.epam.tc.command.MessageName;
import com.epam.tc.manager.ConfigurationManager;
import com.epam.tc.manager.MessageManager;
import com.epam.tc.service.ServiceFactory;
import com.epam.tc.validator.InputDataValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Update information about training command.
 *
 * @author alex raby
 * @version 1.0 change fields Training, description, name data validation also takes place here with setting the description length range and name length
 */
public class UpdateInformationAboutTrainingCommand implements Command {

  /**
   * class object Logger {@link Logger}
   * writes important events to a log file
   */
  private static final Logger logger = LogManager.getLogger(UpdateInformationAboutTrainingCommand.class);

  /** {@inheritDoc} */
  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    TrainingService trainingService = ServiceFactory.getTrainingService();
    InputDataValidation validation = new InputDataValidation();
    int trainingId = Integer.parseInt(request.getParameter(VariableName.TRAINING_ID));
    String information = request.getParameter(VariableName.INFORMATION);
    information = validation.stripXSS(information);
    information = validation.deleteExcessiveSpace(information);
    String trainingName = request.getParameter(VariableName.TRAINING_NAME);
    trainingName = validation.stripXSS(trainingName);
    boolean checkTrainingName = validation.checkSizeTextArea(trainingName, 2, 70);
    boolean checkInformation = validation.checkSizeTextArea(information, 50, 1000);
    if (!checkInformation) {
      request.getSession().setAttribute(VariableName.NAME, trainingName);
      request.getSession().setAttribute(VariableName.INFORMATION, information);
      request.getSession().setAttribute(MessageName.MESSAGE_ABOUT_CHANGES,
              MessageManager.getProperty(MessageName.MESSAGE_TEXTAREA_SIZE));
      return ConfigurationManager.getProperty(PageName.CREATE_TEXT_PAGE);
    }
    if (!checkTrainingName) {
      request.getSession().setAttribute(VariableName.NAME, trainingName);
      request.getSession().setAttribute(VariableName.INFORMATION, information);
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
       request.getSession().setAttribute(VariableName.REDIRECT_TO, true);
      request.getSession().setAttribute(MessageName.MESSAGE_ABOUT_CHANGES,
              MessageManager.getProperty(MessageName.MESSAGE_CHANGES_SAVED));
      return ConfigurationManager.getProperty(PageName.PATH_PAGE_TRAININGS_INFORMATION);
  }
}
