package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandException;
import com.epam.webapp.constant.ConstName;
import com.epam.webapp.constant.ConstPage;
import com.epam.webapp.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class CardManagementCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    request.getSession().setAttribute(ConstName.EDITOR, request.getParameter(ConstName.EDITOR));
    request.getSession().setAttribute(ConstName.CARD_ID, request.getParameter(ConstName.CARD_ID));
    System.out.println(request.getParameter(ConstName.CARD_ID) + " it is cardId");
      return ConfigurationManager.getProperty(ConstPage.CARD_MANAGEMENT);
  }
}
