package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandConst;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.entity.User;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.service.UserService;
import com.epam.webapp.service.impl.UserServiceImpl;
import com.google.protobuf.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class RegistrationUserCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    try {
      UserService userService = new UserServiceImpl();
      User user = userService.checkRegistrationField(request.getParameter(CommandConst.LOGIN), request.getParameter(CommandConst.PASSWORD),
              request.getParameter(CommandConst.NAME), request.getParameter(CommandConst.SURNAME), request.getParameter(CommandConst.EMAIL),
              request.getParameter(CommandConst.TYPE));
      if (user != null) {
        request.getSession().setAttribute(CommandConst.USER, user);
      } else {
//TODO return to error page // FIXME send message - user already exists
      }
    } catch (ServiceException e) {
      throw new CommandException("Error access service", e);
    }
    return ConfigurationManager.getProperty(CommandConst.MAIN_PAGE);
  }

}
