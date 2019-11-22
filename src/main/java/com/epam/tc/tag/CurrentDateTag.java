package com.epam.tc.tag;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.Date;

public class CurrentDateTag extends SimpleTagSupport {

  @Override
  public void doTag() throws IOException {
    final JspWriter out = getJspContext().getOut();
    out.println(new Date());
  }
}
