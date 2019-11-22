package com.epam.tc.entity;

public enum UserStatus {
  UNBLOCKED,
  BLOCKED;

  public static UserStatus getUserType(String name) {
    try {
      return UserStatus.valueOf(name.toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new EnumConstantNotPresentException(UserType.class, "no parameter set"); //  FIXME
    }
  }
}