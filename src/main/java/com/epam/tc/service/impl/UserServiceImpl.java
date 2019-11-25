package com.epam.tc.service.impl;

import com.epam.tc.dao.*;
import com.epam.tc.entity.*;
import com.epam.tc.service.UserService;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.*;

/**
 * @author alex raby
 * @version 1.0
 * this class implements interface methods UserService {@link UserService}
 * methods of this class catch DaoException {@link DaoException} and throw ServiceException {@link ServiceException}
 */
public class UserServiceImpl implements UserService {

  /**
   * class object Logger {@link Logger}
   * writes important events to a log file
   */
  public final Logger logger = LogManager.getLogger(UserServiceImpl.class);

  public final Map<Training, User> findAllMentors() throws ServiceException {
    UserDao userDao = DaoFactory.getUserDao();
    try {
      return userDao.findAllMentors();
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException(e);
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
        return user;
      }
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException(e);
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

//  public final User checkRegistrationField(String login, String password, String name, String surname,
//                                           String email) throws ServiceException {
//    User user = new User();
//    user.setLogin(login);
//    user.setPassword(password);
//    user.setEmail(email);
//    user.setName(name);
//    user.setSurname(surname);
//    UserDao authorizationUserDAOImpl = DaoFactory.getUserDao();
//    try {
//      user = authorizationUserDAOImpl.registration(user);
//      if (!user.getLogin().isEmpty()) {
//        return user;
//      } else {
//
//      }
//    } catch (DaoException e) {
//      logger.error(e);
//      throw new ServiceException(e);
//    }
//  }

  public final boolean checkEnrolled(int userId, int trainingId) throws ServiceException {
    UserDao userDao = DaoFactory.getUserDao();
    try {
      return userDao.checkEnrolled(userId, trainingId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException(e);
    }
  }

  public final List<User> getAllUser() throws ServiceException {
    UserDao userDao = DaoFactory.getUserDao();
    List<User> users;
    try {
      users = userDao.findAllUser();
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException(e);
    }
    return users;
  }

  public final UserType[] usersType() {
    return UserType.values();
  }

  public final UserStatus[] userStatuses() {
    return UserStatus.values();
  }

  public final void updateUserType(int userId, UserType type, UserStatus status) throws ServiceException {
    UserDao userDao = DaoFactory.getUserDao();
    try {
      userDao.updateUserType(userId, type, status);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException(e);
    }
  }

  public final List<User> findStudentsForTraining(int trainingId) throws ServiceException {
    UserDao userDao = DaoFactory.getUserDao();
    List<User> students;
    try {
      students = userDao.findStudentsForTraining(trainingId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException(e);
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
      throw new ServiceException(e);
    }
    return tasks;
  }

  public final boolean sendOfferConsultations(int trainingId, Date date, BigDecimal price) throws ServiceException {
    ConsultationDao consultationDao = DaoFactory.getConsultationDao();
    try {
      return consultationDao.sendOfferConsultations(trainingId, date, price);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException(e);
    }
  }

  public final Map<Training, Date> findConsultationsOffer(int mentorId) throws ServiceException {
    ConsultationDao consultationDao = DaoFactory.getConsultationDao();
    Map<Training, Date> consultations;
    try {
      consultations = consultationDao.findConsultationsOffer(mentorId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException(e);
    }
    return consultations;
  }

  public final boolean sendAgreement(int trainingId, Date date, boolean mark) throws ServiceException {
    ConsultationDao consultationDao = DaoFactory.getConsultationDao();
    try {
      return consultationDao.sendAgreement(trainingId, date, mark);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException(e);
    }
  }
}
