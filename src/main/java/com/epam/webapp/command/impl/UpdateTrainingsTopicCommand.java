package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandConst;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.manager.MessageManager;
import com.epam.webapp.service.impl.TrainingsServiceImpl;
import com.epam.webapp.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class UpdateTrainingsTopicCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    TrainingsServiceImpl trainingsService = new TrainingsServiceImpl();
    String topicName = request.getParameter(CommandConst.TOPIC_NAME);
    String topic = request.getParameter(CommandConst.TOPIC);
    int topicId = Integer.parseInt(request.getParameter(CommandConst.TOPIC_ID));
    boolean done;
    try {
      done = trainingsService.updateTrainingsTopic(topicId, topicName, topic);
    } catch (ServiceException e) {
      throw new CommandException("Error access service", e);
    }
    if (done) {
      request.getSession().setAttribute(CommandConst.MESSAGE_ABOUT_CHANGES,
              MessageManager.getProperty(CommandConst.MESSAGE_CHANGES_SAVED));
      return ConfigurationManager.getProperty(CommandConst.TOPIC_PAGE);
    }
    request.getSession().setAttribute(CommandConst.MESSAGE_ABOUT_CHANGES,
            MessageManager.getProperty(CommandConst.MESSAGE_CHANGES_ERROR));
    return ConfigurationManager.getProperty(CommandConst.TOPIC_PAGE);
  }
}
