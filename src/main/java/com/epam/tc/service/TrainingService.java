package com.epam.tc.service;

import com.epam.tc.entity.*;
import java.util.List;
/**
 * @author alex raby
 * @version 1.0
 * this interface contains methods for working with Training
 * @see Training
 */
public interface TrainingService {

  /**
   * finds training on training id
   * @param trainingId - Training id
   * @return Training
   * @throws ServiceException package Service exception
   * @see Training
   */
  Training findTrainingByIdTraining(int trainingId) throws ServiceException;

  /**
   *  finds trainings for a student with a grade
   * @param studentId - User id
   * @return list of Training
   * @throws ServiceException package Service exception
   * @see Training
   * @see User
   */
  List<Training> findCompletedTrainingForStudent(int studentId) throws ServiceException;

  /**
   * finds trainings for which is recorded
   * @param studentId - User id
   * @return list of Training
   * @throws ServiceException package Service exception
   * @see User
   * @see Training
   */
  List<Training> findAllTrainingsForStudent(int studentId) throws ServiceException;

  /**
   * finds all Training
   * @return list of Training
   * @throws ServiceException package Service exception
   * @see Training
   */
  List<Training> findAllTrainings() throws ServiceException;

  /**
   * adding training to user
   * @param idStudent - User id
   * @param idTraining - Training id
   * @throws ServiceException package Service exception
   * @see User
   * @see Training
   */
  void addTrainingToStudent(int idStudent, int idTraining) throws ServiceException;

  /**
   * finds all the trainings that this mentor oversees
   * @param mentorId - User id
   * @return list of Training
   * @throws ServiceException package Service exception
   * @see User
   * @see Training
   */
  List<Training> findTrainingForMentor(int mentorId) throws ServiceException;

  /**
   * changes the values of the training fields
   * @param trainingId - Training id
   * @param trainingName - Training name
   * @param information - Training information
   * @throws ServiceException package Service exception
   * @see Training
   */
  void updateTrainingsInformation(int trainingId, String trainingName, String information) throws ServiceException;

  /**
   * creating a new training
   * @param trainingName - Training name
   * @param mentorId - user Id
   * @param trainingDescription - Training information
   * @throws ServiceException package Service exception
   * @see Training
   * @see User
   */
  void createTraining(String trainingName, int mentorId, String trainingDescription) throws ServiceException;

  /**
   * checks if an user is registered for this training
   * @param userId - User id
   * @param trainingId - Training id
   * @return boolean
   * @throws ServiceException package Service exception
   * @see Training
   * @see User
   */
  boolean checkTrainingStatusForStudent(int userId, int trainingId) throws ServiceException;

  /**
   * grade final mark
   * @param studentId - User id
   * @param trainingId - Training id
   * @param grade - int ranging from 1 to 10
   * @throws ServiceException package Service exception
   * @see User
   * @see Training
   *
   */
  void setFinalGrade(int studentId, int trainingId, int grade) throws ServiceException;

  /**
   * finding the final student grade for this training
   * @param studentId - User id
   * @param trainingId - training id
   * @return int ranging from 1 to 10
   * @throws ServiceException package Service exception
   * @see Training
   * @see User
   */
  int findFinalGrade(int studentId, int trainingId) throws ServiceException;

  /**
   * closing the set for this training
   * @param trainingId - Training id
   * @throws ServiceException package Service exception
   * @see Training
   */
  void closeReception(int trainingId) throws ServiceException;

  /**
   * training removal
   * @param trainingId - Training id
   * @return boolean
   * @throws ServiceException package Service exception
   * @see Training
   */
  boolean deleteTraining(int trainingId) throws ServiceException;

  /**
   * writing a review
   * @param feedback - String
   * @throws ServiceException package Service exception
   */
  void giveFeedback(String feedback) throws ServiceException;

  /**
   * search for reviews
   * @return list of String
   * @throws ServiceException package Service exception
   */
  List<String> findReviews() throws ServiceException;
}