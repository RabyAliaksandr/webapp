package com.epam.tc.command;


import javax.servlet.http.HttpServletRequest;

public interface Command {
  String execute(HttpServletRequest request) throws CommandException;
}
