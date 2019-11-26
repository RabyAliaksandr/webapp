package com.epam.tc.validator;

/**
 * The type Reg ex.
 */
public class RegEx {

  /**
   * The constant EMAIL_PATTERN.
   */
  public static final String EMAIL_PATTERN = "^[-\\w.]{6,30}+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
  /**
   * The constant NAME_PATTERN.
   */
  public static final String NAME_PATTERN = "^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$";
  /**
   * The constant SURNAME_PATTERN.
   */
  public static final String SURNAME_PATTERN = "^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$";
  /**
   * The constant LOGIN_PATTERN.
   */
  public static final String LOGIN_PATTERN = "^([A-Z]?[a-z]{6,23})$";
  /**
   * The constant PASSWORD_PATTERN.
   */
  public static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9])\\S{8,15}";
  /**
   * The constant PATTERN_SCRIPT.
   */
  public static final String PATTERN_SCRIPT = "<script>(.*?)</script>";
  /**
   * The constant PATTERN_SRC_APOSTROPHE.
   */
  public static final String PATTERN_SRC_APOSTROPHE = "src[\r\n]*=[\r\n]*\\\'(.*?)\\\'";
  /**
   * The constant PATTERN_SRC_QUOTATION_MARKS.
   */
  public static final String PATTERN_SRC_QUOTATION_MARKS ="src[\r\n]*=[\r\n]*\\\"(.*?)\\\"";
  /**
   * The constant PATTERN_CLOSE_SCRIPT.
   */
  public static final String PATTERN_CLOSE_SCRIPT ="</script>";
  /**
   * The constant PATTERN_SCRIPT_ANY.
   */
  public static final String PATTERN_SCRIPT_ANY ="<script(.*?)>";
  /**
   * The constant PATTERN_EVAL.
   */
  public static final String PATTERN_EVAL ="eval\\((.*?)\\)";
  /**
   * The constant PATTERN_EXPRESSION.
   */
  public static final String PATTERN_EXPRESSION ="expression\\((.*?)\\)";
  /**
   * The constant PATTERN_JAVASCRIPT.
   */
  public static final String PATTERN_JAVASCRIPT ="javascript:";
  /**
   * The constant PATTERN_VB_SCRIPT.
   */
  public static final String PATTERN_VB_SCRIPT ="vbscript:";
  /**
   * The constant PATTERN_ONLOAD.
   */
  public static final String PATTERN_ONLOAD ="onload(.*?)=";
  /**
   * The constant PATTERN_MONEY.
   */
  public static final String PATTERN_MONEY = "(0\\.((0[1-9]{1})|([1-9]{1}([0-9]{1})?)))|(([1-9]+[0-9]*)(\\.([0-9]{1,2}))?)";
  /**
   * The constant PATTERN_CARD_NUMBER.
   */
  public static final String PATTERN_CARD_NUMBER = "\\d{16}";
  /**
   * The constant PATTERN_EXCESSIVE_SPACES.
   */
  public static final String PATTERN_EXCESSIVE_SPACES = "\\s+";
}
