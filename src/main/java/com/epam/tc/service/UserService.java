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
   * @return object Map<Training, User>
   * @throws ServiceException package Service exception
   * @see User
   * @see Training
   * @see UserType
   */
  Map<Training, User> findAllMentors() throws ServiceException;

  /**
   * checks if the user with the given login and password
   *
   * @param login    - User login
   * @param password - User password
   * @return object User {@link User}
   * @throws ServiceException the service exception
   */
  User checkLogin(String login, String password) throws ServiceException;

  /**
   * check if the student is enrolled in the training
   *
   * @param userId     - students id
   * @param trainingId - trainings id
   * @return boolean boolean
   * @throws ServiceException package Service exception
   * @see Training
   * @see User
   */
  boolean checkEnrolled(int userId, int trainingId) throws ServiceException;

  /**
   * returns an array of user types
   *
   * @return array user type [ ]
   * @see UserType
   */
  UserType[] usersType();

  /**
   * returns an array of user status
   *
   * @return array user status [ ]
   * @see UserStatus
   */
  UserStatus[] userStatuses();

  /**
   * update user status and user type
   *
   * @param userId - user id
   * @param type   - new user type
   * @param status - new user status
   * @throws ServiceException package Service exception
   * @see User
   * @see UserType
   * @see UserStatus
   */
  void updateUserType(int userId, UserType type, UserStatus status) throws ServiceException;

  /**
   * finds users who are enrolled in this training
   *
   * @param trainingId - training id
   * @return list of users
   * @throws ServiceException package Service exception
   * @see User
   * @see Training
   */
  List<User> findStudentsForTraining(int trainingId) throws ServiceException;

  /**
   * finds users grades for tasks for this training
   *
   * @param studentId  - student id
   * @param trainingId - training id
   * @return list of task
   * @throws ServiceException package Service exception
   * @see User
   * @see Task
   * @see Training@
   */
  List<Task> findStudentsMarkForTrainingsTask(int studentId, int trainingId) throws ServiceException;

  /**
   * checks if a given login
   *
   * @param login - user login
   * @return boolean boolean
   * @throws ServiceException package Service exception
   * @see User
   */
  boolean checkLogin(String login) throws ServiceException;

  /**
   * checks if a given email
   *
   * @param email - user email
   * @return boolean boolean
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
}
