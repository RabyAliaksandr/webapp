package com.epam.tc.dao;

import com.epam.tc.connectionpool.ConnectionPool;
import com.epam.tc.connectionpool.ConnectionPoolException;
import com.epam.tc.connectionpool.DataBaseManager;
import com.epam.tc.dao.impl.UserDaoImpl;
import com.epam.tc.entity.User;
import com.epam.tc.entity.UserStatus;
import com.epam.tc.entity.UserType;
import org.junit.*;

import java.sql.*;

public class UserDaoImplTest {

  static ResultSet resultSet = null;
  static PreparedStatement preparedStatement = null;
  static ConnectionPool connectionPool = null;
  static Connection connection = null;

  @BeforeClass
  public static void init() throws SQLException, ConnectionPoolException {
    DataBaseManager db = new DataBaseManager("testDataBaseConnection.properties");
    connectionPool = ConnectionPool.getInstance();
    connection = connectionPool.takeConnection();
  }

  @After
  public void close() throws SQLException, ConnectionPoolException {
//        resultSet = null;
//    preparedStatement = null; // TODO Check at null in ConPool
  }

  @AfterClass
  public static void destroy() throws ConnectionPoolException {
    connectionPool.closeConnection(connection, preparedStatement, resultSet);
    connectionPool.dispose();
  }

  @Test
  public void testRegistration() throws SQLException, ConnectionPoolException, DaoException {
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
    preparedStatement = connection.prepareStatement("SELECT *  FROM users WHERE user_id = 1");
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
    preparedStatement = connection.prepareStatement("TRUNCATE TABLE users");
    preparedStatement.executeUpdate();
    Assert.assertEquals("users are not equals", expected, actual);
  }
}
