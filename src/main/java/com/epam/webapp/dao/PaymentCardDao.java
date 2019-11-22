package com.epam.webapp.dao;

import com.epam.webapp.entity.PaymentCard;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentCardDao {

  List<PaymentCard> findUsersCard(int userId) throws DaoException;
  void replenishCard(int cardId, BigDecimal sum) throws DaoException;
  boolean transferMoneyCardToCard(int cardDonor, int cardRecipient, BigDecimal sum) throws DaoException;
  boolean paymentConsultation(int cardId, int consultationId, int userId) throws DaoException;
}
