package com.epam.tc.service;

import com.epam.tc.entity.Payment;

import java.util.List;

/**
 * The interface Payment service.
 */
public interface PaymentService {

  /**
   * Find all payments list.
   *
   * @return the list
   * @throws ServiceException the service exception
   */
  List<Payment> findAllPayments() throws ServiceException;
}
