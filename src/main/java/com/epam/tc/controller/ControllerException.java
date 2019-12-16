package com.epam.tc.controller;

/**
 * Controller Exception extends Exception {@link Exception}
 *
 * @author alex raby
 * @version 1.0
 */
public class ControllerException extends Exception {

  /**
   * Instantiates a new Controller exception.
   */
  public ControllerException() {
    super();
  }

  /**
   * Instantiates a new Controller exception.
   *
   * @param message the message
   */
  public ControllerException(String message) {
    super(message);
  }

  /**
   * Instantiates a new Controller exception.
   *
   * @param message the message
   * @param cause   the cause
   */
  public ControllerException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Instantiates a new Controller exception.
   *
   * @param cause the cause
   */
  public ControllerException(Throwable cause) {
    super(cause);
  }
}
