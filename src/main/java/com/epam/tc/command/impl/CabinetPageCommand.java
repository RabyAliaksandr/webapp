package com.epam.tc.command.impl;

import com.epam.tc.command.PageName;
import com.epam.tc.command.Command;
import com.epam.tc.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Cabinet page command.
 *
 * @author alex raby
 * @version 1.0 directs the user to his cabinet page
 */
public class CabinetPageCommand implements Command {

  /** {@inheritDoc} */
  @Override
  public String execute(HttpServletRequest request) {
    return ConfigurationManager.getProperty(PageName.CABINET_PAGE);
  }
}
