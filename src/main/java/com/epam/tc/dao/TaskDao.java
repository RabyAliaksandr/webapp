package com.epam.tc.dao;

import com.epam.tc.entity.Task;

import java.util.List;

/**
 * contains methods for working with Task
 *
 * @author alex raby
 * @version 1.0
 */
public interface TaskDao {

  /**
   * Add task for training boolean.
   *
   * @param trainingId the training id
   * @param taskName   the task name
   * @param taskText   the task text
   * @throws DaoException the dao exception
   * @see Task
   * @see com.epam.tc.entity.Training
   */
  void addTaskForTraining(int trainingId, String taskName, String taskText) throws DaoException;

  /**
   * finds Task for this Training
   *
   * @param trainingId - Training id which will looking
   * @return list of Training
   * @throws DaoException the dao exception
   * @see com.epam.tc.entity.Training
   */
  List<Task> findTasksListForTraining(int trainingId) throws DaoException;

  /**
   * finds a Task by id
   *
   * @param taskId - Task id which will looking
   * @return Task task
   * @throws DaoException the dao exception
   * @see Task
   */
  Task findTask(int taskId) throws DaoException;

  /**
   * updates Task  fields
   *
   * @param taskId   - Task id which will update
   * @param taskName - new Task name n
   * @param task     - new Task task
   * @throws DaoException the dao exception
   * @see Task
   */
  void updateTask(int taskId, String taskName, String task) throws DaoException;

  /**
   * checks the grade for Task for the User
   *
   * @param userId - User id which will looking
   * @param taskId - Task id which  will looking
   * @return int ranging from 1 to 10
   * @throws DaoException the dao exception
   * @see Task
   * @see com.epam.tc.entity.User
   */
  int checkTaskStatus(int userId, int taskId) throws DaoException;

  /**
   * updating the answer field for a given User related Task
   *
   * @param userId - User id who will send solution
   * @param taskId - Task id for which Task solution
   * @param answer - Task answer
   * @throws DaoException the dao exception
   * @see com.epam.tc.entity.User
   * @see Task
   */
  void sendSolution(int userId, int taskId, String answer) throws DaoException;

  /**
   * finding a solution for this Task for the User
   *
   * @param studentId - User id for which to look
   * @param taskId    - Task id for which to look
   * @return - Task {@link Task}
   * @throws DaoException the dao exception
   * @see com.epam.tc.entity.UserType
   * @see com.epam.tc.entity.User
   */
  Task findTaskSolution(int studentId, int taskId) throws DaoException;

  /**
   * assessment of the Task for this User
   *
   * @param studentId - User id for which to set
   * @param taskId    - Task id for which to look
   * @param mark      - Task mark must be from 1 to 10
   * @throws DaoException the dao exception
   * @see com.epam.tc.entity.UserType#STUDENT
   * @see com.epam.tc.entity.User
   * @see Task
   */
  void gradeTask(int studentId, int taskId, int mark) throws DaoException;

  /**
   * finding the average score for the tasks for Student
   *
   * @param userId     - User id for which to look
   * @param trainingId Training id for which to look
   * @return - int ranging from 1 to 10
   * @throws DaoException the dao exception
   * @see com.epam.tc.entity.User
   * @see com.epam.tc.entity.Training
   */
  int avgMarkForTask(int userId, int trainingId) throws DaoException;

  /**
   * finding solved Task for User
   *
   * @param trainingId - Training id for which to look
   * @param studentId  - User id for which to look
   * @return list of Task {@link Task}
   * @throws DaoException the dao exception
   * @see com.epam.tc.entity.UserType#STUDENT
   * @see com.epam.tc.entity.User
   * @see com.epam.tc.entity.Training
   */
  List<Task> findCompletedTasks(int trainingId, int studentId) throws DaoException;

  /**
   * Delete task.
   *
   * @param taskId the task id {@link Task}
   * @throws DaoException the dao exception
   */
  void deleteTask(int taskId) throws DaoException;
}
