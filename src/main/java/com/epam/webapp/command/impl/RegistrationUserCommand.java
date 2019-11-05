package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.entity.User;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.manager.MessageManager;
import com.epam.webapp.service.UserService;
import com.google.protobuf.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class RegistrationUserCommand implements Command {


  private static final String NAME = "name";
  private static final String SURNAME = "surname";
  private static final String EMAIL = "email";
  private static final String LOGIN = "login";
  private static final String PASSWORD = "password";
  private static final String TYPE = "type";
  private static final String USER = "user";
  private static final String MAIN_PAGE = "path.page.main";


  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    try {
      User user = UserService.checkRegistrationField(request.getParameter(LOGIN), request.getParameter(PASSWORD),
              request.getParameter(NAME), request.getParameter(SURNAME), request.getParameter(EMAIL),
              request.getParameter(TYPE));
      if (user!=null){
        request.getSession().setAttribute(USER, user);

      }else{
//TODO return to error page
      }
    } catch (ServiceException e) {
      throw new CommandException(e);
    }

    return  ConfigurationManager.getProperty(MAIN_PAGE);
  }

}
