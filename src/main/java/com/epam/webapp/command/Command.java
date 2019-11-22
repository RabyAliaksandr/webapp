package com.epam.webapp.command;


import com.epam.webapp.service.ServiceException;

import javax.servlet.http.HttpServletRequest;

public interface Command {
  String execute(HttpServletRequest request) throws CommandException;
}
