package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.RequestVariableName;
import com.epam.tc.command.PageName;
import com.epam.tc.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class TopicPageCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {
    int topic = Integer.parseInt(request.getParameter(RequestVariableName.TOPIC_ID));
    request.getSession().setAttribute(RequestVariableName.TOPIC_ID, topic);
    return ConfigurationManager.getProperty(PageName.TOPIC_PAGE);
  }
}
