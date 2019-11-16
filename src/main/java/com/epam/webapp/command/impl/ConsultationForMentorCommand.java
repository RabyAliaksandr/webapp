package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.connectionpool.ConnectionPoolException;
import com.epam.webapp.manager.ConfigurationManager;
import com.epam.webapp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public class ConsultationForMentorCommand implements Command {


  @Override
  public String execute(HttpServletRequest request) throws CommandException, ConnectionPoolException, ParseException {
    UserService userService = new UserService();
    System.out.println(ConfigurationManager.getProperty("path.page.consultationForMentor"));

  return ConfigurationManager.getProperty("path.page.consultationForMentor");
  }
}
