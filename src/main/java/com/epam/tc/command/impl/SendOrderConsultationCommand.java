package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.CommandException;
import com.epam.tc.command.MessageName;
import com.epam.tc.command.RequestVariableName;
import com.epam.tc.command.PageName;
import com.epam.tc.manager.ConfigurationManager;
import com.epam.tc.manager.MessageManager;
import com.epam.tc.service.ConsultationService;
import com.epam.tc.service.PaymentCardService;
import com.epam.tc.service.ServiceFactory;
import com.epam.tc.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class SendOrderConsultationCommand implements Command {

  private static final Logger logger = LogManager.getLogger(SendOrderConsultationCommand.class);

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    boolean done;
    int userId = Integer.parseInt (request.getParameter(RequestVariableName.USER_ID));
    int consultationId = Integer.parseInt(request.getParameter(RequestVariableName.CONSULTATION_ID));
    int cardId = Integer.parseInt(request.getParameter(RequestVariableName.CARD_ID));
    PaymentCardService paymentCardService = ServiceFactory.getPaymentCardService();
    try {
      done = paymentCardService.paymentConsultation(cardId, consultationId, userId);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException(e);
    }
    if(!done) {
      request.getSession().setAttribute(MessageName.ORDER_SENT_MESSAGE,
              MessageManager.getProperty(MessageName.MESSAGE_ORDER_SENT_ERROR));
      return ConfigurationManager.getProperty(PageName.ORDER_CONSULTATION_PAGE);
    }
    String[] tempTaskIds = request.getParameterValues(RequestVariableName.TASK_ID);
    List<Integer> taskIds = new ArrayList<>();
    for (int i = 0; i < tempTaskIds.length; i++) {
      taskIds.add(Integer.parseInt(tempTaskIds[i]));
    }
    String[] tempTopicIds = request.getParameterValues(RequestVariableName.TOPIC_ID);
    List<Integer> topicIds = new ArrayList<>();
    for (int i = 0; i < tempTopicIds.length; i++) {
      topicIds.add(Integer.parseInt(tempTopicIds[i]));
    }
    int studentId = Integer.parseInt(request.getParameter(RequestVariableName.USER_ID));
    ConsultationService consultationService = ServiceFactory.getConsultationService();
    try {
      done = consultationService.sendOrderConsultation(consultationId, studentId, taskIds, topicIds);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException("Error access service", e);
    }
    if (done) {
      request.getSession().setAttribute(RequestVariableName.ORDER_SENT_MESSAGE,
              MessageManager.getProperty(MessageName.MESSAGE_ORDER_SENT));
      return ConfigurationManager.getProperty(PageName.ORDER_CONSULTATION_PAGE);
    }
    request.getSession().setAttribute(RequestVariableName.ORDER_SENT_MESSAGE,
            MessageManager.getProperty(MessageName.MESSAGE_ORDER_SENT_ERROR));
    request.getSession().setAttribute(RequestVariableName.REDIRECT_TO,
            ConfigurationManager.getProperty(PageName.ORDER_CONSULTATION_PAGE));
    return ConfigurationManager.getProperty(PageName.ORDER_CONSULTATION_PAGE);
  }
}
