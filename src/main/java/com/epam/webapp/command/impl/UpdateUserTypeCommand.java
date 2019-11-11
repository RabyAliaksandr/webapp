package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.connectionpool.exception.ConnectionPoolException;
import com.epam.webapp.entity.UserStatus;
import com.epam.webapp.entity.UserTypes;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.manager.MessageManager;
import com.epam.webapp.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class UpdateUserTypeCommand implements Command {

  private static final String USER_ID = "userId";
  private static final String USER_TYPE = "type";
  private static final String ADMIN_CABINET = "path.page.admin_user_management";
  private static final String MESSAGE_ABOUT_CHANGES = "changesSavedMessage";
  private static final String MESSAGE_CHANGES_SAVED = "message.changesSaved";
  private static final String MESSAGE_CHANGES_ERROR = "message.changesError";
  private static final String USER_STATUS = "status";


  @Override
  public String execute(HttpServletRequest request) throws CommandException, CommandException, ConnectionPoolException {
    UserService userService = new UserService();
    int userId = Integer.parseInt(request.getParameter(USER_ID));
    UserTypes type = UserTypes.getUserType(request.getParameter(USER_TYPE));
    UserStatus status = UserStatus.getUserType(request.getParameter(USER_STATUS));

    boolean done = userService.updateUserType(userId, type, status);

    if (done) {
      request.getSession().setAttribute(MESSAGE_ABOUT_CHANGES, MessageManager.getProperty(MESSAGE_CHANGES_SAVED));
      return ConfigurationManager.getProperty(ADMIN_CABINET);
    }
    request.getSession().setAttribute(MESSAGE_ABOUT_CHANGES, MessageManager.getProperty(MESSAGE_CHANGES_ERROR));
    return ConfigurationManager.getProperty(ADMIN_CABINET);
  }
}
