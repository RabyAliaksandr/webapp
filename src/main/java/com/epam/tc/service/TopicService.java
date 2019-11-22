package com.epam.tc.service;

import com.epam.tc.entity.Topic;

import java.util.List;

public interface TopicService {

  List<Topic> findTopicsForTraining(int trainingId) throws ServiceException;

  Topic findTopic(int topicId) throws ServiceException;

  boolean addTopicForTraining(int trainingId, String topicsName, String topicsText) throws ServiceException;

  boolean updateTrainingsTopic(int topicId, String topicName, String topic) throws ServiceException;

  boolean checkTopicStatus(int userId, int topicId) throws ServiceException;

  boolean markTopic(int userId, int topicId) throws ServiceException;

  List<Topic> findLearnedTopics(int studentId, int trainingId) throws ServiceException;

  void deleteTopic(int topicId) throws ServiceException;

}
