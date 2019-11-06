package com.epam.webapp.tag;

import javax.servlet.jsp.tagext.TagSupport;

public class ButtonsTag extends TagSupport {

  @Override
  public int doStartTag() {
    return SKIP_BODY;
  }
  public int doEndTag(){
    return EVAL_PAGE;
  }
}
