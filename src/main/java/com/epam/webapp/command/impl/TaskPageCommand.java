package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandConst;
import com.epam.webapp.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class TaskPageCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {
    int taskId = Integer.parseInt(request.getParameter(CommandConst.TASK_ID));
    String studentId = request.getParameter(CommandConst.STUDENT_ID);
    String showSolution = request.getParameter(CommandConst.SHOW);
    request.getSession().setAttribute(CommandConst.TASK_ID, taskId);
    request.getSession().setAttribute(CommandConst.STUDENT_ID, studentId );
    request.getSession().setAttribute(CommandConst.SHOW, showSolution);
    return ConfigurationManager.getProperty(CommandConst.TASK_PAGE);
  }
}
