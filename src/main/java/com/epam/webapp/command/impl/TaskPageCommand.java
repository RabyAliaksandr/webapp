package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.constant.ConstName;
import com.epam.webapp.constant.ConstPage;
import com.epam.webapp.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class TaskPageCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {
    int taskId = Integer.parseInt(request.getParameter(ConstName.TASK_ID));
    String studentId = request.getParameter(ConstName.STUDENT_ID);
    String showSolution = request.getParameter(ConstName.SHOW);
    request.getSession().setAttribute(ConstName.TASK_ID, taskId);
    request.getSession().setAttribute(ConstName.STUDENT_ID, studentId);
    request.getSession().setAttribute(ConstName.SHOW, showSolution);
    return ConfigurationManager.getProperty(ConstPage.TASK_PAGE);
  }
}
