//package com.epam.webapp.filter;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
//public class LocalizationFilter implements Filter{
//
//private FilterConfig filterCo;
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        this.filterCo = filterConfig;
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//      HttpServletRequest request = (HttpServletRequest) servletRequest;
//      String a = request.getRequestURI();
//      request.getSession().setAttribute("local", request.getParameter("local"));
//      ServletContext context = filterCo.getServletContext();
//      RequestDispatcher requestDispatcher = context.getRequestDispatcher(a);
//      requestDispatcher
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
