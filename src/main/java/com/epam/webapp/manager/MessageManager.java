package com.epam.webapp.manager;

import java.util.ResourceBundle;

public class MessageManager {

  private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("../messages");
  public   MessageManager() { }
  public static String getProperty(String key) {
    return resourceBundle.getString(key);
  }
}