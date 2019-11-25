package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.VariableName;
import com.epam.tc.command.PageName;
import com.epam.tc.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * @author alex raby
 * @version 1.0
 * return to task page
 */
public class TaskPageCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {
    int taskId = Integer.parseInt(request.getParameter(VariableName.TASK_ID));
    String studentId = request.getParameter(VariableName.STUDENT_ID);
    String showSolution = request.getParameter(VariableName.SHOW);
    request.getSession().setAttribute(VariableName.TASK_ID, taskId);
    request.getSession().setAttribute(VariableName.STUDENT_ID, studentId);
    request.getSession().setAttribute(VariableName.SHOW, showSolution);
    return ConfigurationManager.getProperty(PageName.TASK_PAGE);
  }
}
