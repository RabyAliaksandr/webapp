package com.epam.tc.service;

import com.epam.tc.entity.*;
import java.util.List;

public interface TrainingService {

  Training findTrainingByIdTraining(int trainingId) throws ServiceException;

  List<Training> findCompletedTrainingForStudent(int studentId) throws ServiceException;

  List<Training> findAllTrainingsForStudent(int studentId) throws ServiceException;

  List<Training> findAllTrainings() throws ServiceException;

  void addTrainingToStudent(int idStudent, int idTraining) throws ServiceException;

  List<Training> findTrainingForMentor(int mentorId) throws ServiceException;

  void updateTrainingsInformation(int trainingId, String trainingName, String information) throws ServiceException;

  void createTraining(String trainingName, int mentorId, String trainingDescription) throws ServiceException;

  boolean checkTrainingStatusForStudent(int userId, int trainingId) throws ServiceException;

  void setFinalGrade(int studentId, int trainingId, int grade) throws ServiceException;

  int findFinalGrade(int studentId, int trainingId) throws ServiceException;

  void closeReception(int trainingId) throws ServiceException;

  boolean deleteTraining(int trainingId) throws ServiceException;

  void giveFeedback(String feedback) throws ServiceException;
}