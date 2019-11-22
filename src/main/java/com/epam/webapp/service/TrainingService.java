package com.epam.webapp.service;

import com.epam.webapp.dao.DaoException;
import com.epam.webapp.entity.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface TrainingService {

  Training findTrainingByIdTraining(int trainingId) throws ServiceException;

  List<Training> findCompletedTrainingForStudent(int studentId) throws ServiceException;

  List<Training> findAllTrainingsForStudent(int studentId) throws ServiceException;

  List<Training> findAllTrainings() throws ServiceException;

  void findTrainingToStudent(int idStudent, int idTraining) throws ServiceException;

  List<Student> findStudentsByIdTraining(int trainingId) throws ServiceException;

  List<Training> findTrainingForMentor(int mentorId) throws ServiceException;

  List<Topic> findTopicsForTraining(int trainingId) throws ServiceException;

  Topic findTopic(int topicId) throws ServiceException;

  boolean updateTrainingsInformation(int trainingId, String trainingName, String information) throws ServiceException;

  boolean addTopicForTraining(int trainingId, String topicsName, String topicsText) throws ServiceException;

  boolean addTaskForTraining(int trainingId, String taskName, String taskText) throws ServiceException;

  List<Task> findTasksListForTraining(int trainingId) throws ServiceException;

  boolean createTraining(String trainingName, int mentorId, String trainingDescription) throws ServiceException;

  boolean updateTrainingsTopic(int topicId, String topicName, String topic) throws ServiceException;

  boolean checkTopicStatus(int userId, int topicId) throws ServiceException;

  boolean markTopic(int userId, int topicId) throws ServiceException;

  boolean checkTrainingStatusForStudent(int userId, int trainingId) throws ServiceException;

  Task findTask(int taskId) throws ServiceException;

  boolean updateTask(int taskId, String taskName, String task) throws ServiceException;

  int checkTaskStatus(int userId, int taskId) throws ServiceException;

  boolean sendSolution(int userId, int taskId, String answer) throws ServiceException;

  Task findTaskSolution(int studentId, int taskId) throws ServiceException;

  boolean gradeTask(int studentId, int taskId, int mark) throws ServiceException;

  int findAvgMarkForTasks(int userId, int trainingId) throws ServiceException;

  List<Task> findCompletedTasks(int trainingId, int studentId) throws ServiceException;

  List<Topic> findLearnedTopics(int studentId, int trainingId) throws ServiceException;

  List<Consultation> findConsultationsForTraining(int trainingId) throws ServiceException;

  boolean sendOrderConsultation(int consultationId, int studentId, List<Integer> taskIds,
                                List<Integer> topicIds) throws ServiceException;

  void setFinalGrade(int studentId, int trainingId, int grade) throws ServiceException;

  int findFinalGrade(int studentId, int trainingId) throws ServiceException;

  void closeReception(int trainingId) throws ServiceException;

  boolean deleteTraining(int trainingId) throws ServiceException;

  void deleteTopic(int topicId) throws ServiceException;

  void deleteTask(int taskId) throws ServiceException;

  void giveFeedback(String feedback) throws ServiceException;
}