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

public class CloseReceptionCommand implements Command {

  private static Logger logger = LogManager.getLogger(CloseReceptionCommand.class);

  @Override
  public String execute(HttpServletRequest request) throws CommandException {

    int trainingId = Integer.parseInt(request.getParameter(ConstName.TRAINING_ID));
    TrainingService trainingService = new TrainingsServiceImpl();
    try {
      trainingService.closeReception(trainingId);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException(e);
    }
    request.getSession().setAttribute(ConstMessage.CLOSE_RECEPTION,
            MessageManager.getProperty(ConstMessage.CLOSE_RECEPTION_MESSAGE));
    return ConfigurationManager.getProperty(ConstPage.TRAINING_PAGE);
  }
}
