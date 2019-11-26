package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.CommandException;
import com.epam.tc.command.PageName;
import com.epam.tc.command.VariableName;
import com.epam.tc.entity.Topic;
import com.epam.tc.entity.Training;
import com.epam.tc.service.ServiceException;
import com.epam.tc.command.MessageName;
import com.epam.tc.manager.ConfigurationManager;
import com.epam.tc.manager.MessageManager;
import com.epam.tc.service.ServiceFactory;
import com.epam.tc.service.TopicService;
import com.epam.tc.validator.InputDataValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Add topic for training command.
 *
 * @author alex raby
 * @version 1.0 adding a Topic {@link Topic} for a Training {@link Training}
 */
public class AddTopicForTrainingCommand implements Command {

  /**
   * class object Logger {@link Logger}
   * writes important events to a log file
   */
  private static final Logger logger = LogManager.getLogger(AddTopicForTrainingCommand.class);

  /**
   * @see Command
   * @param request - object HttpServletRequest
   * @return
   * @throws CommandException
   */
  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    TopicService topicService = ServiceFactory.getTopicService();
    int trainingId = Integer.parseInt(request.getParameter(VariableName.TRAINING_ID));
    String topicsName = request.getParameter(VariableName.TOPICS_NAME);
    String topicsText = request.getParameter(VariableName.TOPICS_TEXT);
    InputDataValidation validation = new InputDataValidation();
    topicsName = validation.stripXSS(topicsName);
    topicsText = validation.stripXSS(topicsText);
    topicsText = validation.deleteExcessiveSpace(topicsText);
    boolean checkTopicName = validation.checkSizeTextArea(topicsName, 5, 70);
    boolean checkTopicText = validation.checkSizeTextArea(topicsText, 50, 1000);
    if (!checkTopicName) {
      request.getSession().setAttribute(VariableName.NAME, topicsName);
      request.getSession().setAttribute(VariableName.TEXT, topicsText);
      request.getSession().setAttribute(MessageName.MESSAGE_ABOUT_CHANGES,
              MessageManager.getProperty(MessageName.MESSAGE_TEXTAREA_NAME_SIZE));
      return ConfigurationManager.getProperty(PageName.CREATE_TEXT_PAGE);
    }
    if (!checkTopicText) {
      request.getSession().setAttribute(VariableName.NAME, topicsName);
      request.getSession().setAttribute(VariableName.TEXT, topicsText);
      request.getSession().setAttribute(MessageName.MESSAGE_ABOUT_CHANGES,
              MessageManager.getProperty(MessageName.MESSAGE_TEXTAREA_SIZE));
      return ConfigurationManager.getProperty(PageName.CREATE_TEXT_PAGE);
    }
    try {
       topicService.addTopicForTraining(trainingId, topicsName, topicsText);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException("Error access service", e);
    }
      request.getSession().setAttribute(MessageName.MESSAGE_ABOUT_CHANGES,
              MessageManager.getProperty(MessageName.MESSAGE_CHANGES_SAVED));
      return ConfigurationManager.getProperty(PageName.TRAININGS_INFORMATION_PAGE);
  }
}
