package com.epam.webapp.validator;

import com.epam.webapp.constant.ConstRegEx;
import org.apache.logging.log4j.core.pattern.PatternParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserFieldValidation {

  public boolean isEmailValid(String email) {
    Pattern pattern = Pattern.compile(ConstRegEx.EMAIL_PATTERN);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
  }

  public boolean isNameValid(String name) {
    Pattern pattern = Pattern.compile(ConstRegEx.NAME_PATTERN);
    Matcher matcher = pattern.matcher(name);
    return matcher.matches();
  }

  public boolean isSurNameValid(String surName) {
    Pattern pattern = Pattern.compile(ConstRegEx.SURNAME_PATTERN);
    Matcher matcher = pattern.matcher(surName);
    return matcher.matches();
  }

  public boolean isLoginValid(String login) {
    Pattern pattern = Pattern.compile(ConstRegEx.LOGIN_PATTERN);
    Matcher matcher = pattern.matcher(login);
    return matcher.matches();
  }

  public boolean isPasswordValid(String password) {
    Pattern pattern = Pattern.compile(ConstRegEx.PASSWORD_PATTERN);
    Matcher matcher = pattern.matcher(password);
    return matcher.matches();
  }
}
