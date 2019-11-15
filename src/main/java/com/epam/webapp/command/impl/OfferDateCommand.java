package com.epam.webapp.command.impl;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.exception.CommandException;
import com.epam.webapp.connectionpool.ConnectionPoolException;
import com.epam.webapp.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OfferDateCommand implements Command {
  @Override
  public String execute(HttpServletRequest request) throws CommandException, ConnectionPoolException, ParseException {
    String temp = request.getParameter("date");
    System.out.println(temp);
    SimpleDateFormat  simpleDateFormat = new SimpleDateFormat("mm/dd/yyyy");
    Date date = simpleDateFormat.parse(temp);
    System.out.println(date);
    return ConfigurationManager.getProperty("");
  }
}
