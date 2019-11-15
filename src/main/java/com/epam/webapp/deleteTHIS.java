package com.epam.webapp;


import com.epam.webapp.connectionpool.ConnectionPoolException;
import com.epam.webapp.dao.impl.DataListDAOImpl;
import com.epam.webapp.dao.impl.UserDAOImpl;
import com.epam.webapp.entity.UserType;
import com.epam.webapp.service.TrainingsService;

public class deleteTHIS {

  public static void main(String[] args) throws ConnectionPoolException {
    TrainingsService trainingsService = new TrainingsService();
    boolean o = trainingsService.gradeTask(3, 23, 5);
    System.out.println(o);
  }
}
