package com.epam.tc.service.impl;

import com.epam.tc.dao.ConsultationDao;
import com.epam.tc.dao.DaoException;
import com.epam.tc.dao.DaoFactory;
import com.epam.tc.dao.UserDao;
import com.epam.tc.entity.*;
import com.epam.tc.service.UserService;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.*;

/**
 * this class implements interface methods UserService {@link UserService} methods of this class
 * catch DaoException {@link DaoException} and throw ServiceException {@link ServiceException}
 *
 * @author alex raby
 * @version 1.0
 */
public class UserServiceImpl implements UserService {

  /**
   * class object Logger {@link Logger}
   * writes important events to a log file
   */
  private static Logger logger = LogManager.getLogger(UserServiceImpl.class);

  @Override
  public List<User> findAllMentors() throws ServiceException {
    UserDao userDao = DaoFactory.getUserDao();
    try {
      return userDao.findAllMentors();
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException(e);
    }
  }

  /**
   * {@inheritDoc}
   */
  public Map<Training, User> findMentorsAndTrainings() throws ServiceException {
    UserDao userDao = DaoFactory.getUserDao();
    try {
      return userDao.findMentorsAndTrainings();
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException(e);
    }
  }

  /**
   * {@inheritDoc}
   */
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

  /**
   * {@inheritDoc}
   */
  public User checkLogin(String login, String password) throws ServiceException {
    User user = new User();
    user.setLogin(login);
    user.setPassword(password);
    UserDao authorizationUserDAOImpl = DaoFactory.getUserDao();
    try {
      user = authorizationUserDAOImpl.authorization(user);
      return user;
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException(e);
    }
  }

  /**
   * {@inheritDoc}
   */
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

  /**
   * {@inheritDoc}
   */
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

  /**
   * {@inheritDoc}
   */
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

//  public  User checkRegistrationField(String login, String password, String name, String surname,
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

  /**
   * {@inheritDoc}
   */
  public boolean checkEnrolled(int userId, int trainingId) throws ServiceException {
    UserDao userDao = DaoFactory.getUserDao();
    try {
      return userDao.checkEnrolled(userId, trainingId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException(e);
    }
  }

  /**
   * {@inheritDoc}
   */
  public List<User> getAllUser() throws ServiceException {
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

  /**
   * {@inheritDoc}
   */
  public UserType[] usersType() {
    return UserType.values();
  }

  /**
   * {@inheritDoc}
   */
  public UserStatus[] userStatuses() {
    return UserStatus.values();
  }

  /**
   * {@inheritDoc}
   */
  public void updateUserType(int userId, UserType type, UserStatus status) throws ServiceException {
    UserDao userDao = DaoFactory.getUserDao();
    try {
      userDao.updateUserType(userId, type, status);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException(e);
    }
  }

  /**
   * {@inheritDoc}
   */
  public Map<User, Integer> findStudentsForTraining(int trainingId) throws ServiceException {
    UserDao userDao = DaoFactory.getUserDao();
    try {
      return userDao.findStudentsForTraining(trainingId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException(e);
    }
  }

  /**
   * {@inheritDoc}
   */
  public List<Task> findStudentsMarkForTrainingsTask(int studentId, int trainingId) throws ServiceException {
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

  /**
   * {@inheritDoc}
   */
  public boolean sendOfferConsultations(int trainingId, Date date, BigDecimal price) throws ServiceException {
    ConsultationDao consultationDao = DaoFactory.getConsultationDao();
    try {
      return consultationDao.sendOfferConsultations(trainingId, date, price);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException(e);
    }
  }

  /**
   * {@inheritDoc}
   */
  public Map<Training, Date> findConsultationsOffer(int mentorId) throws ServiceException {
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

  /**
   * {@inheritDoc}
   */
  public boolean sendAgreement(int trainingId, Date date, boolean mark) throws ServiceException {
    ConsultationDao consultationDao = DaoFactory.getConsultationDao();
    try {
      return consultationDao.sendAgreement(trainingId, date, mark);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException(e);
    }
  }
}
