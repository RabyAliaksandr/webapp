package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandConst;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.manager.MessageManager;
import com.epam.webapp.service.impl.TrainingsServiceImpl;
import com.epam.webapp.service.exception.ServiceException;
import javax.servlet.http.HttpServletRequest;

public class SendOrderConsultationCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    String[] tempTaskIds = request.getParameterValues(CommandConst.TASK_ID);
    String taskIds = new String();
    for (int i = 0; i < tempTaskIds.length; i++) {
      taskIds = taskIds + tempTaskIds[i] + ",";
    }
    String[] tempTopicIds = request.getParameterValues(CommandConst.TOPIC_ID);
    String topicIds = new String();
    for (int i = 0; i < tempTopicIds.length; i++) {
      topicIds = topicIds + tempTopicIds[i] + ",";
    }
    int consultationId = Integer.parseInt(request.getParameter(CommandConst.CONSULTATION_ID));
    int studentId = Integer.parseInt(request.getParameter(CommandConst.STUDENT_ID));
    TrainingsServiceImpl trainingsService = new TrainingsServiceImpl();
    boolean done;
    try {
      done = trainingsService.sendOrderConsultation(consultationId, studentId, taskIds, topicIds);
    } catch (ServiceException e) {
      throw new CommandException("Error access service", e);
    }
    if (done) {
      request.getSession().setAttribute(CommandConst.ORDER_SENT_MESSAGE,
              MessageManager.getProperty(CommandConst.MESSAGE_ORDER_SENT));
      return ConfigurationManager.getProperty(CommandConst.ORDER_CONSULTATION_PAGE);
    }
    request.getSession().setAttribute(CommandConst.ORDER_SENT_MESSAGE,
            MessageManager.getProperty(CommandConst.MESSAGE_ORDER_SENT_ERROR));
    request.getSession().setAttribute(CommandConst.REDIRECT_TO,
            ConfigurationManager.getProperty(CommandConst.ORDER_CONSULTATION_PAGE));
    return ConfigurationManager.getProperty(CommandConst.ORDER_CONSULTATION_PAGE);
  }
}
