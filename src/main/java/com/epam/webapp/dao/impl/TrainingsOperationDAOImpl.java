package com.epam.webapp.dao.impl;

import com.epam.webapp.connectionpool.ConnectionPool;
import com.epam.webapp.connectionpool.ConnectionPoolException;
import com.epam.webapp.dao.TrainingsOperationDAO;
import com.epam.webapp.dao.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TrainingsOperationDAOImpl implements TrainingsOperationDAO {

  private static final Logger logger = LogManager.getLogger(TrainingsOperationDAOImpl.class);
  private static final String SQL_ADD_COURSE_TO_STUDENT = "INSERT INTO trainingbystudents (userid, trainingid) VALUES (?, ?);";

  @Override
  public void addTrainingsToStudent(int idStudent, int idTraining) throws DAOException {
    ConnectionPool connectionPool =ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      connectionPool.initPool();
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_ADD_COURSE_TO_STUDENT);
      preparedStatement.setInt(1, idStudent);
      preparedStatement.setInt(2, idTraining);
      System.out.println("i'm here training dao impl");
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      logger.error(e);
      throw new DAOException(e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DAOException(e);
    }
  }
}
