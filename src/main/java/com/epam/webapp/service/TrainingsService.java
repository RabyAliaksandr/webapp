package com.epam.webapp.service;

import com.epam.webapp.dao.DAOFactory;
import com.epam.webapp.dao.DataListsDAO;
import com.epam.webapp.connectionpool.ConnectionPoolException;
import com.epam.webapp.dao.TrainingsOperationDAO;
import com.epam.webapp.dao.exception.DAOException;
import com.epam.webapp.entity.Student;
import com.epam.webapp.entity.Task;
import com.epam.webapp.entity.Topic;
import com.epam.webapp.entity.Training;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

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

  public final List<Topic> getTopicsForTraining(int trainingId) throws ConnectionPoolException {
    DataListsDAO dataListsDAO = DAOFactory.getDataListsDAO();
    System.out.println("вызываю сервис");
    List<Topic> topics = new ArrayList<>();
    topics = dataListsDAO.getTopicsForTraining(trainingId);
    return topics;
  }

  public final Topic getTopic(int topicId) throws ConnectionPoolException {
    DataListsDAO dataListsDAO = DAOFactory.getDataListsDAO();
    return dataListsDAO.getTopic(topicId);
  }

  public boolean updateTrainingsInformation(int trainingId, String trainingName, String information) throws ConnectionPoolException {
    DataListsDAO dataListsDAO = DAOFactory.getDataListsDAO();
    return dataListsDAO.updateTrainingsInformation(trainingId, trainingName, information);
  }

  public final boolean addTopicForTraining(int trainingId, String topicsName, String topicsText) throws ConnectionPoolException {
    DataListsDAO dataListsDAO = DAOFactory.getDataListsDAO();
    return dataListsDAO.addTopicForTraining(trainingId, topicsName, topicsText);
  }

  public final boolean addTaskForTraining(int trainingId, String taskName, String taskText) throws ConnectionPoolException {
    DataListsDAO dataListsDAO = DAOFactory.getDataListsDAO();
    return dataListsDAO.addTaskForTraining(trainingId, taskName, taskText);
  }

  public final List<Task> getTasksListForTraining(int trainingId) throws ConnectionPoolException {
    DataListsDAO dataListsDAO = DAOFactory.getDataListsDAO();
    List<Task> tasks = new ArrayList<>();
    tasks = dataListsDAO.getTasksListForTraining(trainingId);
    return tasks;
  }

  public final boolean createTraining(String trainingName, int mentorId, String trainingDescription) throws ConnectionPoolException {
    DataListsDAO dataListsDAO = DAOFactory.getDataListsDAO();
    return dataListsDAO.createTraining(trainingName, mentorId, trainingDescription);
  }

  public final boolean updateTrainingsTopic(int topicId, String topicName, String topic) throws ConnectionPoolException {
    DataListsDAO dataListsDAO = DAOFactory.getDataListsDAO();
    return dataListsDAO.updateTrainingsTopic(topicId, topicName, topic);
  }

  public final boolean checkTopicStatus(int userId, int topicId) throws ConnectionPoolException {
    DataListsDAO dataListsDAO = DAOFactory.getDataListsDAO();
    return dataListsDAO.checkTopicStatus(userId, topicId);
  }

  public final boolean markTopic(int userId, int topicId) throws ConnectionPoolException {

    DataListsDAO dataListsDAO = DAOFactory.getDataListsDAO();
    return dataListsDAO.markTopic(userId, topicId);
  }

  public final boolean checkTrainingStatusForStudent(int userId, int trainingId) throws ConnectionPoolException {
    DataListsDAO dataListsDAO = DAOFactory.getDataListsDAO();
    return dataListsDAO.checkTrainingStatusForStudent(userId, trainingId);
  }

  public final Task getTask(int taskId) throws ConnectionPoolException {
    DataListsDAO dataListsDAO = DAOFactory.getDataListsDAO();
    Task task = new Task();
    task = dataListsDAO.getTask(taskId);
    return task;
  }

  public final boolean updateTask(int taskId, String taskName, String task) throws ConnectionPoolException {
    DataListsDAO dataListsDAO = DAOFactory.getDataListsDAO();
    return dataListsDAO.updateTask(taskId, taskName, task);
  }

  public final boolean checkTaskStatus(int userId, int taskId) throws ConnectionPoolException {
    DataListsDAO dataListsDAO = DAOFactory.getDataListsDAO();
    return dataListsDAO.checkTaskStatus(userId, taskId);
  }

  public final boolean sendSolution(int userId, int taskId, String answer) throws ConnectionPoolException {
    DataListsDAO dataListsDAO = DAOFactory.getDataListsDAO();
    return dataListsDAO.sendSolution(userId, taskId, answer);
  }

  public final String findTaskSolution(int studentId, int taskId) throws ConnectionPoolException {
    DataListsDAO dataListsDAO = DAOFactory.getDataListsDAO();
    String solution;
    solution = dataListsDAO.findTaskSolution(studentId, taskId);
    return solution;
  }

  public final boolean gradeTask(int studentId, int taskId, int mark) throws ConnectionPoolException {
    DataListsDAO dataListsDAO = DAOFactory.getDataListsDAO();
    return dataListsDAO.gradeTask(studentId, taskId, mark);
  }
}
