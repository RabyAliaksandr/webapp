package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.constant.ConstPage;
import com.epam.webapp.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class LogOutCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {
    request.getSession().invalidate();
    return ConfigurationManager.getProperty(ConstPage.INDEX_PAGE);
  }
}