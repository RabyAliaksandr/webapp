package com.epam.tc.command;

import javax.servlet.http.HttpServletRequest;

/**
 * The interface Command.
 *
 * @author alex raby
 * @version 1.0 interface design pattern implementation Command
 */
public interface Command {

  /**
   * method returning the page address
   *
   * @param request - object HttpServletRequest
   * @return page address
   * @throws CommandException native package exception {@link CommandException}
   */
  String execute(HttpServletRequest request) throws CommandException;
}
