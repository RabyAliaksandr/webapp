package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.entity.User;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.manager.MessageManager;
import com.epam.webapp.service.UserService;
import com.google.protobuf.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class AuthorizationCommand implements Command {

  private static final String LOGIN = "login";
  private static final String PASSWORD = "password";
  private static final String USER = "user";

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    String page = null;
    try {
      User user = UserService.checkLogin(request.getParameter(LOGIN), request.getParameter(PASSWORD));
      request.getSession().setAttribute(USER, user);
      request.setAttribute(USER, user);

      if (user != null) {
        page = ConfigurationManager.getProperty("path.page.welcome");
      } else {
        request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
        page = ConfigurationManager.getProperty("path.page.login");
      }
    } catch (ServiceException e) {
      throw new CommandException(e);
    }
    return page;
  }

}