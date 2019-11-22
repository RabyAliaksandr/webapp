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


public class TransferMoneyCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    boolean done = false;
    Integer cardDonor = Integer.parseInt(request.getParameter(ConstName.CARD_DONOR));
    Integer cardRecipient = Integer.parseInt(request.getParameter(ConstName.CART_RECIPIENT));
    BigDecimal sum = new BigDecimal(request.getParameter(ConstName.SUM));
    PaymentCardService paymentCardService = new PaymentCardServiceImpl();
    try {
     done =  paymentCardService.transferMoneyCardToCard(cardDonor, cardRecipient, sum);
    } catch (ServiceException e) {
      throw new CommandException(e);
    }
//    request.getSession().setAttribute(ConstName.EDITOR, null);
   if (done) {
     request.getSession().setAttribute(ConstMessage.MESSAGE_OPERATION,
             MessageManager.getProperty(ConstMessage.MESSAGE_OPERATION_DONE));
   } else {
     request.getSession().setAttribute(ConstMessage.MESSAGE_OPERATION,
             MessageManager.getProperty(ConstMessage.MESSAGE_OPERATION_DIDNT));
   }
    return ConfigurationManager.getProperty(ConstPage.CARD_MANAGEMENT);
  }
}
