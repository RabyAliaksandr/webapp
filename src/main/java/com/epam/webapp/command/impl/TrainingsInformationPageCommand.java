package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class TrainingsInformationPageCommand implements Command {
  private static final String TRAINING_ID = "trainingId";
  private static final String TRAININGS_INFORMATION_PAGE = "path.page.trainingsInformation";

  @Override
  public String execute(HttpServletRequest request) {
    String trainingId = request.getParameter(TRAINING_ID);
    request.getSession().setAttribute("trainingId", trainingId);
    return ConfigurationManager.getProperty(TRAININGS_INFORMATION_PAGE);
  }
}
