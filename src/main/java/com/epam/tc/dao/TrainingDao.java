package com.epam.tc.dao;

import com.epam.tc.entity.Training;
import com.epam.tc.entity.User;

import java.util.List;

/**
 * The interface Training dao.
 *
 * @author alex raby
 * @version 1.0 this interface contains methods for working with DataBase with Training
 * @see Training
 */
public interface TrainingDao {

  /**
   * adding training to user
   *
   * @param idStudent  - User id
   * @param idTraining - Training id
   * @throws DaoException service package exception
   * @see User
   * @see Training
   */
  void addTrainingsToStudent(int idStudent, int idTraining) throws DaoException;

  /**
   * finds all Training
   *
   * @return list of Training
   * @throws DaoException the dao exception
   * @see Training
   */
  List<Training> findTraining() throws DaoException;

  /**
   * finds trainings for which is recorded
   *
   * @param id - User id
   * @return list of Training
   * @throws DaoException the dao exception
   * @see User
   * @see Training
   */
  List<Training> findTrainingsForStudent(int id) throws DaoException;

  /**
   * finds trainings for a student with a grade
   *
   * @param studentId - User id
   * @return list of Training
   * @throws DaoException the dao exception
   * @see Training
   * @see User
   */
  List<Training> findCompletedTrainingForStudent(int studentId) throws DaoException;

  /**
   * finds all the trainings that this mentor oversees
   *
   * @param mentorId - User id
   * @return list of Training
   * @throws DaoException the dao exception
   * @see User
   * @see Training
   */
  List<Training> findTrainingForMentor(int mentorId) throws DaoException;

  /**
   * finds training on training id
   *
   * @param trainingId - Training id
   * @return Training training
   * @throws DaoException the dao exception
   * @see Training
   */
  Training findTrainingByIdTraining(int trainingId) throws DaoException;

  /**
   * changes the values of the training fields
   *
   * @param trainingId   - Training id
   * @param trainingName - Training name
   * @param information  - Training information
   * @throws DaoException the dao exception
   * @see Training
   */
  void updateTrainingsInformation(int trainingId, String trainingName, String information) throws DaoException;

  /**
   * creating a new training
   *
   * @param trainingName        - Training name
   * @param mentorId            - user Id
   * @param trainingDescription - Training information
   * @throws DaoException the dao exception
   * @see Training
   * @see User
   */
  void createTraining(String trainingName, int mentorId, String trainingDescription) throws DaoException;

  /**
   * checks if an user is registered for this training
   *
   * @param userId     - User id
   * @param trainingId - Training id
   * @return boolean boolean
   * @throws DaoException the dao exception
   * @see Training
   * @see User
   */
  boolean checkTrainingStatusForStudent(int userId, int trainingId) throws DaoException;

  /**
   * training removal
   *
   * @param trainingId - Training id
   * @return boolean boolean
   * @throws DaoException the dao exception
   * @see Training
   */
  boolean deleteTraining(int trainingId) throws DaoException;

  /**
   * grade final mark
   *
   * @param studentId  - User id
   * @param trainingId - Training id
   * @param grade      - int ranging from 1 to 10
   * @throws DaoException the dao exception
   * @see User
   * @see Training
   */
  void setFinalGrade(int studentId, int trainingId, int grade) throws DaoException;

  /**
   * finding the final student grade for this training
   *
   * @param studentId  - User id
   * @param trainingId - training id
   * @return int ranging from 1 to 10
   * @throws DaoException the dao exception
   * @see Training
   * @see User
   */
  int findFinalGrade(int studentId, int trainingId) throws DaoException;

  /**
   * closing the set for this training
   *
   * @param trainingId - Training id
   * @throws DaoException the dao exception
   * @see Training
   */
  void closeReception(int trainingId) throws DaoException;

  /**
   * writing a review
   *
   * @param feedback - String
   * @throws DaoException the dao exception
   */
  void giveFeedback(String feedback) throws DaoException;

  /**
   * search for reviews
   *
   * @return list of String
   * @throws DaoException the dao exception
   */
  List<String> findReviews() throws DaoException;
}
