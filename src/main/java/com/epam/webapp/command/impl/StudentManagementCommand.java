package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.connectionpool.ConnectionPoolException;
import com.epam.webapp.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class StudentManagementCommand implements Command {

  private static final String STUDENT_MANAGEMENT_PAGE = "path.page.studentManagement";

  @Override
  public String execute(HttpServletRequest request) throws CommandException, CommandException, ConnectionPoolException {
    return ConfigurationManager.getProperty(STUDENT_MANAGEMENT_PAGE);
  }
}
