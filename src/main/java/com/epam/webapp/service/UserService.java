package com.epam.webapp.service;

import com.epam.webapp.dao.DAOFactory;
import com.epam.webapp.dao.UserDAO;
import com.epam.webapp.connectionpool.exception.ConnectionPoolException;
import com.epam.webapp.dao.exception.DAOException;
import com.epam.webapp.entity.*;
import com.google.protobuf.ServiceException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {

  public final List<User> getAllMentors() throws ConnectionPoolException {
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
    user.setType(UserTypes.getUserType(type));
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

  public final UserTypes[] usersType() {
    return UserTypes.values();
  }

  public final UserStatus[] userStatuses() {
    return UserStatus.values();
  }

  public final boolean updateUserType(int userId, UserTypes type, UserStatus status) throws ConnectionPoolException {
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
    tasks = userDAO
  }
}
