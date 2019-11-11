package com.epam.webapp.dao.impl;

import com.epam.webapp.dao.UserDAO;
import com.epam.webapp.connectionpool.ConnectionPool;
import com.epam.webapp.connectionpool.exception.ConnectionPoolException;
import com.epam.webapp.dao.exception.DAOException;
import com.epam.webapp.entity.User;
import com.epam.webapp.entity.UserStatus;
import com.epam.webapp.entity.UserTypes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

  private final static String SQL_NEW_USER = "INSERT INTO users (name, surname, email, login, password, type) values (?, ?, ?, ?, ?, ?)";
  private final static String SQL_GET_USER = "SELECT userid, name, surname, email, user_status, type FROM users WHERE login=? AND password=?";
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
  private static final String SQL_ALL_MENTORS = "SELECT userid, name, surname FROM users WHERE type = 'mentor'";
  private static final String SQL_ALL_USERS = "SELECT userid, name, surname, login, email, user_status, type FROM users";
  private static final String SQL_USER_LOGIN = "login";
  private static final String SQL_UPDATE_USER_TYPE = "UPDATE users SET type = ?, user_status = ? WHERE userid = ?";
  private static final String SQL_USER_STATUS = "user_status";

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
        user.setStatus(UserStatus.getUserType(resultSet.getString(SQL_USER_STATUS)));
        user.setType(UserTypes.getUserType(resultSet.getString(SQL_USER_TYPE)));
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
      preparedStatement = connection.prepareStatement(SQL_NEW_USER);
      preparedStatement.setString(1, user.getLogin());
      preparedStatement.setString(2, user.getPassword());
      preparedStatement.setString(3, user.getType().toString());
      preparedStatement.setString(4, user.getName());
      preparedStatement.setString(5, user.getSurname());
      preparedStatement.setString(6, user.getEmail());
     preparedStatement.executeUpdate();
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

  @Override
  public List<User> getAllMentors() throws ConnectionPoolException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    connectionPool.initPool();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    List<User> users = new ArrayList<>();

    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_ALL_MENTORS);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        User user = new User();
        user.setId(resultSet.getInt(SQL_USER_ID));
        user.setName(resultSet.getString(SQL_USER_NAME));
        user.setSurname(resultSet.getString(SQL_USER_SURNAME));
        users.add(user);
      }
      return users;
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      connectionPool.closeConnection(connection, preparedStatement, resultSet);
    }
    return users;
  }

  @Override
  public List<User> getAllUser() throws ConnectionPoolException{
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    connectionPool.initPool();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    List<User> users = new ArrayList<>();
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_ALL_USERS);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        User user = new User();
        user.setName(resultSet.getString(SQL_USER_NAME));
        user.setSurname(resultSet.getString(SQL_USER_SURNAME));
        user.setLogin(resultSet.getString(SQL_USER_LOGIN));
        user.setEmail(resultSet.getString(SQL_USER_EMAIL));
        user.setType(UserTypes.getUserType(resultSet.getString(SQL_USER_TYPE)));
        user.setId(resultSet.getInt(SQL_USER_ID));
        user.setStatus(UserStatus.getUserType(resultSet.getString(SQL_USER_STATUS)));
        users.add(user);
      }
      return users;
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      connectionPool.closeConnection(connection, preparedStatement, resultSet);
    }
    return users;
  }

  @Override
  public boolean updateUserType(int userId, UserTypes type, UserStatus status) throws ConnectionPoolException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    connectionPool.initPool();
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_UPDATE_USER_TYPE);
      preparedStatement.setString(1, type.name());
      preparedStatement.setString(2, status.name());
      preparedStatement.setInt(3, userId);
      preparedStatement.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      connectionPool.closeConnection(connection, preparedStatement);
    }
    return false;
  }
}