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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;


public class TransferMoneyCommand implements Command {

  private static Logger logger = LogManager.getLogger(TransferMoneyCommand.class);

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    boolean done;
    Integer cardDonor = Integer.parseInt(request.getParameter(RequestVariableName.CARD_DONOR));
    Integer cardRecipient = Integer.parseInt(request.getParameter(RequestVariableName.CART_RECIPIENT));
    BigDecimal sum = new BigDecimal(request.getParameter(RequestVariableName.SUM));
    PaymentCardService paymentCardService = ServiceFactory.getPaymentCardService();
    try {
     done =  paymentCardService.transferMoneyCardToCard(cardDonor, cardRecipient, sum);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException(e);
    }
   if (done) {
     request.getSession().setAttribute(MessageName.MESSAGE_OPERATION,
             MessageManager.getProperty(MessageName.MESSAGE_OPERATION_DONE));
     logger.info("A payment has been made");
   } else {
     request.getSession().setAttribute(MessageName.MESSAGE_OPERATION,
             MessageManager.getProperty(MessageName.MESSAGE_OPERATION_DIDNT));
     logger.info("Неудачная попытка платежа");
   }
    return ConfigurationManager.getProperty(PageName.CARD_MANAGEMENT);
  }
}
