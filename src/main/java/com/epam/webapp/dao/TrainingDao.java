package com.epam.webapp.dao;

import com.epam.webapp.entity.Training;

import java.util.List;

public interface TrainingDao {

  void addTrainingsToStudent(int idStudent, int idTraining) throws DaoException;

  List<Training> findTraining() throws DaoException;

  List<Training> findTrainingsForStudent(int id) throws DaoException;

  List<Training> findCompletedTrainingForStudent(int studentId) throws DaoException;

  List<Training> findTrainingForMentor(int mentorId) throws DaoException;
}
