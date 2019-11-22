package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.RequestVariableName;
import com.epam.tc.command.PageName;
import com.epam.tc.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class TaskPageCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {
    int taskId = Integer.parseInt(request.getParameter(RequestVariableName.TASK_ID));
    String studentId = request.getParameter(RequestVariableName.STUDENT_ID);
    String showSolution = request.getParameter(RequestVariableName.SHOW);
    request.getSession().setAttribute(RequestVariableName.TASK_ID, taskId);
    request.getSession().setAttribute(RequestVariableName.STUDENT_ID, studentId);
    request.getSession().setAttribute(RequestVariableName.SHOW, showSolution);
    return ConfigurationManager.getProperty(PageName.TASK_PAGE);
  }
}
