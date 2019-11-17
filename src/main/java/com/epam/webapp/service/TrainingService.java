package com.epam.webapp.service;

import com.epam.webapp.entity.Student;
import com.epam.webapp.entity.Task;
import com.epam.webapp.entity.Topic;
import com.epam.webapp.entity.Training;
import com.epam.webapp.service.exception.ServiceException;

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

  boolean checkTaskStatus(int userId, int taskId) throws ServiceException;

  boolean sendSolution(int userId, int taskId, String answer) throws ServiceException;

  Map<String, Integer> findTaskSolution(int studentId, int taskId) throws ServiceException;

  boolean gradeTask(int studentId, int taskId, int mark) throws ServiceException;

  int findAvgMarkForTasks(int userId, int trainingId) throws ServiceException;

  List<Task> findCompletedTasks(int trainingId, int studentId) throws ServiceException;

  List<Topic> findLearnedTopics(int studentId, int trainingId) throws ServiceException;

  Map<Integer, Date> findConsultationsForTraining(int trainingId) throws ServiceException;

  boolean sendOrderConsultation(int consultationId, int studentId, String taskIds, String topicIds) throws ServiceException;
}