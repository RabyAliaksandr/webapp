package com.epam.tc.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;

public class SessionRequestContent {

  private HashMap<String, Object> requestAttributes;
  private HashMap<String, String[]> requestParameters;
  private HashMap<String, Object> sessionAttributes;

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

  public void insertAttributes(HttpServletRequest request) {

  }
}