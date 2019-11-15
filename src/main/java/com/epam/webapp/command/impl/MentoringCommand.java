package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.connectionpool.ConnectionPoolException;
import com.epam.webapp.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class MentoringCommand implements Command {

  private static final String MENTORING_PAGE = "path.page.mentoring";
  private static final String STUDENT_ID = "studentId";
  private static final String TRAINING_ID = "trainingId";

  @Override
  public String execute(HttpServletRequest request) throws CommandException, ConnectionPoolException {

  int studentId = Integer.parseInt(request.getParameter(STUDENT_ID));
  int trainingId = Integer.parseInt(request.getParameter(TRAINING_ID));
  request.getSession().setAttribute(STUDENT_ID, studentId);
    return ConfigurationManager.getProperty(MENTORING_PAGE);
  }
}
