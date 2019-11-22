package com.epam.webapp.service.impl;

import com.epam.webapp.dao.DaoFactory;
import com.epam.webapp.dao.DataListsDao;
import com.epam.webapp.dao.TrainingDao;
import com.epam.webapp.dao.DaoException;
import com.epam.webapp.entity.*;
import com.epam.webapp.service.TrainingService;
import com.epam.webapp.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TrainingsServiceImpl implements TrainingService {

  private static Logger logger = LogManager.getLogger(TrainingService.class);

  public Training findTrainingByIdTraining(int trainingId) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.findTrainingByIdTraining(trainingId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final List<Training> findCompletedTrainingForStudent(int studentId) throws ServiceException {
    DataListsDao dataListsDAO = DaoFactory.getDataListDao();
    try {
      return dataListsDAO.findCompletedTrainingForStudent(studentId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }


  public final List<Training> findAllTrainingsForStudent(int studentId) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.findTrainingsForStudent(studentId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final List<Training> findAllTrainings() throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.findTraining();
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final void findTrainingToStudent(int idStudent, int idTraining) throws ServiceException {
    TrainingDao operationDao = DaoFactory.getTrainingsOperationDAO();
    try {
      operationDao.addTrainingsToStudent(idStudent, idTraining);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final List<Student> findStudentsByIdTraining(int trainingId) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.findStudentsByIdTraining(trainingId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final List<Training> findTrainingForMentor(int mentorId) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.findTrainingForMentor(mentorId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final List<Topic> findTopicsForTraining(int trainingId) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    List<Topic> topics;
    try {
      topics = dataListsDao.findTopicsForTraining(trainingId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
    return topics;
  }

  public final Topic findTopic(int topicId) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.findTopic(topicId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public boolean updateTrainingsInformation(int trainingId, String trainingName,
                                            String information) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.updateTrainingsInformation(trainingId, trainingName, information);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final boolean addTopicForTraining(int trainingId, String topicsName,
                                           String topicsText) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.addTopicForTraining(trainingId, topicsName, topicsText);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final boolean addTaskForTraining(int trainingId, String taskName,
                                          String taskText) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.addTaskForTraining(trainingId, taskName, taskText);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final List<Task> findTasksListForTraining(int trainingId) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    List<Task> tasks;
    try {
      tasks = dataListsDao.findTasksListForTraining(trainingId);
    } catch (DaoException e) {
      logger.error(e);
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
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final boolean updateTrainingsTopic(int topicId, String topicName, String topic) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.updateTrainingsTopic(topicId, topicName, topic);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final boolean checkTopicStatus(int userId, int topicId) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.checkTopicStatus(userId, topicId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final boolean markTopic(int userId, int topicId) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.markTopic(userId, topicId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  @Override
  public void giveFeedback(String feedback) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      dataListsDao.giveFeedback(feedback);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final boolean checkTrainingStatusForStudent(int userId, int trainingId) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.checkTrainingStatusForStudent(userId, trainingId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final Task findTask(int taskId) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    Task task = new Task();
    try {
      task = dataListsDao.findTask(taskId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
    return task;
  }

  public final boolean updateTask(int taskId, String taskName, String task) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.updateTask(taskId, taskName, task);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final int checkTaskStatus(int userId, int taskId) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.checkTaskStatus(userId, taskId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final boolean sendSolution(int userId, int taskId, String answer) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.sendSolution(userId, taskId, answer);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  @Override
  public void deleteTask(int taskId) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      dataListsDao.deleteTask(taskId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException(e);
    }
  }

  @Override
  public void deleteTopic(int topicId) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      dataListsDao.deleteTopic(topicId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException(e);
    }
  }

  @Override
  public boolean deleteTraining(int trainingId) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.deleteTraining(trainingId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException(e);
    }
  }

  public final Task findTaskSolution(int studentId, int taskId) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    Task task = new Task();
    try {
      task = dataListsDao.findTaskSolution(studentId, taskId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
    return task;
  }

  public final boolean gradeTask(int studentId, int taskId, int mark) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.gradeTask(studentId, taskId, mark);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final int findAvgMarkForTasks(int userId, int trainingId) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.avgMarkForTask(userId, trainingId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final List<Task> findCompletedTasks(int trainingId, int studentId) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.findCompletedTasks(trainingId, studentId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final List<Topic> findLearnedTopics(int studentId, int trainingId) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.findLearnedTopics(studentId, trainingId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public List<Consultation> findConsultationsForTraining(int trainingId) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.findConsultationsForTraining(trainingId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final boolean sendOrderConsultation(int consultationId, int studentId, List<Integer> taskIds,
                                             List<Integer> topicIds) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      return dataListsDao.sendOrderConsultation(consultationId, studentId, taskIds, topicIds);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  @Override
  public void setFinalGrade(int studentId, int trainingId, int grade) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      dataListsDao.setFinalGrade(studentId, trainingId, grade);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  @Override
  public void closeReception(int trainingId) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    try {
      dataListsDao.closeReception(trainingId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException(e);
    }
  }

  @Override
  public int findFinalGrade(int studentId, int trainingId) throws ServiceException {
    DataListsDao dataListsDao = DaoFactory.getDataListDao();
    int grade = 0;
    try {
      grade = dataListsDao.findFinalGrade(studentId, trainingId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException(e);
    }
    return grade;
  }
}
