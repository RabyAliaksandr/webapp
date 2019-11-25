package com.epam.tc.service.impl;

import com.epam.tc.dao.DaoException;
import com.epam.tc.dao.DaoFactory;
import com.epam.tc.dao.PaymentCardDao;
import com.epam.tc.entity.PaymentCard;
import com.epam.tc.service.PaymentCardService;
import com.epam.tc.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author alex raby
 * @version 1.0
 * this class implements interface methods PaymentCardService {@link PaymentCardService}
 * methods of this class catch DaoException {@link DaoException} and throw ServiceException {@link ServiceException}
 */
public class PaymentCardServiceImpl implements PaymentCardService {

  /**
   * class object Logger {@link Logger}
   * writes important events to a log file
   */
  private static Logger logger = LogManager.getLogger(PaymentCardServiceImpl.class);

  @Override
  public boolean addPaymentCard(int userId, long cardNumber) throws ServiceException {
    PaymentCardDao paymentCardDao = DaoFactory.getPaymentCardDao();
    try {
      return paymentCardDao.addPaymentCard(userId, cardNumber);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException(e);
    }
  }

  @Override
  public List<PaymentCard> findUsersCard(int userId) throws ServiceException {
    PaymentCardDao paymentCardDao = DaoFactory.getPaymentCardDao();
    try {
      return paymentCardDao.findUsersCard(userId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException(e);
    }
  }

  @Override
  public void replenishCard(int cardId, BigDecimal sum) throws ServiceException {
    PaymentCardDao paymentCardDao = DaoFactory.getPaymentCardDao();
    try {
      paymentCardDao.replenishCard(cardId, sum);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException(e);
    }
  }

  @Override
  public boolean transferMoneyCardToCard(int cardDonor, int cardRecipient, BigDecimal sum) throws ServiceException {
    PaymentCardDao paymentCardDao = DaoFactory.getPaymentCardDao();
    try {
      return paymentCardDao.transferMoneyCardToCard(cardDonor, cardRecipient, sum);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException(e);
    }
  }

  @Override
  public boolean paymentConsultation(int cardId, int consultationId, int userId) throws ServiceException {
    PaymentCardDao paymentCardDao = DaoFactory.getPaymentCardDao();
    try {
      return paymentCardDao.paymentConsultation(cardId, consultationId, userId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException(e);
    }
  }
}
