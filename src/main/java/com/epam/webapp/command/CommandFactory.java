package com.epam.webapp.command;

import com.epam.webapp.command.impl.EmptyCommand;
import com.epam.webapp.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class CommandFactory {

    public Command defineCommand(HttpServletRequest request) {
      Command current = new EmptyCommand();
      String action = request.getParameter("command");
      if (action == null || action.isEmpty()) {
        return current;
      }
      try {
        CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
        current = currentEnum.getCurrentCommand();
      } catch (IllegalArgumentException e) {
        request.setAttribute("wrongAction", action
                + MessageManager.getProperty("message.wrongaction"));
      }
      return current;
    }
  }