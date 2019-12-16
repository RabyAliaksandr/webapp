package com.epam.tc.dao;

import com.epam.tc.entity.*;
import com.epam.tc.validator.RegEx;

import java.util.List;
import java.util.Map;

/**
 * this interface contains methods for working with DataBase with User
 *
 * @author alex raby
 * @version 1.0
 * @see User
 */
public interface UserDao {

  /**
   * Authorization user.
   *
   * @param user the user {@link User}
   * @return the user if such exists on the given login and password or will return empty
   * @throws DaoException the dao exception
   */
  User authorization(User user) throws DaoException;

  /**
   * Registration user.
   *
   * @param user new user {@link User}
   * @return the user to log in
   * @throws DaoException the dao exception
   */
  User registration(User user) throws DaoException;

  /**
   * Set final grade for Training
   *
   * @param assessment the assessment from 1 to 10
   * @param userId     the user id who to mark {@link User}
   * @param trainingId the training id {@link Training}
   * @throws DaoException the dao exception
   */
  void grade(int assessment, int userId, int trainingId) throws DaoException;

  /**
   * Check enrolled boolean.
   *
   * @param userId     the user id
   * @param trainingId the training id {@link Training}
   * @return true if Student {@link UserType#STUDENT} attends this Training
   * @throws DaoException the dao exception
   */
  boolean checkEnrolled(int userId, int trainingId) throws DaoException;

  /**
   * Find all user list.
   *
   * @return the list with all Students {@link UserType#STUDENT}
   * @throws DaoException the dao exception
   */
  List<User> findAllUser() throws DaoException;

  /**
   * Update user type.
   *
   * @param userId the user id which needs to be changed
   * @param type   the type new Type {@link UserType}
   * @param status the status new Status {@link UserStatus}
   * @throws DaoException the dao exception
   */
  void updateUserType(int userId, UserType type, UserStatus status) throws DaoException;

  /**
   * Find students for training list.
   *
   * @param trainingId the training id for which to look {@link Training}
   * @return the list with Students {@link UserType#STUDENT}
   * @throws DaoException the dao exception
   */
  Map<User, Integer> findStudentsForTraining(int trainingId) throws DaoException;

  /**
   * Find students mark for trainings task list.
   *
   * @param studentId  the student id
   * @param trainingId the training id
   * @return the list with Tasks for this Student
   * @throws DaoException the dao exception
   * @see UserType#STUDENT
   * @see Training
   * @see Task
   * @see User
   */
  List<Task> findStudentsMarkForTrainingsTask(int studentId, int trainingId) throws DaoException;

  /**
   * finding all users who have type 'mentor'
   *
   * @return object Map with Training and Mentor
   * @throws DaoException the dao exception
   * @see UserType#MENTOR
   * @see User
   * @see Training
   * @see UserType
   */
  Map<Training, User> findMentorsAndTrainings() throws DaoException;


  /**
   * Check login boolean.
   *
   * @param login the login
   * @return true if matches RegEx {@link RegEx#LOGIN_PATTERN}
   * @throws DaoException the dao exception
   */
  boolean checkLogin(String login) throws DaoException;

  /**
   * Check email boolean.
   *
   * @param email the email
   * @return true if matches RegEx {@link RegEx#EMAIL_PATTERN}
   * @throws DaoException the dao exception
   */
  boolean checkEmail(String email) throws DaoException;

  /**
   * Delete user.
   *
   * @param userId the user id which you want to remove
   * @throws DaoException the dao exception
   * @see User
   */
  void deleteUser(int userId) throws DaoException;

  /**
   * Find students by id training list.
   *
   * @param trainingId the training id {@link Training}
   * @return the list Users {@link User}
   * @throws DaoException the dao exception
   */
  List<User> findStudentsByIdTraining(int trainingId) throws DaoException;

  /**
   * Find all mentors list.
   *
   * @return the list Mentors
   * @throws DaoException the dao exception
   * @see User
   * @see UserType#MENTOR
   */
  List<User> findAllMentors() throws DaoException;
}