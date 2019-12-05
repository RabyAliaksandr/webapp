package com.epam.tc.service;

/**
 * The type Service exception.
 *
 * @author alex raby
 * @version 1.0 this class extends an Exception and is its own exception for service package classes this
 * class overrides class Exception methods
 * @see Exception
 */
public class ServiceException extends Exception {

  /**
   * Instantiates a new Service exception.
   */
  public ServiceException() {
    super();
  }

  /**
   * Instantiates a new Service exception.
   *
   * @param message the message
   */
  public ServiceException(String message) {
    super(message);
  }

  /**
   * Instantiates a new Service exception.
   *
   * @param message the message
   * @param cause   the cause
   */
  public ServiceException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Instantiates a new Service exception.
   *
   * @param cause the cause
   */
  public ServiceException(Throwable cause) {
    super(cause);
  }
}
