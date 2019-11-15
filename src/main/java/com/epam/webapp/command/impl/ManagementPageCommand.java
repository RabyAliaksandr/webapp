package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.connectionpool.ConnectionPoolException;
import com.epam.webapp.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class ManagementPageCommand implements Command {
  private static final String MANAGEMENT_PAGE = "path.page.management";
  private static final String TYPE_OPERATION = "typeOperation";

  @Override
  public String execute(HttpServletRequest request) throws CommandException, CommandException, ConnectionPoolException {
    String typeOperation = request.getParameter(TYPE_OPERATION);
    request.getSession().setAttribute(TYPE_OPERATION, typeOperation);
    return ConfigurationManager.getProperty(MANAGEMENT_PAGE);
  }
}
