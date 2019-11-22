package com.epam.tc.service;

import com.epam.tc.service.impl.*;

public class ServiceFactory {

  private static TrainingService trainingService = new TrainingsServiceImpl();
  private static UserService userService = new UserServiceImpl();
  private static ConsultationService consultationService = new ConsultationServiceImpl();
  private static PaymentCardService paymentCardService = new PaymentCardServiceImpl();
  private static TaskService taskService = new TaskServiceImpl();
  private static TopicService topicService = new TopicServiceImpl();

  public static TrainingService getTrainingService() {
    return trainingService;
  }

  public static UserService getUserService() {
    return userService;
  }

  public static ConsultationService getConsultationService() {
    return consultationService;
  }

  public static PaymentCardService getPaymentCardService() {
    return paymentCardService;
  }

  public static TaskService getTaskService() {
    return taskService;
  }

  public static TopicService getTopicService() {
    return topicService;
  }
}
