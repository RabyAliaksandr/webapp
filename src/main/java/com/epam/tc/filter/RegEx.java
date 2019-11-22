package com.epam.tc.filter;

public class RegEx {

  public static final String EMAIL_PATTERN = "^[-\\w.]{6,30}+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
  public static final String NAME_PATTERN = "^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$";
  public static final String SURNAME_PATTERN = "^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$";
  public static final String LOGIN_PATTERN = "^([A-Z]?[a-z]{6,23})$";
  public static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9])\\S{8,15}";
  public static final String PATTERN_SCRIPT = "<script>(.*?)</script>";
  public static final String PATTERN_SRC_APOSTROPHE = "src[\r\n]*=[\r\n]*\\\'(.*?)\\\'";
  public static final String PATTERN_SRC_QUOTATION_MARKS ="src[\r\n]*=[\r\n]*\\\"(.*?)\\\"";
  public static final String PATTERN_CLOSE_SCRIPT ="</script>";
  public static final String PATTERN_SCRIPT_ANY ="<script(.*?)>";
  public static final String PATTERN_EVAL ="eval\\((.*?)\\)";
  public static final String PATTERN_EXPRESSION ="expression\\((.*?)\\)";
  public static final String PATTERN_JAVASCRIPT ="javascript:";
  public static final String PATTERN_VB_SCRIPT ="vbscript:";
  public static final String PATTERN_ONLOAD ="onload(.*?)=";


}
