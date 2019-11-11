package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.connectionpool.exception.ConnectionPoolException;
import com.epam.webapp.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class TopicPageCommand implements Command {
  private static final String TOPIC_PAGE = "path.page.topicForStudy";
  private static final String TOPIC = "topicId";

  @Override
  public String execute(HttpServletRequest request) throws CommandException, CommandException, ConnectionPoolException {
    String topic = request.getParameter(TOPIC);
    request.getSession().setAttribute(TOPIC, topic);
    return ConfigurationManager.getProperty(TOPIC_PAGE);
  }
}
