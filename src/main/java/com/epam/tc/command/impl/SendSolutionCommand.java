package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.CommandException;
import com.epam.tc.command.MessageName;
import com.epam.tc.command.RequestVariableName;
import com.epam.tc.command.PageName;
import com.epam.tc.manager.ConfigurationManager;
import com.epam.tc.manager.MessageManager;
import com.epam.tc.service.ServiceFactory;
import com.epam.tc.service.TaskService;
import com.epam.tc.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class SendSolutionCommand implements Command {

  private static final Logger logger = LogManager.getLogger(SendSolutionCommand.class);

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    TaskService taskService = ServiceFactory.getTaskService();
    int userId = Integer.parseInt(request.getParameter(RequestVariableName.USER_ID));
    int taskId = Integer.parseInt(request.getParameter(RequestVariableName.TASK_ID));
    String solution = request.getParameter(RequestVariableName.SOLUTION);
    boolean done;
    try {
      done = taskService.sendSolution(userId, taskId, solution);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException("Error access service", e);
    }
    if (done) {
      request.getSession().setAttribute(MessageName.SEND_SOLUTION_MESSAGE,
              MessageManager.getProperty(MessageName.MESSAGE_SEND_SOLUTION));
      return ConfigurationManager.getProperty(PageName.TASK_PAGE);
    }
    request.getSession().setAttribute(MessageName.SEND_SOLUTION_MESSAGE,
            MessageManager.getProperty(MessageName.MESSAGE_SEND_SOLUTION_ERROR));
    return ConfigurationManager.getProperty(PageName.TASK_PAGE);
  }
}
