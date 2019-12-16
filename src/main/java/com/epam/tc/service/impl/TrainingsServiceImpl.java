package com.epam.tc.service.impl;

import com.epam.tc.dao.DaoException;
import com.epam.tc.dao.DaoFactory;
import com.epam.tc.dao.TrainingDao;
import com.epam.tc.entity.Training;
import com.epam.tc.service.ServiceException;
import com.epam.tc.service.TrainingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * this class implements interface methods TrainingService {@link TrainingService} methods of this class
 * catch DaoException {@link DaoException} and throw ServiceException {@link ServiceException}
 *
 * @author alex raby
 * @version 1.0
 */
public class TrainingsServiceImpl implements TrainingService {

  /**
   * class object Logger {@link Logger}
   * writes important events to a log file
   */
  private static Logger logger = LogManager.getLogger(TrainingsServiceImpl.class);

  /**
   * {@inheritDoc}
   */
  public Training findTrainingByIdTraining(int trainingId) throws ServiceException {
    TrainingDao trainingDao = DaoFactory.getTrainingDao();
    try {
      return trainingDao.findTrainingByIdTraining(trainingId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  /**
   * {@inheritDoc}
   */
  public List<Training> findCompletedTrainingForStudent(int studentId) throws ServiceException {
    TrainingDao trainingDao = DaoFactory.getTrainingDao();
    try {
      return trainingDao.findCompletedTrainingForStudent(studentId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }


  /**
   * {@inheritDoc}
   */
  public List<Training> findAllTrainingsForStudent(int studentId) throws ServiceException {
    TrainingDao trainingDao = DaoFactory.getTrainingDao();
    try {
      return trainingDao.findTrainingsForStudent(studentId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  /**
   * {@inheritDoc}
   */
  public List<Training> findAllTrainings() throws ServiceException {
    TrainingDao trainingDao = DaoFactory.getTrainingDao();
    try {
      return trainingDao.findTraining();
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  /**
   * {@inheritDoc}
   */
  public void addTrainingToStudent(int idStudent, int idTraining) throws ServiceException {
    TrainingDao operationDao = DaoFactory.getTrainingDao();
    try {
      operationDao.addTrainingsToStudent(idStudent, idTraining);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  /**
   * {@inheritDoc}
   */
  public List<Training> findTrainingForMentor(int mentorId) throws ServiceException {
    TrainingDao trainingDao = DaoFactory.getTrainingDao();
    try {
      return trainingDao.findTrainingForMentor(mentorId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  /**
   * {@inheritDoc}
   */
  public void updateTrainingsInformation(int trainingId, String trainingName,
                                         String information) throws ServiceException {
    TrainingDao trainingDao = DaoFactory.getTrainingDao();
    try {
      trainingDao.updateTrainingsInformation(trainingId, trainingName, information);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  /**
   * {@inheritDoc}
   */
  public void createTraining(String trainingName, int mentorId,
                             String trainingDescription) throws ServiceException {
    TrainingDao trainingDao = DaoFactory.getTrainingDao();
    try {
      trainingDao.createTraining(trainingName, mentorId, trainingDescription);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  /**
   * {@inheritDoc}
   */
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

  /**
   * {@inheritDoc}
   */
  @Override
  public List<String> findReviews() throws ServiceException {
    TrainingDao trainingDao = DaoFactory.getTrainingDao();
    try {
      return trainingDao.findReviews();
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException(e);
    }
  }

  /**
   * {@inheritDoc}
   */
  public boolean checkTrainingStatusForStudent(int userId, int trainingId) throws ServiceException {
    TrainingDao trainingDao = DaoFactory.getTrainingDao();
    try {
      return trainingDao.checkTrainingStatusForStudent(userId, trainingId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  /**
   * {@inheritDoc}
   */
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

  /**
   * {@inheritDoc}
   */
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

  /**
   * {@inheritDoc}
   */
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

  /**
   * {@inheritDoc}
   */
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
