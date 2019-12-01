package com.epam.tc.dao;

import com.epam.tc.connectionpool.ConnectName;
import com.epam.tc.connectionpool.ConnectionPool;
import com.epam.tc.connectionpool.ConnectionPoolException;
import com.epam.tc.connectionpool.DataBaseManager;
import com.epam.tc.dao.impl.UserDaoImpl;
import com.epam.tc.entity.User;
import com.epam.tc.entity.UserStatus;
import com.epam.tc.entity.UserType;
import org.junit.*;

import java.sql.*;

/**
 * The type User dao impl test.
 */
public class UserDaoImplTest {

  private static ResultSet resultSet = null;
  private static PreparedStatement preparedStatement = null;
  private static ConnectionPool connectionPool = null;
  private static Connection connection = null;

  /**
   * Init.
   *
   * @throws ConnectionPoolException the connection pool exception
   */
  @BeforeClass
  public static void init() throws  ConnectionPoolException {
    DataBaseManager db = DataBaseManager.getInstance(ConnectName.TEST_PROPERTIES_PATH);
    connectionPool = ConnectionPool.getInstance();
    connection = connectionPool.takeConnection();
  }

  /**
   * Close.
   */
  @After
  public void close() {
    resultSet = null;
    preparedStatement = null;
  }

  /**
   * Destroy.
   *
   * @throws ConnectionPoolException the connection pool exception
   * @throws SQLException            the sql exception
   */
  @AfterClass
  public static void destroy() throws ConnectionPoolException, SQLException {
    connection.close();
    connectionPool.dispose();
  }

  /**
   * Test registration.
   *
   * @throws SQLException the sql exception
   * @throws DaoException the dao exception
   */
  @Test
  public void testRegistration() throws SQLException,  DaoException {
    UserDaoImpl userDao = new UserDaoImpl();
    User expected = new User();
    expected.setSurname("surname");
    expected.setName("name");
    expected.setPassword("password");
    expected.setEmail("email");
    expected.setLogin("login");
    expected.setType(UserType.GUEST);
    expected.setStatus(UserStatus.UNBLOCKED);
    userDao.registration(expected);
    User actual = new User();
    preparedStatement = connection.prepareStatement("SELECT *  FROM test_trainings_center.users WHERE user_id = 1");
    resultSet = preparedStatement.executeQuery();
    while (resultSet.next()) {
      actual.setLogin(resultSet.getString("login"));
      actual.setEmail(resultSet.getString("email"));
      actual.setPassword(resultSet.getString("password"));
      actual.setName(resultSet.getString("name"));
      actual.setType(UserType.getUserType(resultSet.getString("type")));
      actual.setStatus(UserStatus.getUserType(resultSet.getString("user_status")));
      actual.setSurname(resultSet.getString("surname"));
    }
    preparedStatement.close();
    preparedStatement = connection.prepareStatement("TRUNCATE TABLE test_trainings_center.users");
    preparedStatement.executeUpdate();
    Assert.assertEquals("users are not equals", expected, actual);
  }

  /**
   * Authorization test.
   *
   * @throws DaoException the dao exception
   * @throws SQLException the sql exception
   */
  @Test
  public void authorizationTest() throws DaoException, SQLException {
    UserDaoImpl userDao = new UserDaoImpl();
    User expected = new User();
    expected.setSurname("surname");
    expected.setName("name");
    expected.setPassword("password");
    expected.setEmail("email");
    expected.setLogin("login");
    expected.setType(UserType.GUEST);
    expected.setStatus(UserStatus.UNBLOCKED);
    userDao.registration(expected);
    preparedStatement = connection.prepareStatement(TestQuery.INSERT_TEST_USER);
    preparedStatement.executeUpdate();
    preparedStatement.close();
    User actual = new User();
    actual = userDao.authorization(expected);
    preparedStatement = connection.prepareStatement("TRUNCATE TABLE test_trainings_center.users");
    preparedStatement.executeUpdate();
    Assert.assertEquals("users are not equals", expected, actual);
  }
}
