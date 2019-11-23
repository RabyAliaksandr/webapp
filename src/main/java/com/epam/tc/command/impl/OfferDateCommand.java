package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.CommandException;
import com.epam.tc.command.MessageName;
import com.epam.tc.command.RequestVariableName;
import com.epam.tc.command.PageName;
import com.epam.tc.manager.ConfigurationManager;
import com.epam.tc.manager.MessageManager;
import com.epam.tc.service.impl.UserServiceImpl;
import com.epam.tc.validator.InputDataValidation;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class OfferDateCommand implements Command {

  private static final Logger logger = LogManager.getLogger(OfferDateCommand.class);

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    String temp = request.getParameter(RequestVariableName.DATE);
    boolean done;
    Date currentDate = new Date(Calendar.getInstance().getTime().getTime());
    SimpleDateFormat dateFormat = new SimpleDateFormat(RequestVariableName.DATE_PATTERN);
    java.util.Date dateUtil;
    try {
      dateUtil = dateFormat.parse(temp);
    } catch (ParseException e) {
      logger.error(e);
      throw new CommandException("Invalid date", e);
    }
    java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
    if (currentDate.after(dateSql)) {
      request.getSession().setAttribute(MessageName.MESSAGE_OFFER_SENT,
              MessageManager.getProperty(MessageName.MESSAGE_SENT_ERROR_DATE));
      return ConfigurationManager.getProperty(PageName.ADMIN_MANAGEMENT);
    }
    InputDataValidation validation = new InputDataValidation();
    String tempPrice = request.getParameter(RequestVariableName.PRICE);
    boolean checkPrice = validation.checkMoneyField(tempPrice);
    if (!checkPrice) {
      request.getSession().setAttribute(MessageName.MESSAGE_OFFER_SENT,
              MessageManager.getProperty(MessageName.MESSAGE_SENT_ERROR_MONEY));
      return ConfigurationManager.getProperty(PageName.ADMIN_MANAGEMENT);
    }
    BigDecimal price = new BigDecimal(tempPrice);
    int trainingId = Integer.parseInt(request.getParameter(RequestVariableName.TRAINING_ID));
    UserServiceImpl userService = new UserServiceImpl();
    try {
      done = userService.sendOfferConsultations(trainingId, dateSql, price);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException("Error access service", e);
    }
    if (done) {
      request.getSession().setAttribute(MessageName.MESSAGE_OFFER_SENT,
              MessageManager.getProperty(MessageName.MESSAGE_SENT));
      return ConfigurationManager.getProperty(PageName.ADMIN_MANAGEMENT);
    }
    request.getSession().setAttribute(MessageName.MESSAGE_OFFER_SENT,
            MessageManager.getProperty(MessageName.MESSAGE_SENT_ERROR));
    return ConfigurationManager.getProperty(PageName.ADMIN_MANAGEMENT);
  }
}
