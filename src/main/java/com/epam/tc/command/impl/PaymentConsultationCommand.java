package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.CommandException;
import com.epam.tc.command.MessageName;
import com.epam.tc.command.RequestVariableName;
import com.epam.tc.command.PageName;
import com.epam.tc.manager.ConfigurationManager;
import com.epam.tc.manager.MessageManager;
import com.epam.tc.service.PaymentCardService;
import com.epam.tc.service.ServiceException;
import com.epam.tc.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;

public class PaymentConsultationCommand implements Command {
  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    boolean done;
    int userId = Integer.parseInt(request.getParameter(RequestVariableName.USER_ID));
    int consultationId = Integer.parseInt(request.getParameter(RequestVariableName.CONSULTATION_ID));
    int cardId = Integer.parseInt(request.getParameter(RequestVariableName.CARD_ID));
    PaymentCardService paymentCardService = ServiceFactory.getPaymentCardService();
    try {
      done = paymentCardService.paymentConsultation(cardId,  consultationId, userId);
    } catch (ServiceException e) {
      throw new CommandException(e);
    }
    if (done) {
      request.getSession().setAttribute(MessageName.ORDER_SENT_MESSAGE,
              MessageManager.getProperty(MessageName.MESSAGE_ORDER_SENT));
    } else {
      request.getSession().setAttribute(MessageName.ORDER_SENT_MESSAGE,
              MessageManager.getProperty(MessageName.MESSAGE_ORDER_SENT_ERROR));
    }
    return ConfigurationManager.getProperty(PageName.ORDER_CONSULTATION_PAGE);
  }
}
