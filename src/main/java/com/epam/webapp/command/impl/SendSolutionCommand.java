package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandException;
import com.epam.webapp.constant.ConstMessage;
import com.epam.webapp.constant.ConstName;
import com.epam.webapp.constant.ConstPage;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.manager.MessageManager;
import com.epam.webapp.service.impl.TrainingsServiceImpl;
import com.epam.webapp.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class SendSolutionCommand implements Command {

  private static final Logger logger = LogManager.getLogger(SendSolutionCommand.class);

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    TrainingsServiceImpl trainingsService = new TrainingsServiceImpl();
    int userId = Integer.parseInt(request.getParameter(ConstName.USER_ID));
    int taskId = Integer.parseInt(request.getParameter(ConstName.TASK_ID));
    String solution = request.getParameter(ConstName.SOLUTION);
    boolean done;
    try {
      done = trainingsService.sendSolution(userId, taskId, solution);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException("Error access service", e);
    }
    if (done) {
      request.getSession().setAttribute(ConstMessage.SEND_SOLUTION_MESSAGE,
              MessageManager.getProperty(ConstMessage.MESSAGE_SEND_SOLUTION));
      return ConfigurationManager.getProperty(ConstPage.TASK_PAGE);
    }
    request.getSession().setAttribute(ConstMessage.SEND_SOLUTION_MESSAGE,
            MessageManager.getProperty(ConstMessage.MESSAGE_SEND_SOLUTION_ERROR));
    return ConfigurationManager.getProperty(ConstPage.TASK_PAGE);
  }
}
