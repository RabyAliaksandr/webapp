package com.epam.tc.command.impl;

import com.epam.tc.command.PageName;
import com.epam.tc.command.Command;
import com.epam.tc.command.CommandException;
import com.epam.tc.command.MessageName;
import com.epam.tc.command.VariableName;
import com.epam.tc.entity.Task;
import com.epam.tc.manager.ConfigurationManager;
import com.epam.tc.manager.MessageManager;
import com.epam.tc.service.ServiceFactory;
import com.epam.tc.service.TaskService;
import com.epam.tc.service.ServiceException;
import com.epam.tc.validator.InputDataValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Update trainings task command.
 *
 * @author alex raby
 * @version 1.0 change fields Task {@link Task}, description, name data validation also takes place here with setting the description length range and name length
 */
public class UpdateTrainingsTaskCommand implements Command {

  /**
   * class object Logger {@link Logger}
   * writes important events to a log file
   */
  private static final Logger logger = LogManager.getLogger(UpdateTrainingsTaskCommand.class);

  /** {@inheritDoc} */
  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    TaskService taskService = ServiceFactory.getTaskService();
    int taskId = Integer.parseInt(request.getParameter(VariableName.TASK_ID));
    String taskName = request.getParameter(VariableName.TASK_NAME);
    String task = request.getParameter(VariableName.TASK);
    InputDataValidation validation = new InputDataValidation();
    taskName = validation.stripXSS(taskName);
    taskName = validation.deleteExcessiveSpace(taskName);
    task = validation.stripXSS(task);
    task = validation.deleteExcessiveSpace(task);
    boolean checkTask = validation.checkSizeTextArea(task, 50, 1000);
    boolean checkTaskName = validation.checkSizeTextArea(taskName, 5, 70);
    if (!checkTask) {
      request.getSession().setAttribute(VariableName.NAME, taskName);
      request.getSession().setAttribute(VariableName.INFORMATION, task);
      request.getSession().setAttribute(MessageName.MESSAGE_ABOUT_CHANGES,
              MessageManager.getProperty(MessageName.MESSAGE_TEXTAREA_SIZE));
      return ConfigurationManager.getProperty(PageName.CREATE_TEXT_PAGE);
    }
    if (!checkTaskName) {
      request.getSession().setAttribute(VariableName.NAME, taskName);
      request.getSession().setAttribute(VariableName.INFORMATION, task);
      request.getSession().setAttribute(MessageName.MESSAGE_ABOUT_CHANGES,
              MessageManager.getProperty(MessageName.MESSAGE_TEXTAREA_SIZE));
      return ConfigurationManager.getProperty(PageName.CREATE_TEXT_PAGE);
    }
    try {
      taskService.updateTask(taskId, taskName, task);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException("Error access service", e);
    }
    request.getSession().setAttribute(VariableName.REDIRECT_TO_PAGE, request.getParameter(VariableName.PAGE_NAME));
      request.getSession().setAttribute(MessageName.MESSAGE_ABOUT_CHANGES,
              MessageManager.getProperty(MessageName.MESSAGE_CHANGES_SAVED));
      return ConfigurationManager.getProperty(PageName.TASK_PAGE);
  }
}
