package com.epam.webapp.dao;

import com.epam.webapp.dao.exception.DAOException;

public interface TrainingsOperationDAO {

  void addTrainingsToStudent(int idStudent, int idTraining) throws DAOException;
}
