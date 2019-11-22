package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.constant.ConstPage;
import com.epam.webapp.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class SetLocalCommand implements Command {
  @Override
  public String execute(HttpServletRequest request) {
    request.getSession().setAttribute("redirectTo", request.getParameter("redirectTo"));
    request.getSession().setAttribute("local", request.getParameter("local"));
    return ConfigurationManager.getProperty(ConstPage.MAIN_PAGE);
  }
}
