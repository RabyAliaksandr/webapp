package com.epam.webapp.dao;

import com.epam.webapp.dao.exception.DaoException;

public interface TrainingsOperationDao {

  void addTrainingsToStudent(int idStudent, int idTraining) throws DaoException;
}
