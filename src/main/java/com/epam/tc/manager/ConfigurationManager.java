package com.epam.tc.manager;

import java.util.ResourceBundle;

/**
 * @author alex raby
 * @version 1.0
 *this class returns a message from
 * the properties file depending on the localization
 * <a href="file:../PathPropertiesFiles.PATH_CONFIG/> {@link PathPropertiesFiles#PATH_CONFIG}
 */
public class ConfigurationManager {

  /**
   * object ResourceBundle {@link ResourceBundle} with parameter
   * PathPropertiesFiles.PATH_LOCAL {@link PathPropertiesFiles#PATH_CONFIG}
   */
  private final static ResourceBundle resourceBundle = ResourceBundle.getBundle(PathPropertiesFiles.PATH_CONFIG);

  /**
   * method for getting a String {@link String} by parameter
   * @param key - String message name
   * @return String
   */
  public static String getProperty(String key) {
    return resourceBundle.getString(key);
  }
}