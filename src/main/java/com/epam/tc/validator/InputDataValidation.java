package com.epam.tc.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputDataValidation {

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

  public boolean checkMoneyField(String sum) {
    Pattern pattern = Pattern.compile(RegEx.PATTERN_MONEY);
    Matcher matcher = pattern.matcher(sum);
    return matcher.matches();
  }

  public boolean checkCardNumber(String number) {
    Pattern pattern = Pattern.compile(RegEx.PATTERN_CARD_NUMBER);
    Matcher matcher = pattern.matcher(number);
    return matcher.matches();
  }

  public boolean checkSizeTextArea(String text, int minSize, int maxSize) {
    if (text.isEmpty()) {
      return false;
    }
    return text.length() <= maxSize && text.length() >= minSize;
  }

  public String deleteExcessiveSpace(String text) {
    text = text.replaceAll(RegEx.PATTERN_EXCESSIVE_SPACES, " ");
    return text;
  }

  public boolean checkGrade(int grade) {
    return grade >= 1 && grade <= 10;
  }

}
