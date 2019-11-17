package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandConst;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.entity.UserStatus;
import com.epam.webapp.entity.UserType;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.manager.MessageManager;
import com.epam.webapp.service.impl.UserServiceImpl;
import com.google.protobuf.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class UpdateUserTypeCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    UserServiceImpl userService = new UserServiceImpl();
    int userId = Integer.parseInt(request.getParameter(CommandConst.USER_ID));
    UserType type = UserType.getUserType(request.getParameter(CommandConst.USER_TYPE));
    UserStatus status = UserStatus.getUserType(request.getParameter(CommandConst.USER_STATUS));
    boolean done;
    try {
      done = userService.updateUserType(userId, type, status);
    } catch (ServiceException e) {
      throw new CommandException("Error access service", e);
    }
    if (done) {
      request.getSession().setAttribute(CommandConst.MESSAGE_ABOUT_CHANGES,
              MessageManager.getProperty(CommandConst.MESSAGE_CHANGES_SAVED));
      return ConfigurationManager.getProperty(CommandConst.ADMIN_CABINET);
    }
    request.getSession().setAttribute(CommandConst.MESSAGE_ABOUT_CHANGES,
            MessageManager.getProperty(CommandConst.MESSAGE_CHANGES_ERROR));
    return ConfigurationManager.getProperty(CommandConst.ADMIN_CABINET);
  }
}
