package com.epam.tc.manager;

import java.util.ResourceBundle;

/**
 * @author alex raby
 * @version 1.0
 *this class returns a message from
 * the properties file depending on the localization
 * <a href="file:../PathPropertiesFiles.PATH_LOCAL/> {@link PathPropertiesFiles#PATH_LOCAL}
 */
public class MessageManager {

  /**
   * object ResourceBundle {@link ResourceBundle} with parameter
   * PathPropertiesFiles.PATH_LOCAL {@link PathPropertiesFiles#PATH_LOCAL}
   */
  private final static ResourceBundle resourceBundle = ResourceBundle.getBundle(PathPropertiesFiles.PATH_LOCAL);

  /**
   * method for getting a String {@link String} by parameter
   * @param key - String message name
   * @return String
   */
  public static String getProperty(String key) {
    return resourceBundle.getString(key);
  }
}