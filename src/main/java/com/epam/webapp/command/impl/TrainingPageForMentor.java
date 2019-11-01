package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class TrainingPageForMentor implements Command {
  @Override
  public String execute(HttpServletRequest request) {
    String trainingd = (String) request.getParameter("trainingid");
    System.out.println(trainingd + "  value trainingID");
    request.getSession().setAttribute("trainingid", trainingd);
    return ConfigurationManager.getProperty("path.page.trainingformentor");
  }
}
