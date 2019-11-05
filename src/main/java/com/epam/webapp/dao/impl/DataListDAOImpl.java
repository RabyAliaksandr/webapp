package com.epam.webapp.dao.impl;

import com.epam.webapp.dao.DataListsDAO;
import com.epam.webapp.dao.UserDAO;
import com.epam.webapp.connectionpool.ConnectionPool;
import com.epam.webapp.connectionpool.exception.ConnectionPoolException;
import com.epam.webapp.entity.Mentor;
import com.epam.webapp.entity.Student;
import com.epam.webapp.entity.Training;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataListDAOImpl implements DataListsDAO {

  private final static String SQL_ALL_TRAININGS = "SELECT * FROM trainings";//TODO
  private static final String SQL_TRAINING_ID = "trainingid";
  private final static String SQL_TRAININGS_BY_STUDENT_ID ="SELECT * FROM trainings JOIN trainingbystudents USING (trainingid) where userid=?";
  private final static String SQL_STUDENTS_BY_ID_TRAINING = "SELECT userid, name, surname, grade_for_training FROM users join trainingbystudents USING (userid) WHERE (trainingid =? and grade_for_training is null )";
  private final static String SQL_NAME = "name";
  private final static String SQL_SURNAME = "surname";
  private final static String STUDENT_ID = "userid";
  private static final String SQL_COMPLETED_TRAININGS_FOR_STUDENTS = "SELECT * FROM trainings  join trainingbystudents using (trainingid) where (trainingbystudents.userid = ? and trainingbystudents.grade_for_training is not null)";
  private final static Logger logger = LogManager.getLogger(UserDAO.class);
  private static final String SQL_MENTOR_ID = "idmentor";
  private static final String SQL_GRADE_FOR_TRAINING = "grade_for_training";
  private static final String SQL_COMPLETED_TRAININGS_FOR_MENTOR = "SELECT * FROM trainings WHERE idmentor = ?";
  private static final String SQL_ALL_TRAININGS_BY_TRAINING_ID = "SELECT * FROM trainings WHERE trainingid = ?";
  private static final String SQL_TRAINING_INFORMATION = "information";
  private static final String SQL_STUDENT_GRADE = "grade_for_training";


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
      preparedStatement.setInt( 1, id);
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
      preparedStatement.setInt( 1, trainingId);
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
      preparedStatement = connection.prepareStatement(SQL_COMPLETED_TRAININGS_FOR_STUDENTS);
      preparedStatement.setInt( 1, studentId);
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
      preparedStatement.setInt( 1, mentorId);
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
