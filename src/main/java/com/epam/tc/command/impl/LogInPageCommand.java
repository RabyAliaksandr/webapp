package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.PageName;
import com.epam.tc.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Log in page command.
 *
 * @author alex raby
 * @version 1.0 return to Login page
 */
public class LogInPageCommand implements Command {

  /** {@inheritDoc} */
  @Override
  public String execute(HttpServletRequest request) {
    return ConfigurationManager.getProperty(PageName.LOGIN_PAGE);
  }
}
