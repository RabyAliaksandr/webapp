package com.epam.tc.service;

import com.epam.tc.entity.Topic;
import com.epam.tc.entity.Training;
import com.epam.tc.entity.User;

import java.util.List;

/**
 * The interface Topic service.
 *
 * @author alex raby
 * @version 1.0 this interface contains methods for working with Training
 * @see Training
 */
public interface TopicService {

  /**
   * search topics for this training
   *
   * @param trainingId - Training id
   * @return list of Topic
   * @throws ServiceException package Service exception
   * @see Training
   */
  List<Topic> findTopicsForTraining(int trainingId) throws ServiceException;

  /**
   * finds Topic by id
   *
   * @param topicId - Topic id
   * @return - Topic
   * @throws ServiceException package Service exception
   * @see Topic
   */
  Topic findTopic(int topicId) throws ServiceException;

  /**
   * adding a training Topic
   *
   * @param trainingId - Training id
   * @param topicsName - Topic name
   * @param topicsText - Topic
   * @throws ServiceException package Service exception
   * @see Topic
   * @see Training
   */
  void addTopicForTraining(int trainingId, String topicsName, String topicsText) throws ServiceException;

  /**
   * updates the fields of the Topic
   *
   * @param topicId   - Topic id
   * @param topicName - Topic name
   * @param topic     - Topic topic
   * @throws ServiceException package Service exception
   * @see Topic
   * @see Training
   */
  void updateTrainingsTopic(int topicId, String topicName, String topic) throws ServiceException;

  /**
   * checks the status of the Topic for User
   *
   * @param userId  - User id
   * @param topicId - Topic id
   * @return the boolean
   * @throws ServiceException package Service exception
   * @see User
   * @see Topic
   */
  boolean checkTopicStatus(int userId, int topicId) throws ServiceException;

  /**
   * adds a topic to the list of Topic for User with the status 'true'
   *
   * @param userId  - User id
   * @param topicId - Topic id
   * @throws ServiceException package Service exception
   * @see Topic
   * @see User
   */
  void markTopic(int userId, int topicId) throws ServiceException;

  /**
   * finds Topic with status 'true' for the User enrolled in this Training
   *
   * @param studentId  - User id
   * @param trainingId - Training id
   * @return - list of Topic
   * @throws ServiceException package Service exception
   * @see Topic
   * @see User
   * @see Training
   */
  List<Topic> findLearnedTopics(int studentId, int trainingId) throws ServiceException;

  /**
   * deleting this Topic
   *
   * @param topicId - Topic id
   * @throws ServiceException package Service exception
   * @see Topic
   */
  void deleteTopic(int topicId) throws ServiceException;
}
