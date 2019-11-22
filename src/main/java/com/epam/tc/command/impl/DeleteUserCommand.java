package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.CommandException;
import com.epam.tc.command.MessageName;
import com.epam.tc.command.RequestVariableName;
import com.epam.tc.command.PageName;
import com.epam.tc.manager.ConfigurationManager;
import com.epam.tc.manager.MessageManager;
import com.epam.tc.service.ServiceFactory;
import com.epam.tc.service.UserService;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteUserCommand implements Command {

  private static Logger logger = LogManager.getLogger(DeleteUserCommand.class);

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    UserService userService = ServiceFactory.getUserService();
    int userId = Integer.parseInt(request.getParameter(RequestVariableName.USER_ID));
    try {
      userService.deleteUser(userId);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException(e);
    }
    request.getSession().setAttribute(MessageName.DELETE_USER,
            MessageManager.getProperty(MessageName.DELETE_USER_MESSAGE));
    return ConfigurationManager.getProperty(PageName.ADMIN_MANAGEMENT);
  }
}
