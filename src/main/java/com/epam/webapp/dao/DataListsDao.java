package com.epam.webapp.dao;

import com.epam.webapp.entity.*;


import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface DataListsDao {

  List<Training> findTraining() throws DaoException;

  List<Training> findTrainingsForStudent(int id) throws DaoException;

  List<Student> findStudentsByIdTraining(int trainingId) throws DaoException;

  List<Training> findCompletedTrainingForStudent(int studentId) throws DaoException;

  List<Training> findTrainingForMentor(int mentorId) throws DaoException;

  Training findTrainingByIdTraining(int trainingId) throws DaoException;

  List<Topic> findTopicsForTraining(int trainingId) throws DaoException;

  Topic findTopic(int topicId) throws DaoException;

  boolean updateTrainingsInformation(int trainingId, String trainingNAme, String information) throws DaoException;

  boolean addTopicForTraining(int trainingId, String topicsName, String topicsText) throws DaoException;

  boolean addTaskForTraining(int trainingId, String taskName, String taskText) throws DaoException;

  List<Task> findTasksListForTraining(int trainingId) throws DaoException;

  boolean createTraining(String trainingName, int mentorId, String trainingDescription) throws DaoException;

  boolean updateTrainingsTopic(int topicId, String topicName, String topic) throws DaoException;

  boolean checkTopicStatus(int userId, int topicId) throws DaoException;

  boolean markTopic(int userId, int topicId) throws DaoException;

  boolean checkTrainingStatusForStudent(int userId, int trainingId) throws DaoException;

  Task findTask(int taskId) throws DaoException;

  boolean updateTask(int taskId, String taskName, String task) throws DaoException;

  int checkTaskStatus(int userId, int taskId) throws DaoException;

  boolean sendSolution(int userId, int taskId, String answer) throws DaoException;

  Task findTaskSolution(int studentId, int taskId) throws DaoException;

  boolean gradeTask(int studentId, int taskId, int mark) throws DaoException;

  int avgMarkForTask(int userId, int trainingId) throws DaoException;

  List<Task> findCompletedTasks(int trainingId, int studentId) throws DaoException;

  List<Topic> findLearnedTopics(int studentId, int trainingId) throws DaoException;

  List<Consultation> findConsultationsForTraining(int trainingId) throws DaoException;

  boolean sendOrderConsultation(int consultationId, int studentId,
                                List<Integer> taskIds, List<Integer> topicIds) throws DaoException;

  void setFinalGrade(int studentId, int trainingId, int grade) throws DaoException;

  int findFinalGrade(int studentId, int trainingId) throws DaoException;

  void closeReception(int trainingId) throws DaoException;

  boolean deleteTraining(int trainingId) throws DaoException;

  void deleteTopic(int topicId) throws DaoException;

  void deleteTask(int taskId) throws DaoException;

  void giveFeedback(String feedback) throws DaoException;
}
