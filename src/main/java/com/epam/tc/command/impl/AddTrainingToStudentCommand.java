package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.CommandException;
import com.epam.tc.command.PageName;
import com.epam.tc.command.VariableName;
import com.epam.tc.entity.Training;
import com.epam.tc.entity.User;
import com.epam.tc.manager.ConfigurationManager;
import com.epam.tc.service.ServiceException;
import com.epam.tc.service.ServiceFactory;
import com.epam.tc.service.TrainingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Add training to student command.
 *
 * @author alex raby
 * @version 1.0 adding a  {@link Training} for a {@link User}
 */
public class AddTrainingToStudentCommand implements Command {

  /**
   * class object Logger {@link Logger}
   * writes important events to a log file
   */
  private static final Logger logger = LogManager.getLogger(AddTrainingToStudentCommand.class);

  /** {@inheritDoc} */
  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    int userId = Integer.parseInt(request.getParameter(VariableName.USER_ID));
    int trainingId = Integer.parseInt(request.getParameter(VariableName.TRAINING_ID));
    TrainingService trainingService = ServiceFactory.getTrainingService();
    try {
      trainingService.addTrainingToStudent(userId, trainingId);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException("Error access service", e);
    }
    return ConfigurationManager.getProperty(PageName.INFORMATION_PAGE);
  }
}
