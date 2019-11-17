package com.epam.webapp.dao.impl;

import com.epam.webapp.connectionpool.ConnectionPool;
import com.epam.webapp.connectionpool.exception.ConnectionPoolException;
import com.epam.webapp.dao.TrainingsOperationDao;
import com.epam.webapp.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TrainingsOperationDAOImpl implements TrainingsOperationDao {

  private static final Logger logger = LogManager.getLogger(TrainingsOperationDAOImpl.class);
  private static final String SQL_ADD_COURSE_TO_STUDENT = "INSERT INTO trainingbystudents (userid, trainingid) VALUES (?, ?);";

  @Override
  public void addTrainingsToStudent(int idStudent, int idTraining) throws DaoException {
    ConnectionPool connectionPool =ConnectionPool.Instance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_ADD_COURSE_TO_STUDENT);
      preparedStatement.setInt(1, idStudent);
      preparedStatement.setInt(2, idTraining);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new DaoException(e);
    } catch (ConnectionPoolException e) {
      e.printStackTrace();
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement);
      } catch (ConnectionPoolException e) {
        throw new DaoException(e);
      }
    }
  }
}
