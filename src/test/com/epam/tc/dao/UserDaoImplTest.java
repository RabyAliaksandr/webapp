package com.epam.tc.dao;

import com.epam.tc.connectionpool.ConnectionPool;
import com.epam.tc.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.sql.*;

public class UserDaoImplTest {

  static Connection connection = null;
  ResultSet resultSet = null;
  PreparedStatement preparedStatement = null;

  @BeforeClass
  public static void init() throws SQLException {
    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_trainings_center?" +
            "useUnicode=true&useJDBCCompliantTimezoneShift=true&" +
            "useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "gfhjkm");
  }

  @After
  public void close() {
    resultSet = null;
    preparedStatement = null;
  }

  @Test
  public void testRegistration() throws SQLException {
    User user = new User();
    user.setLogin("login");
    user.setName("name");
    user.setEmail("email");
    user.setSurname("surname");
    user.setPassword("password");
    preparedStatement = connection.prepareStatement(SqlQuery.SQL_NEW_USER);
    preparedStatement.executeUpdate();
  }
}
