package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.VariableName;
import com.epam.tc.command.PageName;
import com.epam.tc.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * @author alex raby
 * @version 1.0
 * student consultation order
 */
public class OrderConsultationCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {
    int studentId = Integer.parseInt(request.getParameter(VariableName.STUDENT_ID));
    int trainingId = Integer.parseInt(request.getParameter(VariableName.TRAINING_ID));
    request.getSession().setAttribute(VariableName.STUDENT_ID, studentId);
    request.getSession().setAttribute(VariableName.TRAINING_ID, trainingId);
    return ConfigurationManager.getProperty(PageName.ORDER_CONSULTATION_PAGE);
  }
}
