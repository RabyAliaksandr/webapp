package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.connectionpool.exception.ConnectionPoolException;
import com.epam.webapp.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class CreateTextCommand implements Command {
  private static final String CREATE_TEXT_PAGE = "path.page.create_page";

  @Override
  public String execute(HttpServletRequest request) throws CommandException, CommandException, ConnectionPoolException {
    return ConfigurationManager.getProperty(CREATE_TEXT_PAGE);
  }
}
