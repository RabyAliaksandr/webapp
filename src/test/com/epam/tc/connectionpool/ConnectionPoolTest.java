package com.epam.tc.connectionpool;

import org.junit.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Connection pool test.
 */
public class ConnectionPoolTest {

  private static PreparedStatement preparedStatement = null;
  private static ConnectionPool connectionPool = null;
  private static Connection connection = null;


  /**
   * Close.
   */
  @After
  public void close() {
    preparedStatement = null;
  }

  /**
   * Destroy.
   *
   * @throws ConnectionPoolException the connection pool exception
   */
  @AfterClass
  public static void destroy() throws ConnectionPoolException {

    connectionPool.dispose();
  }

  /**
   * Check set auto commit test.
   *
   * @throws ConnectionPoolException the connection pool exception
   * @throws SQLException            the sql exception
   */
  @Test
  public void checkSetAutoCommitTest() throws ConnectionPoolException, SQLException {
    DataBaseManager db = DataBaseManager.getInstance(ConnectName.TEST_PROPERTIES_PATH);
    connectionPool = ConnectionPool.getInstance();
    int poolSize = db.getPoolSize();
    List<Connection> connections = new ArrayList<>();
    for (int i = 0; i < poolSize; i++) {
      Connection connection = connectionPool.takeConnection();
      connections.add(connection);
    }
    connections.forEach(e -> {
      try {
        e.setAutoCommit(false);
        e.close();
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
    });
    boolean expected = true;
    Connection checkAutoCommit = connectionPool.takeConnection();
    boolean actual = checkAutoCommit.getAutoCommit();
    checkAutoCommit.close();
    Assert.assertEquals(actual, expected);
  }
}
