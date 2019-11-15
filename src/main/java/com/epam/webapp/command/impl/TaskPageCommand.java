package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.connectionpool.ConnectionPoolException;
import com.epam.webapp.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class TaskPageCommand implements Command {

  private static final String STUDENT_ID = "studentId";
  private static final String TASK_ID = "taskId";
  private static final String SHOW = "showSolution";
  private static final String TASK_PAGE = "path.page.taskPage";


  @Override
  public String execute(HttpServletRequest request) throws CommandException, CommandException, ConnectionPoolException {

    int taskId = Integer.parseInt(request.getParameter(TASK_ID));
    String studentId = request.getParameter(STUDENT_ID);
    String showSolution = request.getParameter(SHOW);
    request.getSession().setAttribute(TASK_ID, taskId);
    request.getSession().setAttribute(STUDENT_ID, studentId );
    request.getSession().setAttribute(SHOW, showSolution);
    return ConfigurationManager.getProperty(TASK_PAGE);
  }
}
