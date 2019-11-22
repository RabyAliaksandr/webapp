package com.epam.tc.connectionpool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;
 class DataBaseManager {

  private static Logger logger = LogManager.getLogger(DataBaseManager.class);
  private Properties properties = null;
  private String drivers = null;
  private String connectionURL = null;
  private String username = null;
  private String password = null;
  private int poolSize = 0;

   DataBaseManager() {
    properties = new Properties();
    try {
      properties.load(DataBaseManager.class.getClassLoader()
              .getResourceAsStream(ConnectName.PROPERTIES_FILE));
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

  public Properties getProperties() {
    return properties;
  }

  public void setProperties(Properties properties) {
    this.properties = properties;
  }

  public String getDrivers() {
    return drivers;
  }

  public String getConnectionURL() {
    return connectionURL;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getPoolSize() {
    return poolSize;
  }
}

