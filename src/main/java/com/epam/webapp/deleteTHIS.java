package com.epam.webapp;


import com.epam.webapp.connectionpool.ConnectionPoolException;
import com.epam.webapp.connectionpool.DataBaseManager;
import com.epam.webapp.encoder.PassEncoder;
import com.epam.webapp.dao.DaoException;
import com.epam.webapp.dao.impl.UserDaoImpl;
import com.epam.webapp.entity.User;
import com.epam.webapp.service.TrainingService;
import com.epam.webapp.service.impl.TrainingsServiceImpl;
import com.epam.webapp.service.impl.UserServiceImpl;
import com.epam.webapp.validator.UserFieldValidation;
import com.google.protobuf.ServiceException;

import java.text.ParseException;

public class deleteTHIS {

  public static void main(String[] args) throws ConnectionPoolException, ParseException, ServiceException, com.epam.webapp.service.ServiceException, DaoException {
    UserServiceImpl p = new UserServiceImpl();
    TrainingService t = new TrainingsServiceImpl();
    User user = new User();
    System.out.println();
    UserFieldValidation uv = new UserFieldValidation();
    UserDaoImpl userDao = new UserDaoImpl();
    PassEncoder pe = new PassEncoder();
    DataBaseManager db = new DataBaseManager();
    t.giveFeedback("dsds");
  }
}
