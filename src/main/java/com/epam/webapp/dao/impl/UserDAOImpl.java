package com.epam.webapp.dao.impl;

import com.epam.webapp.dao.UserDAO;
import com.epam.webapp.connectionpool.ConnectionPool;
import com.epam.webapp.connectionpool.exception.ConnectionPoolException;
import com.epam.webapp.dao.exception.DAOException;
import com.epam.webapp.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class UserDAOImpl implements UserDAO {

  private final static String SQL_NEW_USER = "INSERT INTO users (name, surname, email, login, password, type) values (?, ?, ?, ?, ?, ?)";
  private final static String SQL_GET_USER = "SELECT userid, name, surname, email, type FROM users WHERE login=? AND password=?";
  private final static String SQL_USER_ID = "userid";
  private final static String SQL_USER_NAME = "name";
  private final static String SQL_USER_SURNAME = "surname";
  private final static String SQL_USER_EMAIL = "email";
  private final static String SQL_USER_TYPE = "type";
  private final static Logger logger = LogManager.getLogger(UserDAO.class);
  private static final String SQL_GRADE = "UPDATE trainingbystudents SET grade_for_training = ? WHERE (userid = ? and trainingid = ?)";
  private static final String SQL_ADD_TRAINING_TO_STUDENT = "INSERT INTO trainingbystudents (userid, trainingid) VALUES (?, ?)";
  private static final String SQL_CHECK_ENROLLED = "SELECT EXISTS(SELECT training.trainingbystudents.userid FROM trainingbystudents WHERE (userid = ? and\n" +
          "                                                                                       trainingid =?))";

  /**
   * check if user is in database - take information about him
   *
   * @param user
   * @return user
   */

  @Override
  public User authorization(User user) throws ConnectionPoolException, DAOException {
    ConnectionPool pool = ConnectionPool.getInstance();
    pool.initPool();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
      connection = pool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_GET_USER);
      preparedStatement.setString(1, user.getLogin());
      preparedStatement.setString(2, user.getPassword());
      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        user.setId(resultSet.getInt(SQL_USER_ID));
        user.setName(resultSet.getString(SQL_USER_NAME));
        user.setSurname(resultSet.getString(SQL_USER_SURNAME));
        user.setEmail(resultSet.getString(SQL_USER_EMAIL));
        user.setType(resultSet.getString(SQL_USER_TYPE));
        return user;
      }
      return null; // TODO return ?????????????????????????????

    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new ConnectionPoolException(e);
    } catch (SQLException e) {
      logger.error(e);
      throw new DAOException(e);
    } finally {
      pool.closeConnection(connection, preparedStatement, resultSet);
    }
  }

  /**
   * check is user in database and if no - then insert him in database
   *
   * @param user
   * @return user
   */

  @Override
  public User registration(User user) throws DAOException, ConnectionPoolException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    connectionPool.initPool();
    try {
      connection = connectionPool.takeConnection();
      System.out.println("готовим запрос");
      preparedStatement = connection.prepareStatement(SQL_NEW_USER);
      preparedStatement.setString(1, user.getLogin());
      preparedStatement.setString(2, user.getPassword());
      preparedStatement.setString(3, user.getType());
      preparedStatement.setString(4, user.getName());
      preparedStatement.setString(5, user.getSurname());
      preparedStatement.setString(6, user.getEmail());
      System.out.println("будем делать запрос");
     preparedStatement.executeUpdate();
      System.out.println("добавили данные");

      return user;


    } catch (ConnectionPoolException | SQLException e) {

      throw new DAOException(e);

    } finally {
      connectionPool.closeConnection(connection, preparedStatement);
    }
  }
  /**
   * update users information in database
   *
   * @param user
   */

  @Override
  public void updateUser(User user) throws DAOException, ConnectionPoolException {

  }

  @Override
    public void grade(int assessment, int userId, int trainingId) throws ConnectionPoolException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    connectionPool.initPool();
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_GRADE);
      preparedStatement.setInt(1, assessment);
      preparedStatement.setInt(2, userId);
      preparedStatement.setInt(3, trainingId);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      connectionPool.closeConnection(connection, preparedStatement);
    }
  }

  @Override
  public void addTrainingToStudent(int userId, int trainingId) throws ConnectionPoolException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    connectionPool.initPool();
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_ADD_TRAINING_TO_STUDENT);
      preparedStatement.setInt(1, userId);
      preparedStatement.setInt(2, trainingId);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      connectionPool.closeConnection(connection, preparedStatement);
    }
  }

  @Override
  public boolean checkEnrolled(int userId, int trainingId) throws ConnectionPoolException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    connectionPool.initPool();
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_CHECK_ENROLLED);
      System.out.println(userId + "userId in DAO");
      System.out.println(trainingId + "trainingId in DAO");
      preparedStatement.setInt(1, userId);
      preparedStatement.setInt(2, trainingId);
      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        return resultSet.getInt(1) > 0;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      connectionPool.closeConnection(connection, preparedStatement);
    }
    return false;
  }
}