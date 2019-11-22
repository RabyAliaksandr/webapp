package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandException;
import com.epam.webapp.constant.ConstMessage;
import com.epam.webapp.constant.ConstName;
import com.epam.webapp.constant.ConstPage;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.manager.MessageManager;
import com.epam.webapp.service.ServiceException;
import com.epam.webapp.service.TrainingService;
import com.epam.webapp.service.impl.TrainingsServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteTrainingCommand implements Command {

  private static Logger logger = LogManager.getLogger(DeleteTrainingCommand.class);

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    int trainingId = Integer.parseInt(request.getParameter(ConstName.TRAINING_ID));
    TrainingService trainingService = new TrainingsServiceImpl();
    boolean done;
    try {
      done = trainingService.deleteTraining(trainingId);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException(e);
    }
    if (done) {
      request.getSession().setAttribute(ConstMessage.DELETE_TRAINING,
              MessageManager.getProperty(ConstMessage.DELETE_TRAINING_MESSAGE));
    } else {
      request.getSession().setAttribute(ConstMessage.DELETE_TRAINING,
              MessageManager.getProperty(ConstMessage.DELETE_TRAINING_MESSAGE_WRONG));
    }
    return ConfigurationManager.getProperty(ConstPage.TRAININGS_PAGE);
  }
}
