package com.epam.tc.dao;

import com.epam.tc.dao.impl.*;

/**
 * The type Dao factory.
 */
public class DaoFactory {

  private static UserDao userDao = new UserDaoImpl();
  private static TrainingDao trainingDao = new TrainingDaoImpl();
  private static PaymentCardDao paymentCardDao = new PaymentCardDaoImpl();
  private static ConsultationDao consultationDao = new ConsultationDaoImpl();
  private static TaskDao taskDao = new TaskDaoImpl();
  private static TopicDao topicDao = new TopicDaoImpl();
  private static PaymentDao paymentDao = new PaymentDaoImpl();

  /**
   * Getter for property 'paymentDao'.
   *
   * @return Value for property 'paymentDao'.
   */
  public static PaymentDao getPaymentDao() {
    return paymentDao;
  }

  /**
   * Gets consultation dao.
   *
   * @return the consultation dao
   */
  public static ConsultationDao getConsultationDao() {
    return consultationDao;
  }

  /**
   * Gets task dao.
   *
   * @return the task dao
   */
  public static TaskDao getTaskDao() {
    return taskDao;
  }

  /**
   * Gets topic dao.
   *
   * @return the topic dao
   */
  public static TopicDao getTopicDao() {
    return topicDao;
  }

  /**
   * Gets user dao.
   *
   * @return the user dao
   */
  public static UserDao getUserDao() {
    return userDao;
  }

  /**
   * Gets training dao.
   *
   * @return the training dao
   */
  public static TrainingDao getTrainingDao() {
    return trainingDao;
  }

  /**
   * Gets payment card dao.
   *
   * @return the payment card dao
   */
  public static PaymentCardDao getPaymentCardDao() {
    return paymentCardDao;
  }
}
