package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.PageName;
import com.epam.tc.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Main page command.
 *
 * @author alex raby
 * @version 1.0 return to main page
 */
public class MainPageCommand implements Command {

  /** {@inheritDoc} */
  @Override
  public String execute(HttpServletRequest request) {
    return ConfigurationManager.getProperty(PageName.MAIN_PAGE);
  }
}
