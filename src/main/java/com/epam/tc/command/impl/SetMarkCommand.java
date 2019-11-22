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

public class SetMarkCommand implements Command {

  private static final Logger logger = LogManager.getLogger(SetMarkCommand.class);

  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    int taskId = Integer.parseInt(request.getParameter(RequestVariableName.TASK_ID));
    int studentId = Integer.parseInt(request.getParameter(RequestVariableName.STUDENT_ID));
    int mark = Integer.parseInt(request.getParameter(RequestVariableName.MARK));
    TaskService taskService = ServiceFactory.getTaskService();
    boolean done;
    try {
      done = taskService.gradeTask(studentId, taskId, mark);
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException("Error access service", e);
    }
    if (done) {
      request.getSession().setAttribute(MessageName.MESSAGE_ABOUT_CHANGES,
              MessageManager.getProperty(MessageName.MESSAGE_CHANGES_SAVED));
      return ConfigurationManager.getProperty(PageName.PAGE_TOPIC_FOR_STUDY);
    }
    request.getSession().setAttribute(MessageName.MESSAGE_ABOUT_CHANGES,
            MessageManager.getProperty(MessageName.MESSAGE_CHANGES_ERROR));
    return ConfigurationManager.getProperty(PageName.PAGE_TOPIC_FOR_STUDY);
  }
}
