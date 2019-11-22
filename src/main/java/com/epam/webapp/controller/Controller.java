package com.epam.webapp.controller;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandFactory;
import com.epam.webapp.command.CommandException;
import com.epam.webapp.connectionpool.ConnectionPoolException;
import com.epam.webapp.constant.ConstMessage;
import com.epam.webapp.constant.ConstName;
import com.epam.webapp.constant.ConstPage;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.manager.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

import static com.epam.webapp.constant.ConstName.REDIRECT_TO;

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
      response.sendRedirect(ConfigurationManager.getProperty(ConstPage.ERROR_PAGE)); //  FIXME CHECKME
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    try {
      processRequest(request, response);
    } catch (CommandException e) {
      logger.error(e);
      response.sendRedirect(ConfigurationManager.getProperty(ConstPage.ERROR_PAGE));
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
        response.sendRedirect(ConstName.CONTROLLER + request.getHeader(ConstName.REFERER)
                .replace(request.getRequestURL(), ""));
        request.getSession().setAttribute(REDIRECT_TO, null);
      }
    } else {
      page = ConfigurationManager.getProperty(ConstPage.INDEX_PAGE);
      request.getSession().setAttribute(ConstName.NULL_PAGE,
              MessageManager.getProperty(ConstMessage.MESSAGE_NULL_PAGE));
      response.sendRedirect(request.getContextPath() + page);
    }
  }
}