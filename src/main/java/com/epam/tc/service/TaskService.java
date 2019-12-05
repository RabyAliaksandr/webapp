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
   * @param trainingId - Training id which will adding
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
   * @param trainingId - Training id which will looking
   * @return list of Training
   * @throws ServiceException package Service exception
   * @see Training
   */
  List<Task> findTasksListForTraining(int trainingId) throws ServiceException;

  /**
   * finds a Task by id
   *
   * @param taskId - Task id which will looking
   * @return Task task
   * @throws ServiceException package Service exception
   * @see Task
   */
  Task findTask(int taskId) throws ServiceException;

  /**
   * updates Task  fields
   *
   * @param taskId   - Task id which will update
   * @param taskName - new Task name n
   * @param task     - new Task task
   * @throws ServiceException package Service exception
   * @see Task
   */
  void updateTask(int taskId, String taskName, String task) throws ServiceException;

  /**
   * checks the grade for Task for the User
   *
   * @param userId - User id which will looking
   * @param taskId - Task id which  will looking
   * @return int ranging from 1 to 10
   * @throws ServiceException package Service exception
   * @see User
   * @see Task
   */
  int checkTaskStatus(int userId, int taskId) throws ServiceException;

  /**
   * updating the answer field for a given User related Task
   *
   * @param userId - User id who will send solution
   * @param taskId - Task id for which Task solution
   * @param answer - Task answer
   * @throws ServiceException package Service exception
   * @see Task
   * @see User
   */
  void sendSolution(int userId, int taskId, String answer) throws ServiceException;

  /**
   * finding a solution for this Task for the User
   *
   * @param studentId - User id for which to look
   * @param taskId    - Task id for which to look
   * @return - Task
   * @throws ServiceException package Service exception
   * @see Task
   * @see User
   */
  Task findTaskSolution(int studentId, int taskId) throws ServiceException;

  /**
   * assessment of the Task for this User
   *
   * @param studentId - User id for which to set
   * @param taskId    - Task id for which to look
   * @param mark      - Task mark must be from 1 to 10
   * @throws ServiceException package Service exception
   * @see User
   * @see Task
   */
  void gradeTask(int studentId, int taskId, int mark) throws ServiceException;

  /**
   * finding the average score for the tasks for Student
   *
   * @param userId     - User id for which to look
   * @param trainingId Training id for which to look
   * @return - int ranging from 1 to 10
   * @throws ServiceException package Service exception
   * @see User
   * @see Training
   */
  int findAvgMarkForTasks(int userId, int trainingId) throws ServiceException;

  /**
   * finding solved Task for User
   *
   * @param trainingId - Training id for which to look
   * @param studentId  - User id for which to look
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
   * @param taskId - Task id which will be deleting
   * @throws ServiceException package Service exception
   * @see Task
   */
  void deleteTask(int taskId) throws ServiceException;
}
