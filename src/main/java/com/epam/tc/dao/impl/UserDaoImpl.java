package com.epam.tc.dao.impl;

import com.epam.tc.dao.SqlColumn;
import com.epam.tc.entity.*;
import com.epam.tc.dao.UserDao;
import com.epam.tc.connectionpool.ConnectionPool;
import com.epam.tc.connectionpool.ConnectionPoolException;
import com.epam.tc.dao.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.epam.tc.dao.SqlQuery.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type User dao.
 */
public class UserDaoImpl implements UserDao {

  private static Logger logger = LogManager.getLogger(UserDaoImpl.class);

  /**
   * {@inheritDoc}
   */
  @Override
  public User authorization(User user) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    ResultSet resultSet = null;
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_USER);) {
      preparedStatement.setString(1, user.getLogin());
      preparedStatement.setString(2, user.getPassword());
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        user.setId(resultSet.getInt(SqlColumn.SQL_USER_ID));
        user.setName(resultSet.getString(SqlColumn.SQL_USER_NAME));
        user.setSurname(resultSet.getString(SqlColumn.SQL_USER_SURNAME));
        user.setEmail(resultSet.getString(SqlColumn.SQL_USER_EMAIL));
        user.setStatus(UserStatus.getUserType(resultSet.getString(SQL_USER_STATUS)));
        user.setType(UserType.getUserType(resultSet.getString(SqlColumn.SQL_USER_TYPE)));
        return user;
      }
      return null;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException(e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException(e);
    } finally {
      try {
        connectionPool.closeConnection(resultSet);
      } catch (ConnectionPoolException e) {
        logger.error(e);
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean checkLogin(String login) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    ResultSet resultSet = null;
    String checkIsExist = null;
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_CHECK_LOGIN);) {
      preparedStatement.setString(1, login);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        checkIsExist = resultSet.getString(SqlColumn.SQL_USER_LOGIN);
      }
      return checkIsExist != null;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException(e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException(e);
    } finally {
      try {
        connectionPool.closeConnection(resultSet);
      } catch (ConnectionPoolException e) {
        logger.error(e);
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean checkEmail(String email) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    ResultSet resultSet = null;
    String checkIsExistEmail = null;
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_CHECK_EMAIL);) {
      preparedStatement.setString(1, email);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        checkIsExistEmail = resultSet.getString(SqlColumn.SQL_USER_EMAIL);
      }
      return checkIsExistEmail != null;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException(e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException(e);
    } finally {
      try {
        connectionPool.closeConnection(resultSet);
      } catch (ConnectionPoolException e) {
        logger.error(e);
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteUser(int userId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER);) {
      preparedStatement.setInt(1, userId);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException(e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException(e);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<User> findStudentsByIdTraining(int trainingId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    ResultSet resultSet = null;
    List<User> students = new ArrayList<>();
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_STUDENTS_BY_ID_TRAINING)) {
      preparedStatement.setInt(1, trainingId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        User student = new User();
        student.setId(resultSet.getInt(SqlColumn.STUDENT_ID));
        student.setName(resultSet.getString(SqlColumn.SQL_NAME));
        student.setSurname(resultSet.getString(SqlColumn.SQL_SURNAME));
//        student.setGrade(resultSet.getInt(SQL_STUDENT_GRADE));
        students.add(student);
      }
      return students;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } finally {
      try {
        connectionPool.closeConnection(resultSet);
      } catch (ConnectionPoolException e) {
        logger.error(e);
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public User registration(User user) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_NEW_USER)) {
      preparedStatement.setString(1, user.getName());
      preparedStatement.setString(2, user.getSurname());
      preparedStatement.setString(3, user.getEmail());
      preparedStatement.setString(4, user.getLogin());
      preparedStatement.setString(5, user.getPassword());
      preparedStatement.executeUpdate();
      logger.debug("was created new user");
      return user;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException(e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException(e);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void grade(int assessment, int userId, int trainingId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_GRADE)) {
      preparedStatement.setInt(1, assessment);
      preparedStatement.setInt(2, userId);
      preparedStatement.setInt(3, trainingId);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException(e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException(e);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean checkEnrolled(int userId, int trainingId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    ResultSet resultSet;
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_CHECK_ENROLLED)) {
      preparedStatement.setInt(1, userId);
      preparedStatement.setInt(2, trainingId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        return resultSet.getInt(1) > 0;
      }
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException(e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException(e);
    }
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Map<Training, User> findAllMentors() throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    ResultSet resultSet = null;
    Map<Training, User> mentorsTraining = new HashMap<>();
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_ALL_MENTORS)) {
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        User user = new User();
        Training training = new Training();
        user.setId(resultSet.getInt(SqlColumn.SQL_USER_ID));
        user.setName(resultSet.getString(SqlColumn.SQL_USER_USER_NAME));
        user.setSurname(resultSet.getString(SqlColumn.SQL_USER_SURNAME));
        training.setId(resultSet.getInt(SqlColumn.SQL_TRAINING_ID));
        training.setName(resultSet.getString(SqlColumn.SQL_TRAINING_NAME));
        mentorsTraining.put(training, user);
      }
      return mentorsTraining;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException(e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException(e);
    } finally {
      try {
        connectionPool.closeConnection(resultSet);
      } catch (ConnectionPoolException e) {
        logger.error(e);
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<User> findAllUser() throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    ResultSet resultSet = null;
    List<User> users = new ArrayList<>();
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_ALL_USERS)) {
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        User user = new User();
        user.setName(resultSet.getString(SqlColumn.SQL_USER_NAME));
        user.setSurname(resultSet.getString(SqlColumn.SQL_USER_SURNAME));
        user.setLogin(resultSet.getString(SqlColumn.SQL_USER_LOGIN));
        user.setEmail(resultSet.getString(SqlColumn.SQL_USER_EMAIL));
        user.setType(UserType.getUserType(resultSet.getString(SqlColumn.SQL_USER_TYPE)));
        user.setId(resultSet.getInt(SqlColumn.SQL_USER_ID));
        user.setStatus(UserStatus.getUserType(resultSet.getString(SQL_USER_STATUS)));
        users.add(user);
      }
      return users;
    } catch (SQLException e) {
      throw new DaoException(e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException(e);
    } finally {
      try {
        connectionPool.closeConnection(resultSet);
      } catch (ConnectionPoolException e) {
        logger.error(e);
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updateUserType(int userId, UserType type, UserStatus status) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER_TYPE)) {
      preparedStatement.setString(1, type.name());
      preparedStatement.setString(2, status.name());
      preparedStatement.setInt(3, userId);
      preparedStatement.executeUpdate();
      logger.info("was changed user type");
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException(e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException(e);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<User> findStudentsForTraining(int trainingId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    ResultSet resultSet = null;
    List<User> students = new ArrayList<>();
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_STUDENT_FOR_TRAINING)) {
      preparedStatement.setInt(1, trainingId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        User student = new User();
        student.setId(resultSet.getInt(SqlColumn.SQL_USER_ID));
        student.setName(resultSet.getString(SqlColumn.SQL_USER_NAME));
        student.setSurname(resultSet.getString(SqlColumn.SQL_USER_SURNAME));
        student.setEmail(resultSet.getString(SqlColumn.SQL_USER_EMAIL));
        student.setLogin(resultSet.getString(SqlColumn.SQL_USER_LOGIN));
//        student.setGrade(resultSet.getInt(SQL_GRADE_FOR_TRAINING));
        students.add(student);
      }
      return students;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException(e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException(e);
    } finally {
      try {
        connectionPool.closeConnection(resultSet);
      } catch (ConnectionPoolException e) {
        logger.error(e);
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Task> findStudentsMarkForTrainingsTask(int studentId, int trainingId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    ResultSet resultSet = null;
    List<Task> tasks = new ArrayList<>();
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_ALL_MARKS_FOR_TRAINING)) {
      preparedStatement.setInt(1, studentId);
      preparedStatement.setInt(2, trainingId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Task task = new Task();
        task.setId(resultSet.getInt(SqlColumn.SQL_TASK_ID));
        task.setName(resultSet.getString(SqlColumn.SQL_TASK_NAME));
        task.setTask(resultSet.getString(SqlColumn.SQL_TASK));
        task.setMark(resultSet.getInt(SqlColumn.SQL_TASK_MARK));
        task.setAnswer(resultSet.getString(SqlColumn.SQL_TASK_ANSWER));
        tasks.add(task);
      }
      return tasks;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException(e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException(e);
    } finally {
      try {
        connectionPool.closeConnection(resultSet);
      } catch (ConnectionPoolException e) {
        logger.error(e);
      }
    }
  }
}