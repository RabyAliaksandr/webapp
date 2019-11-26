package com.epam.tc.command;

import com.epam.tc.command.impl.EmptyCommand;
import com.epam.tc.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Command factory.
 *
 * @author alex raby
 * @version 1.0 implementation class design pattern Command class factory
 */
public class CommandFactory {

  /**
   * method returning implementing class depending on request
   * if this command does not exist returns EmptyCommand {@link EmptyCommand}
   * @param request - object HttpServletRequest {@link HttpServletRequest}
   * @return implementing class Command
   */
  public Command defineCommand(HttpServletRequest request) {
    Command current;
    String action = request.getParameter(VariableName.COMMAND);
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