package com.epam.webapp.dao.impl;

import com.epam.webapp.connectionpool.ConnectionPool;
import com.epam.webapp.connectionpool.ConnectionPoolException;
import com.epam.webapp.dao.DaoException;
import com.epam.webapp.dao.DataListsDao;
import com.epam.webapp.dao.UserDao;
import com.epam.webapp.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.epam.webapp.constant.ConstSqlRequest.*;
import static com.epam.webapp.constant.ConstSqlName.*;

public class DataListDaoImpl implements DataListsDao {

  private final static Logger logger = LogManager.getLogger(UserDao.class);

  @Override
  public List<Training> findTrainingsForStudent(int id) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;
    List<Training> trainings = new ArrayList<>();
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_TRAININGS_BY_STUDENT_ID);
      preparedStatement.setInt(1, id);
      rs = preparedStatement.executeQuery();
      while (rs.next()) {
        Training training = new Training();
        training.setName(rs.getString(SQL_NAME));
        trainings.add(training);
      }
      return trainings;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement, rs);
      } catch (ConnectionPoolException e) {
        logger.error(e);
        throw new DaoException("Error access database", e);
      }
    }
  }

  @Override
  public List<Student> findStudentsByIdTraining(int trainingId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;
    List<Student> students = new ArrayList<>();
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_STUDENTS_BY_ID_TRAINING);
      preparedStatement.setInt(1, trainingId);
      rs = preparedStatement.executeQuery();
      while (rs.next()) {
        Student student = new Student();
        student.setId(rs.getInt(STUDENT_ID));
        student.setName(rs.getString(SQL_NAME));
        student.setSurname(rs.getString(SQL_SURNAME));
        student.setGrade(rs.getInt(SQL_STUDENT_GRADE));
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
        connectionPool.closeConnection(connection, preparedStatement, rs);
      } catch (ConnectionPoolException e) {
        logger.error(e);
        throw new DaoException("Error access database", e);
      }
    }
  }

  @Override
  public List<Training> findCompletedTrainingForStudent(int studentId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;
    List<Training> trainings = new ArrayList<>();
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_TRAININGS_BY_STUDENT_ID);
      preparedStatement.setInt(1, studentId);
      rs = preparedStatement.executeQuery();
      while (rs.next()) {
        Training training = new Training();
        User mentor = new User();
        mentor.setId(rs.getInt(SQL_MENTOR_ID));
        training.setName(rs.getString(SQL_NAME));
        training.setId(rs.getInt(SQL_TRAINING_ID));
        training.setMentor(mentor);
        training.setGrade(rs.getInt(SQL_GRADE_FOR_TRAINING));
        trainings.add(training);
      }
      return trainings;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement, rs);
      } catch (ConnectionPoolException e) {
        logger.error(e);
        throw new DaoException("Error access database", e);
      }
    }
  }

  @Override
  public List<Training> findTrainingForMentor(int mentorId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;
    List<Training> trainings = new ArrayList<>();
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_COMPLETED_TRAININGS_FOR_MENTOR);
      preparedStatement.setInt(1, mentorId);
      rs = preparedStatement.executeQuery();
      while (rs.next()) {
        Training training = new Training();
        training.setName(rs.getString(SQL_NAME));
        training.setId(rs.getInt(SQL_TRAINING_ID));
        trainings.add(training);
      }
      return trainings;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement, rs);
      } catch (ConnectionPoolException e) {
        logger.error(e);
        throw new DaoException("Error access database", e);
      }
    }
  }

  @Override
  public Training findTrainingByIdTraining(int trainingId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;
    Training training = new Training();
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_ALL_TRAININGS_BY_TRAINING_ID);
      preparedStatement.setInt(1, trainingId);
      rs = preparedStatement.executeQuery();
      while (rs.next()) {
        training.setName(rs.getString(SQL_NAME));
        training.setId(rs.getInt(SQL_TRAINING_ID));
        training.setInformation(rs.getString(SQL_TRAINING_INFORMATION));
        training.setStatus(rs.getBoolean(SQL_TRAINING_STATUS));
      }
      return training;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement, rs);
      } catch (ConnectionPoolException e) {
        logger.error(e);
        throw new DaoException("Error access database", e);
      }
    }
  }

  @Override
  public List<Topic> findTopicsForTraining(int trainingId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_TOPICS_FOR_TRAINING);
      preparedStatement.setInt(1, trainingId);
      resultSet = preparedStatement.executeQuery();
      List<Topic> topics = new ArrayList<>();
      while (resultSet.next()) {
        Topic topic = new Topic();
        topic.setId(resultSet.getInt(SQL_TOPIC_ID));
        topic.setName(resultSet.getString(SQL_NAME_TOPIC));
        topic.setTopic(resultSet.getString(SQL_TOPIC));
        topics.add(topic);
      }
      return topics;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement, resultSet);
      } catch (ConnectionPoolException e) {
        logger.error(e);
        throw new DaoException("Error access database", e);
      }
    }
  }

  @Override
  public Topic findTopic(int topicId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Topic topic = new Topic();
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_TOPIC_BY_TOPIC_ID);
      preparedStatement.setInt(1, topicId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        topic.setId(resultSet.getInt(SQL_TOPIC_ID));
        topic.setName(resultSet.getString(SQL_NAME_TOPIC));
        topic.setTopic(resultSet.getString(SQL_TOPIC));
      }
      return topic;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement, resultSet);
      } catch (ConnectionPoolException e) {
        logger.error(e);
        throw new DaoException("Error access database", e);
      }
    }
  }

  @Override
  public boolean updateTrainingsInformation(int trainingId, String trainingName, String information) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_UPDATE_TRAININGS_INFORMATION);
      preparedStatement.setString(1, information);
      preparedStatement.setString(2, trainingName);
      preparedStatement.setInt(3, trainingId);
      preparedStatement.executeUpdate();
      return true;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement);
      } catch (ConnectionPoolException e) {
        logger.error(e);
        throw new DaoException("Error access database", e);
      }
    }
  }

  @Override
  public boolean addTopicForTraining(int trainingId, String topicsName, String topicsText) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_ADD_TOPIC_FOR_TRAINING);
      preparedStatement.setInt(1, trainingId);
      preparedStatement.setString(2, topicsName);
      preparedStatement.setString(3, topicsText);
      preparedStatement.executeUpdate();
      return true;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement);
      } catch (ConnectionPoolException e) {
        logger.error(e);
        throw new DaoException("Error access database", e);
      }
    }
  }

  @Override
  public boolean addTaskForTraining(int trainingId, String taskName, String taskText) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_ADD_TASK_FOR_TRAINING);
      preparedStatement.setInt(1, trainingId);
      preparedStatement.setString(2, taskName);
      preparedStatement.setString(3, taskText);
      preparedStatement.executeUpdate();
      return true;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement);
      } catch (ConnectionPoolException e) {
        logger.error(e);
        throw new DaoException("Error access database", e);
      }
    }
  }

  @Override
  public List<Task> findTasksListForTraining(int trainingId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    List<Task> tasks = new ArrayList<>();
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_TASKS_FOR_TRAINING);
      preparedStatement.setInt(1, trainingId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Task task = new Task();
        task.setId(resultSet.getInt(SQL_TASK_ID));
        task.setName(resultSet.getString(SQL_TASK_NAME));
        task.setTask(resultSet.getString(SQL_TASK));
        tasks.add(task);
      }
      return tasks;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement, resultSet);
      } catch (ConnectionPoolException e) {
        logger.error(e);
        throw new DaoException("Error access database", e);
      }
    }
  }

  @Override
  public boolean createTraining(String trainingName, int mentorId, String trainingDescription) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    PreparedStatement preparedStatement = null;
    Connection connection = null;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_CREATE_TRAINING);
      preparedStatement.setString(1, trainingName);
      preparedStatement.setInt(2, mentorId);
      preparedStatement.setString(3, trainingDescription);
      preparedStatement.executeUpdate();
      return true;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement);
      } catch (ConnectionPoolException e) {
        logger.error(e);
        throw new DaoException("Error access database", e);
      }
    }
  }

  @Override
  public boolean updateTrainingsTopic(int topicId, String topicName, String topic) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_UPDATE_TRAININGS_TOPIC);
      preparedStatement.setString(1, topicName);
      preparedStatement.setString(2, topic);
      preparedStatement.setInt(3, topicId);
      preparedStatement.executeUpdate();
      return true;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement);
      } catch (ConnectionPoolException e) {
        logger.error(e);
        throw new DaoException("Error access database", e);
      }
    }
  }

  @Override
  public boolean checkTopicStatus(int userId, int topicId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    boolean check = false;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_CHECK_STATUS_TOPIC);
      preparedStatement.setInt(1, userId);
      preparedStatement.setInt(2, topicId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        check = resultSet.getBoolean(SQL_TOPIC_STATUS);
      }
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement, resultSet);
      } catch (ConnectionPoolException e) {
        logger.error(e);
        throw new DaoException("Error access database", e);
      }
    }
    return check;
  }

  @Override
  public boolean markTopic(int userId, int topicId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_SET_MARK_TOPIC);
      preparedStatement.setInt(1, userId);
      preparedStatement.setInt(2, topicId);
      preparedStatement.executeUpdate();
      return true;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement);
      } catch (ConnectionPoolException e) {
        logger.error(e);
        throw new DaoException("Error access database", e);
      }
    }
  }

  @Override
  public void giveFeedback(String feedback) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try{
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_GIVE_FEEDBACK);
      preparedStatement.setString(1, feedback);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      e.printStackTrace();
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement);
      } catch (ConnectionPoolException e) {
        logger.error(e);
        throw new DaoException("Error access database", e);
      }
    }
  }

  @Override
  public boolean checkTrainingStatusForStudent(int userId, int trainingId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    boolean done = false;
    ResultSet resultSet;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_CHECK_TRAINING_STATUS_FOR_STUDENT);
      preparedStatement.setInt(1, userId);
      preparedStatement.setInt(2, trainingId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        done = resultSet.getBoolean(1);
      }
      return done;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement);
      } catch (ConnectionPoolException e) {
        logger.error(e);
        throw new DaoException("Error access database", e);
      }
    }
  }

  @Override
  public Task findTask(int taskId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Task task = new Task();
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_TASK_BY_ID);
      preparedStatement.setInt(1, taskId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        task.setId(resultSet.getInt(SQL_TASK_ID));
        task.setTask(resultSet.getString(SQL_TASK));
        task.setName(resultSet.getString(SQL_TASK_NAME));
      }
      return task;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement, resultSet);
      } catch (ConnectionPoolException e) {
        logger.error(e);
        throw new DaoException("Error access database", e);
      }
    }
  }

  @Override
  public boolean updateTask(int taskId, String taskName, String task) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_UPDATE_TRAININGS_TASK);
      preparedStatement.setString(1, taskName);
      preparedStatement.setString(2, task);
      preparedStatement.setInt(3, taskId);
      preparedStatement.executeUpdate();
      return true;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement);
      } catch (ConnectionPoolException e) {
        logger.error(e);
        throw new DaoException("Error access database", e);
      }
    }
  }

  @Override
  public int checkTaskStatus(int userId, int taskId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    int mark = 0;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_CHECK_STATUS_TASK);
      preparedStatement.setInt(1, userId);
      preparedStatement.setInt(2, taskId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        mark = resultSet.getInt(1);
      }
      return mark;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement, resultSet);
      } catch (ConnectionPoolException e) {
        logger.error(e);
        throw new DaoException("Error access database", e);
      }
    }
  }

  @Override
  public boolean sendSolution(int userId, int taskId, String answer) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_SEND_SOLUTION);
      preparedStatement.setString(1, answer);
      preparedStatement.setInt(2, userId);
      preparedStatement.setInt(3, taskId);
      preparedStatement.executeUpdate();
      return true;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement);
      } catch (ConnectionPoolException e) {
        logger.error(e);
        throw new DaoException("Error access database", e);
      }
    }
  }

  @Override
  public void deleteTask(int taskId) throws DaoException {
    ConnectionPool connectionPool =  ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_DELETE_TASK);
      preparedStatement.setInt(1, taskId);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException(e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException(e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement);
      } catch (ConnectionPoolException e) {
        logger.error(e);
        throw new DaoException(e);
      }
    }
  }

  @Override
  public void deleteTopic(int topicId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_DELETE_TOPIC);
      preparedStatement.setInt(1, topicId);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException(e);
    } catch (ConnectionPoolException e) {
      e.printStackTrace();
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement);
      } catch (ConnectionPoolException e) {
        logger.error(e);
        throw new DaoException(e);
      }
    }
  }

  @Override
  public boolean deleteTraining(int trainingId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    int checkUsers = 0;
    try {
      connection = connectionPool.takeConnection();
      connection.setAutoCommit(false);
      preparedStatement = connection.prepareStatement(SQL_CHECK_USERS_ON_TRAINING);
      preparedStatement.setInt(1, trainingId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        checkUsers = resultSet.getInt(1);
      }
      if (checkUsers == 0) {
        preparedStatement = connection.prepareStatement(SQL_DELETE_TRAINING);
        preparedStatement.setInt(1, trainingId);
        preparedStatement.executeUpdate();
        connection.commit();
        return true;
      } else {
        connection.rollback();
      }
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException(e);
    } catch (ConnectionPoolException e) {
      e.printStackTrace();
    } finally {
      try {
        connection.setAutoCommit(true);
        connectionPool.closeConnection(connection, preparedStatement);
      } catch (ConnectionPoolException e) {
        logger.error(e);
        throw new DaoException(e);
      } catch (SQLException e) {
        logger.error(e);
        throw new DaoException(e);
      }
    }
    return false;
  }

  @Override
  public Task findTaskSolution(int studentId, int taskId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Task task = new Task();

    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQl_TASK_SOLUTION);
      preparedStatement.setInt(1, studentId);
      preparedStatement.setInt(2, taskId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        task.setMark(resultSet.getInt(SQL_MARK));
        task.setAnswer(resultSet.getString(SQL_ANSWER));
      }
      return task;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement, resultSet);
      } catch (ConnectionPoolException e) {
        logger.error(e);
        throw new DaoException("Error access database", e);
      }
    }
  }

  @Override
  public boolean gradeTask(int studentId, int taskId, int mark) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    boolean done = false;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_GRADE_TASK);
      preparedStatement.setInt(1, mark);
      preparedStatement.setInt(2, studentId);
      preparedStatement.setInt(3, taskId);
      preparedStatement.executeUpdate();
      return true;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement);
      } catch (ConnectionPoolException e) {
        logger.error(e);
        throw new DaoException("Error access database", e);
      }
    }
  }

  @Override
  public int avgMarkForTask(int userId, int trainingId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    int avgMark = 0;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_AVG_FOR_TASKS);
      preparedStatement.setInt(1, userId);
      preparedStatement.setInt(2, trainingId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        avgMark = resultSet.getInt(SQL_AVG_MARK);
      }
      return avgMark;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement, resultSet);
      } catch (ConnectionPoolException e) {
        logger.error(e);
        throw new DaoException("Error access database", e);
      }
    }
  }

  @Override
  public List<Task> findCompletedTasks(int trainingId, int studentId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    List<Task> tasks = new ArrayList<>();
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_COMPLETED_TASKS_FOR_STUDENT);
      preparedStatement.setInt(1, studentId);
      preparedStatement.setInt(2, trainingId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Task task = new Task();
        task.setId(resultSet.getInt(SQL_TASK_ID));
        task.setName(resultSet.getString(SQL_TASK_NAME));
        tasks.add(task);
      }
      return tasks;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement, resultSet);
      } catch (ConnectionPoolException e) {
        logger.error(e);
        throw new DaoException("Error access database", e);
      }
    }
  }

  @Override
  public List<Topic> findLearnedTopics(int studentId, int trainingId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    List<Topic> topics = new ArrayList<>();
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_LEARNED_TOPICS);
      preparedStatement.setInt(1, studentId);
      preparedStatement.setInt(2, trainingId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Topic topic = new Topic();
        topic.setId(resultSet.getInt(SQL_TOPIC_ID));
        topic.setName(resultSet.getString(SQL_NAME_TOPIC));
        topics.add(topic);
      }
      return topics;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement, resultSet);
      } catch (ConnectionPoolException e) {
        logger.error(e);
        throw new DaoException("Error access database", e);
      }
    }
  }

  @Override
  public List<Consultation> findConsultationsForTraining(int trainingId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    List<Consultation> consultations = new ArrayList<>();
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_CONSULTATIONS_FOR_TRAINING);
      preparedStatement.setInt(1, trainingId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Consultation consultation = new Consultation();
        consultation.setId(resultSet.getInt(SQL_CONSULTATION_ID));
        consultation.setDate(resultSet.getDate(SQL_DATE));
        consultation.setPrice(resultSet.getInt(SQL_PRICE));
        consultations.add(consultation);
      }
      return consultations;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement, resultSet);
      } catch (ConnectionPoolException e) {
        logger.error(e);
        throw new DaoException("Error access database", e);
      }
    }
  }

  @Override
  public boolean sendOrderConsultation(int consultationId, int studentId, List<Integer> taskIds,
                                       List<Integer> topicIds) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_TASKS_FOR_CONSULTATION);
      for (Integer id : taskIds) {
        preparedStatement.setInt(1, consultationId);
        preparedStatement.setInt(2, studentId);
        preparedStatement.setInt(3, id);
        preparedStatement.addBatch();
      }
      preparedStatement.executeBatch();
      preparedStatement = connection.prepareStatement(SQL_TOPICS_FOR_CONSULTATION);
      for (Integer id : topicIds) {
        preparedStatement.setInt(1, consultationId);
        preparedStatement.setInt(2, studentId);
        preparedStatement.setInt(3, id);
        preparedStatement.addBatch();
      }
      preparedStatement.executeUpdate();
      return true;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement);
      } catch (ConnectionPoolException e) {
        logger.error(e);
        throw new DaoException("Error access database", e);
      }
    }
  }

  @Override
  public void closeReception(int trainingId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_CLOSE_RECEPTION);
      preparedStatement.setInt(1, trainingId);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException(e);
    } catch (ConnectionPoolException e) {
      e.printStackTrace();
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement);
      } catch (ConnectionPoolException e) {
        logger.error(e);
        throw new DaoException(e);
      }
    }
  }

  @Override
  public int findFinalGrade(int studentId, int trainingId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;
    int grade = 0;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_FIND_FINAL_GRADE);
      preparedStatement.setInt(1, studentId);
      preparedStatement.setInt(2, trainingId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        grade = resultSet.getInt(SQL_GRADE_FOR_TRAINING);
      }
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException(e);
    } catch (ConnectionPoolException e) {
      e.printStackTrace();
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement, resultSet);
      } catch (ConnectionPoolException e) {
        logger.error(e);
        throw new DaoException(e);
      }
    }
    return grade;
  }

  @Override
  public void setFinalGrade(int studentId, int trainingId, int grade) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_SET_FINAL_GRADE);
      preparedStatement.setInt(1, grade);
      preparedStatement.setInt(2, studentId);
      preparedStatement.setInt(3, trainingId);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      e.printStackTrace();
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement);
      } catch (ConnectionPoolException e) {
        logger.error(e);
        throw new DaoException("Error access database", e);
      }
    }
  }

  @Override
  public List<Training> findTraining() throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;
    List<Training> trainings = new ArrayList<>();
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_ALL_TRAININGS);
      rs = preparedStatement.executeQuery();
      while (rs.next()) {
        Training training = new Training();
        training.setId(rs.getInt(SQL_TRAINING_ID));
        training.setName(rs.getString(SQL_NAME));
        training.setStatus(rs.getBoolean(SQL_TRAINING_STATUS));
        trainings.add(training);
      }
      return trainings;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement, rs);
      } catch (ConnectionPoolException e) {
        logger.error(e);
        throw new DaoException("Error access database", e);
      }
    }
  }
}
