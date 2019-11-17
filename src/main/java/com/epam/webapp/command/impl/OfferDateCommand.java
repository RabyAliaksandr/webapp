package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandConst;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.manager.MessageManager;
import com.epam.webapp.service.impl.UserServiceImpl;
import com.google.protobuf.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class OfferDateCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    String temp = request.getParameter(CommandConst.DATE);
    boolean done;
    Date currentDate = new Date(Calendar.getInstance().getTime().getTime());
    SimpleDateFormat dateFormat = new SimpleDateFormat(CommandConst.DATE_PATTERN);
    java.util.Date dateUtil;
    try {
      dateUtil = dateFormat.parse(temp);
    } catch (ParseException e) {
      throw new CommandException("Invalid date", e);
    }
    java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());

    int trainingId = Integer.parseInt(request.getParameter(CommandConst.TRAINING_ID));
    UserServiceImpl userService = new UserServiceImpl();
    try {
      done = userService.sendOfferConsultations(trainingId, dateSql);
    } catch (ServiceException e) {
      throw new CommandException("Error access service", e);
    }
    if (currentDate.after(dateSql)) {
      request.getSession().setAttribute(CommandConst.MESSAGE_OFFER_SENT,
              MessageManager.getProperty(CommandConst.MESSAGE_SENT_ERROR_DATE));
      return ConfigurationManager.getProperty(CommandConst.ADMIN_MANAGEMENT);
    }
    if (done) {
      request.getSession().setAttribute(CommandConst.MESSAGE_OFFER_SENT,
              MessageManager.getProperty(CommandConst.MESSAGE_SENT));
      return ConfigurationManager.getProperty(CommandConst.ADMIN_MANAGEMENT);
    }
    request.getSession().setAttribute(CommandConst.MESSAGE_OFFER_SENT,
            MessageManager.getProperty(CommandConst.MESSAGE_SENT_ERROR));
    return ConfigurationManager.getProperty(CommandConst.ADMIN_MANAGEMENT);
  }
}
