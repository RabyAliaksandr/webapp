package com.epam.webapp.service.impl;

import com.epam.webapp.dao.DaoFactory;
import com.epam.webapp.dao.DataListsDao;
import com.epam.webapp.dao.TrainingsOperationDao;
import com.epam.webapp.dao.exception.DaoException;
import com.epam.webapp.entity.Student;
import com.epam.webapp.entity.Task;
import com.epam.webapp.entity.Topic;
import com.epam.webapp.entity.Training;
import com.epam.webapp.service.TrainingService;
import com.epam.webapp.service.exception.ServiceException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrainingsServiceImpl implements TrainingService {

  public Training findTrainingByIdTraining(int trainingId) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.findTrainingByIdTraining(trainingId);
    } catch (DaoException e) {
      throw new ServiceException("Error access database", e);
    }
  }

  public final List<Training> findCompletedTrainingForStudent(int studentId) throws ServiceException {
    DataListsDao dataListsDAO = DaoFactory.getDataListDao();
    try {
      return dataListsDAO.findCompletedTrainingForStudent(studentId);
    } catch (DaoException e) {
      throw new ServiceException("Error access database", e);
    }
  }


  public final List<Training> findAllTrainingsForStudent(int studentId) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.findTrainingsForStudent(studentId);
    } catch (DaoException e) {
      throw new ServiceException("Error access database", e);
    }
  }

  public final List<Training> findAllTrainings() throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.findTraining();
    } catch (DaoException e) {
      throw new ServiceException("Error access database", e);
    }
  }

  public final void findTrainingToStudent(int idStudent, int idTraining) throws ServiceException {
    TrainingsOperationDao operationDao = DaoFactory.getTrainingsOperationDAO();
    try {
      operationDao.addTrainingsToStudent(idStudent, idTraining);
    } catch (DaoException e) {
      throw new ServiceException("Error access database", e);
    }
  }

  public final List<Student> findStudentsByIdTraining(int trainingId) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.findStudentsByIdTraining(trainingId);
    } catch (DaoException e) {
      throw new ServiceException("Error access database", e);
    }
  }

  public final List<Training> findTrainingForMentor(int mentorId) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.findTrainingForMentor(mentorId);
    } catch (DaoException e) {
      throw new ServiceException("Error access database", e);
    }
  }

  public final List<Topic> findTopicsForTraining(int trainingId) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    System.out.println("вызываю сервис");
    List<Topic> topics = new ArrayList<>();
    try {
      topics = dataListsDao.findTopicsForTraining(trainingId);
    } catch (DaoException e) {
      throw new ServiceException("Error access database", e);
    }
    return topics;
  }

  public final Topic findTopic(int topicId) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.findTopic(topicId);
    } catch (DaoException e) {
      throw new ServiceException("Error access database", e);
    }
  }

  public boolean updateTrainingsInformation(int trainingId, String trainingName,
                                            String information) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.updateTrainingsInformation(trainingId, trainingName, information);
    } catch (DaoException e) {
      throw new ServiceException("Error access database", e);
    }
  }

  public final boolean addTopicForTraining(int trainingId, String topicsName,
                                           String topicsText) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.addTopicForTraining(trainingId, topicsName, topicsText);
    } catch (DaoException e) {
      throw new ServiceException("Error access database", e);
    }
  }

  public final boolean addTaskForTraining(int trainingId, String taskName,
                                          String taskText) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.addTaskForTraining(trainingId, taskName, taskText);
    } catch (DaoException e) {
      throw new ServiceException("Error access database", e);
    }
  }

  public final List<Task> findTasksListForTraining(int trainingId) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    List<Task> tasks = new ArrayList<>();
    try {
      tasks = dataListsDao.findTasksListForTraining(trainingId);
    } catch (DaoException e) {
      throw new ServiceException("Error access database", e);
    }
    return tasks;
  }

  public final boolean createTraining(String trainingName, int mentorId,
                                      String trainingDescription) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.createTraining(trainingName, mentorId, trainingDescription);
    } catch (DaoException e) {
      throw new ServiceException("Error access database", e);
    }
  }

  public final boolean updateTrainingsTopic(int topicId, String topicName, String topic) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.updateTrainingsTopic(topicId, topicName, topic);
    } catch (DaoException e) {
      throw new ServiceException("Error access database", e);
    }
  }

  public final boolean checkTopicStatus(int userId, int topicId) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.checkTopicStatus(userId, topicId);
    } catch (DaoException e) {
      throw new ServiceException("Error access database", e);
    }
  }

  public final boolean markTopic(int userId, int topicId) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.markTopic(userId, topicId);
    } catch (DaoException e) {
      throw new ServiceException("Error access database", e);
    }
  }

  public final boolean checkTrainingStatusForStudent(int userId, int trainingId) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.checkTrainingStatusForStudent(userId, trainingId);
    } catch (DaoException e) {
      throw new ServiceException("Error access database", e);
    }
  }

  public final Task findTask(int taskId) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    Task task = new Task();
    try {
      task = dataListsDao.findTask(taskId);
    } catch (DaoException e) {
      throw new ServiceException("Error access database", e);
    }
    return task;
  }

  public final boolean updateTask(int taskId, String taskName, String task) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.updateTask(taskId, taskName, task);
    } catch (DaoException e) {
      throw new ServiceException("Error access database", e);
    }
  }

  public final boolean checkTaskStatus(int userId, int taskId) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.checkTaskStatus(userId, taskId);
    } catch (DaoException e) {
      throw new ServiceException("Error access database", e);
    }
  }

  public final boolean sendSolution(int userId, int taskId, String answer) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.sendSolution(userId, taskId, answer);
    } catch (DaoException e) {
      throw new ServiceException("Error access database", e);
    }
  }

  public final Map<String, Integer> findTaskSolution(int studentId, int taskId) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    Map<String, Integer> solution = new HashMap<>();
    try {
      solution = dataListsDao.findTaskSolution(studentId, taskId);
    } catch (DaoException e) {
      throw new ServiceException("Error access database", e);
    }
    return solution;
  }

  public final boolean gradeTask(int studentId, int taskId, int mark) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.gradeTask(studentId, taskId, mark);
    } catch (DaoException e) {
      throw new ServiceException("Error access database", e);
    }
  }

  public final int findAvgMarkForTasks(int userId, int trainingId) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.avgMarkForTask(userId, trainingId);
    } catch (DaoException e) {
      throw new ServiceException("Error access database", e);
    }
  }

  public final List<Task> findCompletedTasks(int trainingId, int studentId) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.findCompletedTasks(trainingId, studentId);
    } catch (DaoException e) {
      throw new ServiceException("Error access database", e);
    }
  }

  public final List<Topic> findLearnedTopics(int studentId, int trainingId) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.findLearnedTopics(studentId, trainingId);
    } catch (DaoException e) {
      throw new ServiceException("Error access database", e);
    }
  }

  public final Map<Integer, Date> findConsultationsForTraining(int trainingId) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.findConsultationsForTraining(trainingId);
    } catch (DaoException e) {
      throw new ServiceException("Error access database", e);
    }
  }

  public final boolean sendOrderConsultation(int consultationId, int studentId, String taskIds,
                                             String topicIds) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.sendOrderConsultation(consultationId, studentId, taskIds, topicIds);
    } catch (DaoException e) {
      throw new ServiceException("Error access database", e);
    }
  }
}
