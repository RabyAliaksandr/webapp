package com.epam.tc.entity;

/**
 * @author alex raby
 * @version 1.0
 * describes the type of user
 * depending on the type, site functionality is available
 */
public enum UserType {

  /**
   * this user has access to the administrator's functional account
   * manages users, mentors, trainings, tasks and topics
   */
  ADMIN,

  /**
   * oversees trainings, assigns assignments, puts notes to students, manages topics
   */
  MENTOR,

  /**
   * can record for training, study topics, solve problems, book consultations
   */
  STUDENT,

  /**
   * this user can only view information about ongoing training
   */
  GUEST;

  public static UserType getUserType(String name) {
    try {
      return UserType.valueOf(name.toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new EnumConstantNotPresentException(UserType.class, "no parameter set");
    }
  }
}