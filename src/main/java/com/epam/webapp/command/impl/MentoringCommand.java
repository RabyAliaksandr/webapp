package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.constant.ConstName;
import com.epam.webapp.constant.ConstPage;
import com.epam.webapp.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class MentoringCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {
    int studentId = Integer.parseInt(request.getParameter(ConstName.STUDENT_ID));
    request.getSession().setAttribute(ConstName.STUDENT_ID, studentId);
    return ConfigurationManager.getProperty(ConstPage.MENTORING_PAGE);
  }
}
