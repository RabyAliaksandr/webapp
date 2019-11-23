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
import com.epam.tc.validator.InputDataValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class  UpdateTrainingsTopicCommand implements Command {

  private static final Logger logger = LogManager.getLogger(UpdateTrainingsTopicCommand.class);

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    TopicService topicService = ServiceFactory.getTopicService();
    String topicName = request.getParameter(RequestVariableName.TOPIC_NAME);
    String topic = request.getParameter(RequestVariableName.TOPIC);
    InputDataValidation validation = new InputDataValidation();
    topicName = validation.stripXSS(topicName);
    topicName = validation.deleteExcessiveSpace(topicName);
    topic = validation.stripXSS(topic);
    topic = validation.deleteExcessiveSpace(topic);
    int topicId = Integer.parseInt(request.getParameter(RequestVariableName.TOPIC_ID));
    boolean checkTopic = validation.checkSizeTextArea(topic, 50, 1000);
    boolean checkTopicName = validation.checkSizeTextArea(topicName, 5, 70);
    if (!checkTopic) {
      request.getSession().setAttribute(RequestVariableName.TEXT, topic);
      request.getSession().setAttribute(RequestVariableName.NAME, topicName);
      request.getSession().setAttribute(MessageName.MESSAGE_ABOUT_CHANGES,
              MessageManager.getProperty(MessageName.MESSAGE_TEXTAREA_SIZE));
      return ConfigurationManager.getProperty(PageName.TOPIC_PAGE);
    }
    if (!checkTopicName) {
      request.getSession().setAttribute(RequestVariableName.TEXT, topic);
      request.getSession().setAttribute(RequestVariableName.NAME, topicName);
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
      request.getSession().setAttribute(MessageName.MESSAGE_ABOUT_CHANGES,
              MessageManager.getProperty(MessageName.MESSAGE_CHANGES_SAVED));
      return ConfigurationManager.getProperty(PageName.TOPIC_PAGE);
  }
}
