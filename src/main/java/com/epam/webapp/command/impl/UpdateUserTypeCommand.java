package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandException;
import com.epam.webapp.constant.ConstMessage;
import com.epam.webapp.constant.ConstName;
import com.epam.webapp.constant.ConstPage;
import com.epam.webapp.entity.UserStatus;
import com.epam.webapp.entity.UserType;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.manager.MessageManager;
import com.epam.webapp.service.impl.UserServiceImpl;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UpdateUserTypeCommand implements Command {

  private static final Logger logger = LogManager.getLogger(UpdateUserTypeCommand.class);

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    UserServiceImpl userService = new UserServiceImpl();
    int userId = Integer.parseInt(request.getParameter(ConstName.USER_ID));
    UserType type = UserType.getUserType(request.getParameter(ConstName.USER_TYPE));
    UserStatus status = UserStatus.getUserType(request.getParameter(ConstName.USER_STATUS));
    boolean done;
    try {
      done = userService.updateUserType(userId, type, status);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException("Error access service", e);
    }
    if (done) {
      request.getSession().setAttribute(ConstMessage.MESSAGE_ABOUT_CHANGES,
              MessageManager.getProperty(ConstMessage.MESSAGE_CHANGES_SAVED));
      return ConfigurationManager.getProperty(ConstPage.PAGE_ADMIN_USER_MANAGEMENT);
    }
    request.getSession().setAttribute(ConstMessage.MESSAGE_ABOUT_CHANGES,
            MessageManager.getProperty(ConstMessage.MESSAGE_CHANGES_ERROR));
    return ConfigurationManager.getProperty(ConstPage.PAGE_ADMIN_USER_MANAGEMENT);
  }
}
