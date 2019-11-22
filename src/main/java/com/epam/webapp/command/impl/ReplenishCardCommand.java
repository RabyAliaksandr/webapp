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

public class ReplenishCardCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    BigDecimal sum = new BigDecimal(request.getParameter(ConstName.SUM));
    int cardId = Integer.parseInt(request.getParameter(ConstName.CARD_ID));
    PaymentCardService paymentCardService = new PaymentCardServiceImpl();
    request.getSession().setAttribute(ConstMessage.MESSAGE_OPERATION,
            MessageManager.getProperty(ConstMessage.MESSAGE_OPERATION_DONE));
    request.getSession().setAttribute(ConstName.EDITOR,
            request.getParameter(ConstName.EDITOR));
    try {
      paymentCardService.replenishCard(cardId, sum);
    } catch (ServiceException e) {
                                      // TODO logger
      throw new CommandException(e);
    }
    return ConfigurationManager.getProperty(ConstPage.CARD_MANAGEMENT);
  }
}
