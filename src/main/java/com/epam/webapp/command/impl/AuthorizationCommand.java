package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandConst;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.entity.User;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.manager.MessageManager;
import com.epam.webapp.service.UserService;
import com.epam.webapp.service.impl.UserServiceImpl;
import com.google.protobuf.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class AuthorizationCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    String page;
    UserService userService = new UserServiceImpl();
    try {
      User user = userService.checkLogin(request.getParameter(CommandConst.LOGIN),
              request.getParameter(CommandConst.PASSWORD));
      request.getSession().setAttribute(CommandConst.USER, user);
      request.setAttribute(CommandConst.USER, user);
      if (user != null) {
        page = ConfigurationManager.getProperty(CommandConst.CABINET_PAGE);
      } else {
        request.setAttribute(CommandConst.NAME_ERROR_MESSAGE,
                MessageManager.getProperty(CommandConst.ERROR_MESSAGE));
        page = ConfigurationManager.getProperty(CommandConst.LOGIN_PAGE);
      }
    } catch (ServiceException e) {
      throw new CommandException(e);
    }
    return page;
  }
}