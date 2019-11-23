package com.epam.tc.service;

import com.epam.tc.entity.*;
import com.google.protobuf.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * @author alex raby
 * @version 1.0
 * this interface has methods for work with User
 * @see User
 */

public interface UserService {

  /**
   * finding all users who have type 'mentor'
   * @return
   * @throws ServiceException
   * @see User
   * @see Training
   * @see UserType
   */
  Map<Training, User> findAllMentors() throws ServiceException;

  /**
   * compare values login and password which are in dataBase to input values
   * @param login
   * @param password
   * @return
   * @throws ServiceException
   */
  User checkLogin(String login, String password) throws ServiceException;

  boolean checkEnrolled(int userId, int trainingId) throws ServiceException;

  List<User> getAllUser() throws ServiceException;

  UserType[] usersType();

  UserStatus[] userStatuses();

  boolean updateUserType(int userId, UserType type, UserStatus status) throws ServiceException;

  List<Student> findStudentsForTraining(int trainingId) throws ServiceException;

  List<Task> findStudentsMarkForTrainingsTask(int studentId, int trainingId) throws ServiceException;

  boolean checkLogin(String login) throws ServiceException;

  boolean checkEmail(String email) throws ServiceException;

  void registration(User user) throws ServiceException;

  void deleteUser(int userId) throws ServiceException;



}
