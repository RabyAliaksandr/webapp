package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.connectionpool.exception.ConnectionPoolException;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.service.TrainingsService;

import javax.servlet.http.HttpServletRequest;

public class TaskPageCommand implements Command {

  private static final String TASK_ID = "taskId";
  private static final String TASK_PAGE = "path.page.taskPage";

  @Override
  public String execute(HttpServletRequest request) throws CommandException, CommandException, ConnectionPoolException {

    int taskId = Integer.parseInt(request.getParameter(TASK_ID));
    request.getSession().setAttribute(TASK_ID, taskId);
    return ConfigurationManager.getProperty(TASK_PAGE);
  }
}
