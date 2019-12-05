package com.epam.tc.service.impl;

import com.epam.tc.dao.DaoException;
import com.epam.tc.dao.TaskDao;
import com.epam.tc.entity.Task;
import com.epam.tc.service.ServiceException;
import com.epam.tc.dao.DaoFactory;
import com.epam.tc.service.TaskService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The type Task service.
 *
 * @author alex raby
 * @version 1.0 this class implements interface methods TaskService {@link TaskService} methods of this class catch DaoException {@link DaoException} and throw ServiceException {@link ServiceException}
 */
public class TaskServiceImpl implements TaskService {

  /**
   * class object Logger {@link Logger}
   * writes important events to a log file
   */
  private static Logger logger = LogManager.getLogger(TaskServiceImpl.class);

  /** {@inheritDoc} */
  public  void addTaskForTraining(int trainingId, String taskName,
                                       String taskText) throws ServiceException {
    TaskDao taskDao = DaoFactory.getTaskDao();
    try {
      taskDao.addTaskForTraining(trainingId, taskName, taskText);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  /** {@inheritDoc} */
  public  List<Task> findTasksListForTraining(int trainingId) throws ServiceException {
    TaskDao taskDao = DaoFactory.getTaskDao();
    List<Task> tasks;
    try {
      tasks = taskDao.findTasksListForTraining(trainingId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
    return tasks;
  }

  /** {@inheritDoc} */
  @Override
  public  Task findTask(int taskId) throws ServiceException {
    TaskDao taskDao = DaoFactory.getTaskDao();
    Task task;
    try {
      task = taskDao.findTask(taskId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
    return task;
  }


  /** {@inheritDoc} */
  public  void updateTask(int taskId, String taskName, String task) throws ServiceException {
    TaskDao taskDao = DaoFactory.getTaskDao();
    try {
      taskDao.updateTask(taskId, taskName, task);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  /** {@inheritDoc} */
  public  int checkTaskStatus(int userId, int taskId) throws ServiceException {
    TaskDao taskDao = DaoFactory.getTaskDao();
    try {
      return taskDao.checkTaskStatus(userId, taskId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  /** {@inheritDoc} */
  public  void sendSolution(int userId, int taskId, String answer) throws ServiceException {
    TaskDao taskDao = DaoFactory.getTaskDao();
    try {
      taskDao.sendSolution(userId, taskId, answer);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  /** {@inheritDoc} */
  public  Task findTaskSolution(int studentId, int taskId) throws ServiceException {
    TaskDao taskDao = DaoFactory.getTaskDao();
    Task task;
    try {
      task = taskDao.findTaskSolution(studentId, taskId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
    return task;
  }

  /** {@inheritDoc} */
  public  void gradeTask(int studentId, int taskId, int mark) throws ServiceException {
    TaskDao taskDao = DaoFactory.getTaskDao();
    try {
      taskDao.gradeTask(studentId, taskId, mark);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  /** {@inheritDoc} */
  @Override
  public  int findAvgMarkForTasks(int userId, int trainingId) throws ServiceException {
    TaskDao taskDao = DaoFactory.getTaskDao();
    try {
      return taskDao.avgMarkForTask(userId, trainingId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  /** {@inheritDoc} */
  public  List<Task> findCompletedTasks(int trainingId, int studentId) throws ServiceException {
    TaskDao taskDao = DaoFactory.getTaskDao();
    try {
      return taskDao.findCompletedTasks(trainingId, studentId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void deleteTask(int taskId) throws ServiceException {
    TaskDao taskDao = DaoFactory.getTaskDao();
    try {
      taskDao.deleteTask(taskId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException(e);
    }
  }
}
