package com.epam.webapp.command;

import com.epam.webapp.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public interface Command {
  String execute(HttpServletRequest request) throws CommandException, CommandException;
}
