package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements Command {

  private static final String LOGIN_PAGE = "path.page.login";

  @Override
  public String execute(HttpServletRequest request) {
    return ConfigurationManager.getProperty(LOGIN_PAGE);
  }
}
