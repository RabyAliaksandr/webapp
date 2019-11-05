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

  private static final String WELCOME_PAGE = "path.page.cabinet";
  private static final String LOGIN_ERROR_PAGE = "message.loginError";
  private static final String LOGIN_PAGE = "path.page.login";
  private static final String LOGIN = "login";
  private static final String PASSWORD = "password";
  private static final String USER = "user";
  private static final String ERROR_LOGIN_MESSAGE = "errorLoginPassMessage";

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    String page;
    try {
      User user = UserService.checkLogin(request.getParameter(LOGIN), request.getParameter(PASSWORD));
      request.getSession().setAttribute(USER, user);
      request.setAttribute(USER, user);

      if (user != null) {
        page = ConfigurationManager.getProperty(WELCOME_PAGE);
      } else {
        request.setAttribute(ERROR_LOGIN_MESSAGE, MessageManager.getProperty(LOGIN_ERROR_PAGE));
        page = ConfigurationManager.getProperty(LOGIN_PAGE);
      }
    } catch (ServiceException e) {
      throw new CommandException(e);
    }
    return page;
  }

}