package com.epam.webapp.dao.impl;

import com.epam.webapp.connectionpool.ConnectionPool;
import com.epam.webapp.connectionpool.ConnectionPoolException;
import com.epam.webapp.dao.TrainingDao;
import com.epam.webapp.dao.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static com.epam.webapp.constant.ConstSqlRequest.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TrainingDaoImpl implements TrainingDao {

  private static final Logger logger = LogManager.getLogger(TrainingDaoImpl.class);

  @Override
  public void addTrainingsToStudent(int idStudent, int idTraining) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_ADD_COURSE_TO_STUDENT);
      preparedStatement.setInt(1, idStudent);
      preparedStatement.setInt(2, idTraining);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException(e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException(e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement);
      } catch (ConnectionPoolException e) {
        logger.error(e);
        throw new DaoException(e);
      }
    }
  }
}
