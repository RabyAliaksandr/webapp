package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.connectionpool.ConnectionPoolException;
import com.epam.webapp.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public class OrderConsultationCommand implements Command {

  private static final String STUDENT_ID = "studentId";
  private static final String TRAINING_ID = "trainingId";
  private static final String ORDER_CONSULTATION_PAGE = "path.page.orderConsultation";
  @Override
  public String execute(HttpServletRequest request) throws CommandException, ConnectionPoolException, ParseException {
//    String[] izms = request.getParameterValues("izm");
//    if (izms.length > 0) {
//      for (int i = 0; i < izms.length; i++) {
//        System.out.println(izms[i]);
//      }
//    }
    int studentId = Integer.parseInt(request.getParameter(STUDENT_ID));
    int trainingId = Integer.parseInt(request.getParameter(TRAINING_ID));
    request.getSession().setAttribute(STUDENT_ID, studentId);
    request.getSession().setAttribute(TRAINING_ID, trainingId);
    return ConfigurationManager.getProperty(ORDER_CONSULTATION_PAGE);
  }
}
