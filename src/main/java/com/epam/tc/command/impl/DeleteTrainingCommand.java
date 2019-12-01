package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.CommandException;
import com.epam.tc.command.PageName;
import com.epam.tc.command.VariableName;
import com.epam.tc.entity.Training;
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
 * The type Delete training command.
 *
 * @author alex raby
 * @version 1.0 deleting Training {@link Training} by Training id
 */
public class DeleteTrainingCommand implements Command {

  /**
   * class object Logger {@link Logger}
   * writes important events to a log file
   */
  private static Logger logger = LogManager.getLogger(DeleteTrainingCommand.class);

  /** {@inheritDoc} */
  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    int trainingId = Integer.parseInt(request.getParameter(VariableName.TRAINING_ID));
    TrainingService trainingService = ServiceFactory.getTrainingService();
    boolean done;
    try {
      done = trainingService.deleteTraining(trainingId);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException(e);
    }
    if (done) {
      request.getSession().setAttribute(MessageName.DELETE_TRAINING,
              MessageManager.getProperty(MessageName.DELETE_TRAINING_MESSAGE));
    } else {
      request.getSession().setAttribute(MessageName.DELETE_TRAINING,
              MessageManager.getProperty(MessageName.DELETE_TRAINING_MESSAGE_WRONG));
    }
    request.getSession().setAttribute(VariableName.REDIRECT_TO_PAGE, VariableName.TRAININGS);
    return ConfigurationManager.getProperty(PageName.TRAININGS_PAGE);
  }
}
