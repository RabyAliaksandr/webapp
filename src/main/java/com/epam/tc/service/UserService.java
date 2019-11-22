package com.epam.tc.service;

import com.epam.tc.entity.*;
import com.google.protobuf.ServiceException;

import java.util.List;
import java.util.Map;

public interface UserService {

  Map<Task, User> findAllMentors() throws ServiceException;

  User checkLogin(String login, String password) throws ServiceException;

//  User checkRegistrationField(String login, String password, String name, String surname,
//                              String email) throws ServiceException;


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
