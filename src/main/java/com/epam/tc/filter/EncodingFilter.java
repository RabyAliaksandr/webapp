package com.epam.tc.filter;


import javax.servlet.*;
import java.io.IOException;

/**
 * @author alex raby
 * @version 1.0
 * class implementing interface Filter {@link Filter}
 * sets the encoding of the request and response
 */
public class EncodingFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) {
  }

  /**
   * sets the encoding of the request and response
   * @param servletRequest - object ServletRequest
   * @param servletResponse - object ServletResponse
   * @param filterChain - object FilterChain
   * @throws IOException standard exception {@link IOException}
   * @throws ServletException standard exception {@link ServletException}
   */
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    servletRequest.setCharacterEncoding("UTF-8");
    servletResponse.setCharacterEncoding("UTF-8");
    filterChain.doFilter(servletRequest, servletResponse);
  }

  @Override
  public void destroy() {
  }
}
