package com.epam.webapp.dao.impl;

import com.epam.webapp.dao.DataListsDAO;
import com.epam.webapp.dao.UserDAO;
import com.epam.webapp.connectionpool.ConnectionPool;
import com.epam.webapp.connectionpool.ConnectionPoolException;
import com.epam.webapp.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//created trigger update student task when insert into task FIXME
//and automat. add task for every student who registered at this training (update_student_task)
//CREATE TRIGGER update_student_task
//        AFTER INSERT ON tasks
//        FOR EACH ROW
//        BEGIN
//        insert into student_task (user_id, task_id)
//        SELECT   users.user_id, tasks.task_id
//        FROM training_by_students  inner join tasks  using(training_id) left  join  student_task using (user_id, task_id) left join users  using (user_id)
//        where student_task.user_id is  null;
//        END ;


public class DataListDAOImpl implements DataListsDAO {

  private final static Logger logger = LogManager.getLogger(UserDAO.class);
  private static final String SQL_TOPIC_BY_TOPIC_ID = "SELECT * FROM topics_for_study WHERE (topic_id = ?)";
  private static final String SQL_UPDATE_TRAININGS_INFORMATION = "UPDATE trainings SET information = ?, name = ? WHERE training_id = ?";
  private static final String SQL_ADD_TOPIC_FOR_TRAINING = "INSERT INTO topics_for_study (training_id, name_topic, topic) VALUES (?, ?, ?)";
  private static final String SQL_ADD_TASK_FOR_TRAINING = "INSERT INTO tasks (training_id, task_name, task) VALUES (?, ?, ?)";
  private static final String SQL_TASKS_FOR_TRAINING = "SELECT * FROM tasks WHERE training_id = ?";
  private final static String SQL_ALL_TRAININGS = "SELECT * FROM trainings";//TODO
  private final static String SQL_TRAININGS_BY_STUDENT_ID = "SELECT * FROM trainings JOIN training_by_students USING (training_id) where user_id = ?";
  private static final String SQL_TOPICS_FOR_TRAINING = "SELECT name_topic, topic, topic_id FROM topics_for_study WHERE training_id = ?";
  private static final String SQL_COMPLETED_TRAININGS_FOR_MENTOR = "SELECT * FROM trainings WHERE mentor_id = ?";
  private static final String SQL_ALL_TRAININGS_BY_TRAINING_ID = "SELECT * FROM trainings WHERE training_id = ?";
  private final static String SQL_STUDENTS_BY_ID_TRAINING = "SELECT user_id, name, surname, grade_for_training FROM " +
          "users join training_by_students USING (user_id) WHERE (training_id =? and grade_for_training = 0)";
  private final static String SQL_NAME = "name";
  private final static String SQL_SURNAME = "surname";
  private final static String STUDENT_ID = "user_id";
  private static final String SQL_COMPLETED_TRAININGS_FOR_STUDENTS = "SELECT * FROM trainings  join trainingbystudents using (trainingid) where (trainingbystudents.userid = ? and trainingbystudents.grade_for_training = 0)";
  private static final String SQL_MENTOR_ID = "mentor_id";
  private static final String SQL_GRADE_FOR_TRAINING = "grade_for_training";
  private static final String SQL_TRAINING_INFORMATION = "information";
  private static final String SQL_STUDENT_GRADE = "grade_for_training";
  private static final String SQL_NAME_TOPIC = "name_topic";
  private static final String SQL_TOPIC = "topic";
  private static final String SQL_TRAINING_ID = "training_id";
  private static final String SQL_TASK_NAME = "task_name";
  private static final String SQL_TASK = "task";
  private static final String SQL_CREATE_TRAINING = "INSERT  INTO trainings (name, mentor_id, information) VALUES (?, ?, ?)";
  private static final String SQL_UPDATE_TRAININGS_TOPIC = "UPDATE topics_for_study SET name_topic = ?, topic = ? WHERE (topic_id = ?)";
  private static final String SQL_CHECK_STATUS_TOPIC = "SELECT topic_status FROM student_topic WHERE (user_id = ? AND  topic_id = ?)";
  private static final String SQL_TOPIC_STATUS = "topic_status";
  private static final String SQL_TASK_ID = "task_id";
  private static final String SQL_TOPIC_ID = "topic_id";
  private static final String SQL_SET_MARK_TOPIC = "INSERT INTO student_topic (user_id, topic_id, topic_status) VALUES (?, ?, true)";
  private static final String SQL_CHECK_TRAINING_STATUS_FOR_STUDENT = "SELECT COUNT(1) FROM training_by_students WHERE (user_id = ? AND training_id = ?);";
  private static final String SQL_STATUS = "status";
  private static final String SQL_TASK_BY_ID = "SELECT * FROM trainings_center.tasks WHERE task_id = ?";
  private static final String SQL_UPDATE_TRAININGS_TASK = "UPDATE trainings_center.tasks SET task_name = ?, task = ? WHERE task_id = ?";
  private static final String SQL_CHECK_STATUS_TASK = "SELECT COUNT(answer) FROM student_task WHERE (user_id = ?  AND task_id = ?)";
  private static final String SQL_MARK = "mark";
  private static final String SQL_SEND_SOLUTION = "UPDATE student_task SET answer = ? WHERE user_id= ? AND task_id= ?";
  private static final String SQl_TASK_SOLUTION = "SELECT answer, mark FROM student_task WHERE user_id = ? AND task_id = ?";
  private static final String SQL_GRADE_TASK = "UPDATE student_task SET mark = ? WHERE user_id = ? AND task_id = ?";
  private static final String SQL_MARK_FOR_TASK = "SELECT mark FROM student_task WHERE user_id = ? AND task_id = ?";
  private static final String SQL_ANSWER = "answer";
  private static final String SQL_AVG_FOR_TASKS = "SELECT AVG(mark) FROM student_task INNER JOIN tasks USING (task_id) " +
          "LEFT JOIN trainings USING (training_id) WHERE user_id = ? AND training_id = ? AND mark > 0";
  private static final String SQL_AVG_MARK = "AVG(mark)";


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
    return trainings;
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
  public boolean updateTrainingsInformation(int trainingId, String trainingName, String information) throws ConnectionPoolException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    connectionPool.initPool();
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_UPDATE_TRAININGS_INFORMATION);
      preparedStatement.setString(1, information);
      preparedStatement.setString(2, trainingName);
      preparedStatement.setInt(3, trainingId);
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
  public boolean updateTrainingsTopic(int topicId, String topicName, String topic) throws ConnectionPoolException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    connectionPool.initPool();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_UPDATE_TRAININGS_TOPIC);
      preparedStatement.setString(1, topicName);
      preparedStatement.setString(2, topic);
      preparedStatement.setInt(3, topicId);
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
  public Task getTask(int taskId) throws ConnectionPoolException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    connectionPool.initPool();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Task task = new Task();

    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_TASK_BY_ID);
      preparedStatement.setInt(1, taskId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        task.setId(resultSet.getInt(SQL_TASK_ID));
        task.setTask(resultSet.getString(SQL_TASK));
        task.setName(resultSet.getString(SQL_TASK_NAME));
      }
      return task;
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      connectionPool.closeConnection(connection, preparedStatement, resultSet);
    }
    return task;
  }

  @Override
  public boolean updateTask(int taskId, String taskName, String task) throws ConnectionPoolException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    connectionPool.initPool();
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_UPDATE_TRAININGS_TASK);
      preparedStatement.setString(1, taskName);
      preparedStatement.setString(2, task);
      preparedStatement.setInt(3, taskId);
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
  public boolean checkTaskStatus(int userId, int taskId) throws ConnectionPoolException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    connectionPool.initPool();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    boolean done = false;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_CHECK_STATUS_TASK);
      preparedStatement.setInt(1, userId);
      preparedStatement.setInt(2, taskId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        done = resultSet.getBoolean(1);
      }
      return done;
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      connectionPool.closeConnection(connection, preparedStatement, resultSet);
    }
    return done;
  }

  @Override
  public boolean sendSolution(int userId, int taskId, String answer) throws ConnectionPoolException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    connectionPool.initPool();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_SEND_SOLUTION);
      preparedStatement.setString(1, answer);
      preparedStatement.setInt(2, userId);
      preparedStatement.setInt(3, taskId);
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
  public Map<String, Integer> findTaskSolution(int studentId, int taskId) throws ConnectionPoolException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    connectionPool.initPool();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Map<String, Integer> solution = new HashMap<>();

    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQl_TASK_SOLUTION);
      preparedStatement.setInt(1, studentId);
      preparedStatement.setInt(2, taskId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        String answer = resultSet.getString(SQL_ANSWER);
        int mark = resultSet.getInt(SQL_MARK);
        solution.put(answer, mark);
      }
      return solution;
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      connectionPool.closeConnection(connection, preparedStatement, resultSet);
    }
    return solution;
  }

  @Override
  public boolean gradeTask(int studentId, int taskId, int mark) throws ConnectionPoolException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    connectionPool.initPool();
    PreparedStatement preparedStatement = null;
    boolean done = false;
    try{
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_GRADE_TASK);
      preparedStatement.setInt(1, mark);
      preparedStatement.setInt(2, studentId);
      preparedStatement.setInt(3, taskId);
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
  public int avgMarkForTask(int userId, int trainingId) throws ConnectionPoolException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = null;
    connectionPool.initPool();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    int avgMark = 0;
    try {
      connection = connectionPool.takeConnection();
      preparedStatement = connection.prepareStatement(SQL_AVG_FOR_TASKS);
      preparedStatement.setInt(1, userId);
      preparedStatement.setInt(2, trainingId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        avgMark = resultSet.getInt(SQL_AVG_MARK);
      }
      return avgMark;
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      connectionPool.closeConnection(connection, preparedStatement, resultSet);
    }
    return avgMark;
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
