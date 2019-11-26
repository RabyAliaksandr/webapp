package com.epam.tc.dao.impl;

import com.epam.tc.connectionpool.ConnectionPoolException;
import com.epam.tc.dao.DaoException;
import com.epam.tc.dao.PaymentCardDao;
import com.epam.tc.dao.SqlColumn;
import com.epam.tc.entity.PaymentCard;
import com.epam.tc.connectionpool.ConnectionPool;

import static com.epam.tc.dao.SqlQuery.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Payment card dao.
 */
public class PaymentCardDaoImpl implements PaymentCardDao {

  private static Logger logger = LogManager.getLogger(PaymentCardDaoImpl.class);

  /** {@inheritDoc} */
  @Override
  public boolean addPaymentCard(int userId, long cardNumber) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection =connectionPool.takeConnection();
      connection.setAutoCommit(false);
      preparedStatement = connection.prepareStatement(SQL_ADD_PAYMENT_CARD);
      preparedStatement.setLong(1, cardNumber);
      preparedStatement.setLong(2, cardNumber);
      int check = preparedStatement.executeUpdate();
      if (check > 0) {
        preparedStatement.close();
        preparedStatement = connection.prepareStatement(SQL_ADD_PAYMENT_CARD_TO_USER);
        preparedStatement.setInt(1, userId);
        preparedStatement.setLong(2, cardNumber);
        preparedStatement.executeUpdate();
        connection.commit();
        return true;
      } else {
        connection.rollback();
        return false;
      }
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException(e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException(e);
    } finally {
      try {
        connection.setAutoCommit(true);
      } catch (SQLException e) {
        logger.error(e);
      }
      try {
        connectionPool.closeConnection(connection, preparedStatement);
      } catch (ConnectionPoolException e) {
        logger.error(e);
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public List<PaymentCard> findUsersCard(int userId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    List<PaymentCard> paymentCards = new ArrayList<>();
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_FIND_USERS_CARD);
      preparedStatement.setInt(1, userId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        PaymentCard paymentCard = new PaymentCard();
        paymentCard.setId(resultSet.getInt(SqlColumn.SQL_CARD_ID));
        paymentCard.setNumber(resultSet.getLong(SqlColumn.SQL_CARD_NUMBER));
        paymentCard.setScore(resultSet.getBigDecimal(SqlColumn.SQL_CARD_SCORE));
        paymentCards.add(paymentCard);
      }
      return paymentCards;
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
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public void replenishCard(int cardId, BigDecimal sum) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_REPLENISH_CARD);
      preparedStatement.setBigDecimal(1, sum);
      preparedStatement.setInt(2, cardId);
      preparedStatement.executeUpdate();
      logger.info("card account was replenished");
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException(e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
    }
  }

  /** {@inheritDoc} */
  @Override
  public boolean transferMoneyCardToCard(int cardDonor, int cardRecipient, BigDecimal sum) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    int check = 0;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_TRANSFER_MONEY_CARD_TO_CARD);
      preparedStatement.setInt(1, cardDonor);
      preparedStatement.setInt(2, cardDonor);
      preparedStatement.setBigDecimal(3, sum);
      preparedStatement.setInt(4, cardRecipient);
      preparedStatement.setBigDecimal(5, sum);
      preparedStatement.setBigDecimal(6, sum);
      preparedStatement.setInt(7, cardDonor);
      preparedStatement.setInt(8, cardRecipient);
      check = preparedStatement.executeUpdate();
      logger.info("money was transferred from card to card");
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException(e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
    }
    return (check > 0 ? true : false);
  }

  /** {@inheritDoc} */
  @Override
  public boolean paymentConsultation(int cardId,  int consultationId, int userId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      connection = connectionPool.takeConnection();
      connection.setAutoCommit(false);
      preparedStatement = connection.prepareStatement(SQL_PUT_MONEY);
      preparedStatement.setInt(1, consultationId);
      preparedStatement.setInt(2, consultationId);
      preparedStatement.setInt(3, userId);
      preparedStatement.setInt(4, cardId);
      int checkRemoval = 0;
      int checkPut = preparedStatement.executeUpdate();
      if (checkPut > 0) {
        preparedStatement = connection.prepareStatement(SQL_REMOVAL_MONEY);
        preparedStatement.setInt(1, consultationId);
        preparedStatement.setInt(2, cardId);
        preparedStatement.setInt(3, consultationId);
        checkRemoval = preparedStatement.executeUpdate();
      }
      if (checkPut == 0 || checkRemoval == 0) {
        connection.rollback();
        return false;
      }
      connection.commit();
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException(e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException(e);
    } finally {
      try {
        connection.setAutoCommit(true);
      } catch (SQLException e) {
        logger.error(e);
      }
      try {
        connectionPool.closeConnection(connection, preparedStatement);
      } catch (ConnectionPoolException e) {
        logger.error(e);
      }
    }
    logger.debug("payment was made for consultation");
    return true;
    }
}
