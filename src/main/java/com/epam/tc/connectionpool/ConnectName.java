package com.epam.tc.connectionpool;

/**
 * contains constant names of properties variable
 *
 * @author alex raby
 * @version 1.0
 */
public class ConnectName {

  /**
   * path to properties file
   */
  public static final String PROPERTIES_FILE = "dataBaseConnection.properties";
  /**
   * driver
   */
  public static final String DRIVER = "jdbc.drivers";
  /**
   * url
   */
  public static final String URL = "jdbc.url";
  /**
   * name
   */
  public static final String NAME = "jdbc.username";
  /**
   * password
   */
  public static final String PASSWORD = "jdbc.password";
  /**
   * ConnectionPool size
   */
  public static final String SIZE = "jdbc.poolSize";
  /**
   * path to test properties. For Tests.
   */
  public static final String TEST_PROPERTIES_PATH = "testDataBaseConnection.properties";
}
