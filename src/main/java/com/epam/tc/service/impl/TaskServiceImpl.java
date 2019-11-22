package com.epam.tc.service.impl;

import com.epam.tc.dao.DaoException;
import com.epam.tc.dao.DaoFactory;
import com.epam.tc.dao.TaskDao;
import com.epam.tc.entity.Task;
import com.epam.tc.service.ServiceException;
import com.epam.tc.service.TaskService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TaskServiceImpl implements TaskService {

  private static Logger logger = LogManager.getLogger(TaskServiceImpl.class);

  public final boolean addTaskForTraining(int trainingId, String taskName,
                                          String taskText) throws ServiceException {
    TaskDao taskDao = DaoFactory.getTaskDao();
    try {
      return taskDao.addTaskForTraining(trainingId, taskName, taskText);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final List<Task> findTasksListForTraining(int trainingId) throws ServiceException {
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

  @Override
  public final Task findTask(int taskId) throws ServiceException {
    TaskDao taskDao = DaoFactory.getTaskDao();
    Task task = new Task();
    try {
      task = taskDao.findTask(taskId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
    return task;
  }


  public final boolean updateTask(int taskId, String taskName, String task) throws ServiceException {
    TaskDao taskDao = DaoFactory.getTaskDao();
    try {
      return taskDao.updateTask(taskId, taskName, task);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final int checkTaskStatus(int userId, int taskId) throws ServiceException {
    TaskDao taskDao = DaoFactory.getTaskDao();
    try {
      return taskDao.checkTaskStatus(userId, taskId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final boolean sendSolution(int userId, int taskId, String answer) throws ServiceException {
    TaskDao taskDao = DaoFactory.getTaskDao();
    try {
      return taskDao.sendSolution(userId, taskId, answer);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final Task findTaskSolution(int studentId, int taskId) throws ServiceException {
    TaskDao taskDao = DaoFactory.getTaskDao();
    Task task = new Task();
    try {
      task = taskDao.findTaskSolution(studentId, taskId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
    return task;
  }

  public final boolean gradeTask(int studentId, int taskId, int mark) throws ServiceException {
    TaskDao taskDao = DaoFactory.getTaskDao();
    try {
      return taskDao.gradeTask(studentId, taskId, mark);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  @Override
  public final int findAvgMarkForTasks(int userId, int trainingId) throws ServiceException {
    TaskDao taskDao = DaoFactory.getTaskDao();
    try {
      return taskDao.avgMarkForTask(userId, trainingId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final List<Task> findCompletedTasks(int trainingId, int studentId) throws ServiceException {
    TaskDao taskDao = DaoFactory.getTaskDao();
    try {
      return taskDao.findCompletedTasks(trainingId, studentId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

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
