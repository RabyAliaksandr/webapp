package com.epam.tc.service;

import com.epam.tc.entity.Task;

import java.util.List;

public interface TaskService {
  boolean addTaskForTraining(int trainingId, String taskName, String taskText) throws ServiceException;

  List<Task> findTasksListForTraining(int trainingId) throws ServiceException;

  Task findTask(int taskId) throws ServiceException;

  boolean updateTask(int taskId, String taskName, String task) throws ServiceException;

  int checkTaskStatus(int userId, int taskId) throws ServiceException;

  boolean sendSolution(int userId, int taskId, String answer) throws ServiceException;

  Task findTaskSolution(int studentId, int taskId) throws ServiceException;

  boolean gradeTask(int studentId, int taskId, int mark) throws ServiceException;

  int findAvgMarkForTasks(int userId, int trainingId) throws ServiceException;

  List<Task> findCompletedTasks(int trainingId, int studentId) throws ServiceException;

  void deleteTask(int taskId) throws ServiceException;
}
