package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.CommandException;
import com.epam.tc.command.MessageName;
import com.epam.tc.command.VariableName;
import com.epam.tc.command.PageName;
import com.epam.tc.manager.ConfigurationManager;
import com.epam.tc.manager.MessageManager;
import com.epam.tc.service.ServiceFactory;
import com.epam.tc.service.TopicService;
import com.epam.tc.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Mark topic command.
 *
 * @author alex raby
 * @version 1.0 set a mark for the topic that it is studied by a student
 */
public class MarkTopicCommand implements Command {

  /**
   * class object Logger {@link Logger}
   * writes important events to a log file
   */
  private static final Logger logger = LogManager.getLogger(MarkTopicCommand.class);

  /** {@inheritDoc} */
  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    TopicService topicService = ServiceFactory.getTopicService();
    int userId = Integer.parseInt(request.getParameter(VariableName.USER_ID));
    int topicId = Integer.parseInt(request.getParameter(VariableName.TOPIC_ID));
    try {
      topicService.markTopic(userId, topicId);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException("Error access service", e);
    }
    request.getSession().setAttribute(MessageName.MESSAGE_ABOUT_DONE,
            MessageManager.getProperty(MessageName.MESSAGE_MARK_DONE));
    return ConfigurationManager.getProperty(PageName.TOPIC_PAGE);
  }
}
