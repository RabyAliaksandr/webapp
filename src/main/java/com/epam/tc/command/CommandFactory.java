package com.epam.tc.command;

import com.epam.tc.command.impl.EmptyCommand;
import com.epam.tc.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class CommandFactory {

  public Command defineCommand(HttpServletRequest request) {
    Command current;
    String action = request.getParameter(RequestVariableName.COMMAND);
    if (action == null || action.isEmpty()) {
      return new EmptyCommand();
    }
    try {
      CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
      current = currentEnum.getCurrentCommand();
    } catch (IllegalArgumentException e) {
      current = new EmptyCommand();
      request.setAttribute(MessageName.WRONG_ACTION,
              action + " " + MessageManager.getProperty(MessageName.MESSAGE_WRONG_ACTION));
    }
    return current;
  }
}