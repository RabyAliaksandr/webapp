package com.epam.tc.service.impl;

import com.epam.tc.dao.DaoException;
import com.epam.tc.dao.DaoFactory;
import com.epam.tc.dao.PaymentDao;
import com.epam.tc.entity.Payment;
import com.epam.tc.service.PaymentService;
import com.epam.tc.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The type Payment.
 */
public class PaymentImpl implements PaymentService {

  private static Logger logger = LogManager.getLogger(PaymentImpl.class);
  @Override
  public List<Payment> findAllPayments() throws ServiceException {
    PaymentDao paymentDao = DaoFactory.getPaymentDao();
    try {
     return paymentDao.findAllPayments();
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException(e);
    }
  }
}
