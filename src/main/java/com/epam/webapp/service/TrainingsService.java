package com.epam.webapp.service;

import com.epam.webapp.dao.DAOFactory;
import com.epam.webapp.dao.DataListsDAO;
import com.epam.webapp.connectionpool.exception.ConnectionPoolException;
import com.epam.webapp.dao.TrainingsOperationDAO;
import com.epam.webapp.dao.exception.DAOException;
import com.epam.webapp.entity.Student;
import com.epam.webapp.entity.Training;

import javax.xml.crypto.Data;
import java.util.HashMap;
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

  public final List<Training> getAllTrainings() throws ConnectionPoolException {// FIXME: 29.10.2019 make  static
    DataListsDAO dataListsDAO = DAOFactory.getDataListsDAO();
    return dataListsDAO.getTraining();
  }

  public final void addTrainingToStudent(int idStudent, int idTraining) {
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

  public boolean updateTrainingsInformation(int trainingId, String information) throws ConnectionPoolException {
    DataListsDAO dataListsDAO = DAOFactory.getDataListsDAO();
    return dataListsDAO.updateTrainingsInformation(trainingId, information);
  }

  public final boolean addTopicForTraining(int trainingId, String topicsName, String topicsText) throws ConnectionPoolException {
    DataListsDAO dataListsDAO = DAOFactory.getDataListsDAO();
    return dataListsDAO.addTopicForTraining(trainingId, topicsName, topicsText);
  }

  public final boolean addTaskForTraining(int trainingId, String taskName, String taskText) throws ConnectionPoolException {
    DataListsDAO dataListsDAO = DAOFactory.getDataListsDAO();
    return dataListsDAO.addTaskForTraining(trainingId, taskName, taskText);
  }

  public final Map<String, String> getTasksListForTraining(int trainingId) throws ConnectionPoolException {
    DataListsDAO dataListsDAO = DAOFactory.getDataListsDAO();
    Map<String, String> tasks = new HashMap<>();
    tasks = dataListsDAO.getTasksListForTraining(trainingId);
    return tasks;
  }

  public final boolean createTraining(String trainingName, int mentorId, String trainingDescription) throws ConnectionPoolException {
    DataListsDAO dataListsDAO = DAOFactory.getDataListsDAO();
    return dataListsDAO.createTraining(trainingName, mentorId, trainingDescription);
  }

  public final boolean updateTrainingsTopic(String topicName, String topicNewName, String topic, int trainingId) throws ConnectionPoolException {
    DataListsDAO dataListsDAO = DAOFactory.getDataListsDAO();
    return dataListsDAO.updateTrainingsTopic(topicName, topicNewName, topic, trainingId);
  }
}
