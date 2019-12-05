package com.epam.tc.tag;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.Date;

/**
 * @author alex raby
 * @version 1.0
 * The type Current date custom tag.
 */
public class CurrentDateTag extends SimpleTagSupport {

  /** {@inheritDoc} */
  @Override
  public void doTag() throws IOException {
    final JspWriter out = getJspContext().getOut();
    out.println(new Date());
  }
}
