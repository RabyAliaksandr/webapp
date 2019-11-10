package com.epam.webapp.manager;

import java.util.ResourceBundle;

public class MessageManager {

  private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("../local");
  public   MessageManager() { }
  public static String getProperty(String key) {
    return resourceBundle.getString(key);
  }
}