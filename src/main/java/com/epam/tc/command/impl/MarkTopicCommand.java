package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.CommandException;
import com.epam.tc.command.MessageName;
import com.epam.tc.command.RequestVariableName;
import com.epam.tc.command.PageName;
import com.epam.tc.manager.ConfigurationManager;
import com.epam.tc.manager.MessageManager;
import com.epam.tc.service.ServiceFactory;
import com.epam.tc.service.TopicService;
import com.epam.tc.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class MarkTopicCommand implements Command {

  private static final Logger logger = LogManager.getLogger(MarkTopicCommand.class);

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    boolean done;
    TopicService topicService = ServiceFactory.getTopicService();
    int userId = Integer.parseInt(request.getParameter(RequestVariableName.USER_ID));
    int topicId = Integer.parseInt(request.getParameter(RequestVariableName.TOPIC_ID));
    try {
      done = topicService.markTopic(userId, topicId);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException("Error access service", e);
    }
    if (done) {
      request.getSession().setAttribute(MessageName.MESSAGE_ABOUT_DONE,
              MessageManager.getProperty(MessageName.MESSAGE_MARK_DONE));
      return ConfigurationManager.getProperty(PageName.TOPIC_PAGE);
    }
    request.getSession().setAttribute(MessageName.MESSAGE_ABOUT_DONE,
            MessageManager.getProperty(MessageName.MESSAGE_MARK_ERROR));
    return ConfigurationManager.getProperty(PageName.TOPIC_PAGE);
  }
}
