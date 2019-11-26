package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.PageName;
import com.epam.tc.command.VariableName;
import com.epam.tc.command.CommandException;
import com.epam.tc.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Card management command.
 *
 * @author alex raby
 * @version 1.0 directs the user to payment card management page
 */
public class CardManagementCommand implements Command {

  /** {@inheritDoc} */
  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    request.getSession().setAttribute(VariableName.EDITOR, request.getParameter(VariableName.EDITOR));
    request.getSession().setAttribute(VariableName.CARD_ID, request.getParameter(VariableName.CARD_ID));
    return ConfigurationManager.getProperty(PageName.CARD_MANAGEMENT);
  }
}
