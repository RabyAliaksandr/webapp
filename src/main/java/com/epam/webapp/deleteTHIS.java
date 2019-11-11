package com.epam.webapp;


import com.epam.webapp.connectionpool.exception.ConnectionPoolException;
import com.epam.webapp.dao.impl.DataListDAOImpl;
import com.epam.webapp.dao.impl.UserDAOImpl;
import com.epam.webapp.entity.*;
import com.epam.webapp.service.TrainingsService;
import com.epam.webapp.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class deleteTHIS {

  public static void main(String[] args) throws ConnectionPoolException {
    DataListDAOImpl dataListDAO = new DataListDAOImpl();
    System.out.println(dataListDAO.getTrainingForMentor(1));

  }
}
