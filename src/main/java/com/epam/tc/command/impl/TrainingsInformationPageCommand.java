package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.RequestVariableName;
import com.epam.tc.command.PageName;
import com.epam.tc.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class TrainingsInformationPageCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {
    String trainingId = request.getParameter(RequestVariableName.TRAINING_ID);
    String editor = request.getParameter(RequestVariableName.EDITOR);
    request.getSession().setAttribute(RequestVariableName.TRAINING_ID, trainingId);
    request.getSession().setAttribute(RequestVariableName.EDITOR, editor);
    return ConfigurationManager.getProperty(PageName.TRAININGS_INFORMATION_PAGE);
  }
}
