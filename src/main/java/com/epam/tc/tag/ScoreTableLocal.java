package com.epam.tc.tag;

import java.util.Locale;
import java.util.ResourceBundle;

public class ScoreTableLocal {

  private Locale locale;
  private ResourceBundle resourceBundle;

  public ScoreTableLocal(String language) {
    locale = new Locale(language);
    resourceBundle = ResourceBundle.getBundle("../local", locale);
  }
  public String getLocalName(String key) {
    return resourceBundle.getString(key);
  }
}
