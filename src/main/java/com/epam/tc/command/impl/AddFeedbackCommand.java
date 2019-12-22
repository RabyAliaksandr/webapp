package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.CommandException;
import com.epam.tc.command.PageName;
import com.epam.tc.command.VariableName;
import com.epam.tc.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class AddFeedbackCommand implements Command {
  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    request.getSession().setAttribute(VariableName.TYPE_OPERATION, VariableName.FEEDBACK);
    return ConfigurationManager.getProperty(PageName.CREATE_TEXT_PAGE);
  }
}
