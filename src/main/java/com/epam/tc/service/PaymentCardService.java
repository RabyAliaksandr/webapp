package com.epam.tc.service;

import com.epam.tc.entity.Consultation;
import com.epam.tc.entity.PaymentCard;
import com.epam.tc.entity.User;

import java.math.BigDecimal;
import java.util.List;

/**
 * The interface Payment card service.
 *
 * @author alex raby
 * @version 1.0 an interface containing methods for working with an object PaymentCard
 */
public interface PaymentCardService {

  /**
   * finds all PaymentCard owned by the User
   *
   * @param userId - User id for which to look
   * @return lit of PaymentCard
   * @throws ServiceException package Service exception
   * @see User
   * @see PaymentCard
   */
  List<PaymentCard> findUsersCard(int userId) throws ServiceException;

  /**
   * PaymentCard score replenishment
   *
   * @param cardId - PaymentCard id to be replenished
   * @param sum    - balance value. class object {@link BigDecimal}
   * @throws ServiceException package Service exception
   * @see PaymentCard
   */
  void replenishCard(int cardId, BigDecimal sum) throws ServiceException;

  /**
   * balance transfer from one PaymentCard to another PaymentCard
   *
   * @param cardDonor     - PaymentCard id with which the amount is debited
   * @param cardRecipient - PaymentCard id on which the amount is written
   * @param sum           - amount by which the account is replenished. class object {@link BigDecimal}
   * @return - boolean done or no
   * @throws ServiceException package Service exception
   */
  boolean transferMoneyCardToCard(int cardDonor, int cardRecipient, BigDecimal sum) throws ServiceException;

  /**
   * payment Consultation
   * the amount of the payment is taken from the database. Consultation table contains price
   *
   * @param cardId         - PaymentCard id with which the amount will be debited
   * @param consultationId - Consultation id for which payment will be made
   * @param userId         - User id who made the payment
   * @return boolean
   * @throws ServiceException package Service exception
   * @see PaymentCard
   * @see User
   * @see Consultation
   */
  boolean paymentConsultation(int cardId, int consultationId, int userId) throws ServiceException;

  /**
   * add PaymentCard
   *
   * @param userId     - User id which will be added
   * @param cardNumber - PaymentCard number which will be added
   * @return - boolean
   * @throws ServiceException package Service exception
   * @see PaymentCard
   * @see User
   */
  boolean addPaymentCard(int userId, long cardNumber) throws ServiceException;
}
