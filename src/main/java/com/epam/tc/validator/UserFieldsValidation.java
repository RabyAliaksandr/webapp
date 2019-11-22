package com.epam.tc.validator;

import com.epam.tc.filter.RegEx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserFieldsValidation {

  public boolean isEmailValid(String email) {
    Pattern pattern = Pattern.compile(RegEx.EMAIL_PATTERN);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
  }

  public boolean isNameValid(String name) {
    Pattern pattern = Pattern.compile(RegEx.NAME_PATTERN);
    Matcher matcher = pattern.matcher(name);
    return matcher.matches();
  }

  public boolean isSurNameValid(String surName) {
    Pattern pattern = Pattern.compile(RegEx.SURNAME_PATTERN);
    Matcher matcher = pattern.matcher(surName);
    return matcher.matches();
  }

  public boolean isLoginValid(String login) {
    Pattern pattern = Pattern.compile(RegEx.LOGIN_PATTERN);
    Matcher matcher = pattern.matcher(login);
    return matcher.matches();
  }

  public boolean isPasswordValid(String password) {
    Pattern pattern = Pattern.compile(RegEx.PASSWORD_PATTERN);
    Matcher matcher = pattern.matcher(password);
    return matcher.matches();
  }
}
