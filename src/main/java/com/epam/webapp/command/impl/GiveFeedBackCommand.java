package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandException;
import com.epam.webapp.constant.ConstMessage;
import com.epam.webapp.constant.ConstName;
import com.epam.webapp.constant.ConstPage;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.manager.MessageManager;
import com.epam.webapp.service.ServiceException;
import com.epam.webapp.service.TrainingService;
import com.epam.webapp.service.impl.TrainingsServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class GiveFeedBackCommand implements Command {

  private static Logger logger = LogManager.getLogger(GiveFeedBackCommand.class);

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    String feedback = request.getParameter(ConstName.FEEDBACK);
    TrainingService trainingService = new TrainingsServiceImpl();
    System.out.println(feedback + "it is feedback");
    try {
      trainingService.giveFeedback(feedback);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException(e);
    }
    request.getSession().setAttribute(ConstMessage.FEEDBACK_MESSAGE,
            MessageManager.getProperty(ConstMessage.FEEDBACK_MESSAGE_SAVE));
    return ConfigurationManager.getProperty(ConstPage.TRAININGS_INFORMATION_PAGE);
  }
}
