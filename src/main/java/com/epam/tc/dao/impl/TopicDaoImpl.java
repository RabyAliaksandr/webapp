package com.epam.tc.dao.impl;

import com.epam.tc.connectionpool.ConnectionPool;
import com.epam.tc.connectionpool.ConnectionPoolException;
import com.epam.tc.dao.DaoException;
import com.epam.tc.dao.TopicDao;
import com.epam.tc.entity.Topic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.jms.ConnectionConsumer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.tc.dao.SqlColumn.*;
import static com.epam.tc.dao.SqlQuery.*;

/**
 * implements TopicDao {@link TopicDao}
 * connects with DataBase
 * takes Connection in ConnectionPool {@link ConnectionPool}
 *
 * @author alex raby
 * @version 1.0
 */
public class TopicDaoImpl implements TopicDao {

  private static Logger logger = LogManager.getLogger(TopicDaoImpl.class);

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Topic> findTopicsForTraining(int trainingId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    ResultSet resultSet = null;
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_TOPICS_FOR_TRAINING)) {
      preparedStatement.setInt(1, trainingId);
      resultSet = preparedStatement.executeQuery();
      List<Topic> topics = new ArrayList<>();
      while (resultSet.next()) {
        Topic topic = new Topic();
        topic.setId(resultSet.getInt(SQL_TOPIC_ID));
        topic.setName(resultSet.getString(SQL_NAME_TOPIC));
        topic.setTopic(resultSet.getString(SQL_TOPIC));
        topics.add(topic);
      }
      return topics;
    } catch (SQLException | ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException(e);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Topic findTopic(int topicId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    ResultSet resultSet = null;
    Topic topic = new Topic();
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_TOPIC_BY_TOPIC_ID)) {
      preparedStatement.setInt(1, topicId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        topic.setId(resultSet.getInt(SQL_TOPIC_ID));
        topic.setName(resultSet.getString(SQL_NAME_TOPIC));
        topic.setTopic(resultSet.getString(SQL_TOPIC));
      }
      return topic;
    } catch (SQLException | ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException(e);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addTopicForTraining(int trainingId, String topicsName, String topicsText) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_TOPIC_FOR_TRAINING)) {
      preparedStatement.setInt(1, trainingId);
      preparedStatement.setString(2, topicsName);
      preparedStatement.setString(3, topicsText);
      preparedStatement.executeUpdate();
    } catch (SQLException | ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException(e);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updateTrainingsTopic(int topicId, String topicName, String topic) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_TRAININGS_TOPIC)) {
      preparedStatement.setString(1, topicName);
      preparedStatement.setString(2, topic);
      preparedStatement.setInt(3, topicId);
      preparedStatement.executeUpdate();
    } catch (SQLException | ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException(e);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean checkTopicStatus(int userId, int topicId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    ResultSet resultSet = null;
    boolean check = false;
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_CHECK_STATUS_TOPIC)) {
      preparedStatement.setInt(1, userId);
      preparedStatement.setInt(2, topicId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        check = resultSet.getBoolean(SQL_TOPIC_STATUS);
      }
    } catch (SQLException | ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException(e);
    }
    return check;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void markTopic(int userId, int topicId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_SET_MARK_TOPIC)) {
      preparedStatement.setInt(1, userId);
      preparedStatement.setInt(2, topicId);
      preparedStatement.executeUpdate();
    } catch (SQLException | ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException(e);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Topic> findLearnedTopics(int studentId, int trainingId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    ResultSet resultSet = null;
    List<Topic> topics = new ArrayList<>();
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_LEARNED_TOPICS)) {
      preparedStatement.setInt(1, studentId);
      preparedStatement.setInt(2, trainingId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Topic topic = new Topic();
        topic.setId(resultSet.getInt(SQL_TOPIC_ID));
        topic.setName(resultSet.getString(SQL_NAME_TOPIC));
        topics.add(topic);
      }
      return topics;
    } catch (SQLException | ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException(e);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteTopic(int topicId) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    try (Connection connection = connectionPool.takeConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_TOPIC)) {
      preparedStatement.setInt(1, topicId);
      preparedStatement.executeUpdate();
    } catch (SQLException | ConnectionPoolException e) {
      logger.error(e);
      throw new DaoException(e);
    }
  }
}
