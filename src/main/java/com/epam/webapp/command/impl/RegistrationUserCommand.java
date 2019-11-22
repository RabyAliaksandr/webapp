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
import com.epam.webapp.validator.UserFieldValidation;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class RegistrationUserCommand implements Command {

  private static final Logger logger = LogManager.getLogger(RegistrationUserCommand.class);

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    try {
      String name = request.getParameter(ConstName.NAME);
      String surName = request.getParameter(ConstName.SURNAME);
      String login = request.getParameter(ConstName.LOGIN);
      String email = request.getParameter(ConstName.EMAIL);
      String password = request.getParameter(ConstName.PASSWORD);
      String repeatPassword = request.getParameter(ConstName.REPEAT_PASSWORD);
      Map<String, String> userFields = new HashMap<>();
      UserFieldValidation validation = new UserFieldValidation();
      boolean checkName = validation.isNameValid(name);
      boolean checkSurName = validation.isSurNameValid(surName);
      boolean checkLogin = validation.isLoginValid(login);
      boolean checkEmail = validation.isEmailValid(email);
      boolean checkPassword = validation.isPasswordValid(password);
      boolean checkRepeatPassword = password.equals(repeatPassword);
      UserService userService = new UserServiceImpl();
      boolean loginIsExist = userService.checkLogin(login);
      boolean emailIsExist = userService.checkEmail(email);

      if (checkName) {
        userFields.put(ConstName.NAME, name);
      } else {
        request.getSession().setAttribute(ConstMessage.VALIDATION_NAME,
                MessageManager.getProperty(ConstMessage.VALIDATION_NAME_WRONG));
      }
      if (checkSurName) {
        userFields.put(ConstName.SURNAME, surName);
      } else {
        request.getSession().setAttribute(ConstMessage.VALIDATION_SURNAME,
                MessageManager.getProperty(ConstMessage.VALIDATION_SURNAME_WRONG));
      }
      if (checkLogin) {
        userFields.put(ConstName.LOGIN, login);
      } else {
        request.getSession().setAttribute(ConstMessage.VALIDATION_LOGIN,
                MessageManager.getProperty(ConstMessage.VALIDATION_LOGIN_WRONG));
      }
      if (checkEmail) {
        userFields.put(ConstName.EMAIL, email);
      } else {
        request.getSession().setAttribute(ConstMessage.VALIDATION_EMAIL,
                MessageManager.getProperty(ConstMessage.VALIDATION_EMAIL_WRONG));
      }
      if (!checkPassword)  {
        request.getSession().setAttribute(ConstMessage.VALIDATION_PASSWORD,
                MessageManager.getProperty(ConstMessage.VALIDATION_PASSWORD_WRONG));
      }
      if (!checkRepeatPassword) {
        request.getSession().setAttribute(ConstMessage.VALIDATION_PASSWORD_REPEAT,
                MessageManager.getProperty(ConstMessage.VALIDATION_PASSWORD_REPEAT_WRONG));
      }
      if (loginIsExist) {
        request.getSession().setAttribute(ConstMessage.LOGIN_IS_EXIST,
                MessageManager.getProperty(ConstMessage.LOGIN_IS_EXIST_MESSAGE));
      }
      if (emailIsExist) {
        request.getSession().setAttribute(ConstMessage.EMAIL_IS_EXIST,
                MessageManager.getProperty(ConstMessage.EMAIL_IS_EXIST_MESSAGE));
      }
      request.getSession().setAttribute(ConstName.USER_FIELDS, userFields);
      if (!checkEmail || !checkLogin || !checkName || !checkPassword || !checkSurName || !checkRepeatPassword
      || loginIsExist || emailIsExist) {
        return ConfigurationManager.getProperty(ConstPage.REGISTRATION_PAGE);
      }
      System.out.println("create new user");
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
    return ConfigurationManager.getProperty(ConstPage.MAIN_PAGE);
  }
}
