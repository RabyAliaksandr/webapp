package com.epam.tc.service;

/**
 * @author alex raby
 * @version 1.0
 * this class extends an Exception and is its own exception for service package classes
 * this class overrides class Exception methods
 * @see Exception
 */
public class ServiceException extends Exception {

  public ServiceException() {
    super();
  }

  public ServiceException(String message) {
    super(message);
  }

  public ServiceException(String message, Throwable cause) {
    super(message, cause);
  }

  public ServiceException(Throwable cause) {
    super(cause);
  }
}
