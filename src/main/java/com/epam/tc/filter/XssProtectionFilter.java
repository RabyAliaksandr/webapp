package com.epam.tc.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * class for filtering attempts of XSS attacks
 *
 * @author alex raby
 * @version 1.0
 */
public class XssProtectionFilter implements Filter {


  /**
   * {@inheritDoc}
   */
  @Override
  public void init(FilterConfig filterConfig) {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void destroy() {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
          throws IOException, ServletException {
    chain.doFilter(new XssRequestWrapper((HttpServletRequest) request), response);
  }

}