package com.epam.tc.dao;

import com.epam.tc.entity.Payment;

import java.util.List;

/**
 * The interface Payment dao.
 */
public interface PaymentDao {
  /**
   * Find all payments list.
   *
   * @return the list
   * @throws DaoException the dao exception
   */
  public List<Payment> findAllPayments() throws DaoException;
}
