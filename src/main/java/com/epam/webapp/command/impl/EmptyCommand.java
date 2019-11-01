package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {
    String page = ConfigurationManager.getProperty("path.page.login");
    return page;
  }
}
