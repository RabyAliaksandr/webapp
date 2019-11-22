package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandException;
import com.epam.webapp.constant.ConstMessage;
import com.epam.webapp.constant.ConstName;
import com.epam.webapp.constant.ConstPage;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.manager.MessageManager;
import com.epam.webapp.service.PaymentCardService;
import com.epam.webapp.service.ServiceException;
import com.epam.webapp.service.impl.PaymentCardServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class PaymentConsulttionCommand implements Command {
  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    boolean done = false;
    int userId = Integer.parseInt(request.getParameter(ConstName.USER_ID));
    int consultationId = Integer.parseInt(request.getParameter(ConstName.CONSULTATION_ID));
    int cardId = Integer.parseInt(request.getParameter(ConstName.CARD_ID));
    BigDecimal sum = new BigDecimal(request.getParameter(ConstName.SUM));
    PaymentCardService paymentCardService = new PaymentCardServiceImpl();
    try {
      done = paymentCardService.paymentConsultation(cardId,  consultationId, userId);
    } catch (ServiceException e) {
      throw new CommandException(e);
    }
    if (done) {
      request.getSession().setAttribute(ConstMessage.ORDER_SENT_MESSAGE,
              MessageManager.getProperty(ConstMessage.MESSAGE_ORDER_SENT));
    } else {
      request.getSession().setAttribute(ConstMessage.ORDER_SENT_MESSAGE,
              MessageManager.getProperty(ConstMessage.MESSAGE_ORDER_SENT_ERROR));
    }
    return ConfigurationManager.getProperty(ConstPage.ORDER_CONSULTATION_PAGE);
  }
}
