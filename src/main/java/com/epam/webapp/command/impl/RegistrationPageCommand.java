package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class RegistrationPageCommand implements Command {


  private static final String REGISTRATION_PAGE = "path.page.registration";

  @Override
  public String execute(HttpServletRequest request) {
    return ConfigurationManager.getProperty(REGISTRATION_PAGE);

  }
}
