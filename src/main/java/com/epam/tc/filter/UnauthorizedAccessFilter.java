package com.epam.tc.filter;

import com.epam.tc.command.PageName;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * class implementing interface Filter {@link Filter} the class is used to protect against direct access to the jsp
 * pages urlPatterns - indicates which parameters for filter operation initParams -
 * indicates what will be installed after filtering
 *
 * @author alex raby
 * @version 1.0
 */
@WebFilter(urlPatterns = {"/jsp/*"},
        initParams = {@WebInitParam(name = PageName.INDEX_PATH_NAME, value = "/index.jsp")})
public class UnauthorizedAccessFilter implements Filter {

  /**
   * class field String indicates which page
   * the user will be directed to when trying to access pages directly
   */
  private String indexPath;

  /**
   * method initializes indexPath {@link UnauthorizedAccessFilter#indexPath}
   * @param fConfig - object FilterConfig {@link FilterConfig}
   */
  public void init(FilterConfig fConfig) {
    indexPath = fConfig.getInitParameter(PageName.INDEX_PATH_NAME);
  }

  /**
   * the method processes requests and,
   * if there are urlPatterns = {"/ jsp / *"} in them, sends it to the specified page
   * @param request - object ServletRequest {@link ServletResponse}
   * @param response - object ServletResponse {@link ServletResponse}
   * @param chain - object FilterChain {@link FilterConfig}
   * @throws IOException standard exception {@link IOException}
   * @throws ServletException standard exception {@link ServletException}
   */
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
          throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;
    httpResponse.sendRedirect(httpRequest.getContextPath() + indexPath);
    chain.doFilter(request, response);
  }

  /** {@inheritDoc} */
  public void destroy() {
  }
}
