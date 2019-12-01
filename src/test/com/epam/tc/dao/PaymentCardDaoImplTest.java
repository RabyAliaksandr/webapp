package com.epam.tc.dao;

import com.epam.tc.connectionpool.ConnectName;
import com.epam.tc.connectionpool.ConnectionPool;
import com.epam.tc.connectionpool.ConnectionPoolException;
import com.epam.tc.connectionpool.DataBaseManager;
import com.epam.tc.dao.impl.PaymentCardDaoImpl;
import org.junit.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The type Payment card dao impl test.
 */
public class PaymentCardDaoImplTest {
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
    preparedStatement.close();
    connection.close();
    connectionPool.dispose();
  }

  /**
   * Payment consultation positive test.
   *
   * @throws SQLException the sql exception
   * @throws DaoException the dao exception
   */
  @Test
  public void paymentConsultationPositiveTest() throws SQLException, DaoException {
    preparedStatement = connection.prepareStatement(TestQuery.INSERT_TEST_USER);
    preparedStatement.executeUpdate();
    preparedStatement.close();
    preparedStatement = connection.prepareStatement(TestQuery.INSERT_TEST_PAYMENT_CARD_FULL_SCORE);
    preparedStatement.executeUpdate();
    preparedStatement.close();
    preparedStatement = connection.prepareStatement(TestQuery.INSERT_TEST_CONSULTATION);
    preparedStatement.executeUpdate();
    preparedStatement.close();
    preparedStatement = connection.prepareStatement(TestQuery.INSERT_TEST_USERS_CARD);
    preparedStatement.executeUpdate();
    PaymentCardDao paymentCardDao = new PaymentCardDaoImpl();
    boolean actual = paymentCardDao.paymentConsultation(1,1, 1);
    boolean expected = true;
    preparedStatement = connection.prepareStatement("TRUNCATE TABLE users");
    preparedStatement.executeUpdate();
    preparedStatement.close();
    preparedStatement = connection.prepareStatement("TRUNCATE TABLE payment_cards");
    preparedStatement.executeUpdate();
    preparedStatement.close();
    preparedStatement = connection.prepareStatement("TRUNCATE consultations");
    preparedStatement.executeUpdate();
    preparedStatement.close();
    preparedStatement = connection.prepareStatement("TRUNCATE TABLE users_payment_card");
    preparedStatement.executeUpdate();
    preparedStatement.close();
    preparedStatement = connection.prepareStatement("TRUNCATE TABLE trainings_center_score");
    preparedStatement.executeUpdate();
    preparedStatement.close();
    Assert.assertEquals(expected, actual);
  }

  /**
   * Payment consultation negative test.
   *
   * @throws SQLException the sql exception
   * @throws DaoException the dao exception
   */
  @Test
  public void paymentConsultationNegativeTest() throws SQLException, DaoException {
    preparedStatement = connection.prepareStatement(TestQuery.INSERT_TEST_USER);
    preparedStatement.executeUpdate();
    preparedStatement.close();
    preparedStatement = connection.prepareStatement(TestQuery.INSERT_TEST_PAYMENT_CARD_EMPTY_SCORE);
    preparedStatement.executeUpdate();
    preparedStatement.close();
    preparedStatement = connection.prepareStatement(TestQuery.INSERT_TEST_CONSULTATION);
    preparedStatement.executeUpdate();
    preparedStatement.close();
    preparedStatement = connection.prepareStatement(TestQuery.INSERT_TEST_USERS_CARD);
    preparedStatement.executeUpdate();
    PaymentCardDao paymentCardDao = new PaymentCardDaoImpl();
    boolean actual = paymentCardDao.paymentConsultation(1,1, 1);
    boolean expected = false;
    preparedStatement = connection.prepareStatement("TRUNCATE TABLE users");
    preparedStatement.executeUpdate();
    preparedStatement.close();
    preparedStatement = connection.prepareStatement("TRUNCATE TABLE payment_cards");
    preparedStatement.executeUpdate();
    preparedStatement.close();
    preparedStatement = connection.prepareStatement("TRUNCATE consultations");
    preparedStatement.executeUpdate();
    preparedStatement.close();
    preparedStatement = connection.prepareStatement("TRUNCATE TABLE users_payment_card");
    preparedStatement.executeUpdate();
    preparedStatement.close();
    preparedStatement = connection.prepareStatement("TRUNCATE TABLE trainings_center_score");
    preparedStatement.executeUpdate();
    preparedStatement.close();
    Assert.assertEquals(expected, actual);
  }

}
