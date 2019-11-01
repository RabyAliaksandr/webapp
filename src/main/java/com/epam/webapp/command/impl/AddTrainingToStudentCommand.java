package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public class AddTrainingToStudentCommand implements Command {
  @Override
  public String execute(HttpServletRequest request) throws CommandException, CommandException {
    return null;
  }
}
