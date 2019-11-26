package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.PageName;
import com.epam.tc.command.VariableName;
import com.epam.tc.entity.Task;
import com.epam.tc.command.CommandException;
import com.epam.tc.command.MessageName;
import com.epam.tc.manager.ConfigurationManager;
import com.epam.tc.manager.MessageManager;
import com.epam.tc.service.ServiceException;
import com.epam.tc.service.ServiceFactory;
import com.epam.tc.service.TaskService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Delete task command.
 *
 * @author alex raby
 * @version 1.0 deleting Task {@link Task} by Task id
 */
public class DeleteTaskCommand implements Command {

  /**
   * class object Logger {@link Logger}
   * writes important events to a log file
   */
  private static Logger logger = LogManager.getLogger(DeleteTaskCommand.class);


  /** {@inheritDoc} */
  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    int taskId = Integer.parseInt(request.getParameter(VariableName.TASK_ID));
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
