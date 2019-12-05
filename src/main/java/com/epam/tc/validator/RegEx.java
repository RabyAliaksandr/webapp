package com.epam.tc.validator;

/**
 * The type Reg ex.
 */
public class RegEx {

  /**
   * The constant Regex for Email.
   */
  public static final String EMAIL_PATTERN = "^[-\\w.]{6,30}+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
  /**
   * The constant Regex for Name.
   */
  public static final String NAME_PATTERN = "^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$";
  /**
   * The constant Regex for Surname.
   */
  public static final String SURNAME_PATTERN = "^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$";
  /**
   * The constant Regex for Login.
   */
  public static final String LOGIN_PATTERN = "^([A-Z]?[a-z]{6,23})$";
  /**
   * The constant Regex for Password.
   */
  public static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9])\\S{8,15}";
  /**
   * The constant Regex for script.
   */
  public static final String PATTERN_SCRIPT = "<script>(.*?)</script>";
  /**
   * The constant Regex for src.
   */
  public static final String PATTERN_SRC_APOSTROPHE = "src[\r\n]*=[\r\n]*\\\'(.*?)\\\'";
  /**
   * The constant Regex for src.
   */
  public static final String PATTERN_SRC_QUOTATION_MARKS ="src[\r\n]*=[\r\n]*\\\"(.*?)\\\"";
  /**
   * The constant Regex for closing tag script.
   */
  public static final String PATTERN_CLOSE_SCRIPT ="</script>";
  /**
   * The constant Regex for tag script with some body.
   */
  public static final String PATTERN_SCRIPT_ANY ="<script(.*?)>";
  /**
   * The constant Regex for eval.
   */
  public static final String PATTERN_EVAL ="eval\\((.*?)\\)";
  /**
   * The constant Regex for expression.
   */
  public static final String PATTERN_EXPRESSION ="expression\\((.*?)\\)";
  /**
   * The constant Regex for javascript.
   */
  public static final String PATTERN_JAVASCRIPT ="javascript:";
  /**
   * The constant Regex for vb script.
   */
  public static final String PATTERN_VB_SCRIPT ="vbscript:";
  /**
   * The constant Regex for onload.
   */
  public static final String PATTERN_ONLOAD ="onload(.*?)=";
  /**
   * The constant Regex for price value.
   */
  public static final String PATTERN_MONEY = "(0\\.((0[1-9]{1})|([1-9]{1}([0-9]{1})?)))|(([1-9]+[0-9]*)(\\.([0-9]{1,2}))?)";
  /**
   * The constant Regex for credit card number.
   */
  public static final String PATTERN_CARD_NUMBER = "\\d{16}";
  /**
   * The constant Regex for many spaces.
   */
  public static final String PATTERN_EXCESSIVE_SPACES = "\\s+";
}
