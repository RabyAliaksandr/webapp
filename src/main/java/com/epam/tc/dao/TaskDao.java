package com.epam.tc.dao;

import com.epam.tc.entity.Task;

import java.util.List;

public interface TaskDao {

  boolean addTaskForTraining(int trainingId, String taskName, String taskText) throws DaoException;

  List<Task> findTasksListForTraining(int trainingId) throws DaoException;

  Task findTask(int taskId) throws DaoException;

  boolean updateTask(int taskId, String taskName, String task) throws DaoException;

  int checkTaskStatus(int userId, int taskId) throws DaoException;

  boolean sendSolution(int userId, int taskId, String answer) throws DaoException;

  Task findTaskSolution(int studentId, int taskId) throws DaoException;

  boolean gradeTask(int studentId, int taskId, int mark) throws DaoException;

  int avgMarkForTask(int userId, int trainingId) throws DaoException;

  List<Task> findCompletedTasks(int trainingId, int studentId) throws DaoException;

  void deleteTask(int taskId) throws DaoException;
}
