package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.connectionpool.ConnectionPoolException;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.manager.MessageManager;
import com.epam.webapp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class OfferDateCommand implements Command {

  private static final String ADMIN_MANAGEMENT = "path.page.management";
  private static final String MESSAGE_OFFER_SENT = "messageOfferSent";
  private static final String MESSAGE_SENT = "message.offerSent";
  private static final String MESSAGE_SENT_ERROR = "message.offerDidntSend";
  private static final String MESSAGE_SENT_ERROR_DATE = "message.offerDidntSendDate";

  @Override
  public String execute(HttpServletRequest request) throws CommandException, ConnectionPoolException, ParseException {
    String temp = request.getParameter("date");
    boolean done = false;
    Date currentDate = new Date(Calendar.getInstance().getTime().getTime());
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    java.util.Date dateUtil = dateFormat.parse(temp);

    java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());

    int trainingId = Integer.parseInt(request.getParameter("trainingId"));
    UserService userService = new UserService();
    done = userService.sendOfferConsultations(trainingId, dateSql);
    if (currentDate.after(dateSql)) {
      request.getSession().setAttribute(MESSAGE_OFFER_SENT, MessageManager.getProperty(MESSAGE_SENT_ERROR_DATE));
      return ConfigurationManager.getProperty(ADMIN_MANAGEMENT);
    }
    if (done) {
      request.getSession().setAttribute(MESSAGE_OFFER_SENT, MessageManager.getProperty(MESSAGE_SENT));
      return ConfigurationManager.getProperty(ADMIN_MANAGEMENT);
    }
    request.getSession().setAttribute(MESSAGE_OFFER_SENT, MessageManager.getProperty(MESSAGE_SENT_ERROR));
    return ConfigurationManager.getProperty(ADMIN_MANAGEMENT);
  }
}
