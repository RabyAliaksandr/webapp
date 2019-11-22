package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.constant.ConstName;
import com.epam.webapp.constant.ConstPage;
import com.epam.webapp.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class OrderConsultationCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {

    int studentId = Integer.parseInt(request.getParameter(ConstName.STUDENT_ID));
    int trainingId = Integer.parseInt(request.getParameter(ConstName.TRAINING_ID));
    request.getSession().setAttribute(ConstName.STUDENT_ID, studentId);
    request.getSession().setAttribute(ConstName.TRAINING_ID, trainingId);
    return ConfigurationManager.getProperty(ConstPage.ORDER_CONSULTATION_PAGE);
  }
}
