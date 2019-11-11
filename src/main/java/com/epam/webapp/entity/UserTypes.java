package com.epam.webapp.entity;

public enum UserTypes {
  ADMIN,
  MENTOR,
  STUDENT;

  public static UserTypes getUserType(String name) {
    name = name.toUpperCase();
    System.out.println(name);
    switch (name) {
      case "ADMIN":
        return ADMIN;
      case "STUDENT":
        return STUDENT;
      case "MENTOR":
        return MENTOR;
      default:
          throw new NullPointerException();
//          TODO throw EnumException()
    }
  }
}

