package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandException;
import com.epam.webapp.constant.ConstMessage;
import com.epam.webapp.constant.ConstName;
import com.epam.webapp.constant.ConstPage;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.manager.MessageManager;
import com.epam.webapp.service.ServiceException;
import com.epam.webapp.service.TrainingService;
import com.epam.webapp.service.impl.TrainingsServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteTaskCommand implements Command {

  private static Logger logger = LogManager.getLogger(DeleteTaskCommand.class);


  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    int taskId = Integer.parseInt(request.getParameter(ConstName.TASK_ID));
    TrainingService trainingService = new TrainingsServiceImpl();
    try {
      trainingService.deleteTask(taskId);
      request.getSession().setAttribute(ConstMessage.MESSAGE_ABOUT_CHANGES,
              MessageManager.getProperty(ConstMessage.MESSAGE_CHANGES_SAVED));
    } catch (ServiceException e) {
      logger.error(e);
      throw new CommandException(e);
    }
    return ConfigurationManager.getProperty(ConstPage.TRAININGS_INFORMATION_PAGE);
  }
}
