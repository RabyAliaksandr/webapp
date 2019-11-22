package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.CommandException;
import com.epam.tc.command.MessageName;
import com.epam.tc.command.RequestVariableName;
import com.epam.tc.command.PageName;
import com.epam.tc.manager.ConfigurationManager;
import com.epam.tc.manager.MessageManager;
import com.epam.tc.service.ServiceException;
import com.epam.tc.service.ServiceFactory;
import com.epam.tc.service.TaskService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteTaskCommand implements Command {

  private static Logger logger = LogManager.getLogger(DeleteTaskCommand.class);


  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    int taskId = Integer.parseInt(request.getParameter(RequestVariableName.TASK_ID));
    TaskService taskService = ServiceFactory.getTaskService();
    try {
      taskService.deleteTask(taskId);
      request.getSession().setAttribute(MessageName.MESSAGE_ABOUT_CHANGES,
              MessageManager.getProperty(MessageName.MESSAGE_CHANGES_SAVED));
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException(e);
    }
    return ConfigurationManager.getProperty(PageName.TRAININGS_INFORMATION_PAGE);
  }
}
