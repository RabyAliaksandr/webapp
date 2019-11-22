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

public class AddTopicForTrainingCommand implements Command {

  private static final Logger logger = LogManager.getLogger(AddTopicForTrainingCommand.class);

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    TopicService topicService = ServiceFactory.getTopicService();
    int trainingId = Integer.parseInt(request.getParameter(RequestVariableName.TRAINING_ID));
    String topicsName = request.getParameter(RequestVariableName.TOPICS_NAME);
    String topicsText = request.getParameter(RequestVariableName.TOPICS_TEXT);
    boolean done;
    try {
      done = topicService.addTopicForTraining(trainingId, topicsName, topicsText);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException("Error access service", e);
    }
    if (done) {
      request.getSession().setAttribute(MessageName.MESSAGE_ABOUT_CHANGES,
              MessageManager.getProperty(MessageName.MESSAGE_CHANGES_SAVED));
      return ConfigurationManager.getProperty(PageName.TRAININGS_INFORMATION_PAGE);
    }
    request.getSession().setAttribute(MessageName.MESSAGE_ABOUT_CHANGES,
            MessageManager.getProperty(MessageName.MESSAGE_CHANGES_ERROR));
    return ConfigurationManager.getProperty(PageName.TRAININGS_INFORMATION_PAGE);
  }
}
