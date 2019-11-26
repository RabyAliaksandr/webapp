package com.epam.tc.dao;

import com.epam.tc.entity.*;
import com.google.protobuf.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * The interface User dao.
 *
 * @author alex raby
 * @version 1.0 this interface contains methods for working with DataBase with User
 * @see User
 */
public interface UserDao {

  /**
   * Authorization user.
   *
   * @param user the user
   * @return the user
   * @throws DaoException the dao exception
   */
  User authorization(User user) throws DaoException;

  /**
   * Registration user.
   *
   * @param user the user
   * @return the user
   * @throws DaoException the dao exception
   */
  User registration(User user) throws DaoException;

  /**
   * Grade.
   *
   * @param assessment the assessment
   * @param userId     the user id
   * @param trainingId the training id
   * @throws DaoException the dao exception
   */
  void grade(int assessment, int userId, int trainingId) throws DaoException;

  /**
   * Check enrolled boolean.
   *
   * @param userId     the user id
   * @param trainingId the training id
   * @return the boolean
   * @throws DaoException the dao exception
   */
  boolean checkEnrolled(int userId, int trainingId) throws DaoException;

  /**
   * Find all user list.
   *
   * @return the list
   * @throws DaoException the dao exception
   */
  List<User> findAllUser() throws DaoException;

  /**
   * Update user type.
   *
   * @param userId the user id
   * @param type   the type
   * @param status the status
   * @throws DaoException the dao exception
   */
  void updateUserType(int userId, UserType type, UserStatus status) throws DaoException;

  /**
   * Find students for training list.
   *
   * @param trainingId the training id
   * @return the list
   * @throws DaoException the dao exception
   */
  List<User> findStudentsForTraining(int trainingId) throws DaoException;

  /**
   * Find students mark for trainings task list.
   *
   * @param studentId  the student id
   * @param trainingId the training id
   * @return the list
   * @throws DaoException the dao exception
   */
  List<Task> findStudentsMarkForTrainingsTask(int studentId, int trainingId) throws DaoException;

  /**
   * finding all users who have type 'mentor'
   *
   * @return map map
   * @throws DaoException the dao exception
   * @see User
   * @see Training
   * @see UserType
   */
  Map<Training, User> findAllMentors() throws DaoException;


  /**
   * Check login boolean.
   *
   * @param login the login
   * @return the boolean
   * @throws DaoException the dao exception
   */
  boolean checkLogin(String login) throws DaoException;

  /**
   * Check email boolean.
   *
   * @param email the email
   * @return the boolean
   * @throws DaoException the dao exception
   */
  boolean checkEmail(String email) throws DaoException;

  /**
   * Delete user.
   *
   * @param userId the user id
   * @throws DaoException the dao exception
   */
  void deleteUser(int userId) throws DaoException;

  /**
   * Find students by id training list.
   *
   * @param trainingId the training id
   * @return the list
   * @throws DaoException the dao exception
   */
  List<User> findStudentsByIdTraining(int trainingId) throws DaoException;
}