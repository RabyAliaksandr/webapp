package com.epam.tc.controller;

import com.epam.tc.connectionpool.ConnectionPool;
import com.epam.tc.connectionpool.ConnectionPoolException;
import com.epam.tc.manager.ConfigurationManager;
import com.epam.tc.manager.MessageManager;
import com.epam.tc.command.Command;
import com.epam.tc.command.CommandFactory;
import com.epam.tc.command.CommandException;
import com.epam.tc.command.MessageName;
import com.epam.tc.command.VariableName;
import com.epam.tc.command.PageName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Controller.
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {
  private static final Logger logger = LogManager.getLogger(Controller.class);

  /**
   * {@inheritDoc}
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    try {
      processRequest(request, response);
    } catch (CommandException e) {
      logger.error(e);
      response.sendRedirect(ConfigurationManager.getProperty(PageName.ERROR_PAGE));
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    try {
      processRequest(request, response);
    } catch (CommandException e) {
      logger.error(e);
      response.sendRedirect(ConfigurationManager.getProperty(PageName.ERROR_PAGE));
    }
  }

  private void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException, CommandException {
    String page;
    CommandFactory client = new CommandFactory();
    Command command = client.defineCommand(request);
    page = command.execute(request);
    if (page != null) {
      if (request.getParameter(VariableName.REDIRECT_TO) == null && request.getSession().getAttribute(VariableName.REDIRECT_TO_PAGE) == null) {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
      }
      if (request.getParameter(VariableName.REDIRECT_TO) != null) {
        response.sendRedirect(VariableName.CONTROLLER + request.getHeader(VariableName.REFERER)
                .replace(request.getRequestURL(), ""));
        request.getSession().setAttribute(VariableName.REDIRECT_TO, null);
      }
      if (request.getSession().getAttribute(VariableName.REDIRECT_TO_PAGE) != null) {
        response.sendRedirect(request.getContextPath() + VariableName.CONTROLLER_COMMAND +
                request.getSession().getAttribute(VariableName.REDIRECT_TO_PAGE));
        request.getSession().setAttribute((VariableName.REDIRECT_TO_PAGE), null);
      }
    } else {
      page = ConfigurationManager.getProperty(PageName.INDEX_PAGE);
      request.getSession().setAttribute(VariableName.NULL_PAGE,
              MessageManager.getProperty(MessageName.MESSAGE_NULL_PAGE));
      response.sendRedirect(request.getContextPath() + page);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void destroy() {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    try {
      connectionPool.dispose();
    } catch (ConnectionPoolException e) {
      logger.error(e);
    }
  }
}