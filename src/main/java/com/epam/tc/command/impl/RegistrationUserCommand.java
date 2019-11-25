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
import com.epam.tc.service.ServiceFactory;
import com.epam.tc.service.UserService;
import com.epam.tc.validator.UserFieldsValidation;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author alex raby
 * @version 1.0
 * new User registration
 */
public class RegistrationUserCommand implements Command {

  /**
   * class object Logger {@link Logger}
   * writes important events to a log file
   */
  private static final Logger logger = LogManager.getLogger(RegistrationUserCommand.class);

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    try {
      String name = request.getParameter(VariableName.NAME);
      String surName = request.getParameter(VariableName.SURNAME);
      String login = request.getParameter(VariableName.LOGIN);
      String email = request.getParameter(VariableName.EMAIL);
      String password = request.getParameter(VariableName.PASSWORD);
      String repeatPassword = request.getParameter(VariableName.REPEAT_PASSWORD);
      Map<String, String> userFields = new HashMap<>();
      UserFieldsValidation validation = new UserFieldsValidation();
      boolean checkName = validation.isNameValid(name);
      boolean checkSurName = validation.isSurNameValid(surName);
      boolean checkLogin = validation.isLoginValid(login);
      boolean checkEmail = validation.isEmailValid(email);
      boolean checkPassword = validation.isPasswordValid(password);
      boolean checkRepeatPassword = password.equals(repeatPassword);
      UserService userService = ServiceFactory.getUserService();
      boolean loginIsExist = userService.checkLogin(login);
      boolean emailIsExist = userService.checkEmail(email);
      if (checkName) {
        userFields.put(VariableName.NAME, name);
      } else {
        request.getSession().setAttribute(MessageName.VALIDATION_NAME,
                MessageManager.getProperty(MessageName.VALIDATION_NAME_WRONG));
      }
      if (checkSurName) {
        userFields.put(VariableName.SURNAME, surName);
      } else {
        request.getSession().setAttribute(MessageName.VALIDATION_SURNAME,
                MessageManager.getProperty(MessageName.VALIDATION_SURNAME_WRONG));
      }
      if (checkLogin) {
        userFields.put(VariableName.LOGIN, login);
      } else {
        request.getSession().setAttribute(MessageName.VALIDATION_LOGIN,
                MessageManager.getProperty(MessageName.VALIDATION_LOGIN_WRONG));
      }
      if (checkEmail) {
        userFields.put(VariableName.EMAIL, email);
      } else {
        request.getSession().setAttribute(MessageName.VALIDATION_EMAIL,
                MessageManager.getProperty(MessageName.VALIDATION_EMAIL_WRONG));
      }
      if (!checkPassword)  {
        request.getSession().setAttribute(MessageName.VALIDATION_PASSWORD,
                MessageManager.getProperty(MessageName.VALIDATION_PASSWORD_WRONG));
      }
      if (!checkRepeatPassword) {
        request.getSession().setAttribute(MessageName.VALIDATION_PASSWORD_REPEAT,
                MessageManager.getProperty(MessageName.VALIDATION_PASSWORD_REPEAT_WRONG));
      }
      if (loginIsExist) {
        request.getSession().setAttribute(MessageName.LOGIN_IS_EXIST,
                MessageManager.getProperty(MessageName.LOGIN_IS_EXIST_MESSAGE));
      }
      if (emailIsExist) {
        request.getSession().setAttribute(MessageName.EMAIL_IS_EXIST,
                MessageManager.getProperty(MessageName.EMAIL_IS_EXIST_MESSAGE));
      }
      request.getSession().setAttribute(VariableName.USER_FIELDS, userFields);
      if (!checkEmail || !checkLogin || !checkName || !checkPassword || !checkSurName || !checkRepeatPassword
      || loginIsExist || emailIsExist) {
        return ConfigurationManager.getProperty(PageName.REGISTRATION_PAGE);
      }
      User user = new User();
      user.setLogin(login);
      user.setEmail(email);
      user.setSurname(surName);
      user.setName(name);
      PassEncoder passEncoder = new PassEncoder();
      user.setPassword(passEncoder.md5Apache(password));
      userService.registration(user);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException("Error access service", e);
    }
    return ConfigurationManager.getProperty(PageName.MAIN_PAGE);
  }
}
