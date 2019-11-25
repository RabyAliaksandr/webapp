package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.VariableName;
import com.epam.tc.command.PageName;
import com.epam.tc.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * @author alex raby
 * @version 1.0
 * return management page and set value variable RequestVariableName.TYPE_OPERATION value takes from request
 * {@link VariableName#TYPE_OPERATION}
 *
 */
public class ManagementPageCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {
    String typeOperation = request.getParameter(VariableName.TYPE_OPERATION);
    request.getSession().setAttribute(VariableName.TYPE_OPERATION, typeOperation);
    return ConfigurationManager.getProperty(PageName.MANAGEMENT_PAGE);
  }
}
