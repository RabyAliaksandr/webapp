package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandConst;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.manager.MessageManager;
import com.epam.webapp.service.impl.TrainingsServiceImpl;
import com.epam.webapp.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class UpdateInformationAboutTrainingCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    TrainingsServiceImpl trainingsService = new TrainingsServiceImpl();
    int trainingId = Integer.parseInt(request.getParameter(CommandConst.TRAINING_ID));
    String information = request.getParameter(CommandConst.INFORMATION);
    String trainingName = request.getParameter(CommandConst.TRAINING_NAME);
    boolean done;
    try {
      done = trainingsService.updateTrainingsInformation(trainingId, trainingName, information);
    } catch (ServiceException e) {
      throw new CommandException("Error access service", e);
    }
    if (done) {
      request.getSession().setAttribute(CommandConst.MESSAGE_ABOUT_CHANGES,
              MessageManager.getProperty(CommandConst.MESSAGE_CHANGES_SAVED));
      return ConfigurationManager.getProperty(CommandConst.PATH_PAGE_TRAININGS_INFORMATION);
    }
    request.getSession().setAttribute(CommandConst.MESSAGE_ABOUT_CHANGES,
            MessageManager.getProperty(CommandConst.MESSAGE_CHANGES_ERROR));
    return ConfigurationManager.getProperty(CommandConst.PATH_PAGE_TRAININGS_INFORMATION);
  }
}
