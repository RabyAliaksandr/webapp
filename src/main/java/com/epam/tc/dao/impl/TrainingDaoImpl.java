package com.epam.tc.dao.impl;

import com.epam.tc.connectionpool.ConnectionPool;
import com.epam.tc.connectionpool.ConnectionPoolException;
import com.epam.tc.dao.TrainingDao;
import com.epam.tc.dao.DaoException;
import com.epam.tc.entity.Training;
import com.epam.tc.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.epam.tc.dao.SqlColumn.*;
import static com.epam.tc.dao.SqlQuery.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Training dao.
 */
public class TrainingDaoImpl implements TrainingDao {

  private static final Logger logger = LogManager.getLogger(TrainingDaoImpl.class);

  /**
   * {@inheritDoc}
   */
  @Override
  public void addTrainingsToStudent(int idStudent, int idTraining) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_COURSE_TO_STUDENT)) {
      preparedStatement.setInt(1, idStudent);
      preparedStatement.setInt(2, idTraining);
      preparedStatement.executeUpdate();
      logger.info("student added training");
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
  public List<Training> findTraining() throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    ResultSet resultSet = null;
    List<Training> trainings = new ArrayList<>();
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_ALL_TRAININGS)) {
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Training training = new Training();
        training.setId(resultSet.getInt(SQL_TRAINING_ID));
        training.setName(resultSet.getString(SQL_NAME));
        training.setStatus(resultSet.getBoolean(SQL_TRAINING_STATUS));
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
  public List<Training> findTrainingsForStudent(int id) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    ResultSet resultSet = null;
    List<Training> trainings = new ArrayList<>();
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_TRAININGS_BY_STUDENT_ID)) {
      preparedStatement.setInt(1, id);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Training training = new Training();
        training.setName(resultSet.getString(SQL_NAME));
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
  public List<Training> findCompletedTrainingForStudent(int studentId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    ResultSet resultSet = null;
    List<Training> trainings = new ArrayList<>();
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_TRAININGS_BY_STUDENT_ID)) {
      preparedStatement.setInt(1, studentId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Training training = new Training();
        User mentor = new User();
        mentor.setId(resultSet.getInt(SQL_MENTOR_ID));
        training.setName(resultSet.getString(SQL_NAME));
        training.setId(resultSet.getInt(SQL_TRAINING_ID));
        training.setMentor(mentor);
        training.setGrade(resultSet.getInt(SQL_GRADE_FOR_TRAINING));
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
  public List<Training> findTrainingForMentor(int mentorId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    ResultSet resultSet = null;
    List<Training> trainings = new ArrayList<>();
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_COMPLETED_TRAININGS_FOR_MENTOR)) {
      preparedStatement.setInt(1, mentorId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Training training = new Training();
        training.setName(resultSet.getString(SQL_NAME));
        training.setId(resultSet.getInt(SQL_TRAINING_ID));
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
  public Training findTrainingByIdTraining(int trainingId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    ResultSet resultSet = null;
    Training training = new Training();
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_ALL_TRAININGS_BY_TRAINING_ID)) {
      preparedStatement.setInt(1, trainingId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        training.setName(resultSet.getString(SQL_NAME));
        training.setId(resultSet.getInt(SQL_TRAINING_ID));
        training.setInformation(resultSet.getString(SQL_TRAINING_INFORMATION));
        training.setStatus(resultSet.getBoolean(SQL_TRAINING_STATUS));
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
  public void updateTrainingsInformation(int trainingId, String trainingName, String information) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_TRAININGS_INFORMATION)) {
      preparedStatement.setString(1, information);
      preparedStatement.setString(2, trainingName);
      preparedStatement.setInt(3, trainingId);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createTraining(String trainingName, int mentorId, String trainingDescription) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_TRAINING)) {
      preparedStatement.setString(1, trainingName);
      preparedStatement.setInt(2, mentorId);
      preparedStatement.setString(3, trainingDescription);
      preparedStatement.executeUpdate();
      logger.info("was created new training");
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean checkTrainingStatusForStudent(int userId, int trainingId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    boolean done = false;
    ResultSet resultSet;
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_CHECK_TRAINING_STATUS_FOR_STUDENT)) {
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
    }

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean deleteTraining(int trainingId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet;
    int checkUser = 0;
    try {
      connection = connectionPool.takeConnection();
      connection.setAutoCommit(false);
      preparedStatement = connection.prepareStatement(SQL_CHECK_USERS_ON_TRAINING);
      preparedStatement.setInt(1, trainingId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        checkUser = resultSet.getInt(1);
      }
      if (checkUser == 0) {
        preparedStatement = connection.prepareStatement(SQL_DELETE_TRAINING);
        preparedStatement.setInt(1, trainingId);
        preparedStatement.executeUpdate();
        connection.commit();
        logger.debug("was deleted training");
        return true;
      } else {
        connection.rollback();
      }
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException(e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException(e);
    } finally {
      try {
        if (connection != null) {
          connection.setAutoCommit(true);
        }
        connectionPool.closeConnection(connection, preparedStatement);
      } catch (ConnectionPoolException | SQLException e) {
        logger.error(e);
      }
    }
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setFinalGrade(int studentId, int trainingId, int grade) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_SET_FINAL_GRADE)) {
      preparedStatement.setInt(1, grade);
      preparedStatement.setInt(2, studentId);
      preparedStatement.setInt(3, trainingId);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException(e);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int findFinalGrade(int studentId, int trainingId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    ResultSet resultSet = null;
    int grade = 0;
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_FINAL_GRADE)) {
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
      logger.error(e);
      throw new DaoException(e);
    } finally {
      try {
        connectionPool.closeConnection(resultSet);
      } catch (ConnectionPoolException e) {
        logger.error(e);
      }
    }
    return grade;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void closeReception(int trainingId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_CLOSE_RECEPTION)) {
      preparedStatement.setInt(1, trainingId);
      preparedStatement.executeUpdate();
      logger.info("was closed reception to training");
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
  public void giveFeedback(String feedback) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_GIVE_FEEDBACK)) {
      preparedStatement.setString(1, feedback);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException(e);
    }
  }

//  @Override
//  public void addTrainingToStudent(int userId, int trainingId) throws DaoException {
//    ConnectionPool connectionPool = ConnectionPool.getInstance();
//    Connection connection = null;
//    PreparedStatement preparedStatement = null;
//    try {
//      connection = connectionPool.takeConnection();
//      preparedStatement = connection.prepareStatement(SQL_ADD_TRAINING_TO_STUDENT);
//      preparedStatement.setInt(1, userId);
//      preparedStatement.setInt(2, trainingId);
//      preparedStatement.executeUpdate();
//    } catch (SQLException e) {
//      logger.error(e);
//      throw new DaoException(e);
//    } catch (ConnectionPoolException e) {
//      logger.error(e);
//      throw new DaoException(e);
//    } finally {
//      try {
//        connectionPool.closeConnection(connection, preparedStatement);
//      } catch (ConnectionPoolException e) {
//        logger.error(e);
//      }
//    }
//  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<String> findReviews() throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    ResultSet resultSet = null;
    List<String> reviews = new ArrayList<>();
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_REVIEWS)) {
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        String review = resultSet.getString(SQL_REVIEW);
        reviews.add(review);
      }
      return reviews;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException(e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException(e);
    }
  }
}
