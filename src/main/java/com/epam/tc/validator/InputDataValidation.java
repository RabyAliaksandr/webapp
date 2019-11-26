package com.epam.tc.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Input data validation.
 */
public class InputDataValidation {

  /**
   * Strip xss string.
   *
   * @param value the value
   * @return the string
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

  /**
   * Check money field boolean.
   *
   * @param sum the sum
   * @return the boolean
   */
  public boolean checkMoneyField(String sum) {
    Pattern pattern = Pattern.compile(RegEx.PATTERN_MONEY);
    Matcher matcher = pattern.matcher(sum);
    return matcher.matches();
  }

  /**
   * Check card number boolean.
   *
   * @param number the number
   * @return the boolean
   */
  public boolean checkCardNumber(String number) {
    Pattern pattern = Pattern.compile(RegEx.PATTERN_CARD_NUMBER);
    Matcher matcher = pattern.matcher(number);
    return matcher.matches();
  }

  /**
   * Check size text area boolean.
   *
   * @param text    the text
   * @param minSize the min size
   * @param maxSize the max size
   * @return the boolean
   */
  public boolean checkSizeTextArea(String text, int minSize, int maxSize) {
    if (text.isEmpty()) {
      return false;
    }
    return text.length() <= maxSize && text.length() >= minSize;
  }

  /**
   * Delete excessive space string.
   *
   * @param text the text
   * @return the string
   */
  public String deleteExcessiveSpace(String text) {
    text = text.replaceAll(RegEx.PATTERN_EXCESSIVE_SPACES, " ");
    return text;
  }

  /**
   * Check grade boolean.
   *
   * @param grade the grade
   * @return the boolean
   */
  public boolean checkGrade(int grade) {
    return grade >= 1 && grade <= 10;
  }

}
