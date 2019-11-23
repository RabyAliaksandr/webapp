package com.epam.tc.service;

import com.epam.tc.entity.PaymentCard;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentCardService {

  List<PaymentCard> findUsersCard(int userId) throws ServiceException;

  void replenishCard(int cardId, BigDecimal sum) throws ServiceException;

  boolean transferMoneyCardToCard(int cardDonor, int cardRecipient, BigDecimal sum) throws ServiceException;

  boolean paymentConsultation(int cardId, int consultationId, int userId) throws ServiceException;

  boolean addPaymentCard(int userId, long cardNumber) throws ServiceException;
}
