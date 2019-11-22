package com.epam.tc;


import com.epam.tc.connectionpool.ConnectionPoolException;
import com.epam.tc.dao.DaoException;
import com.epam.tc.service.ServiceFactory;
import com.epam.tc.service.TrainingService;
import com.google.protobuf.ServiceException;
import java.text.ParseException;
public class deleteTHIS {

  public static void main(String[] args) throws ConnectionPoolException, ParseException, ServiceException, com.epam.tc.service.ServiceException, DaoException {

    TrainingService trainingService = ServiceFactory.getTrainingService();
    trainingService.checkTrainingStatusForStudent(1, 1);


  }
}
