package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.PageName;
import com.epam.tc.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class CabinetPageCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {
    return ConfigurationManager.getProperty(PageName.CABINET_PAGE);
  }
}
