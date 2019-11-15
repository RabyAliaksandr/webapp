package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.connectionpool.ConnectionPoolException;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.manager.MessageManager;
import com.epam.webapp.service.TrainingsService;

import javax.servlet.http.HttpServletRequest;

public class SendSolutionCommand implements Command {

  private static final String USER_ID = "userId";
  private static final String TASK_ID = "taskId";
  private static final String SOLUTION = "solution";
  private static final String TASK_PAGE = "path.page.taskPage";
  private static final String SEND_SOLUTION_MESSAGE = "sendSolutionMessage";
  private static final String MESSAGE_SEND_SOLUTION = "message.sendSolution";
  private static final String MESSAGE_SEND_SOLUTION_ERROR = "message.sendSolutionError";



  @Override
  public String execute(HttpServletRequest request) throws CommandException, ConnectionPoolException {
    TrainingsService trainingsService = new TrainingsService();
    int userId = Integer.parseInt(request.getParameter(USER_ID));
    int taskId = Integer.parseInt(request.getParameter(TASK_ID));
    String solution = request.getParameter(SOLUTION);
    boolean done = trainingsService.sendSolution(userId, taskId, solution);
    if (done) {
      request.getSession().setAttribute(SEND_SOLUTION_MESSAGE, MessageManager.getProperty(MESSAGE_SEND_SOLUTION));
      return ConfigurationManager.getProperty(TASK_PAGE);
    }
    request.getSession().setAttribute(SEND_SOLUTION_MESSAGE, MessageManager.getProperty(MESSAGE_SEND_SOLUTION_ERROR));
    return ConfigurationManager.getProperty(TASK_PAGE);
  }
}
