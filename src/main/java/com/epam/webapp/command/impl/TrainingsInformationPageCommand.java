package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.constant.ConstName;
import com.epam.webapp.constant.ConstPage;
import com.epam.webapp.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class TrainingsInformationPageCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {
    String trainingId = request.getParameter(ConstName.TRAINING_ID);
    String editor = request.getParameter(ConstName.EDITOR);
    request.getSession().setAttribute(ConstName.TRAINING_ID, trainingId);
    request.getSession().setAttribute(ConstName.EDITOR, editor);
    return ConfigurationManager.getProperty(ConstPage.TRAININGS_INFORMATION_PAGE);
  }
}
