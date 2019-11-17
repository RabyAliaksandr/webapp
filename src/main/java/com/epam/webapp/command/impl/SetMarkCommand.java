package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandConst;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.manager.MessageManager;
import com.epam.webapp.service.impl.TrainingsServiceImpl;
import com.epam.webapp.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class SetMarkCommand implements Command {


  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    int taskId = Integer.parseInt(request.getParameter(CommandConst.TASK_ID));
    int studentId = Integer.parseInt(request.getParameter(CommandConst.STUDENT_ID));
    int mark = Integer.parseInt(request.getParameter(CommandConst.MARK));
    TrainingsServiceImpl trainingsService = new TrainingsServiceImpl();
    boolean done;
    try {
      done = trainingsService.gradeTask(studentId, taskId, mark);
    } catch (ServiceException e) {
      throw new CommandException("Error access service", e);
    }
    if (done) {
      request.getSession().setAttribute(CommandConst.MESSAGE_ABOUT_CHANGES,
              MessageManager.getProperty(CommandConst.MESSAGE_CHANGES_SAVED));
      return ConfigurationManager.getProperty(CommandConst.PAGE_TOPIC_FOR_STUDY);
    }
    request.getSession().setAttribute(CommandConst.MESSAGE_ABOUT_CHANGES,
            MessageManager.getProperty(CommandConst.MESSAGE_CHANGES_ERROR));
    return ConfigurationManager.getProperty(CommandConst.PAGE_TOPIC_FOR_STUDY);
  }
}
