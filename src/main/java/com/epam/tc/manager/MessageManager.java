package com.epam.tc.manager;

import java.util.ResourceBundle;

/**
 * The type Message manager.
 * this class returns a message from the properties file depending on the localization
 * {@link PathPropertiesFiles#PATH_LOCAL}
 *
 * @author alex raby
 * @version 1.0
 */
public class MessageManager {

  /**
   * object ResourceBundle {@link ResourceBundle} with parameter
   * PathPropertiesFiles.PATH_LOCAL {@link PathPropertiesFiles#PATH_LOCAL}
   */
  private final static ResourceBundle resourceBundle = ResourceBundle.getBundle(PathPropertiesFiles.PATH_LOCAL);

  /**
   * method for getting a String message by parameter
   *
   * @param key - String message name
   * @return message
   */
  public static String getProperty(String key) {
    return resourceBundle.getString(key);
  }
}