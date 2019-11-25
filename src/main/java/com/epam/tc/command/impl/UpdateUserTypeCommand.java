package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.CommandException;
import com.epam.tc.command.MessageName;
import com.epam.tc.command.VariableName;
import com.epam.tc.command.PageName;
import com.epam.tc.entity.UserStatus;
import com.epam.tc.entity.UserType;
import com.epam.tc.manager.ConfigurationManager;
import com.epam.tc.manager.MessageManager;
import com.epam.tc.service.ServiceFactory;
import com.epam.tc.service.UserService;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * @author alex raby
 * @version 1.0
 * change fields User type {@link UserType} and User status {@link UserStatus}
 */
public class UpdateUserTypeCommand implements Command {

  /**
   * class object Logger {@link Logger}
   * writes important events to a log file
   */
  private static final Logger logger = LogManager.getLogger(UpdateUserTypeCommand.class);

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    UserService userService = ServiceFactory.getUserService();
    int userId = Integer.parseInt(request.getParameter(VariableName.USER_ID));
      UserType type = UserType.getUserType(request.getParameter(VariableName.USER_TYPE));
      UserStatus status = UserStatus.getUserType(request.getParameter(VariableName.USER_STATUS));
      boolean done;
      try {
      userService.updateUserType(userId, type, status);
      } catch (ServiceException e) {
        logger.error(e);
        throw new CommandException("Error access service", e);
      }
        request.getSession().setAttribute(MessageName.MESSAGE_ABOUT_CHANGES,
                MessageManager.getProperty(MessageName.MESSAGE_CHANGES_SAVED));
        return ConfigurationManager.getProperty(PageName.PAGE_ADMIN_USER_MANAGEMENT);
  }
}
