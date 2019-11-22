package com.epam.webapp.command;

import com.epam.webapp.command.impl.EmptyCommand;
import com.epam.webapp.constant.ConstMessage;
import com.epam.webapp.constant.ConstName;
import com.epam.webapp.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class CommandFactory {

  public Command defineCommand(HttpServletRequest request) {
    Command current;
    String action = request.getParameter(ConstName.COMMAND);
    if (action == null || action.isEmpty()) {
      return new EmptyCommand();
    }
    try {
      CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
      current = currentEnum.getCurrentCommand();
    } catch (IllegalArgumentException e) {
      current = new EmptyCommand();
      request.setAttribute(ConstMessage.WRONG_ACTION,
              action + MessageManager.getProperty(ConstMessage.MESSAGE_WRONG_ACTION));
    }
    return current;
  }
}