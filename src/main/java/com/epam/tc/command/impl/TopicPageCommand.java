package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.VariableName;
import com.epam.tc.command.PageName;
import com.epam.tc.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * @author alex raby
 * @version 1.0
 * return to Topic page
 */
public class TopicPageCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {
    int topic = Integer.parseInt(request.getParameter(VariableName.TOPIC_ID));
    request.getSession().setAttribute(VariableName.TOPIC_ID, topic);
    return ConfigurationManager.getProperty(PageName.TOPIC_PAGE);
  }
}
