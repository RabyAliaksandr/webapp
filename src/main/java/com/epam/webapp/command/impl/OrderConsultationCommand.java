package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandConst;
import com.epam.webapp.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class OrderConsultationCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {

    int studentId = Integer.parseInt(request.getParameter(CommandConst.STUDENT_ID));
    int trainingId = Integer.parseInt(request.getParameter(CommandConst.TRAINING_ID));
    request.getSession().setAttribute(CommandConst.STUDENT_ID, studentId);
    request.getSession().setAttribute(CommandConst.TRAINING_ID, trainingId);
    return ConfigurationManager.getProperty(CommandConst.ORDER_CONSULTATION_PAGE);
  }
}
