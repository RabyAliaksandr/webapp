package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandException;
import com.epam.webapp.constant.ConstMessage;
import com.epam.webapp.constant.ConstName;
import com.epam.webapp.constant.ConstPage;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.manager.MessageManager;
import com.epam.webapp.service.impl.UserServiceImpl;
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
    String temp = request.getParameter(ConstName.DATE);
    boolean done;
    Date currentDate = new Date(Calendar.getInstance().getTime().getTime());
    SimpleDateFormat dateFormat = new SimpleDateFormat(ConstName.DATE_PATTERN);
    java.util.Date dateUtil;
    try {
      dateUtil = dateFormat.parse(temp);
    } catch (ParseException e) {
      logger.error(e);
      throw new CommandException("Invalid date", e);
    }
    java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
    BigDecimal price = new BigDecimal(request.getParameter(ConstName.PRICE));
    System.out.println(request.getParameter(ConstName.PRICE) + " it is command");
    int trainingId = Integer.parseInt(request.getParameter(ConstName.TRAINING_ID));
    UserServiceImpl userService = new UserServiceImpl();
    try {
      done = userService.sendOfferConsultations(trainingId, dateSql, price);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException("Error access service", e);
    }
    if (currentDate.after(dateSql)) {
      request.getSession().setAttribute(ConstMessage.MESSAGE_OFFER_SENT,
              MessageManager.getProperty(ConstMessage.MESSAGE_SENT_ERROR_DATE));
      return ConfigurationManager.getProperty(ConstPage.ADMIN_MANAGEMENT);
    }
    if (done) {
      request.getSession().setAttribute(ConstMessage.MESSAGE_OFFER_SENT,
              MessageManager.getProperty(ConstMessage.MESSAGE_SENT));
      return ConfigurationManager.getProperty(ConstPage.ADMIN_MANAGEMENT);
    }
    request.getSession().setAttribute(ConstMessage.MESSAGE_OFFER_SENT,
            MessageManager.getProperty(ConstMessage.MESSAGE_SENT_ERROR));
    return ConfigurationManager.getProperty(ConstPage.ADMIN_MANAGEMENT);
  }
}
