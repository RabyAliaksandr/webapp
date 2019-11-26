package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.CommandException;
import com.epam.tc.command.PageName;
import com.epam.tc.command.VariableName;
import com.epam.tc.manager.ConfigurationManager;
import com.epam.tc.manager.MessageManager;
import com.epam.tc.service.ServiceException;
import com.epam.tc.service.ServiceFactory;
import com.epam.tc.service.TrainingService;
import com.epam.tc.command.MessageName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Close reception command.
 *
 * @author alex raby
 * @version 1.0 training set closing
 */
public class CloseReceptionCommand implements Command {

  /**
   * class object Logger {@link Logger}
   * writes important events to a log file
   */
  private static Logger logger = LogManager.getLogger(CloseReceptionCommand.class);

  /** {@inheritDoc} */
  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    int trainingId = Integer.parseInt(request.getParameter(VariableName.TRAINING_ID));
    TrainingService trainingService = ServiceFactory.getTrainingService();
    try {
      trainingService.closeReception(trainingId);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException(e);
    }
    request.getSession().setAttribute(MessageName.CLOSE_RECEPTION,
            MessageManager.getProperty(MessageName.CLOSE_RECEPTION_MESSAGE));
    return ConfigurationManager.getProperty(PageName.TRAINING_PAGE);
  }
}
