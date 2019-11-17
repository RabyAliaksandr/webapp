package com.epam.webapp;


import com.epam.webapp.connectionpool.exception.ConnectionPoolException;
import com.epam.webapp.entity.User;
import com.epam.webapp.service.impl.UserServiceImpl;
import com.google.protobuf.ServiceException;

import java.text.ParseException;
import java.util.List;

public class deleteTHIS {

  public static void main(String[] args) throws ConnectionPoolException, ParseException, ServiceException {


    UserServiceImpl i = new UserServiceImpl();
    List<User> d = i.getAllUser();
  }
}
