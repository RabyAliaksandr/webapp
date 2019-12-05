package com.epam.tc.filter;


import javax.servlet.*;
import java.io.IOException;

/**
 * The type Encoding filter.
 * class implementing interface Filter {@link Filter} sets the encoding of the request and response
 * @author alex raby
 * @version 1.0
 */
public class EncodingFilter implements Filter {

  /** {@inheritDoc} */
  @Override
  public void init(FilterConfig filterConfig) {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    servletRequest.setCharacterEncoding("UTF-8");
    servletResponse.setCharacterEncoding("UTF-8");
    filterChain.doFilter(servletRequest, servletResponse);
  }

  /** {@inheritDoc} */
  @Override
  public void destroy() {
  }
}
