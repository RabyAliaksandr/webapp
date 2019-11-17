package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandConst;
import com.epam.webapp.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class TrainingsInformationPageCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {
    String trainingId = request.getParameter(CommandConst.TRAINING_ID);
    String editor = request.getParameter(CommandConst.EDITOR);
    request.getSession().setAttribute(CommandConst.TRAINING_ID, trainingId);
    request.getSession().setAttribute(CommandConst.EDITOR, editor);
    return ConfigurationManager.getProperty(CommandConst.TRAININGS_INFORMATION_PAGE);
  }
}
