package com.epam.tc.entity;

import com.epam.tc.validator.RegEx;

/**
 * The type User.
 *
 * @author alex raby
 * @version 1.0 class describes an entity User
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
   *
   * @return UserStatus {@link UserStatus}
   */
  public UserStatus getStatus() {
    return status;
  }

  /**
   * method to assignment value of the field{@link User#status}
   *
   * @param status - UserStatus {@link UserStatus}
   */
  public void setStatus(UserStatus status) {
    this.status = status;
  }

  /**
   * method to get the value of the field {@link User#id}
   *
   * @return id, int
   */
  public int getId() {
    return id;
  }

  /**
   * method to assignment value of the field{@link User#id}
   *
   * @param id the id
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * method to get the value of the field {@link User#login}
   *
   * @return user login, String
   */
  public String getLogin() {
    return login;
  }

  /**
   * method to assignment value of the field{@link User#login}
   * must match the pattern {@link RegEx#LOGIN_PATTERN}
   *
   * @param login the login
   */
  public void setLogin(String login) {
    this.login = login;
  }

  /**
   * method to get the value of the field {@link User#password}
   *
   * @return encrypted password
   */
  public String getPassword() {
    return password;
  }

  /**
   * method to assignment value of the field{@link User#password}
   * must match the pattern {@link RegEx#PASSWORD_PATTERN}
   *
   * @param password the password
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * method to get the value of the field {@link User#type}
   *
   * @return UserType {@link UserType}
   */
  public UserType getType() {
    return type;
  }

  /**
   * method to assignment value of the field{@link User#type}
   *
   * @param type - UserType {@link UserType}
   */
  public void setType(UserType type) {
    this.type = type;
  }

  /**
   * method to get the value of the field {@link User#name}
   *
   * @return user name, String
   */
  public String getName() {
    return name;
  }

  /**
   * method to assignment value of the field{@link User#name}
   * must match the pattern {@link RegEx#NAME_PATTERN}
   *
   * @param name - user name, String
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * method to get the value of the field {@link User#surname}
   *
   * @return user surname, String
   */
  public String getSurname() {
    return surname;
  }

  /**
   * method to assignment value of the field{@link User#surname}
   * must match the pattern {@link RegEx#SURNAME_PATTERN}
   *
   * @param surname the surname
   */
  public void setSurname(String surname) {
    this.surname = surname;
  }

  /**
   * method to get the value of the field {@link User#email}
   *
   * @return user email
   */
  public String getEmail() {
    return email;
  }

  /**
   * method to assignment value of the field{@link User#email}
   * must match the pattern {@link RegEx#EMAIL_PATTERN}
   *
   * @param email user email, String
   */
  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    User user = (User) obj;
    if (getSurname() != null ? !getSurname().equals(user.getSurname()) : user.getSurname() != null) {
      return false;
    }
    if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null) {
      return false;
    }
    if (getId() != user.getId()) {
      return false;
    }
    if (getStatus() != null ? !getStatus().equals(user.getStatus()) : user.getStatus() != null) {
      return false;
    }
    if (getType() != null ? !getType().equals(user.getType()) : user.getType() != null) {
      return false;
    }
    if (getLogin() != null ? !getLogin().equals(user.getLogin()) : user.getLogin() != null) {
      return false;
    }
    return getPassword() != null ? getPassword().equals(user.getPassword()) : user.getLogin() == null;
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 37 * result + (id);
    result = 37 * result + (name == null ? 0 : name.hashCode());
    result = 37 * result + (surname == null ? 0 : surname.hashCode());
    result = 37 * result + (status == null ? 0: status.hashCode());
    result = 37 * result + (type == null ? 0 : type.hashCode());
    result = 37 * result + (login == null ? 0 : login.hashCode());
    result = 37 * result + (password == null ? 0 : password.hashCode());
    result = 37 * result + (email ==null ? 0 : email.hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder toString = new StringBuilder();
    toString.append("User # ").append(id);
    toString.append(" name: ").append(name);
    toString.append(" surname: ").append(surname);
    toString.append(" login: ").append(login);
    toString.append(" password: ").append("********");
    toString.append(" email: ").append(email);
    toString.append(" type ").append(type);
    toString.append(" status: ").append(status);
    return String.valueOf(toString);
  }
}