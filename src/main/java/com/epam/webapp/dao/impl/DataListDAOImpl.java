package com.epam.webapp.dao.impl;

import com.epam.webapp.dao.DataListsDAO;
import com.epam.webapp.dao.UserDAO;
import com.epam.webapp.connectionpool.ConnectionPool;
import com.epam.webapp.connectionpool.exception.ConnectionPoolException;
import com.epam.webapp.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataListDAOImpl implements DataListsDAO {

  private final static Logger logger = LogManager.getLogger(UserDAO.class);
  private static final String SQL_TOPIC_BY_TOPIC_ID = "SELECT * FROM topics_for_study WHERE (topic_id = ?)";
  private static final String SQL_UPDATE_TRAININGS_INFORMATION = "UPDATE trainings SET information = ? WHERE trainingid = ?";
  private static final String SQL_ADD_TOPIC_FOR_TRAINING = "INSERT INTO topics_for_study (training_id, name_topic, topic) VALUES (?, ?, ?)";
  private static final String SQL_ADD_TASK_FOR_TRAINING = "INSERT INTO tasks (training_id, task_name, task) VALUES (?, ?, ?)";
  private static final String SQL_TASKS_FOR_TRAINING = "SELECT * FROM tasks WHERE training_id = ?";
  private final static String SQL_ALL_TRAININGS = "SELECT * FROM trainings";//TODO
  private final static String SQL_TRAININGS_BY_STUDENT_ID = "SELECT * FROM trainings JOIN trainingbystudents USING (trainingid) where userid=?";
  private static final String SQL_TOPICS_FOR_TRAINING = "SELECT name_topic, topic, topic_id FROM topics_for_study WHERE training_id = ?";
  private static final String SQL_COMPLETED_TRAININGS_FOR_MENTOR = "SELECT * FROM trainings WHERE idmentor = ?";
  private static final String SQL_ALL_TRAININGS_BY_TRAINING_ID = "SELECT * FROM trainings WHERE trainingid = ?";
  private final static String SQL_STUDENTS_BY_ID_TRAINING = "SELECT userid, name, surname, grade_for_training FROM users join trainingbystudents USING (userid) WHERE (trainingid =? and grade_for_training = 0)";
  private final static String SQL_NAME = "name";
  private final static String SQL_SURNAME = "surname";
  private final static String STUDENT_ID = "userid";
  private static final String SQL_COMPLETED_TRAININGS_FOR_STUDENTS = "SELECT * FROM trainings  join trainingbystudents using (trainingid) where (trainingbystudents.userid = ? and trainingbystudents.grade_for_training = 0)";
  private static final String SQL_MENTOR_ID = "idmentor";
  private static final String SQL_GRADE_FOR_TRAINING = "grade_for_training";
  private static final String SQL_TRAINING_INFORMATION = "information";
  private static final String SQL_STUDENT_GRADE = "grade_for_training";
  private static final String SQL_NAME_TOPIC = "name_topic";
  private static final String SQL_TOPIC = "topic";
  private static final String SQL_TRAINING_ID = "trainingid";
  private static final String SQL_TASK_NAME = "task_name";
  private static final String SQL_TASK = "task";
  private static final String SQL_CREATE_TRAINING = "INSERT  INTO trainings (name, idmentor, information) VALUES (?, ?, ?);";
  private static final String SQL_UPDATE_TRAININGS_TOPIC = "UPDATE topics_for_study SET name_topic = ?, topic = ? WHERE (name_topic = ? AND training_id = ?)";
  private static final String SQL_CHECK_STATUS_TOPIC = "SELECT topic_status FROM students_topic WHERE (user_id = ? AND  topic_id = ?)";
  private static final String SQL_TOPIC_STATUS = "topic_status";
  private static final String SQL_TASK_ID = "task_id";
  private static final String SQL_TOPIC_ID = "topic_id";
  private static final String SQL_SET_MARK_TOPIC = "INSERT INTO students_topic (user_id, topic_id, topic_status) VALUES (?, ?, 1)";
  private static final String SQL_CHECK_TRAINING_STATUS_FOR_STUDENT = "SELECT COUNT(1) FROM trainingbystudents WHERE (userid = ? AND trainingid = ?);";
  private static final String SQL_STATUS = "status";


  @Override
  public List<Student> getListStudents(Training training) {
    return null;
  }

  @Override
  public List<Mentor> getListMentor(Training training) {
    return null;
  }

  @Override
  public List<Training> getTrainingsForStudent(int id) throws ConnectionPoolException {
    System.out.println("my id ===============" + id);
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;
    List<Training> trainings = new ArrayList<>();
    connectionPool.initPool();
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_TRAININGS_BY_STUDENT_ID);
      preparedStatement.setInt(1, id);
      System.out.println("my id ===============" + id);
      rs = preparedStatement.executeQuery();
      System.out.println("i am here !!!!!!!!");
      while (rs.next()) {
        Training training = new Training();
        training.setName(rs.getString(SQL_NAME));
        trainings.add(training);
      }
      return trainings;
    } catch (SQLException | ConnectionPoolException e) {

    } finally {
      connectionPool.closeConnection(connection, preparedStatement, rs);
    }
    return null;
  }

  @Override
  public List<Student> getStudentsByIdTraining(int trainingId) throws ConnectionPoolException {
    System.out.println("my id ===============" + trainingId);
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;
    List<Student> students = new ArrayList<>();
    connectionPool.initPool();
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_STUDENTS_BY_ID_TRAINING);
      preparedStatement.setInt(1, trainingId);
      rs = preparedStatement.executeQuery();
      while (rs.next()) {

        Student student = new Student();
        System.out.println("here is norm");

        student.setId(rs.getInt(STUDENT_ID));

        System.out.println(rs.getInt(STUDENT_ID));

        student.setName(rs.getString(SQL_NAME));

        System.out.println(rs.getString(SQL_NAME));

        student.setSurname(rs.getString(SQL_SURNAME));

        System.out.println(rs.getString(SQL_SURNAME));

        student.setGrade(rs.getInt(SQL_STUDENT_GRADE));
        System.out.println(rs.getInt(SQL_STUDENT_GRADE));
        students.add(student);
      }
      return students;
    } catch (SQLException | ConnectionPoolException e) {

    } finally {
      connectionPool.closeConnection(connection, preparedStatement, rs);
    }
    return null;
  }

  @Override
  public List<Training> getCompletedTrainingForStudent(int studentId) throws ConnectionPoolException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;
    List<Training> trainings = new ArrayList<>();
    connectionPool.initPool();
    try {
      connection = connectionPool.takeConnection();
//      preparedStatement = connection.prepareStatement(SQL_COMPLETED_TRAININGS_FOR_STUDENTS);
      preparedStatement = connection.prepareStatement(SQL_TRAININGS_BY_STUDENT_ID);
      preparedStatement.setInt(1, studentId);
      rs = preparedStatement.executeQuery();
      while (rs.next()) {
        Training training = new Training();
        Mentor mentor = new Mentor();
        mentor.setId(rs.getInt(SQL_MENTOR_ID));
        training.setName(rs.getString(SQL_NAME));
        training.setId(rs.getInt(SQL_TRAINING_ID));
        training.setMentor(mentor);
        training.setGrade(rs.getInt(SQL_GRADE_FOR_TRAINING));
        trainings.add(training);
      }
      return trainings;
    } catch (SQLException | ConnectionPoolException e) {

    } finally {
      connectionPool.closeConnection(connection, preparedStatement, rs);
    }
    return null;
  }

  @Override
  public List<Training> getTrainingForMentor(int mentorId) throws ConnectionPoolException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;
    List<Training> trainings = new ArrayList<>();
    connectionPool.initPool();
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_COMPLETED_TRAININGS_FOR_MENTOR);
      preparedStatement.setInt(1, mentorId);
      rs = preparedStatement.executeQuery();
      while (rs.next()) {
        Training training = new Training();
        training.setName(rs.getString(SQL_NAME));
        training.setId(rs.getInt(SQL_TRAINING_ID));
        trainings.add(training);
      }
      return trainings;
    } catch (SQLException | ConnectionPoolException e) {

    } finally {
      connectionPool.closeConnection(connection, preparedStatement, rs);
    }
    return null;
  }

  @Override
  public Training getTrainingByIdTraining(int trainingId) throws ConnectionPoolException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;
    connectionPool.initPool();
    Training training = new Training();

    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_ALL_TRAININGS_BY_TRAINING_ID);
      preparedStatement.setInt(1, trainingId);
      rs = preparedStatement.executeQuery();
      while (rs.next()) {
        training.setName(rs.getString(SQL_NAME));
        training.setId(rs.getInt(SQL_TRAINING_ID));
        training.setInformation(rs.getString(SQL_TRAINING_INFORMATION));
      }
      return training;
    } catch (SQLException | ConnectionPoolException e) {

    } finally {
      connectionPool.closeConnection(connection, preparedStatement, rs);
    }
    return null;
  }

  @Override
  public List<Topic> getTopicsForTraining(int trainingId) throws ConnectionPoolException {

    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    connectionPool.initPool();
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_TOPICS_FOR_TRAINING);
      preparedStatement.setInt(1, trainingId);
      resultSet = preparedStatement.executeQuery();
      List<Topic> topics = new ArrayList<>();
      while (resultSet.next()) {
        Topic topic = new Topic();
        System.out.println("in dao");
        System.out.println(resultSet.getInt(SQL_TOPIC_ID) + " topic id");
        topic.setId(resultSet.getInt(SQL_TOPIC_ID));
        topic.setName(resultSet.getString(SQL_NAME_TOPIC));
        topic.setTopic(resultSet.getString(SQL_TOPIC));
        topics.add(topic);
        System.out.println(topic);
      }
      return topics;
    } catch (SQLException | ConnectionPoolException e) {

    } finally {
      connectionPool.closeConnection(connection, preparedStatement, resultSet);
    }
    return null;

  }

  @Override
  public Topic getTopic(int topicId) throws ConnectionPoolException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Topic topic = new Topic();
    connectionPool.initPool();
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_TOPIC_BY_TOPIC_ID);
      preparedStatement.setInt(1, topicId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        topic.setId(resultSet.getInt(SQL_TOPIC_ID));
        topic.setName(resultSet.getString(SQL_NAME_TOPIC));
        topic.setTopic(resultSet.getString(SQL_TOPIC));
        System.out.println(topic.getTopic());
      }
      return topic;
    } catch (SQLException | ConnectionPoolException e) {

    } finally {
      connectionPool.closeConnection(connection, preparedStatement, resultSet);
    }
    return null;
  }

  @Override
  public boolean updateTrainingsInformation(int trainingId, String information) throws ConnectionPoolException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    connectionPool.initPool();
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_UPDATE_TRAININGS_INFORMATION);
      preparedStatement.setString(1, information);
      preparedStatement.setInt(2, trainingId);
      preparedStatement.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      connectionPool.closeConnection(connection, preparedStatement);
    }
    return false;
  }

  @Override
  public boolean addTopicForTraining(int trainingId, String topicsName, String topicsText) throws ConnectionPoolException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    connectionPool.initPool();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_ADD_TOPIC_FOR_TRAINING);
      preparedStatement.setInt(1, trainingId);
      preparedStatement.setString(2, topicsName);
      preparedStatement.setString(3, topicsText);
      preparedStatement.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      connectionPool.closeConnection(connection, preparedStatement);
    }
    return false;
  }

  @Override
  public boolean addTaskForTraining(int trainingId, String taskName, String taskText) throws ConnectionPoolException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    connectionPool.initPool();
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_ADD_TASK_FOR_TRAINING);
      preparedStatement.setInt(1, trainingId);
      preparedStatement.setString(2, taskName);
      preparedStatement.setString(3, taskText);
      preparedStatement.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      connectionPool.closeConnection(connection, preparedStatement);
    }
    return false;
  }

  @Override
  public List<Task> getTasksListForTraining(int trainingId) throws ConnectionPoolException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    connectionPool.initPool();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    List<Task> tasks = new ArrayList<>();

    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_TASKS_FOR_TRAINING);
      preparedStatement.setInt(1, trainingId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Task task = new Task();
        task.setId(resultSet.getInt(SQL_TASK_ID));
        task.setName(resultSet.getString(SQL_TASK_NAME));
        task.setTask(resultSet.getString(SQL_TASK));
        tasks.add(task);
      }
      return tasks;
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      connectionPool.closeConnection(connection, preparedStatement, resultSet);
    }
    return null;
  }

  @Override
  public boolean createTraining(String trainingName, int mentorId, String trainingDescription) throws ConnectionPoolException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    connectionPool.initPool();
    PreparedStatement preparedStatement = null;
    Connection connection = null;

    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_CREATE_TRAINING);
      preparedStatement.setString(1, trainingName);
      preparedStatement.setInt(2, mentorId);
      preparedStatement.setString(3, trainingDescription);
      preparedStatement.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      connectionPool.closeConnection(connection, preparedStatement);
    }
    return false;
  }

  @Override
  public boolean updateTrainingsTopic(String topicName, String topicNewName, String topic, int trainingId) throws ConnectionPoolException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    connectionPool.initPool();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_UPDATE_TRAININGS_TOPIC);
      preparedStatement.setString(1, topicNewName);
      preparedStatement.setString(2, topic);
      preparedStatement.setString(3, topicName);
      preparedStatement.setInt(4, trainingId);
      preparedStatement.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ConnectionPoolException e) {
      e.printStackTrace();
    } finally {
      connectionPool.closeConnection(connection, preparedStatement);
    }
    return false;
  }

  @Override
  public boolean checkTopicStatus(int userId, int topicId) throws ConnectionPoolException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    connectionPool.initPool();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    boolean check = false;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_CHECK_STATUS_TOPIC);
      preparedStatement.setInt(1, userId);
      preparedStatement.setInt(2, topicId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        check = resultSet.getBoolean(SQL_TOPIC_STATUS);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      connectionPool.closeConnection(connection, preparedStatement, resultSet);
    }
    return check;
  }

  @Override
  public boolean markTopic(int userId, int topicId) throws ConnectionPoolException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    connectionPool.initPool();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_SET_MARK_TOPIC);
      preparedStatement.setInt(1, userId);
      preparedStatement.setInt(2, topicId);
      preparedStatement.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      connectionPool.closeConnection(connection, preparedStatement);
    }
    return false;
  }

  @Override
  public boolean checkTrainingStatusForStudent(int userId, int trainingId) throws ConnectionPoolException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    connectionPool.initPool();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    boolean done = false;
    ResultSet resultSet = null;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_CHECK_TRAINING_STATUS_FOR_STUDENT);
      preparedStatement.setInt(1, userId);
      preparedStatement.setInt(2, trainingId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        done = resultSet.getBoolean(1);
        System.out.println(done);
      }
      return done;
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      connectionPool.closeConnection(connection, preparedStatement);
    }
    return done;
  }


  @Override
  public List<Training> getTraining() throws ConnectionPoolException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;
    List<Training> trainings = new ArrayList<>();
    connectionPool.initPool();
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_ALL_TRAININGS);
      rs = preparedStatement.executeQuery();
      while (rs.next()) {
        Training training = new Training();
        training.setId(rs.getInt(SQL_TRAINING_ID));
        training.setName(rs.getString(SQL_NAME));
        trainings.add(training);
      }
      return trainings;
    } catch (SQLException | ConnectionPoolException e) {

    } finally {
      connectionPool.closeConnection(connection, preparedStatement, rs);
    }
    return null;
  }


}
