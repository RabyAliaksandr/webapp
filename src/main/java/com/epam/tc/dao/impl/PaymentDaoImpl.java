package com.epam.tc.dao.impl;

import com.epam.tc.connectionpool.ConnectionPool;
import com.epam.tc.connectionpool.ConnectionPoolException;
import com.epam.tc.dao.DaoException;
import com.epam.tc.dao.PaymentDao;
import com.epam.tc.dao.SqlColumn;
import com.epam.tc.dao.SqlQuery;
import com.epam.tc.entity.Consultation;
import com.epam.tc.entity.Payment;
import com.epam.tc.entity.PaymentCard;
import com.epam.tc.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * implements PaymentDao {@link PaymentDao}
 * connects with DataBase
 * takes Connection in ConnectionPool {@link ConnectionPool}
 *
 * @author alex raby
 * @version 1.0
 */
public class PaymentDaoImpl implements PaymentDao {

  private static Logger logger = LogManager.getLogger(PaymentDaoImpl.class);

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Payment> findAllPayments() throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    ResultSet resultSet;
    List<Payment> payments = new ArrayList<>();
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_ALL_PAYMENTS)) {
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Payment payment = new Payment();
        Consultation consultation = new Consultation();
        User user = new User();
        PaymentCard paymentCard = new PaymentCard();
        user.setName(resultSet.getString(SqlColumn.SQL_USER_NAME));
        user.setSurname(resultSet.getString(SqlColumn.SQL_USER_SURNAME));
        paymentCard.setNumber(resultSet.getLong(SqlColumn.SQL_CARD_NUMBER));
        consultation.setDate(resultSet.getDate(SqlColumn.SQL_PAYMENT_DATE));
        consultation.setPrice(resultSet.getInt(SqlColumn.SQL_PRICE));
        payment.setConsultation(consultation);
        payment.setPaymentCard(paymentCard);
        payment.setUser(user);
        payments.add(payment);
      }
      return payments;
    } catch (SQLException | ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException(e);
    }
  }
}
