package com.epam.tc.dao;

import com.epam.tc.entity.Topic;
import com.epam.tc.entity.Training;
import com.epam.tc.entity.User;
import com.epam.tc.entity.UserType;

import java.util.List;

/**
 * This interface contains methods for working with DataBase with Topic
 *
 * @author alex raby
 * @version 1.0
 * @see Topic
 */
public interface TopicDao {

  /**
   * search topics for this training
   *
   * @param trainingId - Training id for which we are looking
   * @return list of Topic
   * @throws DaoException the dao exception
   * @see Training
   */
  List<Topic> findTopicsForTraining(int trainingId) throws DaoException;

  /**
   * finds Topic by id
   *
   * @param topicId - Topic id
   * @return - Topic
   * @throws DaoException the dao exception
   * @see Topic
   */
  Topic findTopic(int topicId) throws DaoException;

  /**
   * adding a training Topic
   *
   * @param trainingId - Training id
   * @param topicsName - Topic name
   * @param topicsText - Topic
   * @throws DaoException the dao exception
   * @see Topic
   * @see Training
   */
  void addTopicForTraining(int trainingId, String topicsName, String topicsText) throws DaoException;

  /**
   * updates the fields of the Topic
   *
   * @param topicId   - Topic id to be added
   * @param topicName - Topic name to be added
   * @param topic     - Topic topic to be added
   * @throws DaoException the dao exception
   * @see Topic
   * @see Training
   */
  void updateTrainingsTopic(int topicId, String topicName, String topic) throws DaoException;

  /**
   * checks the status of the Topic for User
   *
   * @param userId  - User id which we will update
   * @param topicId - Topic id which we will update
   * @return true if Topic is learned
   * @throws DaoException the dao exception
   * @see User
   * @see Topic
   */
  boolean checkTopicStatus(int userId, int topicId) throws DaoException;

  /**
   * adds a topic to the list of Topic for User with the status 'true'
   *
   * @param userId  - User id for which we will put a mark
   * @param topicId - Topic id for which we will put a mark
   * @throws DaoException the dao exception
   * @see Topic
   * @see User
   */
  void markTopic(int userId, int topicId) throws DaoException;

  /**
   * finds Topic with status 'true' for the User enrolled in this Training
   *
   * @param studentId  - User id for which we will put a mark
   * @param trainingId - Training id for which we will put a mark
   * @return - list of Topic
   * @throws DaoException the dao exception
   * @see Topic
   * @see User
   * @see UserType#STUDENT
   * @see Training
   */
  List<Topic> findLearnedTopics(int studentId, int trainingId) throws DaoException;

  /**
   * deleting this Topic
   *
   * @param topicId - Topic id
   * @throws DaoException the dao exception
   * @see Topic
   */
  void deleteTopic(int topicId) throws DaoException;
}
