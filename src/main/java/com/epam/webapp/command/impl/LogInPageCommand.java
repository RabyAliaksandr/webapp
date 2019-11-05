package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class LogInPageCommand implements Command {

  private static final String toLogInPage = "path.page.login";

  @Override
  public String execute(HttpServletRequest request) throws CommandException, CommandException {
    return ConfigurationManager.getProperty(toLogInPage);
  }
}
