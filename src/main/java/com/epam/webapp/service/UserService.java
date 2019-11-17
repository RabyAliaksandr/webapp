package com.epam.webapp.service;

import com.epam.webapp.entity.*;
import com.google.protobuf.ServiceException;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface UserService {

  Map<Task, User> findAllMentors() throws ServiceException;

  User checkLogin(String login, String password) throws ServiceException;

  User checkRegistrationField(String login, String password, String name, String surname,
                              String email, String type) throws ServiceException;

  void grade(int assessment, int userId, int trainingId) throws ServiceException;

  void addTrainingToStudent(int userId, int trainingId) throws ServiceException;

  boolean checkEnrolled(int userId, int trainingId) throws ServiceException;

  List<User> getAllUser() throws ServiceException;

  UserType[] usersType();

  UserStatus[] userStatuses();

  boolean updateUserType(int userId, UserType type, UserStatus status) throws ServiceException;

  List<Student> findStudentsForTraining(int trainingId) throws ServiceException;

  List<Task> findStudentsMarkForTrainingsTask(int studentId, int trainingId) throws ServiceException;

  boolean sendOfferConsultations(int trainingId, Date date) throws ServiceException;

  Map<Training, Date> findConsultationsOffer(int mentorId) throws ServiceException;

  boolean sendAgreement(int trainingId, Date date, boolean mark) throws ServiceException;
}
