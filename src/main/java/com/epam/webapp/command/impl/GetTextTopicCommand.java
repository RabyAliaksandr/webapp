package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.connectionpool.exception.ConnectionPoolException;

import javax.servlet.http.HttpServletRequest;

public class GetTextTopicCommand implements Command {
  @Override
  public String execute(HttpServletRequest request) throws CommandException, CommandException, ConnectionPoolException {

    String string =  request.getParameter("text_topic");
    System.out.println(string);

    return null;
  }
}
