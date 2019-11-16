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

public class SendAgreementCommand implements Command {
  private static final String TRAINING_ID = "trainingId";
  private static final String AGREEMENT = "agreement";
  private static final String CONSULTATION_MANAGEMENT = "path.page.consultationForMentor";
  private static final String MESSAGE_AGREEMENT_SENT = "message.agreementSent";
  private static final String AGREEMENT_SENT = "messageAgreement";
  private static final String MESSAGE_AGREEMENT_ERROR = "message.agreementDidntSend";

  @Override
  public String execute(HttpServletRequest request) throws CommandException, ConnectionPoolException, ParseException {
    UserService userService = new UserService();
    int trainingId = Integer.parseInt(request.getParameter(TRAINING_ID));
    System.out.println(trainingId + "it's trsid");
    boolean agreement = Boolean.parseBoolean(request.getParameter(AGREEMENT));
    System.out.println(agreement + "it's agreement");
    String temp = request.getParameter("date");
    System.out.println(temp + " it's temp");
    java.sql.Date dateSql = Date.valueOf(temp);
    System.out.println(dateSql + " it's date");

    boolean done = userService.sendAgreement(trainingId, dateSql, agreement);
    if (done) {
      request.getSession().setAttribute(AGREEMENT_SENT, MessageManager.getProperty(MESSAGE_AGREEMENT_SENT));
      return ConfigurationManager.getProperty(CONSULTATION_MANAGEMENT);
    }
    request.getSession().setAttribute(MESSAGE_AGREEMENT_SENT, MessageManager.getProperty(MESSAGE_AGREEMENT_ERROR));
    return ConfigurationManager.getProperty(CONSULTATION_MANAGEMENT);
  }
}
