package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.RequestVariableName;
import com.epam.tc.command.PageName;
import com.epam.tc.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class OrderConsultationCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {
    int studentId = Integer.parseInt(request.getParameter(RequestVariableName.STUDENT_ID));
    int trainingId = Integer.parseInt(request.getParameter(RequestVariableName.TRAINING_ID));
    request.getSession().setAttribute(RequestVariableName.STUDENT_ID, studentId);
    request.getSession().setAttribute(RequestVariableName.TRAINING_ID, trainingId);
    return ConfigurationManager.getProperty(PageName.ORDER_CONSULTATION_PAGE);
  }
}
