package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.RequestVariableName;
import com.epam.tc.command.PageName;
import com.epam.tc.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class CreateTextCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {
    String typeOperation = request.getParameter(RequestVariableName.TYPE_OPERATION);
    request.getSession().setAttribute(RequestVariableName.TYPE_OPERATION, typeOperation);
    return ConfigurationManager.getProperty(PageName.CREATE_TEXT_PAGE);
  }
}
