package com.epam.webapp.dao;

import com.epam.webapp.connectionpool.exception.ConnectionPoolException;
import com.epam.webapp.entity.Mentor;
import com.epam.webapp.entity.Student;
import com.epam.webapp.entity.Training;

import java.util.List;
import java.util.Map;

public interface DataListsDAO {

  List<Student> getListStudents(Training training);
  List<Mentor> getListMentor(Training training);
  List<Training> getTraining() throws ConnectionPoolException;
  List<Training> getTrainingsForStudent(int id) throws ConnectionPoolException;
  List<Student> getStudentsByIdTraining(int trainingId) throws ConnectionPoolException;
  List<Training> getCompletedTrainingForStudent(int studentId) throws ConnectionPoolException;
  List<Training> getTrainingForMentor(int mentorId) throws ConnectionPoolException;
  Training getTrainingByIdTraining(int trainingId) throws ConnectionPoolException;
  Map<String, String> getTopicsForTraining(int trainingId) throws ConnectionPoolException;
  String getTopic(int trainingId, String topicName) throws ConnectionPoolException;
}
