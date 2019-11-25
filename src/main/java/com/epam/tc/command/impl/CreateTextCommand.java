package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.VariableName;
import com.epam.tc.command.PageName;
import com.epam.tc.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * @author alex raby
 * @version 1.0
 * return to page create or edit text
 */
public class CreateTextCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {
    String typeOperation = request.getParameter(VariableName.TYPE_OPERATION);
    request.getSession().setAttribute(VariableName.TYPE_OPERATION, typeOperation);
    return ConfigurationManager.getProperty(PageName.CREATE_TEXT_PAGE);
  }
}
