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

public class MarkTopicCommand implements Command {

  private static final Logger logger = LogManager.getLogger(MarkTopicCommand.class);

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    boolean done;
    TrainingsServiceImpl trainingsService = new TrainingsServiceImpl();
    int userId = Integer.parseInt(request.getParameter(ConstName.USER_ID));
    int topicId = Integer.parseInt(request.getParameter(ConstName.TOPIC_ID));
    try {
      done = trainingsService.markTopic(userId, topicId);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException("Error access service", e);
    }
    if (done) {
      request.getSession().setAttribute(ConstMessage.MESSAGE_ABOUT_DONE,
              MessageManager.getProperty(ConstMessage.MESSAGE_MARK_DONE));
      return ConfigurationManager.getProperty(ConstPage.TOPIC_PAGE);
    }
    request.getSession().setAttribute(ConstMessage.MESSAGE_ABOUT_DONE,
            MessageManager.getProperty(ConstMessage.MESSAGE_MARK_ERROR));
    return ConfigurationManager.getProperty(ConstPage.TOPIC_PAGE);
  }
}
