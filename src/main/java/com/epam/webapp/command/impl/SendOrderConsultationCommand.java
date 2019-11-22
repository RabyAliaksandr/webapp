package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandException;
import com.epam.webapp.constant.ConstMessage;
import com.epam.webapp.constant.ConstName;
import com.epam.webapp.constant.ConstPage;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.manager.MessageManager;
import com.epam.webapp.service.PaymentCardService;
import com.epam.webapp.service.impl.PaymentCardServiceImpl;
import com.epam.webapp.service.impl.TrainingsServiceImpl;
import com.epam.webapp.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SendOrderConsultationCommand implements Command {

  private static final Logger logger = LogManager.getLogger(SendOrderConsultationCommand.class);

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    boolean done = false;
    int userId = Integer.parseInt (request.getParameter(ConstName.USER_ID));
    int consultationId = Integer.parseInt(request.getParameter(ConstName.CONSULTATION_ID));
    int cardId = Integer.parseInt(request.getParameter(ConstName.CARD_ID));
    PaymentCardService paymentCardService = new PaymentCardServiceImpl();
    try {
      done = paymentCardService.paymentConsultation(cardId, consultationId, userId);
    } catch (ServiceException e) {
      throw new CommandException(e);
    }
    if(!done) {
      request.getSession().setAttribute(ConstMessage.ORDER_SENT_MESSAGE,
              MessageManager.getProperty(ConstMessage.MESSAGE_ORDER_SENT_ERROR));
      return ConfigurationManager.getProperty(ConstPage.ORDER_CONSULTATION_PAGE);
    }
    String[] tempTaskIds = request.getParameterValues(ConstName.TASK_ID);
    List<Integer> taskIds = new ArrayList<>();
    for (int i = 0; i < tempTaskIds.length; i++) {
      taskIds.add(Integer.parseInt(tempTaskIds[i]));
    }
    String[] tempTopicIds = request.getParameterValues(ConstName.TOPIC_ID);
    List<Integer> topicIds = new ArrayList<>();
    for (int i = 0; i < tempTopicIds.length; i++) {
      topicIds.add(Integer.parseInt(tempTopicIds[i]));
    }
    int studentId = Integer.parseInt(request.getParameter(ConstName.USER_ID));
    TrainingsServiceImpl trainingsService = new TrainingsServiceImpl();
    try {

      done = trainingsService.sendOrderConsultation(consultationId, studentId, taskIds, topicIds);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException("Error access service", e);
    }
    if (done) {
      request.getSession().setAttribute(ConstName.ORDER_SENT_MESSAGE,
              MessageManager.getProperty(ConstMessage.MESSAGE_ORDER_SENT));
      return ConfigurationManager.getProperty(ConstPage.ORDER_CONSULTATION_PAGE);
    }
    request.getSession().setAttribute(ConstName.ORDER_SENT_MESSAGE,
            MessageManager.getProperty(ConstMessage.MESSAGE_ORDER_SENT_ERROR));
    request.getSession().setAttribute(ConstName.REDIRECT_TO,
            ConfigurationManager.getProperty(ConstPage.ORDER_CONSULTATION_PAGE));
    return ConfigurationManager.getProperty(ConstPage.ORDER_CONSULTATION_PAGE);
  }
}
