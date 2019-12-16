package com.epam.tc.dao;

import com.epam.tc.entity.Payment;

import java.util.List;

/**
 * contains methods for object Payment
 *
 * @author alex raby
 * @version 1.0
 */
public interface PaymentDao {

  /**
   * Find all Payments
   *
   * @return the list Payment {@link Payment}
   * @throws DaoException the dao exception
   */
   List<Payment> findAllPayments() throws DaoException;
}
