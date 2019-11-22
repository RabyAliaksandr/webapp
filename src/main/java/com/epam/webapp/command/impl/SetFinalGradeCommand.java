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

public class SetFinalGradeCommand implements Command {

  private static Logger logger = LogManager.getLogger(SetFinalGradeCommand.class);

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    int studentId = Integer.parseInt(request.getParameter(ConstName.STUDENT_ID));
    int trainingId = Integer.parseInt(request.getParameter(ConstName.TRAINING_ID));
    int grade = Integer.parseInt(request.getParameter(ConstName.GRADE));

    TrainingService trainingService = new TrainingsServiceImpl();
    try {
      trainingService.setFinalGrade(studentId, trainingId, grade);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException(e);
    }
    request.getSession().setAttribute(ConstMessage.FINAL_GRADE_MESSAGE,
            MessageManager.getProperty(ConstMessage.FINAL_GRADE_MESSAGE_DONE));
    return ConfigurationManager.getProperty(ConstPage.MENTORING_PAGE);
  }
}
