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

public class CreateTrainingCommand implements Command {

  private static final Logger logger = LogManager.getLogger(CreateTrainingCommand.class);

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    TrainingsServiceImpl trainingsService = new TrainingsServiceImpl();
    String trainingName = request.getParameter(ConstName.TRAINING_NAME);
    int mentorId = Integer.parseInt(request.getParameter(ConstName.MENTOR_ID));
    String trainingDescription = request.getParameter(ConstName.DESCRIPTION);
    boolean done;
    try {
      done = trainingsService.createTraining(trainingName, mentorId, trainingDescription);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException("Error access service", e);
    }
    if (done) {
      request.getSession().setAttribute(ConstMessage.MESSAGE_ABOUT_CHANGES,
              MessageManager.getProperty(ConstMessage.MESSAGE_CHANGES_SAVED));
      return ConfigurationManager.getProperty(ConstPage.TRAININGS_PAGE);
    }
    request.getSession().setAttribute(ConstMessage.MESSAGE_ABOUT_CHANGES,
            MessageManager.getProperty(ConstMessage.MESSAGE_CHANGES_ERROR));
    return ConfigurationManager.getProperty(ConstPage.TRAININGS_PAGE);
  }
}
