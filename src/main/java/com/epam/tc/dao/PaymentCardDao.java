package com.epam.tc.dao;

import com.epam.tc.entity.PaymentCard;

import java.math.BigDecimal;
import java.util.List;

/**
 * methods for work with object PaymentCard {@link PaymentCard}
 * @author alex raby
 * @version 1.0
 */
public interface PaymentCardDao {

  /**
   * finds all PaymentCard owned by the User
   *
   * @param userId - User id for which to look
   * @return lit of PaymentCard
   * @throws DaoException the dao exception
   */
  List<PaymentCard> findUsersCard(int userId) throws DaoException;

  /**
   * PaymentCard score replenishment
   *
   * @param cardId - PaymentCard id to be replenished
   * @param sum    - balance value. class object {@link BigDecimal}
   * @throws DaoException the dao exception
   */
  void replenishCard(int cardId, BigDecimal sum) throws DaoException;

  /**
   * balance transfer from one PaymentCard to another PaymentCard
   *
   * @param cardDonor     - PaymentCard id with which the amount is debited
   * @param cardRecipient - PaymentCard id on which the amount is written
   * @param sum           - amount by which the account is replenished. class object {@link BigDecimal}
   * @return - boolean done or no
   * @throws DaoException the dao exception
   */
  boolean transferMoneyCardToCard(int cardDonor, int cardRecipient, BigDecimal sum) throws DaoException;

  /**
   * payment Consultation
   * the amount of the payment is taken from the database. Consultation table contains price
   *
   * @param cardId         - PaymentCard id with which the amount will be debited
   * @param consultationId - Consultation id for which payment will be made
   * @param userId         - User id who made the payment
   * @return boolean
   * @throws DaoException the dao exception
   */
  boolean paymentConsultation(int cardId, int consultationId, int userId) throws DaoException;

  /**
   * add PaymentCard
   *
   * @param userId     - User id which will be added
   * @param cardNumber - PaymentCard number which will be added
   * @return - boolean
   * @throws DaoException the dao exception
   */
  boolean addPaymentCard(int userId, long cardNumber) throws DaoException;


}
