package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.CommandException;
import com.epam.tc.command.MessageName;
import com.epam.tc.command.VariableName;
import com.epam.tc.command.PageName;
import com.epam.tc.entity.Topic;
import com.epam.tc.manager.ConfigurationManager;
import com.epam.tc.manager.MessageManager;
import com.epam.tc.service.ServiceFactory;
import com.epam.tc.service.TopicService;
import com.epam.tc.service.ServiceException;
import com.epam.tc.validator.InputDataValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Update trainings topic command.
 *
 * @author alex raby
 * @version 1.0 change fields Task {@link Topic}, description, name data validation also takes place here with setting the description length range and name length
 */
public class  UpdateTrainingsTopicCommand implements Command {

  /**
   * class object Logger {@link Logger}
   * writes important events to a log file
   */
  private static final Logger logger = LogManager.getLogger(UpdateTrainingsTopicCommand.class);

  /** {@inheritDoc} */
  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    TopicService topicService = ServiceFactory.getTopicService();
    String topicName = request.getParameter(VariableName.TOPIC_NAME);
    String topic = request.getParameter(VariableName.TOPIC);
    InputDataValidation validation = new InputDataValidation();
    topicName = validation.stripXSS(topicName);
    topicName = validation.deleteExcessiveSpace(topicName);
    topic = validation.stripXSS(topic);
    topic = validation.deleteExcessiveSpace(topic);
    int topicId = Integer.parseInt(request.getParameter(VariableName.TOPIC_ID));
    boolean checkTopic = validation.checkSizeTextArea(topic, 50, 1000);
    boolean checkTopicName = validation.checkSizeTextArea(topicName, 5, 70);
    if (!checkTopic) {
      request.getSession().setAttribute(VariableName.TEXT, topic);
      request.getSession().setAttribute(VariableName.NAME, topicName);
      request.getSession().setAttribute(MessageName.MESSAGE_ABOUT_CHANGES,
              MessageManager.getProperty(MessageName.MESSAGE_TEXTAREA_SIZE));
      return ConfigurationManager.getProperty(PageName.TOPIC_PAGE);
    }
    if (!checkTopicName) {
      request.getSession().setAttribute(VariableName.TEXT, topic);
      request.getSession().setAttribute(VariableName.NAME, topicName);
      request.getSession().setAttribute(MessageName.MESSAGE_ABOUT_CHANGES,
              MessageManager.getProperty(MessageName.MESSAGE_TEXTAREA_NAME_SIZE));
      return ConfigurationManager.getProperty(PageName.TOPIC_PAGE);
    }
    try {
    topicService.updateTrainingsTopic(topicId, topicName, topic);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException("Error access service", e);
    }
    request.getSession().setAttribute(VariableName.REDIRECT_TO_PAGE, request.getParameter(VariableName.PAGE_NAME));
      request.getSession().setAttribute(MessageName.MESSAGE_ABOUT_CHANGES,
              MessageManager.getProperty(MessageName.MESSAGE_CHANGES_SAVED));
      return ConfigurationManager.getProperty(PageName.TOPIC_PAGE);
  }
}
