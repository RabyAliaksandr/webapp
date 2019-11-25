package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.CommandException;
import com.epam.tc.command.MessageName;
import com.epam.tc.command.VariableName;
import com.epam.tc.command.PageName;
import com.epam.tc.manager.ConfigurationManager;
import com.epam.tc.manager.MessageManager;
import com.epam.tc.service.ServiceException;
import com.epam.tc.service.ServiceFactory;
import com.epam.tc.service.TrainingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * @author alex raby
 * @version 1.0
 * accepts and sends feedback to DataBase
 */
public class GiveFeedBackCommand implements Command {


  /**
   * class object Logger {@link Logger}
   * writes important events to a log file
   */
  private static Logger logger = LogManager.getLogger(GiveFeedBackCommand.class);

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    String feedback = request.getParameter(VariableName.FEEDBACK);
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
