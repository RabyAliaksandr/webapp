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

public class SetFinalGradeCommand implements Command {

  private static Logger logger = LogManager.getLogger(SetFinalGradeCommand.class);

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    int studentId = Integer.parseInt(request.getParameter(RequestVariableName.STUDENT_ID));
    int trainingId = Integer.parseInt(request.getParameter(RequestVariableName.TRAINING_ID));
    int grade = Integer.parseInt(request.getParameter(RequestVariableName.GRADE));
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
