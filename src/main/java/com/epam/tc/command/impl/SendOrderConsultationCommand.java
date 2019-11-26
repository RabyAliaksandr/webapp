package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.CommandException;
import com.epam.tc.command.PageName;
import com.epam.tc.command.VariableName;
import com.epam.tc.manager.ConfigurationManager;
import com.epam.tc.manager.MessageManager;
import com.epam.tc.service.PaymentCardService;
import com.epam.tc.service.ServiceException;
import com.epam.tc.service.ServiceFactory;
import com.epam.tc.command.MessageName;
import com.epam.tc.service.ConsultationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Send order consultation command.
 *
 * @author alex raby
 * @version 1.0 Sending proposal for consultation from administrator to mentor
 */
public class SendOrderConsultationCommand implements Command {

  /**
   * class object Logger {@link Logger}
   * writes important events to a log file
   */
  private static final Logger logger = LogManager.getLogger(SendOrderConsultationCommand.class);

  /** {@inheritDoc} */
  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    boolean done;
    int userId = Integer.parseInt (request.getParameter(VariableName.USER_ID));
    System.out.println(userId + " its userId");
    int consultationId = Integer.parseInt(request.getParameter(VariableName.CONSULTATION_ID));
    System.out.println(consultationId + "consult id");
    int cardId = Integer.parseInt(request.getParameter(VariableName.CARD_ID));
    System.out.println(cardId + "cardId");
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
    String[] tempTaskIds = request.getParameterValues(VariableName.TASK_ID);
    List<Integer> taskIds = new ArrayList<>();
    for (int i = 0; i < tempTaskIds.length; i++) {
      taskIds.add(Integer.parseInt(tempTaskIds[i]));
    }
    String[] tempTopicIds = request.getParameterValues(VariableName.TOPIC_ID);
    List<Integer> topicIds = new ArrayList<>();
    for (int i = 0; i < tempTopicIds.length; i++) {
      topicIds.add(Integer.parseInt(tempTopicIds[i]));
    }
    int studentId = Integer.parseInt(request.getParameter(VariableName.USER_ID));
    ConsultationService consultationService = ServiceFactory.getConsultationService();
    try {
     consultationService.sendOrderConsultation(consultationId, studentId, taskIds, topicIds);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException("Error access service", e);
    }
      request.getSession().setAttribute(VariableName.ORDER_SENT_MESSAGE,
              MessageManager.getProperty(MessageName.MESSAGE_ORDER_SENT));
      return ConfigurationManager.getProperty(PageName.ORDER_CONSULTATION_PAGE);
  }
}
