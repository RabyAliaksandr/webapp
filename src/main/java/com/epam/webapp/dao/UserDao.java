package com.epam.webapp.dao;

import com.epam.webapp.entity.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;


public interface UserDao {

  User authorization(User user) throws DaoException;

  User registration(User user) throws DaoException;

  void grade(int assessment, int userId, int trainingId) throws DaoException;

  void addTrainingToStudent(int userId, int trainingId) throws DaoException;

  boolean checkEnrolled(int userId, int trainingId) throws DaoException;

  List<User> findAllUser() throws DaoException;

  boolean updateUserType(int userId, UserType type, UserStatus status) throws DaoException;

  List<Student> findStudentsForTraining(int trainingId) throws DaoException;

  List<Task> findStudentsMarkForTrainingsTask(int studentId, int trainingId) throws DaoException;

  boolean sendOfferConsultations(int trainingId, Date date, BigDecimal price) throws DaoException;

  Map<Training, Date> findConsultationsOffer(int mentorId) throws DaoException;

  boolean sendAgreement(int trainingId, Date date, boolean mark) throws DaoException;

  Map<Task, User> findAllMentors() throws DaoException;

  boolean checkLogin(String login) throws DaoException;

  boolean checkEmail(String email) throws DaoException;

  void deleteUser(int userId) throws DaoException;

}