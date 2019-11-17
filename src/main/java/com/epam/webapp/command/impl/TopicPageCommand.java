package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandConst;
import com.epam.webapp.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class TopicPageCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {
    int topic = Integer.parseInt(request.getParameter(CommandConst.TOPIC_ID));
    request.getSession().setAttribute(CommandConst.TOPIC_ID, topic);
    return ConfigurationManager.getProperty(CommandConst.TOPIC_PAGE);
  }
}
