package com.epam.tc.controller;

import com.epam.tc.command.Command;
import com.epam.tc.command.CommandFactory;
import com.epam.tc.command.CommandException;
import com.epam.tc.command.MessageName;
import com.epam.tc.command.RequestVariableName;
import com.epam.tc.command.PageName;
import com.epam.tc.manager.ConfigurationManager;
import com.epam.tc.manager.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.tc.command.RequestVariableName.REDIRECT_TO;

@WebServlet("/controller")
public class Controller extends HttpServlet {
  private static final Logger logger = LogManager.getLogger(Controller.class);

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    try {
      processRequest(request, response);
    } catch (CommandException e) {
      logger.error(e);
      response.sendRedirect(ConfigurationManager.getProperty(PageName.ERROR_PAGE)); //  FIXME CHECKME
    }
  }

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
      if (request.getParameter(REDIRECT_TO) == null) {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
      } else {
        request.getSession().setAttribute("local", request.getParameter("local"));
        response.sendRedirect(RequestVariableName.CONTROLLER + request.getHeader(RequestVariableName.REFERER)
                .replace(request.getRequestURL(), ""));
        request.getSession().setAttribute(REDIRECT_TO, null);
      }
    } else {
      page = ConfigurationManager.getProperty(PageName.INDEX_PAGE);
      request.getSession().setAttribute(RequestVariableName.NULL_PAGE,
              MessageManager.getProperty(MessageName.MESSAGE_NULL_PAGE));
      response.sendRedirect(request.getContextPath() + page);
    }
  }
}