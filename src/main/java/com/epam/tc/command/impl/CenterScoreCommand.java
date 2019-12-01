package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.CommandException;
import com.epam.tc.command.PageName;
import com.epam.tc.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Center score command.
 */
public class CenterScoreCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    return ConfigurationManager.getProperty(PageName.CENTER_SCORE);
  }
}
