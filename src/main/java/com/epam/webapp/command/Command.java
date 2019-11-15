package com.epam.webapp.command;

import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.connectionpool.ConnectionPoolException;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public interface Command {
  String execute(HttpServletRequest request) throws CommandException, ConnectionPoolException, ParseException;
}
