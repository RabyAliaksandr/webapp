package com.epam.webapp.dao;

import com.epam.webapp.dao.impl.DataListDaoImpl;
import com.epam.webapp.dao.impl.PaymentCardDaoImpl;
import com.epam.webapp.dao.impl.TrainingDaoImpl;
import com.epam.webapp.dao.impl.UserDaoImpl;

public class DaoFactory {

  private static UserDao userDao = new UserDaoImpl();
  private static DataListsDao dataListDao = new DataListDaoImpl();
  private static TrainingDao trainingsOperationDAO = new TrainingDaoImpl();
  private static PaymentCardDao paymentCardDao = new PaymentCardDaoImpl();

  public static DataListsDao getDataListDao() {
    return dataListDao;
  }

  public static UserDao getUserDao() {
    return userDao;
  }

  public static TrainingDao getTrainingsOperationDAO() {
    return trainingsOperationDAO;
  }

  public static PaymentCardDao getPaymentCardDao() {
    return paymentCardDao;
  }
}
