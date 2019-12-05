package com.epam.tc.service;

import com.epam.tc.entity.Training;
import com.epam.tc.entity.User;

import java.util.List;

/**
 * The interface Training service.
 * this interface contains methods for working with Training
 * @author alex raby
 * @version 1.0
 * @see Training
 */
public interface TrainingService {

  /**
   * finds training on training id
   *
   * @param trainingId - Training id which will looking
   * @return Training training
   * @throws ServiceException package Service exception
   * @see Training
   */
  Training findTrainingByIdTraining(int trainingId) throws ServiceException;

  /**
   * finds trainings for a student with a grade
   *
   * @param studentId - User id which will looking
   * @return list of Training
   * @throws ServiceException package Service exception
   * @see Training
   * @see User
   */
  List<Training> findCompletedTrainingForStudent(int studentId) throws ServiceException;

  /**
   * finds trainings for which is recorded
   *
   * @param studentId - User id which will looking
   * @return list of Training
   * @throws ServiceException package Service exception
   * @see User
   * @see Training
   */
  List<Training> findAllTrainingsForStudent(int studentId) throws ServiceException;

  /**
   * finds all Training
   *
   * @return list of Training
   * @throws ServiceException package Service exception
   * @see Training
   */
  List<Training> findAllTrainings() throws ServiceException;

  /**
   * adding training to user
   *
   * @param idStudent  - User id for which to adding
   * @param idTraining - Training id which will be adding
   * @throws ServiceException package Service exception
   * @see User
   * @see Training
   */
  void addTrainingToStudent(int idStudent, int idTraining) throws ServiceException;

  /**
   * finds all the trainings that this mentor oversees
   *
   * @param mentorId - User id for which to look
   * @return list of Training
   * @throws ServiceException package Service exception
   * @see User
   * @see Training
   */
  List<Training> findTrainingForMentor(int mentorId) throws ServiceException;

  /**
   * changes the values of the training fields
   *
   * @param trainingId   - Training id
   * @param trainingName - Training name to be changed
   * @param information  - Training information to be changed
   * @throws ServiceException package Service exception
   * @see Training
   */
  void updateTrainingsInformation(int trainingId, String trainingName, String information) throws ServiceException;

  /**
   * creating a new training
   *
   * @param trainingName        - Training name
   * @param mentorId            - user Id who will be mentor
   * @param trainingDescription - Training information
   * @throws ServiceException package Service exception
   * @see Training
   * @see User
   */
  void createTraining(String trainingName, int mentorId, String trainingDescription) throws ServiceException;

  /**
   * checks if an user is registered for this training
   *
   * @param userId     - User id for whom is looking
   * @param trainingId - Training id for whom is looking
   * @return boolean boolean
   * @throws ServiceException package Service exception
   * @see Training
   * @see User
   */
  boolean checkTrainingStatusForStudent(int userId, int trainingId) throws ServiceException;

  /**
   * grade final mark
   *
   * @param studentId  - User id which will be marked
   * @param trainingId - Training id which will be marked
   * @param grade      - int ranging from 1 to 10
   * @throws ServiceException package Service exception
   * @see User
   * @see Training
   */
  void setFinalGrade(int studentId, int trainingId, int grade) throws ServiceException;

  /**
   * finding the final student grade for this training
   *
   * @param studentId  - User id for whom is looking
   * @param trainingId - training id for whom is looking
   * @return int ranging from 1 to 10
   * @throws ServiceException package Service exception
   * @see Training
   * @see User
   */
  int findFinalGrade(int studentId, int trainingId) throws ServiceException;

  /**
   * closing the set for this training
   *
   * @param trainingId - Training id which will be closing
   * @throws ServiceException package Service exception
   * @see Training
   */
  void closeReception(int trainingId) throws ServiceException;

  /**
   * training removal
   *
   * @param trainingId - Training id which will be deleted
   * @return boolean done or no
   * @throws ServiceException package Service exception
   * @see Training
   */
  boolean deleteTraining(int trainingId) throws ServiceException;

  /**
   * writing a review
   *
   * @param feedback - String it is review
   * @throws ServiceException package Service exception
   */
  void giveFeedback(String feedback) throws ServiceException;

  /**
   * search for reviews
   *
   * @return list of String
   * @throws ServiceException package Service exception
   */
  List<String> findReviews() throws ServiceException;
}