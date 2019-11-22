package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.constant.ConstName;
import com.epam.webapp.constant.ConstPage;
import com.epam.webapp.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class TopicPageCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {
    int topic = Integer.parseInt(request.getParameter(ConstName.TOPIC_ID));
    request.getSession().setAttribute(ConstName.TOPIC_ID, topic);
    return ConfigurationManager.getProperty(ConstPage.TOPIC_PAGE);
  }
}
