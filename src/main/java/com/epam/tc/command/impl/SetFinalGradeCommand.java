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
import com.epam.tc.validator.InputDataValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Set final grade command.
 *
 * @author alex raby
 * @version 1.0 final grade for training
 */
public class SetFinalGradeCommand implements Command {

  /**
   * class object Logger {@link Logger}
   * writes important events to a log file
   */
  private static Logger logger = LogManager.getLogger(SetFinalGradeCommand.class);

  /** {@inheritDoc} */
  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    int studentId = Integer.parseInt(request.getParameter(VariableName.STUDENT_ID));
    int trainingId = Integer.parseInt(request.getParameter(VariableName.TRAINING_ID));
    int grade = Integer.parseInt(request.getParameter(VariableName.GRADE));
    InputDataValidation validation = new InputDataValidation();
    boolean checkGrade = validation.checkGrade(grade);
    if (!checkGrade) {
      request.getSession().setAttribute(MessageName.FINAL_GRADE_MESSAGE,
              MessageManager.getProperty(MessageName.FINAL_GRADE_INVALID));
      return ConfigurationManager.getProperty(PageName.MENTORING_PAGE);
    }
    TrainingService trainingService = ServiceFactory.getTrainingService();
    try {
      trainingService.setFinalGrade(studentId, trainingId, grade);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException(e);
    }
    request.getSession().setAttribute(MessageName.FINAL_GRADE_MESSAGE,
            MessageManager.getProperty(MessageName.FINAL_GRADE_MESSAGE_DONE));
    return ConfigurationManager.getProperty(PageName.MENTORING_PAGE);
  }
}
