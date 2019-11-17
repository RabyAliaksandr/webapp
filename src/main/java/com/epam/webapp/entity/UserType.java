package com.epam.webapp.entity;

public enum UserType {
  ADMIN,
  MENTOR,
  STUDENT,
  GUEST;

  public static UserType getUserType(String name) {
    try {
      return UserType.valueOf(name.toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new EnumConstantNotPresentException(UserType.class, "no parameter set"); //  FIXME
    }
  }
}