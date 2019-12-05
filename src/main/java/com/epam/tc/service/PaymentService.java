package com.epam.tc.service;

import com.epam.tc.entity.Payment;

import java.util.List;

/**
 * The interface Payment service.
 * @author alex raby
 * @version 1.0
 * @see Payment
 */
public interface PaymentService {

  /**
   * Find all payments list.
   *
   * @return the list
   * @throws ServiceException the service exception
   * @see Payment
   */
  List<Payment> findAllPayments() throws ServiceException;
}
