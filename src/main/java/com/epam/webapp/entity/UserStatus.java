package com.epam.webapp.entity;

public enum UserStatus {
  UNBLOCKED,
  BLOCKED;

  public static UserStatus getUserType(String name) {
    try {
      return UserStatus.valueOf(name.toUpperCase());
    } catch (EnumConstantNotPresentException e) {
      throw new EnumConstantNotPresentException(UserType.class, "no parameter set"); //  FIXME
    }
  }
}