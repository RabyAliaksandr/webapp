package com.epam.webapp.service;

import com.epam.webapp.dao.DAOFactory;
import com.epam.webapp.dao.DataListsDAO;
import com.epam.webapp.connectionpool.exception.ConnectionPoolException;
import com.epam.webapp.dao.TrainingsOperationDAO;
import com.epam.webapp.dao.exception.DAOException;
import com.epam.webapp.entity.Student;
import com.epam.webapp.entity.Training;

import java.util.List;
import java.util.Map;

public class TrainingsService {

  public Training getTrainingByIdTraining(int trainingId) throws ConnectionPoolException {
    DataListsDAO dataListsDAO = DAOFactory.getDataListsDAO();
    return dataListsDAO.getTrainingByIdTraining(trainingId);
  }

  public final List<Training> getCompletedTrainingForStudent(int studentId) throws ConnectionPoolException {
    DataListsDAO dataListsDAO = DAOFactory.getDataListsDAO();
    return dataListsDAO.getCompletedTrainingForStudent(studentId);
  }


  public final List<Training> getAllTrainingsForStudent(int studentId) throws ConnectionPoolException {// FIXME: 29.10.2019 make  static
    DataListsDAO dataListsDAO = DAOFactory.getDataListsDAO();
    return dataListsDAO.getTrainingsForStudent(studentId);
  }
  public final  List<Training> getAllTrainings() throws ConnectionPoolException {// FIXME: 29.10.2019 make  static
    DataListsDAO dataListsDAO = DAOFactory.getDataListsDAO();
    return dataListsDAO.getTraining();
  }

  public final  void addTrainingToStudent(int idStudent, int idTraining) {
    TrainingsOperationDAO operationDAO = DAOFactory.getOperationDAO();
    try {
      operationDAO.addTrainingsToStudent(idStudent, idTraining);
    } catch (DAOException e) {
      e.printStackTrace();
    }
  }

  public final List<Student> getStudentsByIdTraining(int trainingId) throws ConnectionPoolException {
    DataListsDAO dataListsDAO = DAOFactory.getDataListsDAO();
    return dataListsDAO.getStudentsByIdTraining(trainingId);
  }

  public final List<Training> getTrainingForMentor(int mentorId) throws ConnectionPoolException {
    DataListsDAO dataListsDAO = DAOFactory.getDataListsDAO();
    return dataListsDAO.getTrainingForMentor(mentorId);
  }

  public final Map<String, String> getTopicsForTraining(int trainingId) throws ConnectionPoolException {
    DataListsDAO dataListsDAO = DAOFactory.getDataListsDAO();
    return dataListsDAO.getTopicsForTraining(trainingId);
  }

  public final String getTopic(int trainingId, String topicName) throws ConnectionPoolException {
    DataListsDAO dataListsDAO = DAOFactory.getDataListsDAO();
    return dataListsDAO.getTopic(trainingId, topicName);
  }
}
