package com.epam.tc.dao.impl;

import com.epam.tc.connectionpool.ConnectionPool;
import com.epam.tc.connectionpool.ConnectionPoolException;
import com.epam.tc.dao.ConsultationDao;
import com.epam.tc.dao.DaoException;
import com.epam.tc.entity.Consultation;
import com.epam.tc.entity.Training;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.epam.tc.dao.SqlColumn.*;
import static com.epam.tc.dao.SqlQuery.*;
import static com.epam.tc.dao.SqlQuery.SQL_TOPICS_FOR_CONSULTATION;

public class ConsultationDaoImpl implements ConsultationDao {

  private final static Logger logger = LogManager.getLogger(ConsultationDaoImpl.class);
  private ConnectionPool connectionPool = ConnectionPool.getInstance();

  @Override
  public List<Consultation> findConsultationsForTraining(int trainingId) throws DaoException {
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
  public boolean sendOfferConsultations(int trainingId, Date date, BigDecimal price) throws DaoException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_OFFER_CONSULTATION);
      preparedStatement.setInt(1, trainingId);
      preparedStatement.setString(2, date.toString());
      preparedStatement.setBigDecimal(3, price);
      preparedStatement.setInt(4, trainingId);
      preparedStatement.setString(5, date.toString());
      preparedStatement.executeUpdate();
      return true;
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
  public Map<Training, Date> findConsultationsOffer(int mentorId) throws DaoException {
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
      logger.error(e);
      throw new DaoException(e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException(e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement, resultSet);
      } catch (ConnectionPoolException e) {
        logger.error(e);
        throw new DaoException(e);
      }
    }
  }

  @Override
  public boolean sendAgreement(int trainingId, Date date, boolean mark) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
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
}
