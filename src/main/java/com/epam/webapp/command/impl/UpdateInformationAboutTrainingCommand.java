package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandException;
import com.epam.webapp.constant.ConstMessage;
import com.epam.webapp.constant.ConstName;
import com.epam.webapp.constant.ConstPage;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.manager.MessageManager;
import com.epam.webapp.service.impl.TrainingsServiceImpl;
import com.epam.webapp.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UpdateInformationAboutTrainingCommand implements Command {

  private static final Logger logger = LogManager.getLogger(UpdateInformationAboutTrainingCommand.class);

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    TrainingsServiceImpl trainingsService = new TrainingsServiceImpl();
    int trainingId = Integer.parseInt(request.getParameter(ConstName.TRAINING_ID));
    String information = request.getParameter(ConstName.INFORMATION);
    String trainingName = request.getParameter(ConstName.TRAINING_NAME);
    boolean done;
    try {
      done = trainingsService.updateTrainingsInformation(trainingId, trainingName, information);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException("Error access service", e);
    }
    if (done) {
      request.getSession().setAttribute(ConstMessage.MESSAGE_ABOUT_CHANGES,
              MessageManager.getProperty(ConstMessage.MESSAGE_CHANGES_SAVED));
      return ConfigurationManager.getProperty(ConstPage.PATH_PAGE_TRAININGS_INFORMATION);
    }
    request.getSession().setAttribute(ConstMessage.MESSAGE_ABOUT_CHANGES,
            MessageManager.getProperty(ConstMessage.MESSAGE_CHANGES_ERROR));
    return ConfigurationManager.getProperty(ConstPage.PATH_PAGE_TRAININGS_INFORMATION);
  }
}
