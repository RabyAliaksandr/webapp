package com.epam.tc.command.impl;

import com.epam.tc.command.*;
import com.epam.tc.entity.PaymentCard;
import com.epam.tc.entity.User;
import com.epam.tc.manager.ConfigurationManager;
import com.epam.tc.manager.MessageManager;
import com.epam.tc.service.PaymentCardService;
import com.epam.tc.service.ServiceException;
import com.epam.tc.service.ServiceFactory;
import com.epam.tc.validator.InputDataValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * @author alex raby
 * @version 1.0
 * adding a PaymentCard {@link PaymentCard} for a User {@link User}
 */
public class AddPaymentCardCommand implements Command {

  /**
   * class object Logger {@link Logger}
   * writes important events to a log file
   */
  private static Logger logger = LogManager.getLogger(AddPaymentCardCommand.class);

  /**
   * @see Command
   * @param request - object HttpServletRequest
   * @return page path
   * @throws CommandException package exception {@link CommandException}
   */
  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    int userId = Integer.parseInt(request.getParameter(VariableName.USER_ID));
    String tempCardNumber = request.getParameter(VariableName.CARD_NUMBER);
    InputDataValidation validation = new InputDataValidation();
    boolean checkNumber = validation.checkCardNumber(tempCardNumber);
    if (!checkNumber) {
      request.getSession().setAttribute(MessageName.MESSAGE_ADD_PAYMENT_CARD,
              MessageManager.getProperty(MessageName.MESSAGE_ADD_PAYMENT_CARD_INVALID_NUMBER));
      return ConfigurationManager.getProperty(PageName.CARD_MANAGEMENT);
    }
    long cardNumber = Long.parseLong(tempCardNumber);
    PaymentCardService paymentCardService = ServiceFactory.getPaymentCardService();
    boolean checkAddCard;
    try {
      checkAddCard = paymentCardService.addPaymentCard(userId, cardNumber);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException(e);
    }
    if (checkAddCard) {
      request.getSession().setAttribute(MessageName.MESSAGE_ADD_PAYMENT_CARD,
              MessageManager.getProperty(MessageName.MESSAGE_ADD_PAYMENT_CARD_DONE));
      return ConfigurationManager.getProperty(PageName.CARD_MANAGEMENT);
    } else {
      request.getSession().setAttribute(MessageName.MESSAGE_ADD_PAYMENT_CARD,
              MessageManager.getProperty(MessageName.MESSAGE_ADD_PAYMENT_CARD_WRONG));
      return ConfigurationManager.getProperty(PageName.CARD_MANAGEMENT);
    }
  }
}
