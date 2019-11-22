package com.epam.webapp.service.impl;

import com.epam.webapp.dao.DaoFactory;
import com.epam.webapp.dao.UserDao;
import com.epam.webapp.dao.DaoException;
import com.epam.webapp.entity.*;
import com.epam.webapp.service.UserService;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.*;

public class UserServiceImpl implements UserService {

  public final Logger logger = LogManager.getLogger(UserService.class);

  public final Map<Task, User> findAllMentors() throws ServiceException {
    UserDao userDao = DaoFactory.getUserDao();
    try {
      return userDao.findAllMentors();
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  @Override
  public void deleteUser(int userId) throws ServiceException {
    UserDao userDao = DaoFactory.getUserDao();
    try {
      userDao.deleteUser(userId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException(e);
    }
  }

  public final User checkLogin(String login, String password) throws ServiceException {
    User user = new User();
    user.setLogin(login);
    user.setPassword(password);
    UserDao authorizationUserDAOImpl = DaoFactory.getUserDao();
    try {
      user = authorizationUserDAOImpl.authorization(user);
      if (user != null) {
        return user;
      } else {
//TODO return error page
        return null; //FIXME
      }

    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  @Override
  public void registration(User user) throws ServiceException {
    UserDao userDao = DaoFactory.getUserDao();
    try {
      userDao.registration(user);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException(e);
    }
  }

  @Override
  public boolean checkLogin(String login) throws ServiceException {
    UserDao userDao = DaoFactory.getUserDao();
    try {
      return userDao.checkLogin(login);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException(e);
    }
  }

  @Override
  public boolean checkEmail(String email) throws ServiceException {
    UserDao userDao = DaoFactory.getUserDao();
    try {
      return userDao.checkEmail(email);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException(e);
    }
  }

  public final User checkRegistrationField(String login, String password, String name, String surname,
                                           String email) throws ServiceException {
    User user = new User();
    user.setLogin(login);
    user.setPassword(password);
    user.setEmail(email);
    user.setName(name);
    user.setSurname(surname);
    UserDao authorizationUserDAOImpl = DaoFactory.getUserDao();
    try {
      user = authorizationUserDAOImpl.registration(user);
      if (!user.getLogin().isEmpty()) {
        return user;
      } else {
//TODO return error page
        return null;// FIXME
      }
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final void grade(int assessment, int userId, int trainingId) throws ServiceException {
    UserDao userDao = DaoFactory.getUserDao();
    try {
      userDao.grade(assessment, userId, trainingId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final void addTrainingToStudent(int userId, int trainingId) throws ServiceException {
    UserDao userDao = DaoFactory.getUserDao();
    try {
      userDao.addTrainingToStudent(userId, trainingId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final boolean checkEnrolled(int userId, int trainingId) throws ServiceException {
    UserDao userDao = DaoFactory.getUserDao();
    try {
      return userDao.checkEnrolled(userId, trainingId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final List<User> getAllUser() throws ServiceException {
    UserDao userDao = DaoFactory.getUserDao();
    List<User> users;
    try {
      users = userDao.findAllUser();
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
    return users;
  }

  public final UserType[] usersType() {
    return UserType.values();
  }

  public final UserStatus[] userStatuses() {
    return UserStatus.values();
  }

  public final boolean updateUserType(int userId, UserType type, UserStatus status) throws ServiceException {
    UserDao userDao = DaoFactory.getUserDao();
    try {
      return userDao.updateUserType(userId, type, status);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final List<Student> findStudentsForTraining(int trainingId) throws ServiceException {
    UserDao userDao = DaoFactory.getUserDao();
    List<Student> students;
    try {
      students = userDao.findStudentsForTraining(trainingId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
    return students;
  }

  public final List<Task> findStudentsMarkForTrainingsTask(int studentId, int trainingId) throws ServiceException {
    UserDao userDao = DaoFactory.getUserDao();
    List<Task> tasks;
    try {
      tasks = userDao.findStudentsMarkForTrainingsTask(studentId, trainingId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
    return tasks;
  }

  public final boolean sendOfferConsultations(int trainingId, Date date, BigDecimal price) throws ServiceException {
    UserDao userDao = DaoFactory.getUserDao();
    try {
      return userDao.sendOfferConsultations(trainingId, date, price);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final Map<Training, Date> findConsultationsOffer(int mentorId) throws ServiceException {
    UserDao userDao = DaoFactory.getUserDao();
    Map<Training, Date> consultations;
    try {
      consultations = userDao.findConsultationsOffer(mentorId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
    return consultations;
  }

  public final boolean sendAgreement(int trainingId, Date date, boolean mark) throws ServiceException {
    UserDao userDao = DaoFactory.getUserDao();
    try {
      return userDao.sendAgreement(trainingId, date, mark);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }
}
