package com.epam.tc.connectionpool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

/**
 * reading data to connect to the database from properties file
 * for ConnectionPool {@link ConnectionPool}
 *
 * @author alex raby
 * @version 1.0
 */
public final class DataBaseManager {

  private static Logger logger = LogManager.getLogger(DataBaseManager.class);
  private static Properties properties = null;
  private static String drivers = null;
  private static String connectionURL = null;
  private static String username = null;
  private static String password = null;
  private static int poolSize = 0;
  private static DataBaseManager instance;

  private DataBaseManager(String propertiesPath) {
    init(propertiesPath);
  }

  /**
   * Gets instance.
   *
   * @param propertiesPath the properties path
   * @return the this object
   */
  public static DataBaseManager getInstance(String propertiesPath) {
    if (instance == null) {
      instance = new DataBaseManager(propertiesPath);
    }
    return instance;
  }


  private void init(String propertiesPath) {
    properties = new Properties();
    try {
      properties.load(DataBaseManager.class.getClassLoader()
              .getResourceAsStream(propertiesPath));
    } catch (IOException e) {
      logger.fatal(e);
      throw new RuntimeException(e);
    }
    drivers = properties.getProperty(ConnectName.DRIVER);
    connectionURL = properties.getProperty(ConnectName.URL);
    username = properties.getProperty(ConnectName.NAME);
    password = properties.getProperty(ConnectName.PASSWORD);
    poolSize = Integer.parseInt(properties.getProperty(ConnectName.SIZE));
  }

  /**
   * Gets properties.
   *
   * @return the properties
   */
  public Properties getProperties() {
    return properties;
  }

  /**
   * Sets properties.
   *
   * @param properties the properties
   */
  public void setProperties(Properties properties) {
    DataBaseManager.properties = properties;
  }

  /**
   * Gets drivers.
   *
   * @return the drivers
   */
  public String getDrivers() {
    return drivers;
  }

  /**
   * Gets connection url.
   *
   * @return the connection url
   */
  public String getConnectionURL() {
    return connectionURL;
  }

  /**
   * Gets username.
   *
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * Gets password.
   *
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets password.
   *
   * @param password the password
   */
  public void setPassword(String password) {
    DataBaseManager.password = password;
  }

  /**
   * Gets pool size.
   *
   * @return the pool size
   */
  public int getPoolSize() {
    return poolSize;
  }
}

