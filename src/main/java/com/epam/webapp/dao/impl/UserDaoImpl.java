package com.epam.webapp.dao.impl;

import com.epam.webapp.dao.UserDao;
import com.epam.webapp.connectionpool.ConnectionPool;
import com.epam.webapp.connectionpool.exception.ConnectionPoolException;
import com.epam.webapp.dao.exception.DaoException;
import com.epam.webapp.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {

  private final static String SQL_NEW_USER = "INSERT INTO users (name, surname, email, login, password, type)" +
          " values (?, ?, ?, ?, ?, ?)";
  private final static String SQL_GET_USER = "SELECT user_id, name, surname, email, user_status, type FROM users WHERE " +
          "login=? AND password=?";
  private final static String SQL_USER_ID = "user_id";
  private final static String SQL_USER_NAME = "name";
  private final static String SQL_USER_SURNAME = "surname";
  private final static String SQL_USER_EMAIL = "email";
  private final static String SQL_USER_TYPE = "type";
  private final static Logger logger = LogManager.getLogger(UserDao.class);
  private static final String SQL_GRADE = "UPDATE trainingbystudents SET grade_for_training = ? WHERE " +
          "(user_id = ? and training_id = ?)";
  private static final String SQL_ADD_TRAINING_TO_STUDENT = "INSERT INTO training_by_students " +
          "(user_id, training_id) VALUES (?, ?)";
  private static final String SQL_CHECK_ENROLLED = "SELECT EXISTS(SELECT trainings_center.training_by_students.user_id FROM" +
          " training_by_students WHERE (user_id = ? and training_id =?))";
  private static final String SQL_ALL_MENTORS = "SELECT user_id, users.name as user_name, surname, t.name, training_id FROM users " +
          "left join trainings t on users.user_id = t.mentor_id WHERE users.type = 'mentor'";
  private static final String SQL_ALL_USERS = "SELECT user_id, name, surname, login, email, user_status, type FROM users";
  private static final String SQL_USER_LOGIN = "login";
  private static final String SQL_UPDATE_USER_TYPE = "UPDATE users SET type = ?, user_status = ? WHERE user_id = ?";
  private static final String SQL_USER_STATUS = "user_status";
  private static final String SQL_STUDENT_FOR_TRAINING = "SELECT * FROM users  JOIN training_by_students " +
          "USING(user_id) WHERE (training_id = ? AND type = 'student')";
  private static final String SQL_ALL_MARKS_FOR_TRAINING = "SELECT task_id, task_name, mark, answer, task FROM " +
          "training_by_students INNER JOIN tasks USING (training_id) LEFT JOIN student_task USING (user_id, task_id) " +
          "WHERE user_id = ? AND training_id = ?";
  private static final String SQL_TASK_ID = "task_id";
  private static final String SQL_TASK_NAME = "task_name";
  private static final String SQL_TASK = "task";
  private static final String SQL_TASK_MARK = "mark";
  private static final String SQL_TASK_ANSWER = "answer";
  private static final String SQL_TRAINING_ID = "training_id";
  private static final String SQL_TRAINING_NAME = "name";
  private static final String SQL_USER_USER_NAME = "user_name";
  private static final String SQL_OFFER_CONSULTATION = "INSERT  INTO  consultations (training_id, date) SELECT * FROM " +
          "(SELECT ?, ?) AS tmp WHERE\n" +
          "NOT EXISTS(SELECT training_id, date FROM consultations WHERE training_id = ? AND date = ?)";
  private static final String SQL_CONSULTATION_OFFER = "SELECT training_id, date, name AS training_name FROM " +
          "consultations JOIN trainings USING (training_id) WHERE mentor_id = ? AND mentor_mark IS NULL";
  private static final String SQL_TRAINING_NAME_AS = "training_name";
  private static final String SQL_DATE = "date";
  private static final String SQL_SEND_AGREEMENT = "UPDATE consultations SET mentor_mark = ? WHERE" +
          " training_id = ? AND date = ?";

  /**
   * check if user is in database - take information about him
   *
   * @param user
   * @return user
   */

  @Override
  public User authorization(User user) throws DaoException {
    ConnectionPool pool = ConnectionPool.Instance();
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
        user.setType(UserType.getUserType(resultSet.getString(SQL_USER_TYPE)));
        return user;
      }
      return new User();
    } catch (SQLException e) {
      throw new DaoException(e);
    } catch (ConnectionPoolException e) {
      throw new DaoException(e);
    } finally {
      try {
        pool.closeConnection(connection, preparedStatement, resultSet);
      } catch (ConnectionPoolException e) {
        throw new DaoException(e);
      }
    }
  }

  /**
   * check is user in database and if no - then insert him in database
   *
   * @param user
   * @return user
   */

  @Override
  public User registration(User user) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.Instance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
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
    } catch (SQLException e) {
      throw new DaoException(e);
    } catch (ConnectionPoolException e) {
      e.printStackTrace();
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement);
      } catch (ConnectionPoolException e) {
        throw new DaoException(e);
      }
    }
    return user;
  }

  @Override
  public void grade(int assessment, int userId, int trainingId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.Instance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_GRADE);
      preparedStatement.setInt(1, assessment);
      preparedStatement.setInt(2, userId);
      preparedStatement.setInt(3, trainingId);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new DaoException(e);
    } catch (ConnectionPoolException e) {
      e.printStackTrace();
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement);
      } catch (ConnectionPoolException e) {
        throw new DaoException(e);
      }
    }
  }

  @Override
  public void addTrainingToStudent(int userId, int trainingId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.Instance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_ADD_TRAINING_TO_STUDENT);
      preparedStatement.setInt(1, userId);
      preparedStatement.setInt(2, trainingId);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new DaoException(e);
    } catch (ConnectionPoolException e) {
      e.printStackTrace();
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement);
      } catch (ConnectionPoolException e) {
        throw new DaoException(e);
      }
    }
  }

  @Override
  public boolean checkEnrolled(int userId, int trainingId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.Instance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet;
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
      throw new DaoException(e);
    } catch (ConnectionPoolException e) {
      e.printStackTrace();
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement);
      } catch (ConnectionPoolException e) {
        throw new DaoException(e);
      }
    }
    return false;
  }

  @Override
  public Map<Task, User> findAllMentors() throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.Instance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Map<Task, User> mentorsTraining = new HashMap<>();

    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_ALL_MENTORS);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        User user = new User();
        Task task = new Task();
        user.setId(resultSet.getInt(SQL_USER_ID));
        user.setName(resultSet.getString(SQL_USER_USER_NAME));
        user.setSurname(resultSet.getString(SQL_USER_SURNAME));
        task.setId(resultSet.getInt(SQL_TRAINING_ID));
        task.setName(resultSet.getString(SQL_TRAINING_NAME));
        mentorsTraining.put(task, user);
      }
      return mentorsTraining;
    } catch (SQLException e) {
      throw new DaoException(e);
    } catch (ConnectionPoolException e) {
      e.printStackTrace();
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement, resultSet);
      } catch (ConnectionPoolException e) {
        throw new DaoException(e);
      }
    }
    return mentorsTraining;
  }

  @Override
  public List<User> findAllUser() throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.Instance();
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
        user.setType(UserType.getUserType(resultSet.getString(SQL_USER_TYPE)));
        user.setId(resultSet.getInt(SQL_USER_ID));
        user.setStatus(UserStatus.getUserType(resultSet.getString(SQL_USER_STATUS)));
        users.add(user);
      }
      return users;
    } catch (SQLException e) {
      throw new DaoException(e);
    } catch (ConnectionPoolException e) {
      e.printStackTrace();
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement, resultSet);
      } catch (ConnectionPoolException e) {
        throw new DaoException(e);
      }
    }
    return users;
  }

  @Override
  public boolean updateUserType(int userId, UserType type, UserStatus status) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.Instance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_UPDATE_USER_TYPE);
      preparedStatement.setString(1, type.name());
      preparedStatement.setString(2, status.name());
      preparedStatement.setInt(3, userId);
      preparedStatement.executeUpdate();
      return true;
    } catch (SQLException e) {
      throw new DaoException(e);
    } catch (ConnectionPoolException e) {
      throw new DaoException(e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement);
      } catch (ConnectionPoolException e) {
        throw new DaoException(e);
      }
    }
  }

  @Override
  public List<Student> findStudentsForTraining(int trainingId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.Instance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    List<Student> students = new ArrayList<>();
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_STUDENT_FOR_TRAINING);
      preparedStatement.setInt(1, trainingId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Student student = new Student();
        student.setId(resultSet.getInt(SQL_USER_ID));
        student.setName(resultSet.getString(SQL_USER_NAME));
        student.setSurname(resultSet.getString(SQL_USER_SURNAME));
        student.setEmail(resultSet.getString(SQL_USER_EMAIL));
        student.setLogin(resultSet.getString(SQL_USER_LOGIN));
        students.add(student);
      }
      return students;
    } catch (SQLException e) {
      throw new DaoException(e);
    } catch (ConnectionPoolException e) {
      e.printStackTrace();
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement);
      } catch (ConnectionPoolException e) {
        throw new DaoException(e);
      }
    }
    return students;
  }

  @Override
  public List<Task> findStudentsMarkForTrainingsTask(int studentId, int trainingId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.Instance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    List<Task> tasks = new ArrayList<>();
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_ALL_MARKS_FOR_TRAINING);
      preparedStatement.setInt(1, studentId);
      preparedStatement.setInt(2, trainingId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Task task = new Task();
        task.setId(resultSet.getInt(SQL_TASK_ID));
        task.setName(resultSet.getString(SQL_TASK_NAME));
        task.setTask(resultSet.getString(SQL_TASK));
        task.setMark(resultSet.getInt(SQL_TASK_MARK));
        task.setAnswer(resultSet.getString(SQL_TASK_ANSWER));
        tasks.add(task);
      }
      return tasks;
    } catch (SQLException e) {
      throw new DaoException(e);
    } catch (ConnectionPoolException e) {
      throw new DaoException(e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement, resultSet);
      } catch (ConnectionPoolException e) {
        throw new DaoException(e);
      }
    }
  }

  @Override
  public boolean sendOfferConsultations(int trainingId, Date date) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.Instance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_OFFER_CONSULTATION);
      preparedStatement.setInt(1, trainingId);
      preparedStatement.setString(2, date.toString());
      preparedStatement.setInt(3, trainingId);
      preparedStatement.setString(4, date.toString());
      preparedStatement.executeUpdate();
      return true;
    } catch (SQLException e) {
      throw new DaoException(e);
    } catch (ConnectionPoolException e) {
      throw new DaoException(e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement);
      } catch (ConnectionPoolException e) {
        throw new DaoException(e);
      }
    }
  }

  @Override
  public Map<Training, Date> findConsultationsOffer(int mentorId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.Instance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Map<Training, Date> consultations = new HashMap<>();
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_CONSULTATION_OFFER);
      preparedStatement.setInt(1, mentorId);
      resultSet = preparedStatement.executeQuery();
     while (resultSet.next()) {
       Training training = new Training();
       training.setId(resultSet.getInt(SQL_TRAINING_ID));
       training.setName(resultSet.getString(SQL_TRAINING_NAME_AS));
       Date date = resultSet.getDate(SQL_DATE);
       consultations.put(training, date);
     }
     return consultations;
    } catch (SQLException e) {
      throw new DaoException(e);
    } catch (ConnectionPoolException e) {
      e.printStackTrace();
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement, resultSet);
      } catch (ConnectionPoolException e) {
        throw new DaoException(e);
      }
    }
    return consultations;
  }

  @Override
  public boolean sendAgreement(int trainingId, Date date, boolean mark) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.Instance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_SEND_AGREEMENT);
      preparedStatement.setBoolean(1, mark);
      preparedStatement.setInt(2, trainingId);
      preparedStatement.setString(3, date.toString());
      preparedStatement.executeUpdate();
      return true;
    } catch (SQLException e) {
      throw new DaoException(e);
    } catch (ConnectionPoolException e) {
      e.printStackTrace();
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement);
      } catch (ConnectionPoolException e) {
        throw new DaoException(e);
      }
    }
    return false;
  }
}