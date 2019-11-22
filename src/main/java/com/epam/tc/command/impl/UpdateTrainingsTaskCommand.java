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

public class UpdateTrainingsTaskCommand implements Command {

  private static final Logger logger = LogManager.getLogger(UpdateTrainingsTaskCommand.class);

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    TaskService taskService = ServiceFactory.getTaskService();
    int taskId = Integer.parseInt(request.getParameter(RequestVariableName.TASK_ID));
    String taskName = request.getParameter(RequestVariableName.TASK_NAME);
    String task = request.getParameter(RequestVariableName.TASK);
    boolean done;
    try {
      done = taskService.updateTask(taskId, taskName, task);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException("Error access service", e);
    }
    if (done) {
      request.getSession().setAttribute(MessageName.MESSAGE_ABOUT_CHANGES,
              MessageManager.getProperty(MessageName.MESSAGE_CHANGES_SAVED));
      return ConfigurationManager.getProperty(PageName.TASK_PAGE);
    }
    request.getSession().setAttribute(MessageName.MESSAGE_ABOUT_CHANGES,
            MessageManager.getProperty(MessageName.MESSAGE_CHANGES_ERROR));
    return ConfigurationManager.getProperty(PageName.TASK_PAGE);
  }
}
