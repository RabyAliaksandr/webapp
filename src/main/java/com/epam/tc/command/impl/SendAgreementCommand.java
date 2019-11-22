package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.CommandException;
import com.epam.tc.command.MessageName;
import com.epam.tc.command.RequestVariableName;
import com.epam.tc.command.PageName;
import com.epam.tc.manager.ConfigurationManager;
import com.epam.tc.manager.MessageManager;
import com.epam.tc.service.ConsultationService;
import com.epam.tc.service.ServiceException;
import com.epam.tc.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class SendAgreementCommand implements Command {

  private static final Logger logger = LogManager.getLogger(SendAgreementCommand.class);

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    ConsultationService consultationService = ServiceFactory.getConsultationService();
    int trainingId = Integer.parseInt(request.getParameter(RequestVariableName.TRAINING_ID));
    boolean agreement = Boolean.parseBoolean(request.getParameter(RequestVariableName.AGREEMENT));
    String temp = request.getParameter(RequestVariableName.DATE);
    java.sql.Date dateSql = Date.valueOf(temp);
    boolean done;
    try {
      done = consultationService.sendAgreement(trainingId, dateSql, agreement);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException("Error access service", e);
    }
    if (done) {
      request.getSession().setAttribute(MessageName.AGREEMENT_SENT,
              MessageManager.getProperty(MessageName.MESSAGE_AGREEMENT_SENT));
      return ConfigurationManager.getProperty(PageName.CONSULTATION_MANAGEMENT);
    }
    request.getSession().setAttribute(MessageName.MESSAGE_AGREEMENT_SENT,
            MessageManager.getProperty(MessageName.MESSAGE_AGREEMENT_ERROR));
    return ConfigurationManager.getProperty(PageName.CONSULTATION_MANAGEMENT);
  }
}
