package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class ToRegistrationCommand implements Command {


  @Override
  public String execute(HttpServletRequest request) {
    String page = ConfigurationManager.getProperty("path.page.registration");
    return page;
  }
}
