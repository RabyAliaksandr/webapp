package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.connectionpool.ConnectionPoolException;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.manager.MessageManager;
import com.epam.webapp.service.TrainingsService;

import javax.servlet.http.HttpServletRequest;

public class MarkTopicCommand implements Command {

  private static final String USER_ID = "userId";
  private static final String TOPIC_ID = "topicId";
  private static final String MESSAGE_ABOUT_DONE = "markDoneMessage";
  private static final String MESSAGE_MARK_DONE = "message.markDone";
  private static final String MESSAGE_MARK_ERROR = "message.markError";
  private static final String TOPIC_PAGE = "path.page.topicForStudy";


  @Override
  public String execute(HttpServletRequest request) throws CommandException, CommandException, ConnectionPoolException {
    boolean done = false;
    TrainingsService trainingsService = new TrainingsService();
    int userId = Integer.parseInt(request.getParameter(USER_ID));
    System.out.println(userId + "userI");
    int topicId = Integer.parseInt(request.getParameter(TOPIC_ID));
    System.out.println(topicId + "topI");
    done = trainingsService.markTopic(userId, topicId);
    if (done) {
      request.getSession().setAttribute(MESSAGE_ABOUT_DONE, MessageManager.getProperty(MESSAGE_MARK_DONE));
      return ConfigurationManager.getProperty(TOPIC_PAGE);
    }
    request.getSession().setAttribute(MESSAGE_ABOUT_DONE, MessageManager.getProperty(MESSAGE_MARK_ERROR));
    return ConfigurationManager.getProperty(TOPIC_PAGE);
  }
}
