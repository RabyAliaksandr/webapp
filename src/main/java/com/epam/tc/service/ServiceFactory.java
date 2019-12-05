package com.epam.tc.service;

import com.epam.tc.service.impl.*;

/**
 * The type Service factory.
 *
 * @author alex raby
 * @version 1.0 this class is Factory design pattern for service package classes
 * @see TrainingsServiceImpl
 * @see UserServiceImpl
 * @see ConsultationServiceImpl
 * @see TaskServiceImpl
 * @see TopicServiceImpl
 * @see PaymentCardServiceImpl
 */
public class ServiceFactory {

  /** Do not instantiate ServiceFactory. */
  private ServiceFactory(){}

  /**
   * this field is a object TraTrainingsServiceImpl
   * @see TrainingsServiceImpl
   * @see TrainingService
   */
  private static TrainingService trainingService = new TrainingsServiceImpl();

  private static PaymentService paymentService = new PaymentImpl();

  /**
   * this field is a object UserServiceImpl
   * @see UserService
   * @see UserServiceImpl
   */
  private static UserService userService = new UserServiceImpl();

  /**
   * this field is a object ConsultationServiceImpl
   * @see ConsultationService
   * @see ConsultationServiceImpl
   */
  private static ConsultationService consultationService = new ConsultationServiceImpl();

  /**
   * this field is a object PaymentCardServiceImpl
   * @see PaymentCardService
   * @see PaymentCardServiceImpl
   */
  private static PaymentCardService paymentCardService = new PaymentCardServiceImpl();

  /**
   * this field is a object TaskServiceImpl
   * @see TaskService
   * @see TaskServiceImpl
   */
  private static TaskService taskService = new TaskServiceImpl();

  /**
   * this field is a object TopicServiceImpl
   * @see TopicService
   * @see TopicServiceImpl
   */
  private static TopicService topicService = new TopicServiceImpl();

  /**
   * this method to get the field trainingService {@link ServiceFactory#trainingService}
   *
   * @return TrainingService training service
   * @see TrainingService
   */
  public static TrainingService getTrainingService() {
    return trainingService;
  }

  /**
   * this method to get the field userService {@link ServiceFactory#userService}
   *
   * @return UserService user service
   * @see UserService
   */
  public static UserService getUserService() {
    return userService;
  }

  /**
   * this method to get the field consultationService {@link ServiceFactory#consultationService}
   *
   * @return ConsultationService consultation service
   * @see ConsultationService
   */
  public static ConsultationService getConsultationService() {
    return consultationService;
  }

  /**
   * this method to get the field paymentCardService {@link ServiceFactory#paymentCardService}
   *
   * @return PaymentCardService payment card service
   * @see PaymentCardService
   */
  public static PaymentCardService getPaymentCardService() {
    return paymentCardService;
  }

  /**
   * this method to get the field taskService {@link ServiceFactory#taskService}
   *
   * @return TaskService task service
   * @see TaskService
   */
  public static TaskService getTaskService() {
    return taskService;
  }

  /**
   * this method to get the field topicService {@link ServiceFactory#topicService}
   *
   * @return TopicService topic service
   * @see TopicService
   */
  public static TopicService getTopicService() {
    return topicService;
  }

  /**
   * Getter for property 'paymentService'.
   *
   * @return Value for property 'paymentService'.
   * @see PaymentService
   */
  public static PaymentService getPaymentService() {
    return paymentService;
  }
}
