package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.PageName;
import com.epam.tc.command.VariableName;
import com.epam.tc.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * @author alex raby
 * @version 1.0
 * change of locale in the cabinet page
 */
public class SetLocalCabinetCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {
    request.getSession().setAttribute(VariableName.LOCAL, request.getParameter(VariableName.LOCAL));
    return ConfigurationManager.getProperty(PageName.CABINET_PAGE);
  }
}