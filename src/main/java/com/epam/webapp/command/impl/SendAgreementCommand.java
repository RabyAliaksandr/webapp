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

public class SendAgreementCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    UserServiceImpl userService = new UserServiceImpl();
    int trainingId = Integer.parseInt(request.getParameter(CommandConst.TRAINING_ID));
    boolean agreement = Boolean.parseBoolean(request.getParameter(CommandConst.AGREEMENT));
    String temp = request.getParameter(CommandConst.DATE);
    java.sql.Date dateSql = Date.valueOf(temp);
    boolean done;
    try {
      done = userService.sendAgreement(trainingId, dateSql, agreement);
    } catch (ServiceException e) {
      throw new CommandException("Error access service", e);
    }
    if (done) {
      request.getSession().setAttribute(CommandConst.AGREEMENT_SENT,
              MessageManager.getProperty(CommandConst.MESSAGE_AGREEMENT_SENT));
      return ConfigurationManager.getProperty(CommandConst.CONSULTATION_MANAGEMENT);
    }
    request.getSession().setAttribute(CommandConst.MESSAGE_AGREEMENT_SENT,
            MessageManager.getProperty(CommandConst.MESSAGE_AGREEMENT_ERROR));
    return ConfigurationManager.getProperty(CommandConst.CONSULTATION_MANAGEMENT);
  }
}
