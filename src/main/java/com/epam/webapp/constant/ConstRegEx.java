package com.epam.webapp.constant;

public class ConstRegEx {

  public static final String EMAIL_PATTERN = "^[-\\w.]{6,30}+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
  public static final String NAME_PATTERN = "^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$";
  public static final String SURNAME_PATTERN = "^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$";
  public static final String LOGIN_PATTERN = "^([A-Z]?[a-z]{6,23})$";
  public static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9])\\S{8,15}";
  public static final String PATTERN_1 = "<script>(.*?)</script>";
  public static final String PATTERN_2 = "src[\r\n]*=[\r\n]*\\\'(.*?)\\\'";
  public static final String PATTERN_3 ="src[\r\n]*=[\r\n]*\\\"(.*?)\\\"";
  public static final String PATTERN_4 ="</script>";
  public static final String PATTERN_5 ="<script(.*?)>";
  public static final String PATTERN_6 ="eval\\((.*?)\\)";
  public static final String PATTERN_7 ="expression\\((.*?)\\)";
  public static final String PATTERN_8 ="javascript:";
  public static final String PATTERN_9 ="vbscript:";
  public static final String PATTERN_10 ="onload(.*?)=";


}
