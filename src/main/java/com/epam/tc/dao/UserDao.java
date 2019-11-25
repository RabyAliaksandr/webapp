package com.epam.tc.dao;

import com.epam.tc.entity.*;
import com.google.protobuf.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * @author alex raby
 * @version 1.0
 * this interface contains methods for working with DataBase with User
 * @see User
 */
public interface UserDao {

  User authorization(User user) throws DaoException;

  User registration(User user) throws DaoException;

  void grade(int assessment, int userId, int trainingId) throws DaoException;

  boolean checkEnrolled(int userId, int trainingId) throws DaoException;

  List<User> findAllUser() throws DaoException;

  void updateUserType(int userId, UserType type, UserStatus status) throws DaoException;

  List<User> findStudentsForTraining(int trainingId) throws DaoException;

  List<Task> findStudentsMarkForTrainingsTask(int studentId, int trainingId) throws DaoException;

  /**
   * finding all users who have type 'mentor'
   * @return
   * @throws ServiceException
   * @see User
   * @see Training
   * @see UserType
   */
  Map<Training, User> findAllMentors() throws DaoException;


  boolean checkLogin(String login) throws DaoException;

  boolean checkEmail(String email) throws DaoException;

  void deleteUser(int userId) throws DaoException;

  List<User> findStudentsByIdTraining(int trainingId) throws DaoException;
}