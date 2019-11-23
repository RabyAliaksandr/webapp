package com.epam.tc.filter;

import com.epam.tc.validator.InputDataValidation;
import com.epam.tc.validator.RegEx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.regex.Pattern;

public class XssRequestWrapper extends HttpServletRequestWrapper {


  public XssRequestWrapper(HttpServletRequest request) {
    super(request);
  }

  InputDataValidation inputDataValidation;
  @Override
  public String[] getParameterValues(String parameter) {
    String[] values = super.getParameterValues(parameter);
    this.inputDataValidation = new InputDataValidation();

    if (values == null) {
      return null;
    }

    int count = values.length;
    String[] encodedValues = new String[count];
    for (int i = 0; i < count; i++) {
      encodedValues[i] = inputDataValidation.stripXSS(values[i]);
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

  public String stripXSS(String value) {
    if (value != null) {
      Pattern scriptPattern = Pattern.compile(RegEx.PATTERN_SCRIPT, Pattern.CASE_INSENSITIVE);
      value = scriptPattern.matcher(value).replaceAll("");
      scriptPattern = Pattern.compile(RegEx.PATTERN_SRC_APOSTROPHE, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
      value = scriptPattern.matcher(value).replaceAll("");
      scriptPattern = Pattern.compile(RegEx.PATTERN_SRC_QUOTATION_MARKS, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
      value = scriptPattern.matcher(value).replaceAll("");
      scriptPattern = Pattern.compile(RegEx.PATTERN_CLOSE_SCRIPT, Pattern.CASE_INSENSITIVE);
      value = scriptPattern.matcher(value).replaceAll("");
      scriptPattern = Pattern.compile(RegEx.PATTERN_SCRIPT_ANY, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
      value = scriptPattern.matcher(value).replaceAll("");
      scriptPattern = Pattern.compile(RegEx.PATTERN_EVAL, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
      value = scriptPattern.matcher(value).replaceAll("");
      scriptPattern = Pattern.compile(RegEx.PATTERN_EXPRESSION, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
      value = scriptPattern.matcher(value).replaceAll("");
      scriptPattern = Pattern.compile(RegEx.PATTERN_JAVASCRIPT, Pattern.CASE_INSENSITIVE);
      value = scriptPattern.matcher(value).replaceAll("");
      scriptPattern = Pattern.compile(RegEx.PATTERN_VB_SCRIPT, Pattern.CASE_INSENSITIVE);
      value = scriptPattern.matcher(value).replaceAll("");
      scriptPattern = Pattern.compile(RegEx.PATTERN_ONLOAD, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
      value = scriptPattern.matcher(value).replaceAll("");
    }
    return value;
  }
}