package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.CommandException;
import com.epam.tc.command.MessageName;
import com.epam.tc.command.RequestVariableName;
import com.epam.tc.command.PageName;
import com.epam.tc.manager.ConfigurationManager;
import com.epam.tc.manager.MessageManager;
import com.epam.tc.service.ServiceException;
import com.epam.tc.service.ServiceFactory;
import com.epam.tc.service.TopicService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteTopicCommand implements Command {

  private static Logger logger = LogManager.getLogger(DeleteTopicCommand.class);

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    int topicId = Integer.parseInt(request.getParameter(RequestVariableName.TOPIC_ID));
    TopicService topicService = ServiceFactory.getTopicService();
    try {
      topicService.deleteTopic(topicId);
      request.getSession().setAttribute(MessageName.MESSAGE_ABOUT_CHANGES,
              MessageManager.getProperty(MessageName.MESSAGE_CHANGES_SAVED));
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException(e);
    }
    return ConfigurationManager.getProperty(PageName.TRAININGS_INFORMATION_PAGE);
  }
}
