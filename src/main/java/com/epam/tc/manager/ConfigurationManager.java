package com.epam.tc.manager;

import java.util.ResourceBundle;

public class ConfigurationManager {

  private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("../config");

  public ConfigurationManager() {
  }

  public static String getProperty(String key) {
    return resourceBundle.getString(key);
  }
}