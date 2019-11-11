package com.epam.webapp.entity;

public enum UserStatus {
  UNBLOCKED,
  BLOCKED;

  public static UserStatus getUserType(String name) {
    name = name.toUpperCase();
    switch (name) {
      case "BLOCKED":
        return BLOCKED;
      case "UNBLOCKED":
        return UNBLOCKED;
      default:
        throw new NullPointerException();
//          TODO throw EnumException()
    }
  }
}