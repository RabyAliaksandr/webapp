package com.epam.webapp.filter;

import com.epam.webapp.constant.ConstRegEx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.regex.Pattern;

public class XssRequestWrapper extends HttpServletRequestWrapper {

  public XssRequestWrapper(HttpServletRequest request) {
    super(request);
  }

  @Override
  public String[] getParameterValues(String parameter) {
    String[] values = super.getParameterValues(parameter);

    if (values == null) {
      return null;
    }

    int count = values.length;
    String[] encodedValues = new String[count];
    for (int i = 0; i < count; i++) {
      encodedValues[i] = stripXSS(values[i]);
    }

    return encodedValues;
  }

  @Override
  public String getParameter(String parameter) {
    String value = super.getParameter(parameter);

    return stripXSS(value);
  }

  @Override
  public String getHeader(String name) {
    String value = super.getHeader(name);
    return stripXSS(value);
  }

  private String stripXSS(String value) {
    if (value != null) {
      value = value.replaceAll("", "");
      Pattern scriptPattern = Pattern.compile(ConstRegEx.PATTERN_1, Pattern.CASE_INSENSITIVE);
      value = scriptPattern.matcher(value).replaceAll("");
      scriptPattern = Pattern.compile(ConstRegEx.PATTERN_2, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
      value = scriptPattern.matcher(value).replaceAll("");
      scriptPattern = Pattern.compile(ConstRegEx.PATTERN_3, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
      value = scriptPattern.matcher(value).replaceAll("");
      scriptPattern = Pattern.compile(ConstRegEx.PATTERN_4, Pattern.CASE_INSENSITIVE);
      value = scriptPattern.matcher(value).replaceAll("");
      scriptPattern = Pattern.compile(ConstRegEx.PATTERN_5, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
      value = scriptPattern.matcher(value).replaceAll("");
      scriptPattern = Pattern.compile(ConstRegEx.PATTERN_6, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
      value = scriptPattern.matcher(value).replaceAll("");
      scriptPattern = Pattern.compile(ConstRegEx.PATTERN_7, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
      value = scriptPattern.matcher(value).replaceAll("");
      scriptPattern = Pattern.compile(ConstRegEx.PATTERN_8, Pattern.CASE_INSENSITIVE);
      value = scriptPattern.matcher(value).replaceAll("");
      scriptPattern = Pattern.compile(ConstRegEx.PATTERN_9, Pattern.CASE_INSENSITIVE);
      value = scriptPattern.matcher(value).replaceAll("");
      scriptPattern = Pattern.compile(ConstRegEx.PATTERN_10, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
      value = scriptPattern.matcher(value).replaceAll("");
    }
    return value;
  }
}