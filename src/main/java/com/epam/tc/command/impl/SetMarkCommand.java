package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.CommandException;
import com.epam.tc.command.PageName;
import com.epam.tc.command.VariableName;
import com.epam.tc.manager.ConfigurationManager;
import com.epam.tc.manager.MessageManager;
import com.epam.tc.service.ServiceException;
import com.epam.tc.service.ServiceFactory;
import com.epam.tc.command.MessageName;
import com.epam.tc.service.TaskService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Set mark command.
 *
 * @author alex raby
 * @version 1.0 grade Task for student
 */
public class SetMarkCommand implements Command {

  /**
   * class object Logger {@link Logger}
   * writes important events to a log file
   */
  private static final Logger logger = LogManager.getLogger(SetMarkCommand.class);

  /** {@inheritDoc} */
  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    int taskId = Integer.parseInt(request.getParameter(VariableName.TASK_ID));
    int studentId = Integer.parseInt(request.getParameter(VariableName.STUDENT_ID));
    int mark = Integer.parseInt(request.getParameter(VariableName.MARK));
    TaskService taskService = ServiceFactory.getTaskService();
    boolean done;
    try {
      taskService.gradeTask(studentId, taskId, mark);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException("Error access service", e);
    }
      request.getSession().setAttribute(MessageName.MESSAGE_ABOUT_CHANGES,
              MessageManager.getProperty(MessageName.MESSAGE_CHANGES_SAVED));
      return ConfigurationManager.getProperty(PageName.PAGE_TOPIC_FOR_STUDY);
  }
}
