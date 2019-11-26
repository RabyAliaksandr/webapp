package com.epam.tc.connectionpool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DatabaseMetaData;
import java.sql.SQLWarning;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.util.Map;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.Blob;
import java.sql.Savepoint;
import java.sql.Clob;
import java.sql.NClob;
import java.sql.SQLXML;
import java.sql.SQLClientInfoException;
import java.sql.SQLDataException;
import java.util.Properties;
import java.sql.Struct;
import java.sql.Array;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;


/**
 * The type Connection pool.
 */
public class ConnectionPool {

  private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
  private static BlockingQueue<Connection> connectionQueue;
  private static BlockingQueue<Connection> givenAwayConQueue;
  private static String driverName;
  private static String url;
  private static String user;
  private static String password;
  private static int sizePool;
  private static ConnectionPool instance;
  private static DataBaseManager dataBaseManager;

  static {
    instance = new ConnectionPool();
    try {
      initPool();
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new RuntimeException(e);
    }
  }

  /**
   * Gets instance.
   *
   * @return the instance
   */
  public static ConnectionPool getInstance() {
    return instance;
  }

  /** Do not instantiate ConnectionPool. */
  private ConnectionPool()  {
    this.dataBaseManager = new DataBaseManager();
    this.driverName = dataBaseManager.getDrivers();
    this.url = dataBaseManager.getConnectionURL();
    this.user = dataBaseManager.getUsername();
    this.password = dataBaseManager.getPassword();
    this.sizePool = dataBaseManager.getPoolSize();
  }

  /**
   * initialization connecting
   */
  private static void initPool() throws ConnectionPoolException {
    try {
      Class.forName(driverName);
      givenAwayConQueue = new ArrayBlockingQueue<>(sizePool);
      connectionQueue = new ArrayBlockingQueue<>(sizePool);
      for (int i = 0; i < sizePool; i++) {
        Connection connection = DriverManager.getConnection(url, user, password);
        PooledConnection pooledConnection = new PooledConnection(connection);
        connectionQueue.add(pooledConnection);
      }
    } catch (ClassNotFoundException e) {
      logger.error(e);
      throw new ConnectionPoolException(e);
    } catch (SQLException e) {
      logger.error(e);
      throw new ConnectionPoolException(e);
    }
  }

  /**
   * Take connection connection.
   *
   * @return the connection
   * @throws ConnectionPoolException the connection pool exception
   */
  public Connection takeConnection() throws ConnectionPoolException {
    Connection connection;
    try {
      connection = connectionQueue.take();
      givenAwayConQueue.add(connection);
    } catch (InterruptedException e) {
      logger.error(e);
      throw new ConnectionPoolException(e);
    }
    return connection;
  }

  /**
   * Dispose.
   *
   * @throws ConnectionPoolException the connection pool exception
   */
  public void dispose() throws ConnectionPoolException {
    clearConnectionQueue();
  }


  /**
   * Close connection.
   *
   * @param rs the rs
   * @throws ConnectionPoolException the connection pool exception
   */
  public void closeConnection(ResultSet rs) throws ConnectionPoolException {
    try {
      rs.close();
    } catch (SQLException e) {
      logger.error("Error closing connection.", e);
      throw new ConnectionPoolException(e);
    }
  }


  /**
   * closing connecting
   *
   * @param con the con
   * @param st  the st
   * @param rs  the rs
   * @throws ConnectionPoolException the connection pool exception
   */
  public void closeConnection(Connection con, Statement st, ResultSet rs) throws ConnectionPoolException {
    try {
      con.close();
    } catch (SQLException e) {
      logger.error("Error closing connection.", e);
      throw new ConnectionPoolException(e);
    }
    try {
      rs.close();
    } catch (SQLException e) {
      logger.error("Error closing connection.", e);
      throw new ConnectionPoolException(e);
    }

    try {
      st.close();
    } catch (SQLException e) {
      logger.error("Error closing connection.", e);
      throw new ConnectionPoolException(e);
    }
  }

  /**
   * closing connecting
   *
   * @param con the con
   * @param st  the st
   * @throws ConnectionPoolException the connection pool exception
   */
  public void closeConnection(Connection con, Statement st) throws ConnectionPoolException {
    try {
      st.close();
    } catch (SQLException e) {
      logger.error("Error clearing connection queue.", e);
      throw new ConnectionPoolException(e);
    }
    try {
      con.close();
    } catch (SQLException e) {
      logger.error("Error closing connection.", e);
      throw new ConnectionPoolException(e);
    }
  }

  /**
   * clear connection queue
   */
  private void clearConnectionQueue() throws ConnectionPoolException {
    closeConnectionsQueue(givenAwayConQueue);
    closeConnectionsQueue(connectionQueue);
  }

  /**
   * close connections queue
   *
   * @param queue
   * @throws SQLException
   */
  private void closeConnectionsQueue(BlockingQueue<Connection> queue) throws ConnectionPoolException {
    Connection connection;
    while ((connection = queue.poll()) != null) {
      try {
        if (!connection.getAutoCommit()) {
          connection.commit();
        }
      } catch (SQLException e) {
        logger.error("Error clearing connection queue.", e);
        throw new ConnectionPoolException(e);
      }
      try {
        ((PooledConnection) connection).reallyClose();
      } catch (SQLException e) {
        logger.error("Error clearing connection queue.", e);
        throw new ConnectionPoolException(e);
      }
    }
  }

  /**
   * private inner class for creating connection
   */
  private static class PooledConnection implements Connection {

    private Connection connection;

    /**
     * Instantiates a new Pooled connection.
     *
     * @param connection the connection
     * @throws SQLException the sql exception
     */
    PooledConnection(Connection connection) throws SQLException {
      this.connection = connection;
      this.connection.setAutoCommit(true);

    }


    /**
     * Really close.
     *
     * @throws SQLException the sql exception
     */
    void reallyClose() throws SQLException {
      connection.close();
    }

    /** {@inheritDoc} */
    @Override
    public void close() throws SQLException {
      if (connection.isClosed()) {
        throw new SQLDataException("Attempting to close closed connection.");
      }
      if (connection.isReadOnly()) {
        connection.setReadOnly(false);
      }
      if (!givenAwayConQueue.remove(this)) {
        throw new SQLDataException("Error deleting connection from the given away connections pool.");
      }

      if (!connectionQueue.offer(this)) {
        throw new SQLException("Error allocating connection in the pool.");
      }
    }

    /** {@inheritDoc} */
    @Override
    public Statement createStatement() throws SQLException {
      return connection.createStatement();
    }

    /** {@inheritDoc} */
    @Override
    public PreparedStatement prepareStatement(String s) throws SQLException {
      return connection.prepareStatement(s);
    }

    /** {@inheritDoc} */
    @Override
    public CallableStatement prepareCall(String s) throws SQLException {
      return connection.prepareCall(s);
    }

    /** {@inheritDoc} */
    @Override
    public String nativeSQL(String s) throws SQLException {
      return connection.nativeSQL(s);
    }

    /** {@inheritDoc} */
    @Override
    public void setAutoCommit(boolean b) throws SQLException {
      connection.setAutoCommit(b);
    }

    /** {@inheritDoc} */
    @Override
    public boolean getAutoCommit() throws SQLException {
      return connection.getAutoCommit();
    }

    /** {@inheritDoc} */
    @Override
    public void commit() throws SQLException {
      connection.commit();
    }

    /** {@inheritDoc} */
    @Override
    public void rollback() throws SQLException {
      connection.rollback();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isClosed() throws SQLException {
      return connection.isClosed();
    }

    /** {@inheritDoc} */
    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
      return connection.getMetaData();
    }

    /** {@inheritDoc} */
    @Override
    public void setReadOnly(boolean b) throws SQLException {
      connection.setReadOnly(b);
    }

    /** {@inheritDoc} */
    @Override
    public boolean isReadOnly() throws SQLException {
      return connection.isReadOnly();
    }

    /** {@inheritDoc} */
    @Override
    public void setCatalog(String s) throws SQLException {
      connection.setCatalog(s);
    }

    /** {@inheritDoc} */
    @Override
    public String getCatalog() throws SQLException {
      return connection.getCatalog();
    }

    /** {@inheritDoc} */
    @Override
    public void setTransactionIsolation(int i) throws SQLException {
      connection.setTransactionIsolation(i);
    }

    /** {@inheritDoc} */
    @Override
    public int getTransactionIsolation() throws SQLException {
      return connection.getTransactionIsolation();
    }

    /** {@inheritDoc} */
    @Override
    public SQLWarning getWarnings() throws SQLException {
      return connection.getWarnings();
    }

    /** {@inheritDoc} */
    @Override
    public void clearWarnings() throws SQLException {
      connection.clearWarnings();
    }

    /** {@inheritDoc} */
    @Override
    public Statement createStatement(int i, int i1) throws SQLException {
      return connection.createStatement(i, i1);
    }

    /** {@inheritDoc} */
    @Override
    public PreparedStatement prepareStatement(String s, int i, int i1) throws SQLException {
      return connection.prepareStatement(s, i, i1);
    }

    /** {@inheritDoc} */
    @Override
    public CallableStatement prepareCall(String s, int i, int i1) throws SQLException {
      return connection.prepareCall(s, i, i1);
    }

    /** {@inheritDoc} */
    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
      return connection.getTypeMap();
    }

    /** {@inheritDoc} */
    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
      connection.setTypeMap(map);
    }

    /** {@inheritDoc} */
    @Override
    public void setHoldability(int i) throws SQLException {
      connection.setHoldability(i);
    }

    /** {@inheritDoc} */
    @Override
    public int getHoldability() throws SQLException {
      return connection.getHoldability();
    }

    /** {@inheritDoc} */
    @Override
    public Savepoint setSavepoint() throws SQLException {
      return connection.setSavepoint();
    }

    /** {@inheritDoc} */
    @Override
    public Savepoint setSavepoint(String s) throws SQLException {
      return connection.setSavepoint(s);
    }

    /** {@inheritDoc} */
    @Override
    public void rollback(Savepoint savepoint) throws SQLException {
      connection.rollback();
    }

    /** {@inheritDoc} */
    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
      connection.releaseSavepoint(savepoint);
    }

    /** {@inheritDoc} */
    @Override
    public Statement createStatement(int i, int i1, int i2) throws SQLException {
      return connection.createStatement(i, i1, i2);
    }

    /** {@inheritDoc} */
    @Override
    public PreparedStatement prepareStatement(String s, int i, int i1, int i2) throws SQLException {
      return connection.prepareStatement(s, i, i1, i2);
    }

    /** {@inheritDoc} */
    @Override
    public CallableStatement prepareCall(String s, int i, int i1, int i2) throws SQLException {
      return connection.prepareCall(s, i, i1, i2);
    }

    /** {@inheritDoc} */
    @Override
    public PreparedStatement prepareStatement(String s, int i) throws SQLException {
      return connection.prepareStatement(s, i);
    }

    /** {@inheritDoc} */
    @Override
    public PreparedStatement prepareStatement(String s, int[] ints) throws SQLException {
      return connection.prepareStatement(s, ints);
    }

    /** {@inheritDoc} */
    @Override
    public PreparedStatement prepareStatement(String s, String[] strings) throws SQLException {
      return connection.prepareStatement(s, strings);
    }

    /** {@inheritDoc} */
    @Override
    public Clob createClob() throws SQLException {
      return connection.createClob();
    }

    /** {@inheritDoc} */
    @Override
    public Blob createBlob() throws SQLException {
      return connection.createBlob();
    }

    /** {@inheritDoc} */
    @Override
    public NClob createNClob() throws SQLException {
      return connection.createNClob();
    }

    /** {@inheritDoc} */
    @Override
    public SQLXML createSQLXML() throws SQLException {
      return createSQLXML();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isValid(int i) throws SQLException {
      return connection.isValid(i);
    }

    /** {@inheritDoc} */
    @Override
    public void setClientInfo(String s, String s1) throws SQLClientInfoException {
      connection.setClientInfo(s, s1);
    }

    /** {@inheritDoc} */
    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
      connection.setClientInfo(properties);
    }

    /** {@inheritDoc} */
    @Override
    public String getClientInfo(String s) throws SQLException {
      return connection.getClientInfo(s);
    }

    /** {@inheritDoc} */
    @Override
    public Properties getClientInfo() throws SQLException {
      return connection.getClientInfo();
    }

    /** {@inheritDoc} */
    @Override
    public Array createArrayOf(String s, Object[] objects) throws SQLException {
      return connection.createArrayOf(s, objects);
    }

    /** {@inheritDoc} */
    @Override
    public Struct createStruct(String s, Object[] objects) throws SQLException {
      return connection.createStruct(s, objects);
    }

    /** {@inheritDoc} */
    @Override
    public void setSchema(String s) throws SQLException {
      connection.setSchema(s);
    }

    /** {@inheritDoc} */
    @Override
    public String getSchema() throws SQLException {
      return connection.getSchema();
    }

    /** {@inheritDoc} */
    @Override
    public void abort(Executor executor) throws SQLException {
      connection.abort(executor);
    }

    /** {@inheritDoc} */
    @Override
    public void setNetworkTimeout(Executor executor, int i) throws SQLException {
      connection.setNetworkTimeout(executor, i);
    }

    /** {@inheritDoc} */
    @Override
    public int getNetworkTimeout() throws SQLException {
      return connection.getNetworkTimeout();
    }

    /** {@inheritDoc} */
    @Override
    public <T> T unwrap(Class<T> aClass) throws SQLException {
      return connection.unwrap(aClass);
    }

    /** {@inheritDoc} */
    @Override
    public boolean isWrapperFor(Class<?> aClass) throws SQLException {
      return connection.isWrapperFor(aClass);
    }
  }
}
