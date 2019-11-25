package com.epam.tc.dao.impl;

import com.epam.tc.connectionpool.ConnectionPool;
import com.epam.tc.connectionpool.ConnectionPoolException;
import com.epam.tc.dao.DaoException;
import com.epam.tc.dao.TaskDao;
import com.epam.tc.entity.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.tc.dao.SqlColumn.*;
import static com.epam.tc.dao.SqlQuery.*;

public class TaskDaoImpl implements TaskDao {

  private static Logger logger = LogManager.getLogger(TaskDaoImpl.class);

  @Override
  public boolean addTaskForTraining(int trainingId, String taskName, String taskText) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_ADD_TASK_FOR_TRAINING);
      preparedStatement.setInt(1, trainingId);
      preparedStatement.setString(2, taskName);
      preparedStatement.setString(3, taskText);
      preparedStatement.executeUpdate();
      return true;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement);
      } catch (ConnectionPoolException e) {
        logger.error(e);
      }
    }
  }

  @Override
  public List<Task> findTasksListForTraining(int trainingId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    List<Task> tasks = new ArrayList<>();
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_TASKS_FOR_TRAINING);
      preparedStatement.setInt(1, trainingId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Task task = new Task();
        task.setId(resultSet.getInt(SQL_TASK_ID));
        task.setName(resultSet.getString(SQL_TASK_NAME));
        task.setTask(resultSet.getString(SQL_TASK));
        tasks.add(task);
      }
      return tasks;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement, resultSet);
      } catch (ConnectionPoolException e) {
        logger.error(e);
      }
    }
  }

  @Override
  public Task findTask(int taskId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Task task = new Task();
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_TASK_BY_ID);
      preparedStatement.setInt(1, taskId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        task.setId(resultSet.getInt(SQL_TASK_ID));
        task.setTask(resultSet.getString(SQL_TASK));
        task.setName(resultSet.getString(SQL_TASK_NAME));
      }
      return task;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement, resultSet);
      } catch (ConnectionPoolException e) {
        logger.error(e);
      }
    }
  }

  @Override
  public boolean updateTask(int taskId, String taskName, String task) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_UPDATE_TRAININGS_TASK);
      preparedStatement.setString(1, taskName);
      preparedStatement.setString(2, task);
      preparedStatement.setInt(3, taskId);
      preparedStatement.executeUpdate();
      return true;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement);
      } catch (ConnectionPoolException e) {
        logger.error(e);
      }
    }
  }

  @Override
  public int checkTaskStatus(int userId, int taskId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    int mark = 0;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_CHECK_STATUS_TASK);
      preparedStatement.setInt(1, userId);
      preparedStatement.setInt(2, taskId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        mark = resultSet.getInt(1);
      }
      return mark;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement, resultSet);
      } catch (ConnectionPoolException e) {
        logger.error(e);
      }
    }
  }

  @Override
  public boolean sendSolution(int userId, int taskId, String answer) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_SEND_SOLUTION);
      preparedStatement.setString(1, answer);
      preparedStatement.setInt(2, userId);
      preparedStatement.setInt(3, taskId);
      preparedStatement.executeUpdate();
      return true;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement);
      } catch (ConnectionPoolException e) {
        logger.error(e);
      }
    }
  }

  @Override
  public Task findTaskSolution(int studentId, int taskId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Task task = new Task();

    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQl_TASK_SOLUTION);
      preparedStatement.setInt(1, studentId);
      preparedStatement.setInt(2, taskId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        task.setMark(resultSet.getInt(SQL_MARK));
        task.setAnswer(resultSet.getString(SQL_ANSWER));
      }
      return task;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement, resultSet);
      } catch (ConnectionPoolException e) {
        logger.error(e);
      }
    }
  }

  @Override
  public boolean gradeTask(int studentId, int taskId, int mark) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    boolean done = false;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_GRADE_TASK);
      preparedStatement.setInt(1, mark);
      preparedStatement.setInt(2, studentId);
      preparedStatement.setInt(3, taskId);
      preparedStatement.executeUpdate();
      return true;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement);
      } catch (ConnectionPoolException e) {
        logger.error(e);
      }
    }
  }

  @Override
  public int avgMarkForTask(int userId, int trainingId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    int avgMark = 0;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_AVG_FOR_TASKS);
      preparedStatement.setInt(1, userId);
      preparedStatement.setInt(2, trainingId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        avgMark = resultSet.getInt(SQL_AVG_MARK);
      }
      return avgMark;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement, resultSet);
      } catch (ConnectionPoolException e) {
        logger.error(e);
      }
    }
  }

  @Override
  public List<Task> findCompletedTasks(int trainingId, int studentId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    List<Task> tasks = new ArrayList<>();
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_COMPLETED_TASKS_FOR_STUDENT);
      preparedStatement.setInt(1, studentId);
      preparedStatement.setInt(2, trainingId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Task task = new Task();
        task.setId(resultSet.getInt(SQL_TASK_ID));
        task.setName(resultSet.getString(SQL_TASK_NAME));
        tasks.add(task);
      }
      return tasks;
    } catch (SQLException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } catch (ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException("Error access database", e);
    } finally {
      try {
        connectionPool.closeConnection(connection, preparedStatement, resultSet);
      } catch (ConnectionPoolException e) {
        logger.error(e);
      }
    }
  }

  @Override
  public void deleteTask(int taskId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_DELETE_TASK);
      preparedStatement.setInt(1, taskId);
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
      }
    }
  }
}
