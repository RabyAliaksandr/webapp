package com.epam.webapp.dao;

import com.epam.webapp.dao.impl.DataListDAOImpl;
import com.epam.webapp.dao.impl.TrainingsOperationDAOImpl;
import com.epam.webapp.dao.impl.UserDAOImpl;

public class DAOFactory {

  private static UserDAO userDAO = new UserDAOImpl();
  private static DataListsDAO dataListsDAO = new DataListDAOImpl();
  private static TrainingsOperationDAO operationDAO = new TrainingsOperationDAOImpl();

  public static DataListsDAO getDataListsDAO() {return dataListsDAO;}
  public static UserDAO getUserDAO() {
    return userDAO;
  }
  public static TrainingsOperationDAO getOperationDAO() {return operationDAO;}
}
