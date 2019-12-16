package com.epam.tc.filter;

import com.epam.tc.validator.InputDataValidation;
import com.epam.tc.validator.RegEx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.regex.Pattern;

/**
 * request processing class used in XssProtectionFilter {@link XssProtectionFilter}
 *
 * @author alex raby
 * @version 1.0
 */
public class XssRequestWrapper extends HttpServletRequestWrapper {

  /**
   * object InputDataValidation {@link InputDataValidation}
   */
  private InputDataValidation inputDataValidation;

  /**
   * class constructor
   *
   * @param request - object HttpServletRequest class field inputDataValidation {@link XssRequestWrapper#inputDataValidation}
   */
  XssRequestWrapper(HttpServletRequest request) {
    super(request);
    this.inputDataValidation = new InputDataValidation();
  }

  /**
   * override method
   *
   * @param parameter - parameter by which we obtain data from the request
   * @return filtered data
   */
  @Override
  public String[] getParameterValues(String parameter) {
    String[] values = super.getParameterValues(parameter);
    if (values == null) {
      return values;
    }
    int count = values.length;
    String[] encodedValues = new String[count];
    for (int i = 0; i < count; i++) {
      encodedValues[i] = inputDataValidation.stripXSS(values[i]);
    }
    return encodedValues;
  }

  /**
   * override method
   *
   * @param parameter - parameter by which get data from the request
   * @return filtered data
   */
  @Override
  public String getParameter(String parameter) {
    String value = super.getParameter(parameter);
    return stripXSS(value);
  }

  /**
   * override method
   *
   * @param name - by which get the header
   * @return string without xss
   */
  @Override
  public String getHeader(String name) {
    String value = super.getHeader(name);
    return stripXSS(value);
  }

  /**
   * Strip xss string.
   *
   * @param value String for to clear unwanted strings
   * @return cleared string
   */
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