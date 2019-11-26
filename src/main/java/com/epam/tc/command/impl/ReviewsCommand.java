package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.PageName;
import com.epam.tc.command.CommandException;
import com.epam.tc.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Reviews command.
 *
 * @author alex raby
 * @version 1.0 return to reviews page
 */
public class ReviewsCommand implements Command {

  /** {@inheritDoc} */
  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    return ConfigurationManager.getProperty(PageName.REVIEWS);
  }
}
