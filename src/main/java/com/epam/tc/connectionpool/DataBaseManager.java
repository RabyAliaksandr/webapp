package com.epam.tc.connectionpool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

/**
 * The type Data base manager.
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
   * @return the instance
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
    this.drivers = properties.getProperty(ConnectName.DRIVER);
    this.connectionURL = properties.getProperty(ConnectName.URL);
    this.username = properties.getProperty(ConnectName.NAME);
    this.password = properties.getProperty(ConnectName.PASSWORD);
    this.poolSize = Integer.parseInt(properties.getProperty(ConnectName.SIZE));
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
    this.properties = properties;
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
    this.password = password;
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

