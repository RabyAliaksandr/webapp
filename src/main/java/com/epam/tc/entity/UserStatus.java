package com.epam.tc.entity;

/**
 * The enum User status.
 *
 * @author alex raby
 * @version 1.0 describes the status of users and determines the availability of the service for the user
 */
public enum UserStatus {

  /**
   * a user with this status can fully use the site’s
   * capabilities in accordance with the type of user
   */
  UNBLOCKED,

  /**
   * a user with this status cannot access his personal account and its functionality
   */
  BLOCKED;

  /**
   * the method returns the value of the user according to the input parameter.
   * in case of lack of coincidence throws an exception
   *
   * @param name - input parameter, String.
   * @return UserType user type
   * @throws EnumConstantNotPresentException standard exception {@link EnumConstantNotPresentException}
   */
  public static UserStatus getUserType(String name) {
    try {
     return UserStatus.valueOf(name.toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new EnumConstantNotPresentException(UserType.class, "no parameter set");
    }
  }
}