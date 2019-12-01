package com.epam.tc.dao;

import com.epam.tc.connectionpool.ConnectName;
import com.epam.tc.connectionpool.ConnectionPool;
import com.epam.tc.connectionpool.ConnectionPoolException;
import com.epam.tc.connectionpool.DataBaseManager;
import com.epam.tc.dao.impl.TrainingDaoImpl;
import org.junit.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Training dao impl test.
 */
public class TrainingDaoImplTest {

  private static ResultSet resultSet = null;
  private static PreparedStatement preparedStatement = null;
  private static ConnectionPool connectionPool = null;
  private static Connection connection = null;

  /**
   * Init.
   *
   * @throws ConnectionPoolException the connection pool exception
   */
  @BeforeClass
  public static void init() throws ConnectionPoolException {
    DataBaseManager db = DataBaseManager.getInstance(ConnectName.TEST_PROPERTIES_PATH);
    connectionPool = ConnectionPool.getInstance();
    connection = connectionPool.takeConnection();
  }

  /**
   * Close.
   */
  @After
  public void close() {
    resultSet = null;
    preparedStatement = null;
  }

  /**
   * Destroy.
   *
   * @throws ConnectionPoolException the connection pool exception
   * @throws SQLException            the sql exception
   */
  @AfterClass
  public static void destroy() throws ConnectionPoolException, SQLException {
    connection.close();
    connectionPool.dispose();
  }

  /**
   * Delete training negative test.
   *
   * @throws SQLException the sql exception
   * @throws DaoException the dao exception
   */
  @Test
  public void deleteTrainingNegativeTest() throws SQLException, DaoException {
    preparedStatement = connection.prepareStatement(TestQuery.INSERT_TEST_USER);
    preparedStatement.executeUpdate();
    preparedStatement.close();
    preparedStatement = connection.prepareStatement(TestQuery.INSERT_TEST_TRAINING);
    preparedStatement.executeUpdate();
    preparedStatement.close();
    preparedStatement = connection.prepareStatement(TestQuery.ADD_TEST_TRAINING_TO_STUDENT);
    preparedStatement.executeUpdate();
    preparedStatement.close();
    TrainingDao trainingDao = new TrainingDaoImpl();
    boolean actual = trainingDao.deleteTraining(1);
    boolean expected = false;
    preparedStatement = connection.prepareStatement("TRUNCATE TABLE test_trainings_center.users");
    preparedStatement.executeUpdate();
    preparedStatement.close();
    preparedStatement = connection.prepareStatement("TRUNCATE TABLE test_trainings_center.training_by_students");
    preparedStatement.executeUpdate();
    preparedStatement.close();
    preparedStatement = connection.prepareStatement("TRUNCATE TABLE test_trainings_center.trainings");
    preparedStatement.executeUpdate();
    preparedStatement.close();
    Assert.assertEquals(expected, actual);
  }

  /**
   * Delete training positive test.
   *
   * @throws SQLException the sql exception
   * @throws DaoException the dao exception
   */
  @Test
  public void deleteTrainingPositiveTest() throws SQLException, DaoException {
    preparedStatement = connection.prepareStatement(TestQuery.INSERT_TEST_USER);
    preparedStatement.executeUpdate();
    preparedStatement.close();
    preparedStatement = connection.prepareStatement(TestQuery.INSERT_TEST_TRAINING);
    preparedStatement.executeUpdate();
    preparedStatement.close();
    preparedStatement = connection.prepareStatement(TestQuery.ADD_TEST_TRAINING_TO_STUDENT_WITH_GRADE);
    preparedStatement.executeUpdate();
    preparedStatement.close();
    TrainingDao trainingDao = new TrainingDaoImpl();
    boolean actual = trainingDao.deleteTraining(1);
    boolean expected = true;
    preparedStatement = connection.prepareStatement("TRUNCATE TABLE test_trainings_center.users");
    preparedStatement.executeUpdate();
    preparedStatement.close();
    preparedStatement = connection.prepareStatement("TRUNCATE TABLE test_trainings_center.training_by_students");
    preparedStatement.executeUpdate();
    preparedStatement.close();
    preparedStatement = connection.prepareStatement("TRUNCATE TABLE test_trainings_center.trainings");
    preparedStatement.executeUpdate();
    preparedStatement.close();
    Assert.assertEquals(expected, actual);
  }
}

