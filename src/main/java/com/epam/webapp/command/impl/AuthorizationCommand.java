package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandException;
import com.epam.webapp.constant.ConstMessage;
import com.epam.webapp.constant.ConstName;
import com.epam.webapp.constant.ConstPage;
import com.epam.webapp.encoder.PassEncoder;
import com.epam.webapp.entity.User;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.manager.MessageManager;
import com.epam.webapp.service.UserService;
import com.epam.webapp.service.impl.UserServiceImpl;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AuthorizationCommand implements Command {

  private static final Logger logger = LogManager.getLogger(AuthorizationCommand.class);

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
      String page;
    PassEncoder passEncoder = new PassEncoder();
      try {
        User user = new User();
        UserService userService = new UserServiceImpl();
        user = userService.checkLogin(request.getParameter(ConstName.LOGIN),
                passEncoder.md5Apache(request.getParameter(ConstName.PASSWORD)));
        request.getSession().setAttribute(ConstName.USER, user);
        request.setAttribute(ConstName.USER, user);
        if (user != null) {
          page = ConfigurationManager.getProperty(ConstPage.CABINET_PAGE);
        } else {
          request.setAttribute(ConstMessage.NAME_ERROR_MESSAGE,
                  MessageManager.getProperty(ConstMessage.ERROR_MESSAGE));
          page = ConfigurationManager.getProperty(ConstPage.LOGIN_PAGE);
        }
      } catch (ServiceException e) {
        throw new CommandException(e);
      }
      return page;
    }
  }