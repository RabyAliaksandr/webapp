package com.epam.webapp.service;

import com.epam.webapp.dao.DAOFactory;
import com.epam.webapp.dao.UserDAO;
import com.epam.webapp.connectionpool.ConnectionPoolException;
import com.epam.webapp.dao.exception.DAOException;
import com.epam.webapp.entity.*;
import com.google.protobuf.ServiceException;

import java.sql.Date;
import java.sql.SQLException;
import java.util.*;

public class UserService {

  public final Map<Task, User> getAllMentors() throws ConnectionPoolException {
    UserDAO userDAO = DAOFactory.getUserDAO();
    return userDAO.getAllMentors();
  }

  public final static User checkLogin(String login, String password) throws ServiceException {
    User user = new User();
    user.setLogin(login);
    user.setPassword(password);
    UserDAO authorizationUserDAOImpl = DAOFactory.getUserDAO();
    try {
      user = authorizationUserDAOImpl.authorization(user);
      if (user != null) {
        return user;
      } else {
//TODO return error page
        return null;
      }

    } catch (DAOException | ConnectionPoolException e) {
      throw new ServiceException("Error access database", e);
    }
  }

  public final static User checkRegistrationField(String login, String password, String name, String surname,
                                                  String email, String type) throws ServiceException {
    User user = new User();
    user.setLogin(login);
    user.setPassword(password);
    user.setType(UserType.getUserType(type));
    user.setEmail(email);
    user.setName(name);
    user.setSurname(surname);
    UserDAO authorizationUserDAOImpl = DAOFactory.getUserDAO();
    try {
      user = authorizationUserDAOImpl.registration(user);
      if (user != null) {
        return user;
      } else {
//TODO return error page
        return null;
    }
    } catch (DAOException | ConnectionPoolException e) {
      throw new ServiceException("Error access database", e);
    }
  }

  public final void grade(int assessment, int userId, int trainingId) throws ConnectionPoolException {
    UserDAO userDAO = DAOFactory.getUserDAO();
    userDAO.grade(assessment, userId, trainingId);
  }

  public final void addTrainingToStudent(int userId, int trainingId) throws ConnectionPoolException {
    UserDAO userDAO = DAOFactory.getUserDAO();
    userDAO.addTrainingToStudent(userId, trainingId);
  }

  public final boolean checkEnrolled(int userId, int trainingId) throws ConnectionPoolException {
    UserDAO userDAO = DAOFactory.getUserDAO();
    return userDAO.checkEnrolled(userId, trainingId);
  }

  public final List<User> getAllUser() throws ConnectionPoolException, SQLException {
    UserDAO userDAO = DAOFactory.getUserDAO();
    List<User> users = new ArrayList<>();
    users = userDAO.getAllUser();
    return users;
  }

  public final UserType[] usersType() {
    return UserType.values();
  }

  public final UserStatus[] userStatuses() {
    return UserStatus.values();
  }

  public final boolean updateUserType(int userId, UserType type, UserStatus status) throws ConnectionPoolException {
    UserDAO userDAO = DAOFactory.getUserDAO();
    return userDAO.updateUserType(userId, type, status);
  }

  public final List<Student> getStudentsForTraining(int trainingId) throws ConnectionPoolException {
    UserDAO userDAO = DAOFactory.getUserDAO();
    List<Student> students = new ArrayList<>();
    students = userDAO.getStudentsForTraining(trainingId);
    return students;
  }

  public final List<Task> getTaskByStudentId(int studentId) {
    UserDAO userDAO = DAOFactory.getUserDAO();
    List<Task> tasks = new ArrayList<>();
//    tasks = userDAO
    return tasks;
  }

  public final List<Task> findStudentsMarkForTrainingsTask(int studentId, int trainingId) throws ConnectionPoolException {
    UserDAO userDAO = DAOFactory.getUserDAO();
    List<Task> tasks = new ArrayList<>();
    tasks = userDAO.findStudentsMarkForTrainingsTask(studentId, trainingId);
    return tasks;
  }

  public final boolean sendOfferConsultations(int trainingId, Date date) throws ConnectionPoolException {
    UserDAO userDAO = DAOFactory.getUserDAO();
    return userDAO.sendOfferConsultations(trainingId, date);
  }

  public final Map<Training, Date> findConsultationsOffer(int mentorId) throws ConnectionPoolException {
    UserDAO userDAO = DAOFactory.getUserDAO();
    Map<Training, Date> consultations = new HashMap<>();
    consultations = userDAO.findConsultationsOffer(mentorId);
    return consultations;
  }

  public final boolean sendAgreement(int trainingId, Date date, boolean mark) throws ConnectionPoolException {
    UserDAO userDAO = DAOFactory.getUserDAO();
    return userDAO.sendAgreement(trainingId, date, mark);
  }


}
