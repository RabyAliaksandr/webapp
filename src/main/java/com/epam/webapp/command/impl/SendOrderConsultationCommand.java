package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.connectionpool.ConnectionPoolException;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.manager.MessageManager;
import com.epam.webapp.service.TrainingsService;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public class SendOrderConsultationCommand implements Command {

  private static final String STUDENT_ID = "studentId";
  private static final String ORDER_CONSULTATION_PAGE = "path.page.orderConsultation";
  private static final String CONSULTATION_ID = "consultationId";
  private static final String ORDER_SENT_MESSAGE = "orderSentMessage";
  private static final String MESSAGE_ORDER_SENT = "message.orderSent";
  private static final String MESSAGE_ORDER_SENT_ERROR = "message.orderDidntSend";

  @Override
  public String execute(HttpServletRequest request) throws CommandException, ConnectionPoolException, ParseException {
    String[] tempTaskIds = request.getParameterValues("taskId");
    String taskIds = new String();
    for (int i = 0; i < tempTaskIds.length; i++) {
      taskIds = taskIds + tempTaskIds[i] + ",";
    }
    String[] tempTopicIds = request.getParameterValues("topicId");
    String topicIds = new String();
    for (int i = 0; i < tempTopicIds.length; i++) {
      topicIds = topicIds + tempTopicIds[i] + ",";
    }
    int consultationId = Integer.parseInt(request.getParameter(CONSULTATION_ID));
    int studentId = Integer.parseInt(request.getParameter(STUDENT_ID));
    TrainingsService trainingsService = new TrainingsService();
    boolean done = trainingsService.sendOrderConsultation(consultationId, studentId, taskIds, topicIds);
    if (done) {
      request.getSession().setAttribute(ORDER_SENT_MESSAGE, MessageManager.getProperty(MESSAGE_ORDER_SENT));
      return ConfigurationManager.getProperty(ORDER_CONSULTATION_PAGE);
    }
    request.getSession().setAttribute(ORDER_SENT_MESSAGE, MessageManager.getProperty(MESSAGE_ORDER_SENT_ERROR));
    return ConfigurationManager.getProperty(ORDER_CONSULTATION_PAGE);
  }
}
