package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class LogOutCommand implements Command {

  private static final String INDEX_PAGE = "path.page.index";

  @Override
  public String execute(HttpServletRequest request) {

    request.getSession().invalidate();
    return ConfigurationManager.getProperty(INDEX_PAGE);
  }
}