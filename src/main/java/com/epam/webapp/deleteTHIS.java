package com.epam.webapp;


import com.epam.webapp.connectionpool.exception.ConnectionPoolException;
import com.epam.webapp.dao.impl.UserDAOImpl;
import com.epam.webapp.entity.Student;
import com.epam.webapp.entity.Training;
import com.epam.webapp.service.TrainingsService;
import com.epam.webapp.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class deleteTHIS {

  public static void main(String[] args) throws ConnectionPoolException {
    TrainingsService tr = new TrainingsService();
    tr.updateTrainingsInformation(1, "null it is not null");

  }
}
