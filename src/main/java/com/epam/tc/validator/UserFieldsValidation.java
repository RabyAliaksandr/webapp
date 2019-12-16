package com.epam.tc.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type User fields validation.
 *
 * @author alex raby
 * @version 1.0
 */
public class UserFieldsValidation {

  /**
   * Is email validation by RegEx
   *
   * @param email String
   * @return the boolean conformity RegEx
   * @see RegEx#EMAIL_PATTERN
   */
  public boolean isEmailValid(String email) {
    Pattern pattern = Pattern.compile(RegEx.EMAIL_PATTERN);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
  }

  /**
   * Is name validation by by Regex
   *
   * @param name String
   * @return the boolean conformity RegEx
   * @see RegEx#NAME_PATTERN
   */
  public boolean isNameValid(String name) {
    Pattern pattern = Pattern.compile(RegEx.NAME_PATTERN);
    Matcher matcher = pattern.matcher(name);
    return matcher.matches();
  }

  /**
   * Is surname validation by RegEx
   *
   * @param surName String
   * @return the boolean conformity RegEx
   * @see RegEx#SURNAME_PATTERN
   */
  public boolean isSurNameValid(String surName) {
    Pattern pattern = Pattern.compile(RegEx.SURNAME_PATTERN);
    Matcher matcher = pattern.matcher(surName);
    return matcher.matches();
  }

  /**
   * Is login validation by RegEx
   *
   * @param login String
   * @return the boolean conformity RegEx
   * @see RegEx#LOGIN_PATTERN
   */
  public boolean isLoginValid(String login) {
    Pattern pattern = Pattern.compile(RegEx.LOGIN_PATTERN);
    Matcher matcher = pattern.matcher(login);
    return matcher.matches();
  }

  /**
   * Is password validation by RegEx
   *
   * @param password String
   * @return the boolean conformity RegEx
   * @see RegEx#PASSWORD_PATTERN
   */
  public boolean isPasswordValid(String password) {
    Pattern pattern = Pattern.compile(RegEx.PASSWORD_PATTERN);
    Matcher matcher = pattern.matcher(password);
    return matcher.matches();
  }
}
