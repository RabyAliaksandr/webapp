package com.epam.tc.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author alex raby
 * @version 1.0
 * class for filtering attempts of XSS attacks
 */
public class XssProtectionFilter implements Filter {


  /**
   * overloaded interface method
   * @param filterConfig - object Filter {@link Filter}
   */
  @Override
  public void init(FilterConfig filterConfig)  {
  }

  /**
   * overloaded interface method
   */
  @Override
  public void destroy() {
  }

  /**
   * method calls a class object to filter request and response
   * @param request - object ServletRequest {@link ServletRequest}
   * @param response - object ServletResponse {@link ServletResponse}
   * @param chain - object FilterChain {@link FilterChain}
   * @throws IOException standard exception {@link IOException}
   * @throws ServletException standard exception {@link ServletException}
   */
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
          throws IOException, ServletException {
    chain.doFilter(new XssRequestWrapper((HttpServletRequest) request), response);
  }

}