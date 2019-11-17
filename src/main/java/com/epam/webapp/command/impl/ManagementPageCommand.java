package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandConst;
import com.epam.webapp.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class ManagementPageCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {
    String typeOperation = request.getParameter(CommandConst.TYPE_OPERATION);
    request.getSession().setAttribute(CommandConst.TYPE_OPERATION, typeOperation);
    return ConfigurationManager.getProperty(CommandConst.MANAGEMENT_PAGE);
  }
}
