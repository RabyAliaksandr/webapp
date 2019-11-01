package com.epam.webapp.dao;

import com.epam.webapp.connectionpool.exception.ConnectionPoolException;
import com.epam.webapp.dao.exception.DAOException;
import com.epam.webapp.entity.User;


public interface UserDAO {
   User authorization(User user) throws ConnectionPoolException, DAOException;
   User registration(User user) throws DAOException, ConnectionPoolException;
   void updateUser(User user) throws DAOException, ConnectionPoolException;
}