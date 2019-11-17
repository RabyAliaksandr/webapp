package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandConst;
import com.epam.webapp.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class MentoringCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {
    int studentId = Integer.parseInt(request.getParameter(CommandConst.STUDENT_ID));
    request.getSession().setAttribute(CommandConst.STUDENT_ID, studentId);
    return ConfigurationManager.getProperty(CommandConst.MENTORING_PAGE);
  }
}
