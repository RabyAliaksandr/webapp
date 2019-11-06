package com.epam.webapp;


import com.epam.webapp.connectionpool.exception.ConnectionPoolException;
import com.epam.webapp.entity.Student;
import com.epam.webapp.entity.Training;
import com.epam.webapp.service.TrainingsService;
import com.epam.webapp.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class deleteTHIS {

  public static void main(String[] args) throws ConnectionPoolException {
   TrainingsService ts = new TrainingsService();
    Map<String, String> states = ts.getTopicsForTraining(1);
    for(Map.Entry<String, String> item : states.entrySet()){

      System.out.println( item.getKey());
    }

  }
}
