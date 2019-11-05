package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class TrainingsPageCommand implements Command {

  private static final String TRAININGS_PAGE = "path.page.trainings_page";

  @Override
  public String execute(HttpServletRequest request) throws CommandException, CommandException {
    return ConfigurationManager.getProperty(TRAININGS_PAGE);
  }
}
