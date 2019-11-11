package com.epam.webapp.dao;

import com.epam.webapp.connectionpool.exception.ConnectionPoolException;
import com.epam.webapp.entity.*;

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
  List<Topic> getTopicsForTraining(int trainingId) throws ConnectionPoolException;
  Topic getTopic(int topicId) throws ConnectionPoolException;
  boolean updateTrainingsInformation(int trainingId, String information) throws ConnectionPoolException;
  boolean addTopicForTraining(int trainingId, String topicsName, String topicsText) throws ConnectionPoolException;
  boolean addTaskForTraining(int trainingId, String taskName, String taskText) throws ConnectionPoolException;
  List<Task> getTasksListForTraining(int trainingId) throws ConnectionPoolException;
  boolean createTraining(String trainingName, int mentorId, String trainingDescription) throws ConnectionPoolException;
  boolean updateTrainingsTopic(String topicName, String topicNewName, String topic, int trainingId) throws ConnectionPoolException;
  boolean checkTopicStatus(int userId, int topicId) throws ConnectionPoolException;
  boolean markTopic(int userId, int topicId) throws ConnectionPoolException;
  boolean checkTrainingStatusForStudent(int userId, int trainingId) throws ConnectionPoolException;
}
