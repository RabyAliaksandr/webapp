package com.epam.tc.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type User fields validation.
 */
public class UserFieldsValidation {

  /**
   * Is email valid boolean.
   *
   * @param email the email
   * @return the boolean
   */
  public boolean isEmailValid(String email) {
    Pattern pattern = Pattern.compile(RegEx.EMAIL_PATTERN);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
  }

  /**
   * Is name valid boolean.
   *
   * @param name the name
   * @return the boolean
   */
  public boolean isNameValid(String name) {
    Pattern pattern = Pattern.compile(RegEx.NAME_PATTERN);
    Matcher matcher = pattern.matcher(name);
    return matcher.matches();
  }

  /**
   * Is sur name valid boolean.
   *
   * @param surName the sur name
   * @return the boolean
   */
  public boolean isSurNameValid(String surName) {
    Pattern pattern = Pattern.compile(RegEx.SURNAME_PATTERN);
    Matcher matcher = pattern.matcher(surName);
    return matcher.matches();
  }

  /**
   * Is login valid boolean.
   *
   * @param login the login
   * @return the boolean
   */
  public boolean isLoginValid(String login) {
    Pattern pattern = Pattern.compile(RegEx.LOGIN_PATTERN);
    Matcher matcher = pattern.matcher(login);
    return matcher.matches();
  }

  /**
   * Is password valid boolean.
   *
   * @param password the password
   * @return the boolean
   */
  public boolean isPasswordValid(String password) {
    Pattern pattern = Pattern.compile(RegEx.PASSWORD_PATTERN);
    Matcher matcher = pattern.matcher(password);
    return matcher.matches();
  }
}
