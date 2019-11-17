package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandConst;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.manager.MessageManager;
import com.epam.webapp.service.impl.TrainingsServiceImpl;
import com.epam.webapp.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class MarkTopicCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    boolean done;
    TrainingsServiceImpl trainingsService = new TrainingsServiceImpl();
    int userId = Integer.parseInt(request.getParameter(CommandConst.USER_ID));
    int topicId = Integer.parseInt(request.getParameter(CommandConst.TOPIC_ID));
    try {
      done = trainingsService.markTopic(userId, topicId);
    } catch (ServiceException e) {
      throw new CommandException("Error access service", e);
    }
    if (done) {
      request.getSession().setAttribute(CommandConst.MESSAGE_ABOUT_DONE,
              MessageManager.getProperty(CommandConst.MESSAGE_MARK_DONE));
      return ConfigurationManager.getProperty(CommandConst.TOPIC_PAGE);
    }
    request.getSession().setAttribute(CommandConst.MESSAGE_ABOUT_DONE,
            MessageManager.getProperty(CommandConst.MESSAGE_MARK_ERROR));
    return ConfigurationManager.getProperty(CommandConst.TOPIC_PAGE);
  }
}
