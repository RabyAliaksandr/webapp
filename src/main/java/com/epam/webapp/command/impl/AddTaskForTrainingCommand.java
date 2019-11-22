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

public class AddTaskForTrainingCommand implements Command {

  private static final Logger logger = LogManager.getLogger(AddTaskForTrainingCommand.class);

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    TrainingsServiceImpl trainingsService = new TrainingsServiceImpl();
    int trainingId = Integer.parseInt(request.getParameter(ConstName.TRAINING_ID));
    String taskName = request.getParameter(ConstName.TASK_NAME);
    String taskText = request.getParameter(ConstName.TASK_TEXT);
    boolean done;
    try {
      done = trainingsService.addTaskForTraining(trainingId, taskName, taskText);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException("Error access service", e);
    }
    if (done) {
      request.getSession().setAttribute(ConstMessage.MESSAGE_ABOUT_CHANGES,
              MessageManager.getProperty(ConstMessage.MESSAGE_CHANGES_SAVED));
      return ConfigurationManager.getProperty(ConstPage.TRAINING_PAGE);
    }
    request.getSession().setAttribute(ConstMessage.MESSAGE_ABOUT_CHANGES,
            MessageManager.getProperty(ConstMessage.MESSAGE_CHANGES_ERROR));
    return ConfigurationManager.getProperty(ConstPage.TRAINING_PAGE);
  }
}
