package com.epam.tc.dao.impl;

import com.epam.tc.connectionpool.ConnectionPool;
import com.epam.tc.connectionpool.ConnectionPoolException;
import com.epam.tc.dao.ConsultationDao;
import com.epam.tc.dao.DaoException;
import com.epam.tc.dao.SqlColumn;
import com.epam.tc.entity.Consultation;
import com.epam.tc.entity.Training;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.epam.tc.dao.SqlQuery.*;
import static com.epam.tc.dao.SqlQuery.SQL_TOPICS_FOR_CONSULTATION;

/**
 * The type Consultation dao.
 */
public class ConsultationDaoImpl implements ConsultationDao {

  private final static Logger logger = LogManager.getLogger(ConsultationDaoImpl.class);

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Consultation> findConsultationsForTraining(int trainingId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    ResultSet resultSet = null;
    List<Consultation> consultations = new ArrayList<>();
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_CONSULTATIONS_FOR_TRAINING);
    ) {
      preparedStatement.setInt(1, trainingId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Consultation consultation = new Consultation();
        consultation.setId(resultSet.getInt(SqlColumn.SQL_CONSULTATION_ID));
        consultation.setDate(resultSet.getDate(SQL_DATE));
        consultation.setPrice(resultSet.getInt(SqlColumn.SQL_PRICE));
        consultations.add(consultation);
      }
      return consultations;
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
  public void sendOrderConsultation(int consultationId, int studentId, List<Integer> taskIds,
                                    List<Integer> topicIds) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_TASKS_FOR_CONSULTATION);
         PreparedStatement preparedStatementTopics = connection.prepareStatement(SQL_TOPICS_FOR_CONSULTATION);
    ) {
      for (Integer id : taskIds) {
        preparedStatement.setInt(1, consultationId);
        preparedStatement.setInt(2, studentId);
        preparedStatement.setInt(3, id);
        preparedStatement.addBatch();
      }
      preparedStatement.executeBatch();

      for (Integer id : topicIds) {
        preparedStatementTopics.setInt(1, consultationId);
        preparedStatementTopics.setInt(2, studentId);
        preparedStatementTopics.setInt(3, id);
        preparedStatementTopics.addBatch();
      }
      preparedStatementTopics.executeUpdate();
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
  public boolean sendOfferConsultations(int trainingId, Date date, BigDecimal price) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_OFFER_CONSULTATION)) {
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
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Map<Training, Date> findConsultationsOffer(int mentorId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    ResultSet resultSet = null;
    Map<Training, Date> consultations = new HashMap<>();
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_CONSULTATION_OFFER);
    ) {
      preparedStatement.setInt(1, mentorId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Training training = new Training();
        training.setId(resultSet.getInt(SqlColumn.SQL_TRAINING_ID));
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
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean sendAgreement(int trainingId, Date date, boolean mark) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_SEND_AGREEMENT);
    ) {
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
    }
  }
}
