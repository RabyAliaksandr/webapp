package com.epam.tc.dao;

import com.epam.tc.entity.Task;

import java.util.List;

/**
 * The interface Task dao.
 */
public interface TaskDao {

  /**
   * Add task for training boolean.
   *
   * @param trainingId the training id
   * @param taskName   the task name
   * @param taskText   the task text
   * @return the boolean
   * @throws DaoException the dao exception
   */
  void addTaskForTraining(int trainingId, String taskName, String taskText) throws DaoException;

  /**
   * Find tasks list for training list.
   *
   * @param trainingId the training id
   * @return the list
   * @throws DaoException the dao exception
   */
  List<Task> findTasksListForTraining(int trainingId) throws DaoException;

  /**
   * Find task task.
   *
   * @param taskId the task id
   * @return the task
   * @throws DaoException the dao exception
   */
  Task findTask(int taskId) throws DaoException;

  /**
   * Update task boolean.
   *
   * @param taskId   the task id
   * @param taskName the task name
   * @param task     the task
   * @return the boolean
   * @throws DaoException the dao exception
   */
  boolean updateTask(int taskId, String taskName, String task) throws DaoException;

  /**
   * Check task status int.
   *
   * @param userId the user id
   * @param taskId the task id
   * @return the int
   * @throws DaoException the dao exception
   */
  int checkTaskStatus(int userId, int taskId) throws DaoException;

  /**
   * Send solution boolean.
   *
   * @param userId the user id
   * @param taskId the task id
   * @param answer the answer
   * @return the boolean
   * @throws DaoException the dao exception
   */
  boolean sendSolution(int userId, int taskId, String answer) throws DaoException;

  /**
   * Find task solution task.
   *
   * @param studentId the student id
   * @param taskId    the task id
   * @return the task
   * @throws DaoException the dao exception
   */
  Task findTaskSolution(int studentId, int taskId) throws DaoException;

  /**
   * Grade task boolean.
   *
   * @param studentId the student id
   * @param taskId    the task id
   * @param mark      the mark
   * @return the boolean
   * @throws DaoException the dao exception
   */
  boolean gradeTask(int studentId, int taskId, int mark) throws DaoException;

  /**
   * Avg mark for task int.
   *
   * @param userId     the user id
   * @param trainingId the training id
   * @return the int
   * @throws DaoException the dao exception
   */
  int avgMarkForTask(int userId, int trainingId) throws DaoException;

  /**
   * Find completed tasks list.
   *
   * @param trainingId the training id
   * @param studentId  the student id
   * @return the list
   * @throws DaoException the dao exception
   */
  List<Task> findCompletedTasks(int trainingId, int studentId) throws DaoException;

  /**
   * Delete task.
   *
   * @param taskId the task id
   * @throws DaoException the dao exception
   */
  void deleteTask(int taskId) throws DaoException;
}
