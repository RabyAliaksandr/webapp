package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandException;
import com.epam.webapp.constant.ConstMessage;
import com.epam.webapp.constant.ConstName;
import com.epam.webapp.constant.ConstPage;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.manager.MessageManager;
import com.epam.webapp.service.UserService;
import com.epam.webapp.service.impl.UserServiceImpl;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteUserCommand implements Command {

  private static Logger logger = LogManager.getLogger(DeleteUserCommand.class);

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    UserService userService = new UserServiceImpl();
    int userId = Integer.parseInt(request.getParameter(ConstName.USER_ID));
    try {
      userService.deleteUser(userId);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException(e);
    }
    request.getSession().setAttribute(ConstMessage.DELETE_USER,
            MessageManager.getProperty(ConstMessage.DELETE_USER_MESSAGE));
    return ConfigurationManager.getProperty(ConstPage.ADMIN_MANAGEMENT);
  }
}
