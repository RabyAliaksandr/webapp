package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;

import javax.servlet.http.HttpServletRequest;

public class UnknownCommand implements Command {
  @Override
  public String execute(HttpServletRequest request) {
    return null;
  }
}
