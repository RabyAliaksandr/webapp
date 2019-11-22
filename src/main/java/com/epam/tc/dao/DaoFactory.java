package com.epam.tc.dao;

import com.epam.tc.dao.impl.*;

public class DaoFactory {

  private static UserDao userDao = new UserDaoImpl();
  private static TrainingDao trainingDao = new TrainingDaoImpl();
  private static PaymentCardDao paymentCardDao = new PaymentCardDaoImpl();
  private static ConsultationDao consultationDao = new ConsultationDaoImpl();
  private static TaskDao taskDao = new TaskDaoImpl();
  private static TopicDao topicDao = new TopicDaoImpl();

  public static ConsultationDao getConsultationDao() {
    return consultationDao;
  }

  public static TaskDao getTaskDao() {
    return taskDao;
  }

  public static TopicDao getTopicDao() {
    return topicDao;
  }

  public static UserDao getUserDao() {
    return userDao;
  }

  public static TrainingDao getTrainingDao() {
    return trainingDao;
  }

  public static PaymentCardDao getPaymentCardDao() {
    return paymentCardDao;
  }
}
