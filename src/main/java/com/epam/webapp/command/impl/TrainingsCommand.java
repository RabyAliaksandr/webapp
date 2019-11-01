package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class TrainingsCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) throws CommandException, CommandException {
    return ConfigurationManager.getProperty("path.page.trainings");
  }
}
