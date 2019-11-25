package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.VariableName;
import com.epam.tc.command.PageName;
import com.epam.tc.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * @author alex raby
 * @version 1.0
 * change of locale
 */
public class SetLocalCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {
    request.getSession().setAttribute(VariableName.LOCAL, request.getParameter(VariableName.LOCAL));
    return ConfigurationManager.getProperty(PageName.MAIN_PAGE);
  }
}
