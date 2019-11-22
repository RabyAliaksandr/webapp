package com.epam.tc.dao;

import com.epam.tc.entity.Topic;

import java.util.List;

public interface TopicDao {

  List<Topic> findTopicsForTraining(int trainingId) throws DaoException;

  Topic findTopic(int topicId) throws DaoException;

  boolean addTopicForTraining(int trainingId, String topicsName, String topicsText) throws DaoException;

  boolean updateTrainingsTopic(int topicId, String topicName, String topic) throws DaoException;

  boolean checkTopicStatus(int userId, int topicId) throws DaoException;

  boolean markTopic(int userId, int topicId) throws DaoException;

  List<Topic> findLearnedTopics(int studentId, int trainingId) throws DaoException;

  void deleteTopic(int topicId) throws DaoException;

}
