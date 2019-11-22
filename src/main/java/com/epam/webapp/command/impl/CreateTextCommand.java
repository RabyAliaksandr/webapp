package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.constant.ConstName;
import com.epam.webapp.constant.ConstPage;
import com.epam.webapp.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class CreateTextCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {
    String typeOperation = request.getParameter(ConstName.TYPE_OPERATION);
    request.getSession().setAttribute(ConstName.TYPE_OPERATION, typeOperation);
    return ConfigurationManager.getProperty(ConstPage.CREATE_TEXT_PAGE);
  }
}
