package com.epam.webapp.dao;

import com.epam.webapp.dao.impl.DataListDaoImpl;
import com.epam.webapp.dao.impl.TrainingsOperationDAOImpl;
import com.epam.webapp.dao.impl.UserDaoImpl;

public class DaoFactory {

  private static UserDao userDao = new UserDaoImpl();
  private static DataListsDao dataListDao = new DataListDaoImpl();
  private static TrainingsOperationDao trainingsOperationDAO = new TrainingsOperationDAOImpl();

  public static DataListsDao getDataListDao() {return dataListDao;}
  public static UserDao getUserDao() {
    return userDao;
  }
  public static TrainingsOperationDao getTrainingsOperationDAO() {return trainingsOperationDAO;}
}
