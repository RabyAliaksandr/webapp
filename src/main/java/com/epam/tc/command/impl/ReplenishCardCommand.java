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
import com.epam.tc.validator.InputDataValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class ReplenishCardCommand implements Command {

  private static Logger logger = LogManager.getLogger(ReplenishCardCommand.class);

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    String tempSum = request.getParameter(RequestVariableName.SUM);
    InputDataValidation validation = new InputDataValidation();
    boolean checkSum = validation.checkMoneyField(tempSum);
    if (!checkSum) {
      request.getSession().setAttribute(MessageName.MESSAGE_OPERATION,
              MessageManager.getProperty(MessageName.MESSAGE_INVALID_SUM));
      return ConfigurationManager.getProperty(PageName.CARD_MANAGEMENT);
    }
    BigDecimal sum = new BigDecimal(tempSum);
    int cardId = Integer.parseInt(request.getParameter(RequestVariableName.CARD_ID));
    PaymentCardService paymentCardService = ServiceFactory.getPaymentCardService();
    request.getSession().setAttribute(MessageName.MESSAGE_OPERATION,
            MessageManager.getProperty(MessageName.MESSAGE_OPERATION_DONE));
    request.getSession().setAttribute(RequestVariableName.EDITOR,
            request.getParameter(RequestVariableName.EDITOR));
    try {
      paymentCardService.replenishCard(cardId, sum);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException(e);
    }
    return ConfigurationManager.getProperty(PageName.CARD_MANAGEMENT);
  }
}
