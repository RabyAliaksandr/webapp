package com.epam.tc.dao;

import com.epam.tc.entity.Training;
import com.epam.tc.entity.User;
import com.epam.tc.entity.UserType;

import java.util.List;

/**
 * this interface contains methods for working with DataBase with Training
 *
 * @author alex raby
 * @version 1.0
 * @see Training
 */
public interface TrainingDao {

  /**
   * adding training to user
   *
   * @param idStudent  - User id for which to adding
   * @param idTraining - Training id which will be adding
   * @throws DaoException service package exception
   * @see UserType#STUDENT
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
   * finds trainings for Student
   *
   * @param id - User id for which to look
   * @return list of Training
   * @throws DaoException the dao exception
   * @see User
   * @see UserType#STUDENT
   * @see Training
   */
  List<Training> findTrainingsForStudent(int id) throws DaoException;

  /**
   * finds trainings for a student with a grade
   *
   * @param studentId - User id for which to look
   * @return list of Training
   * @throws DaoException the dao exception
   * @see Training
   * @see UserType#STUDENT
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
   * @see UserType#MENTOR
   * @see Training
   */
  List<Training> findTrainingForMentor(int mentorId) throws DaoException;

  /**
   * finds training by training id
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
   * @see UserType#MENTOR
   * @see User
   */
  void createTraining(String trainingName, int mentorId, String trainingDescription) throws DaoException;

  /**
   * checks if an user is registered for this training
   *
   * @param userId     - User id for whom is looking
   * @param trainingId - Training id for whom is looking
   * @return boolean true if Student finished this Training
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
   * @param studentId  - User id which will be marked
   * @param trainingId - Training id which will be marked
   * @param grade      - int ranging from 1 to 10
   * @throws DaoException the dao exception
   * @see User
   * @see Training
   */
  void setFinalGrade(int studentId, int trainingId, int grade) throws DaoException;

  /**
   * finding the final student grade for this training
   *
   * @param studentId  - User id for whom is looking
   * @param trainingId - training id for whom is looking
   * @return int ranging from 1 to 10
   * @throws DaoException the dao exception
   * @see Training
   * @see User
   */
  int findFinalGrade(int studentId, int trainingId) throws DaoException;

  /**
   * closing the set for this training
   *
   * @param trainingId - Training id for which will be closed
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
