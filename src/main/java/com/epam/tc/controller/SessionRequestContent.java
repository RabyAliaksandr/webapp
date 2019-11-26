package com.epam.tc.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * The type Session request content.
 */
public class SessionRequestContent {

  private HashMap<String, Object> requestAttributes;
  private HashMap<String, String[]> requestParameters;
  private HashMap<String, Object> sessionAttributes;

  /**
   * Extract values.
   *
   * @param request the request
   */
  public void extractValues(HttpServletRequest request) {
    Enumeration<String> attributeNames = request.getAttributeNames();
    while (attributeNames.hasMoreElements()) {
      String name = attributeNames.nextElement();
      requestAttributes.put(name, request.getParameter(name));
    }

    Enumeration<String> parameterNames = request.getParameterNames();
    while (parameterNames.hasMoreElements()) {
      String name = parameterNames.nextElement();
      requestParameters.put(name, request.getParameterValues(name));
    }

    Enumeration<String> sessionAttributeNames = request.getSession().getAttributeNames();
    while (sessionAttributeNames.hasMoreElements()) {
      String name = sessionAttributeNames.nextElement();
      sessionAttributes.put(name, request.getSession().getAttribute(name));
    }
  }

  /**
   * Insert attributes.
   *
   * @param request the request
   */
  public void insertAttributes(HttpServletRequest request) {

  }
}