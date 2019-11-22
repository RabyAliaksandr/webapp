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
import com.epam.tc.service.TrainingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class GiveFeedBackCommand implements Command {

  private static Logger logger = LogManager.getLogger(GiveFeedBackCommand.class);

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    String feedback = request.getParameter(RequestVariableName.FEEDBACK);
    TrainingService trainingService = ServiceFactory.getTrainingService();
    try {
      trainingService.giveFeedback(feedback);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException(e);
    }
    request.getSession().setAttribute(MessageName.FEEDBACK_MESSAGE,
            MessageManager.getProperty(MessageName.FEEDBACK_MESSAGE_SAVE));
    return ConfigurationManager.getProperty(PageName.TRAININGS_INFORMATION_PAGE);
  }
}
