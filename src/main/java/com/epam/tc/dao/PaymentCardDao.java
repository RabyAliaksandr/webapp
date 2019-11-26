package com.epam.tc.dao;

import com.epam.tc.entity.PaymentCard;

import java.math.BigDecimal;
import java.util.List;

/**
 * The interface Payment card dao.
 */
public interface PaymentCardDao {

  /**
   * Find users card list.
   *
   * @param userId the user id
   * @return the list
   * @throws DaoException the dao exception
   */
  List<PaymentCard> findUsersCard(int userId) throws DaoException;

  /**
   * Replenish card.
   *
   * @param cardId the card id
   * @param sum    the sum
   * @throws DaoException the dao exception
   */
  void replenishCard(int cardId, BigDecimal sum) throws DaoException;

  /**
   * Transfer money card to card boolean.
   *
   * @param cardDonor     the card donor
   * @param cardRecipient the card recipient
   * @param sum           the sum
   * @return the boolean
   * @throws DaoException the dao exception
   */
  boolean transferMoneyCardToCard(int cardDonor, int cardRecipient, BigDecimal sum) throws DaoException;

  /**
   * Payment consultation boolean.
   *
   * @param cardId         the card id
   * @param consultationId the consultation id
   * @param userId         the user id
   * @return the boolean
   * @throws DaoException the dao exception
   */
  boolean paymentConsultation(int cardId, int consultationId, int userId) throws DaoException;

  /**
   * Add payment card boolean.
   *
   * @param userId     the user id
   * @param cardNumber the card number
   * @return the boolean
   * @throws DaoException the dao exception
   */
  boolean addPaymentCard(int userId, long cardNumber) throws DaoException;
}
