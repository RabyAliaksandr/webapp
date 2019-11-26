package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.CommandException;
import com.epam.tc.command.PageName;
import com.epam.tc.command.VariableName;
import com.epam.tc.entity.User;
import com.epam.tc.manager.ConfigurationManager;
import com.epam.tc.manager.MessageManager;
import com.epam.tc.service.ServiceFactory;
import com.epam.tc.service.UserService;
import com.epam.tc.command.MessageName;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Delete user command.
 *
 * @author alex raby
 * @version 1.0 deleting User {@link User} by User id
 */
public class DeleteUserCommand implements Command {

  /**
   * class object Logger {@link Logger}
   * writes important events to a log file
   */
  private static Logger logger = LogManager.getLogger(DeleteUserCommand.class);

  /** {@inheritDoc} */
  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    UserService userService = ServiceFactory.getUserService();
    int userId = Integer.parseInt(request.getParameter(VariableName.USER_ID));
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
