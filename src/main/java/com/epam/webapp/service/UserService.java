package com.epam.webapp.service;

import com.epam.webapp.dao.DAOFactory;
import com.epam.webapp.dao.UserDAO;
import com.epam.webapp.connectionpool.exception.ConnectionPoolException;
import com.epam.webapp.dao.exception.DAOException;
import com.epam.webapp.dao.impl.UserDAOImpl;
import com.epam.webapp.entity.User;
import com.google.protobuf.ServiceException;

public class UserService {
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
    user.setType(type);
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
  public final  void addTrainingToStudent(int userId, int trainingId) throws ConnectionPoolException {
    UserDAO userDAO = DAOFactory.getUserDAO();
    userDAO.addTrainingToStudent(userId, trainingId);
  }
}
