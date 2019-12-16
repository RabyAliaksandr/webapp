package com.epam.tc.service;

import com.epam.tc.entity.*;
import com.google.protobuf.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * The interface User service.
 *
 * @author alex raby
 * @version 1.0 this interface contains methods for working with User
 * @see User
 */
public interface UserService {

  /**
   * finding all users who have type 'mentor' {@link UserType#MENTOR}
   *
   * @return object Map Training and Mentor for this Training
   * @throws ServiceException package Service exception
   * @see User
   * @see Training
   * @see UserType
   */
  Map<Training, User> findMentorsAndTrainings() throws ServiceException;

  /**
   * checks if the user with the given login and password
   *
   * @param login    - User login
   * @param password - User password
   * @return true if login and password is in DataBase
   * @throws ServiceException the service exception
   */
  User checkLogin(String login, String password) throws ServiceException;

  /**
   * check if the student is enrolled in the training
   *
   * @param userId     - students id
   * @param trainingId - trainings id
   * @return boolean true if Student attends this Training
   * @throws ServiceException package Service exception
   * @see Training
   * @see User
   */
  boolean checkEnrolled(int userId, int trainingId) throws ServiceException;

  /**
   * returns an array of user types
   *
   * @return array user type [] {@link UserType}
   */
  UserType[] usersType();

  /**
   * returns an array of user status
   *
   * @return array user status [] {@link UserStatus}
   */
  UserStatus[] userStatuses();

  /**
   * update user status and user type
   *
   * @param userId - user id who needs to change
   * @param type   - new user type {@link UserType}
   * @param status - new user status {@link UserStatus}
   * @throws ServiceException package Service exception
   * @see User
   */
  void updateUserType(int userId, UserType type, UserStatus status) throws ServiceException;

  /**
   * finds users who are enrolled in this training
   *
   * @param trainingId - training id for which to look
   * @return object Map User and Integer - final grade for Training
   * @throws ServiceException package Service exception
   * @see User
   * @see Training
   */
  Map<User, Integer> findStudentsForTraining(int trainingId) throws ServiceException;

  /**
   * finds users grades for tasks for this training
   *
   * @param studentId  - student id for which to look
   * @param trainingId - training id for which to look
   * @return list of task
   * @throws ServiceException package Service exception
   * @see User
   * @see Task
   * @see Training
   */
  List<Task> findStudentsMarkForTrainingsTask(int studentId, int trainingId) throws ServiceException;

  /**
   * checks if a given login
   *
   * @param login - user login
   * @return boolean true if matches the expression
   * @throws ServiceException package Service exception
   * @see User
   */
  boolean checkLogin(String login) throws ServiceException;

  /**
   * checks if a given email
   *
   * @param email - user email
   * @return boolean true if matches the expression
   * @throws ServiceException package Service exception
   * @see User
   */
  boolean checkEmail(String email) throws ServiceException;

  /**
   * new user registration method
   *
   * @param user - new user
   * @throws ServiceException package Service exception
   * @see User
   */
  void registration(User user) throws ServiceException;

  /**
   * delete this user method
   *
   * @param userId - user id
   * @throws ServiceException package Service exception
   * @see User
   */
  void deleteUser(int userId) throws ServiceException;

  /**
   * Find all mentors list.
   *
   * @return the list
   * @throws ServiceException the service exception
   */
  List<User> findAllMentors() throws ServiceException;
}
