package com.epam.webapp.connectionpool;

import java.util.ResourceBundle;

/**
 *  class to get data from data file properties
 */
public class DBResourceManager {

  private final static DBResourceManager instance = new DBResourceManager();
  private ResourceBundle bundle = ResourceBundle.getBundle("connectionPool");

  public static DBResourceManager getInstance() {
    return instance;
  }

  /**
   * method to get data from connectionPool.properties
   *
   * @param key
   * @return
   */
  public String getValue(String key) {
    return bundle.getString(key);
  }
}
