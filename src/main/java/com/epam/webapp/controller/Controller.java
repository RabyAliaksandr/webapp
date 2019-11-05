package com.epam.webapp.controller;
import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandFactory;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.manager.MessageManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)

          throws ServletException, IOException {
    try {
      processRequest(request, response);
    } catch (CommandException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    try {
      processRequest(request, response);
    } catch (CommandException e) {
      e.printStackTrace();
    }
  }

  private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
    String page;
    CommandFactory client = new CommandFactory();
    Command command = client.defineCommand(request);
    page = command.execute(request);
    if (page != null) {
      RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
      dispatcher.forward(request, response);
    } else {
      page = ConfigurationManager.getProperty("path.page.index");
      request.getSession().setAttribute("nullPage",
              MessageManager.getProperty("message.nullpage"));
      response.sendRedirect(request.getContextPath() + page);
    }
  }
}