package com.epam.tc.dao;

import com.epam.tc.entity.Topic;
import com.epam.tc.entity.Training;
import com.epam.tc.entity.User;

import java.util.List;

/**
 * @author alex raby
 * @version 1.0
 * this interface contains methods for working with DataBase with Topic
 * @see Topic
 */

public interface TopicDao {

  /**
   * search topics for this training
   * @param trainingId - Training id
   * @return list of Topic
   * @throws DaoException
   * @see Training
   */
  List<Topic> findTopicsForTraining(int trainingId) throws DaoException;

  /**
   * finds Topic by id
   * @param topicId - Topic id
   * @return - Topic
   * @throws DaoException
   * @see Topic
   */
  Topic findTopic(int topicId) throws DaoException;

  /**
   * adding a training Topic
   * @param trainingId - Training id
   * @param topicsName - Topic name
   * @param topicsText - Topic
   * @throws DaoException
   * @see Topic
   * @see Training
   */
  void addTopicForTraining(int trainingId, String topicsName, String topicsText) throws DaoException;

  /**
   * updates the fields of the Topic
   * @param topicId - Topic id
   * @param topicName - Topic name
   * @param topic - Topic topic
   * @throws DaoException
   * @see Topic
   * @see Training
   */
  void updateTrainingsTopic(int topicId, String topicName, String topic) throws DaoException;

  /**
   * checks the status of the Topic for User
   * @param userId - User id
   * @param topicId - Topic id
   * @return - boolean
   * @throws DaoException
   * @see User
   * @see Topic
   */
  boolean checkTopicStatus(int userId, int topicId) throws DaoException;

  /**
   * adds a topic to the list of Topic for User with the status 'true'
   * @param userId - User id
   * @param topicId - Topic id
   * @throws DaoException
   * @see Topic
   * @see User
   */
  void markTopic(int userId, int topicId) throws DaoException;

  /**
   * finds Topic with status 'true' for the User enrolled in this Training
   * @param studentId - User id
   * @param trainingId - Training id
   * @return - list of Topic
   * @throws DaoException
   * @see Topic
   * @see User
   * @see Training
   */
  List<Topic> findLearnedTopics(int studentId, int trainingId) throws DaoException;

  /**
   * deleting this Topic
   * @param topicId - Topic id
   * @throws DaoException
   * @see Topic
   */
  void deleteTopic(int topicId) throws DaoException;
}
