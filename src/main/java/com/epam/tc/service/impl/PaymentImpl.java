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
 * The type Payment implements interface Payment {@link PaymentService}
 * @author alex raby
 * @version 1.0
 */
public class PaymentImpl implements PaymentService {

  /**
   * class object Logger {@link Logger}
   * writes important events to a log file
   */
  private static Logger logger = LogManager.getLogger(PaymentImpl.class);

  /** {@inheritDoc} */
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
