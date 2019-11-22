package com.epam.tc.dao;

import com.epam.tc.entity.Training;

import java.util.List;

public interface TrainingDao {

  void addTrainingsToStudent(int idStudent, int idTraining) throws DaoException;

  List<Training> findTraining() throws DaoException;

  List<Training> findTrainingsForStudent(int id) throws DaoException;

  List<Training> findCompletedTrainingForStudent(int studentId) throws DaoException;

  List<Training> findTrainingForMentor(int mentorId) throws DaoException;

  Training findTrainingByIdTraining(int trainingId) throws DaoException;

  void updateTrainingsInformation(int trainingId, String trainingNAme, String information) throws DaoException;

  void createTraining(String trainingName, int mentorId, String trainingDescription) throws DaoException;

  boolean checkTrainingStatusForStudent(int userId, int trainingId) throws DaoException;

  boolean deleteTraining(int trainingId) throws DaoException;

  void setFinalGrade(int studentId, int trainingId, int grade) throws DaoException;

  int findFinalGrade(int studentId, int trainingId) throws DaoException;

  void closeReception(int trainingId) throws DaoException;

  void giveFeedback(String feedback) throws DaoException;

  void addTrainingToStudent(int userId, int trainingId) throws DaoException;


}
