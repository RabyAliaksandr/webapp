package com.epam.tc.command.impl;

import com.epam.tc.command.PageName;
import com.epam.tc.command.Command;
import com.epam.tc.command.CommandException;
import com.epam.tc.command.MessageName;
import com.epam.tc.command.VariableName;
import com.epam.tc.entity.Task;
import com.epam.tc.entity.Training;
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
 * The type Add task for training command.
 *
 * @author alex raby
 * @version 1.0 adding a Task {@link Task} for a Training {@link Training}
 */
public class AddTaskForTrainingCommand implements Command {

  /**
   * class object Logger {@link Logger}
   * writes important events to a log file
   */
  private static final Logger logger = LogManager.getLogger(AddTaskForTrainingCommand.class);

  /**
   * @see Command
   * @param request - object HttpServletRequest
   * @return
   * @throws CommandException
   */
  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    TaskService taskService = ServiceFactory.getTaskService();
    int trainingId = Integer.parseInt(request.getParameter(VariableName.TRAINING_ID));
    String taskName = request.getParameter(VariableName.TASK_NAME);
    String taskText = request.getParameter(VariableName.TASK_TEXT);
    InputDataValidation validation = new InputDataValidation();
    taskName = validation.stripXSS(taskName);
    taskText = validation.stripXSS(taskText);
    taskName = validation.deleteExcessiveSpace(taskName);
    taskText = validation.deleteExcessiveSpace(taskText);
    boolean checkName = validation.checkSizeTextArea(taskName, 5, 70);
    boolean checkText = validation.checkSizeTextArea(taskText, 50, 1000);
    if (!checkName) {
      request.getSession().setAttribute(VariableName.TEXT, taskText);
      request.getSession().setAttribute(VariableName.NAME, taskName);
      request.getSession().setAttribute(MessageName.MESSAGE_ABOUT_CHANGES,
              MessageManager.getProperty(MessageName.MESSAGE_TEXTAREA_NAME_SIZE));
      return ConfigurationManager.getProperty(PageName.CREATE_TEXT_PAGE);
    }
    if (!checkText) {
      request.getSession().setAttribute(VariableName.TEXT, taskText);
      request.getSession().setAttribute(VariableName.NAME, taskName);
      request.getSession().setAttribute(MessageName.MESSAGE_ABOUT_CHANGES,
              MessageManager.getProperty(MessageName.MESSAGE_TEXTAREA_SIZE));
      return ConfigurationManager.getProperty(PageName.CREATE_TEXT_PAGE);
    }
    try {
      taskService.addTaskForTraining(trainingId, taskName, taskText);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException("Error access service", e);
    }
    request.getSession().setAttribute(MessageName.MESSAGE_ABOUT_CHANGES,
            MessageManager.getProperty(MessageName.MESSAGE_CHANGES_SAVED));
    return ConfigurationManager.getProperty(PageName.TRAINING_PAGE);

  }
}
