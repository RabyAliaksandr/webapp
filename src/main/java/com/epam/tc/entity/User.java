package com.epam.tc.entity;

import com.epam.tc.validator.RegEx;

/**
 * @author alex raby
 * @version 1.0
 * class describes an entity User
 */
public class User {

  /**
   * unique number
   */
  private int id;

  /**
   * unique User login
   * must match the pattern {@link RegEx#LOGIN_PATTERN}
   */
  private String login;

  /**
   * User password
   * must match the pattern {@link RegEx#PASSWORD_PATTERN}
   */
  private String password;

  /**
   * User type {@link UserType}
   */
  private UserType type;

  /**
   * User name
   * must match the pattern {@link RegEx#NAME_PATTERN}
   */
  private String name;

  /**
   * User surname
   * must match the pattern {@link RegEx#SURNAME_PATTERN}
   */
  private String surname;

  /**
   * User email
   * must match the pattern {@link RegEx#EMAIL_PATTERN}
   */
  private String email;

  /**
   * User status {@link UserStatus}
   */
  private UserStatus status;

  /**
   * method to get the value of the field {@link User#status}
   * @return UserStatus {@link UserStatus}
   */
  public UserStatus getStatus() {
    return status;
  }

  /**
   * method to assignment value of the field{@link User#status}
   * @param status - UserStatus {@link UserStatus}
   */
  public void setStatus(UserStatus status) {
    this.status = status;
  }

  /**
   * method to get the value of the field {@link User#id}
   * @return id, int
   */
  public int getId() {
    return id;
  }

  /**
   * method to assignment value of the field{@link User#id}
   * @param id, unique number
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * method to get the value of the field {@link User#login}
   * @return user login, String
   */
  public String getLogin() {
    return login;
  }

  /**
   * method to assignment value of the field{@link User#login}
   * must match the pattern {@link RegEx#LOGIN_PATTERN}
   * @param login, String
   */
  public void setLogin(String login) {
    this.login = login;
  }

  /**
   * method to get the value of the field {@link User#password}
   * @return encrypted password
   */
  public String getPassword() {
    return password;
  }

  /**
   * method to assignment value of the field{@link User#password}
   * must match the pattern {@link RegEx#PASSWORD_PATTERN}
   * @param password, encrypted password
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * method to get the value of the field {@link User#type}
   * @return UserType {@link UserType}
   */
  public UserType getType() {
    return type;
  }

  /**
   * method to assignment value of the field{@link User#type}
   * @param type - UserType {@link UserType}
   */
  public void setType(UserType type) {
    this.type = type;
  }

  /**
   * method to get the value of the field {@link User#name}
   * @return user name, String
   */
  public String getName() {
    return name;
  }

  /**
   * method to assignment value of the field{@link User#name}
   * must match the pattern {@link RegEx#NAME_PATTERN}
   * @param name - user name, String
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * method to get the value of the field {@link User#surname}
   * @return user surname, String
   */
  public String getSurname() {
    return surname;
  }

  /**
   * method to assignment value of the field{@link User#surname}
   * must match the pattern {@link RegEx#SURNAME_PATTERN}
   * @param surname, user surname, String
   */
  public void setSurname(String surname) {
    this.surname = surname;
  }

  /**
   * method to get the value of the field {@link User#email}
   * @return user email
   */
  public String getEmail() {
    return email;
  }

  /**
   * method to assignment value of the field{@link User#email}
   * must match the pattern {@link RegEx#EMAIL_PATTERN}
   * @param email user email, String
   */
  public void setEmail(String email) {
    this.email = email;
  }
}
