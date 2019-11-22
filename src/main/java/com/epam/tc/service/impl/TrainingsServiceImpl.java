package com.epam.tc.service.impl;

import com.epam.tc.dao.*;
import com.epam.tc.entity.*;
import com.epam.tc.service.TrainingService;
import com.epam.tc.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TrainingsServiceImpl implements TrainingService {

  private static Logger logger = LogManager.getLogger(TrainingService.class);

  public Training findTrainingByIdTraining(int trainingId) throws ServiceException {
    TrainingDao trainingDao = DaoFactory.getTrainingDao();
    try {
      return trainingDao.findTrainingByIdTraining(trainingId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final List<Training> findCompletedTrainingForStudent(int studentId) throws ServiceException {
    TrainingDao trainingDao = DaoFactory.getTrainingDao();
    try {
      return trainingDao.findCompletedTrainingForStudent(studentId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }


  public final List<Training> findAllTrainingsForStudent(int studentId) throws ServiceException {
    TrainingDao trainingDao = DaoFactory.getTrainingDao();
    try {
      return trainingDao.findTrainingsForStudent(studentId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final List<Training> findAllTrainings() throws ServiceException {
    TrainingDao trainingDao = DaoFactory.getTrainingDao();
    try {
      return trainingDao.findTraining();
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final void addTrainingToStudent(int idStudent, int idTraining) throws ServiceException {
    TrainingDao operationDao = DaoFactory.getTrainingDao();
    try {
      operationDao.addTrainingsToStudent(idStudent, idTraining);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final List<Training> findTrainingForMentor(int mentorId) throws ServiceException {
    TrainingDao trainingDao = DaoFactory.getTrainingDao();
    try {
      return trainingDao.findTrainingForMentor(mentorId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final void updateTrainingsInformation(int trainingId, String trainingName,
                                            String information) throws ServiceException {
    TrainingDao trainingDao = DaoFactory.getTrainingDao();
    try {
      trainingDao.updateTrainingsInformation(trainingId, trainingName, information);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final void createTraining(String trainingName, int mentorId,
                                      String trainingDescription) throws ServiceException {
    TrainingDao trainingDao = DaoFactory.getTrainingDao();
    try {
       trainingDao.createTraining(trainingName, mentorId, trainingDescription);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  @Override
  public void giveFeedback(String feedback) throws ServiceException {
    TrainingDao trainingDao = DaoFactory.getTrainingDao();
    try {
      trainingDao.giveFeedback(feedback);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final boolean checkTrainingStatusForStudent(int userId, int trainingId) throws ServiceException {
    TrainingDao trainingDao = DaoFactory.getTrainingDao();
    try {
      return trainingDao.checkTrainingStatusForStudent(userId, trainingId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  @Override
  public boolean deleteTraining(int trainingId) throws ServiceException {
    TrainingDao trainingDao = DaoFactory.getTrainingDao();
    try {
      return trainingDao.deleteTraining(trainingId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException(e);
    }
  }

  @Override
  public void setFinalGrade(int studentId, int trainingId, int grade) throws ServiceException {
    TrainingDao trainingDao = DaoFactory.getTrainingDao();
    try {
      trainingDao.setFinalGrade(studentId, trainingId, grade);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  @Override
  public void closeReception(int trainingId) throws ServiceException {
    TrainingDao trainingDao = DaoFactory.getTrainingDao();
    try {
      trainingDao.closeReception(trainingId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException(e);
    }
  }

  @Override
  public int findFinalGrade(int studentId, int trainingId) throws ServiceException {
    TrainingDao trainingDao = DaoFactory.getTrainingDao();
    int grade;
    try {
      grade = trainingDao.findFinalGrade(studentId, trainingId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException(e);
    }
    return grade;
  }
}
