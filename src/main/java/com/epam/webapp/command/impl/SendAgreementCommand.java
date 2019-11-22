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
import java.sql.Date;

public class SendAgreementCommand implements Command {

  private static final Logger logger = LogManager.getLogger(SendAgreementCommand.class);

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    UserServiceImpl userService = new UserServiceImpl();
    int trainingId = Integer.parseInt(request.getParameter(ConstName.TRAINING_ID));
    boolean agreement = Boolean.parseBoolean(request.getParameter(ConstName.AGREEMENT));
    String temp = request.getParameter(ConstName.DATE);
    java.sql.Date dateSql = Date.valueOf(temp);
    boolean done;
    try {
      done = userService.sendAgreement(trainingId, dateSql, agreement);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException("Error access service", e);
    }
    if (done) {
      request.getSession().setAttribute(ConstMessage.AGREEMENT_SENT,
              MessageManager.getProperty(ConstMessage.MESSAGE_AGREEMENT_SENT));
      return ConfigurationManager.getProperty(ConstPage.CONSULTATION_MANAGEMENT);
    }
    request.getSession().setAttribute(ConstMessage.MESSAGE_AGREEMENT_SENT,
            MessageManager.getProperty(ConstMessage.MESSAGE_AGREEMENT_ERROR));
    return ConfigurationManager.getProperty(ConstPage.CONSULTATION_MANAGEMENT);
  }
}
