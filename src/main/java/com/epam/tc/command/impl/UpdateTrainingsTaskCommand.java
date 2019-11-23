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
import com.epam.tc.validator.InputDataValidation;
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
    InputDataValidation validation = new InputDataValidation();
    taskName = validation.stripXSS(taskName);
    taskName = validation.deleteExcessiveSpace(taskName);
    task = validation.stripXSS(task);
    task = validation.deleteExcessiveSpace(task);
    boolean checkTask = validation.checkSizeTextArea(task, 50, 1000);
    boolean checkTaskName = validation.checkSizeTextArea(taskName, 5, 70);
    if (!checkTask) {
      request.getSession().setAttribute(RequestVariableName.NAME, taskName);
      request.getSession().setAttribute(RequestVariableName.INFORMATION, task);
      request.getSession().setAttribute(MessageName.MESSAGE_ABOUT_CHANGES,
              MessageManager.getProperty(MessageName.MESSAGE_TEXTAREA_SIZE));
      return ConfigurationManager.getProperty(PageName.CREATE_TEXT_PAGE);
    }
    if (!checkTaskName) {
      request.getSession().setAttribute(RequestVariableName.NAME, taskName);
      request.getSession().setAttribute(RequestVariableName.INFORMATION, task);
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
      request.getSession().setAttribute(MessageName.MESSAGE_ABOUT_CHANGES,
              MessageManager.getProperty(MessageName.MESSAGE_CHANGES_SAVED));
      return ConfigurationManager.getProperty(PageName.TASK_PAGE);
  }
}
