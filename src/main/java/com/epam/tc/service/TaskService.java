package com.epam.tc.service;

import com.epam.tc.entity.Task;
import com.epam.tc.entity.Training;
import com.epam.tc.entity.User;

import java.util.List;

/**
 * The interface Task service.
 *
 * @author alex raby
 * @version 1.0 contains methods for working with Task
 * @see Task
 */
public interface TaskService {

  /**
   * adding Task for Training
   *
   * @param trainingId - Training id
   * @param taskName   - Task name
   * @param taskText   - Task task
   * @throws ServiceException package Service exception
   * @see Task
   * @see Training
   */
  void addTaskForTraining(int trainingId, String taskName, String taskText) throws ServiceException;

  /**
   * finds Task for this Training
   *
   * @param trainingId - Training id
   * @return list of Training
   * @throws ServiceException package Service exception
   * @see Training
   */
  List<Task> findTasksListForTraining(int trainingId) throws ServiceException;

  /**
   * finds a Task by id
   *
   * @param taskId - Task id
   * @return Task task
   * @throws ServiceException package Service exception
   * @see Task
   */
  Task findTask(int taskId) throws ServiceException;

  /**
   * updates Task  fields
   *
   * @param taskId   - Task id
   * @param taskName - new Task name
   * @param task     - new Task task
   * @throws ServiceException package Service exception
   * @see Task
   */
  void updateTask(int taskId, String taskName, String task) throws ServiceException;

  /**
   * checks the grade for Task for the User
   *
   * @param userId - User id
   * @param taskId - Task id
   * @return int ranging from 1 to 10
   * @throws ServiceException package Service exception
   * @see User
   * @see Task
   */
  int checkTaskStatus(int userId, int taskId) throws ServiceException;

  /**
   * updating the answer field for a given User related Task
   *
   * @param userId - User id
   * @param taskId - Task id
   * @param answer - Task answer
   * @throws ServiceException package Service exception
   * @see Task
   * @see User
   */
  void sendSolution(int userId, int taskId, String answer) throws ServiceException;

  /**
   * finding a solution for this Task for the User
   *
   * @param studentId - User id
   * @param taskId    - Task id
   * @return - Task
   * @throws ServiceException package Service exception
   * @see Task
   * @see User
   */
  Task findTaskSolution(int studentId, int taskId) throws ServiceException;

  /**
   * assessment of the Task for this User
   *
   * @param studentId - User id
   * @param taskId    - Task id
   * @param mark      - Task mark
   * @throws ServiceException package Service exception
   * @see User
   * @see Task
   */
  void gradeTask(int studentId, int taskId, int mark) throws ServiceException;

  /**
   * finding the average score for the tasks
   *
   * @param userId     - User id
   * @param trainingId Training id
   * @return - int ranging from 1 to 10
   * @throws ServiceException package Service exception
   * @see User
   * @see Training
   */
  int findAvgMarkForTasks(int userId, int trainingId) throws ServiceException;

  /**
   * finding solved Task for User
   *
   * @param trainingId - Training id
   * @param studentId  - User id
   * @return list of Task
   * @throws ServiceException package Service exception
   * @see Task
   * @see Training
   * @see User
   */
  List<Task> findCompletedTasks(int trainingId, int studentId) throws ServiceException;

  /**
   * deleting a Task by id
   *
   * @param taskId - Task id
   * @throws ServiceException package Service exception
   * @see Task
   */
  void deleteTask(int taskId) throws ServiceException;
}
