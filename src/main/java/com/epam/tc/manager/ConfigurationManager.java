package com.epam.tc.manager;

import java.util.ResourceBundle;

/**
 * The type Configuration manager.
 * this class returns a message from the properties file depending on the localization
 * {@link PathPropertiesFiles#PATH_CONFIG}
 *
 * @author alex raby
 * @version 1.0
 */
public class ConfigurationManager {

  /**
   * object ResourceBundle {@link ResourceBundle} with parameter
   * PathPropertiesFiles.PATH_LOCAL {@link PathPropertiesFiles#PATH_CONFIG}
   */
  private final static ResourceBundle resourceBundle = ResourceBundle.getBundle(PathPropertiesFiles.PATH_CONFIG);

  /**
   * method for getting a String message {@link String} by parameter
   *
   * @param key - String message name
   * @return String message
   */
  public static String getProperty(String key) {
    return resourceBundle.getString(key);
  }
}