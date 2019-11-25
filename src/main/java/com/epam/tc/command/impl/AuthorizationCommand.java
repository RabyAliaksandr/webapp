package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.CommandException;
import com.epam.tc.command.MessageName;
import com.epam.tc.command.VariableName;
import com.epam.tc.command.PageName;
import com.epam.tc.encoder.PassEncoder;
import com.epam.tc.entity.User;
import com.epam.tc.manager.ConfigurationManager;
import com.epam.tc.manager.MessageManager;
import com.epam.tc.service.UserService;
import com.epam.tc.service.impl.UserServiceImpl;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * @author alex raby
 * @version 1.0
 * user authorization
 * if this user does not exist, reports an incorrect login or password
 */
public class AuthorizationCommand implements Command {

  /**
   * class object Logger {@link Logger}
   * writes important events to a log file
   */
  private static final Logger logger = LogManager.getLogger(AuthorizationCommand.class);

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    PassEncoder passEncoder = new PassEncoder();
    try {
      User user = new User();
      UserService userService = new UserServiceImpl();
      user = userService.checkLogin(request.getParameter(VariableName.LOGIN),
              passEncoder.md5Apache(request.getParameter(VariableName.PASSWORD)));
      request.getSession().setAttribute(VariableName.USER, user);
      request.setAttribute(VariableName.USER, user);
      if (user != null) {
        return ConfigurationManager.getProperty(PageName.CABINET_PAGE);
      } else {
        request.setAttribute(MessageName.NAME_ERROR_MESSAGE,
                MessageManager.getProperty(MessageName.ERROR_MESSAGE));
        return ConfigurationManager.getProperty(PageName.LOGIN_PAGE);
      }
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException(e);
    }
  }
}