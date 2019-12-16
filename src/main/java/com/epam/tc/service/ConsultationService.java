package com.epam.tc.service;

import com.epam.tc.entity.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * The interface Consultation service.
 *
 * @author alex raby
 * @version 1.0 the interface contains methods for working with objects Consultation {@link Consultation}
 */
public interface ConsultationService {

  /**
   * finds available consultations for this training
   *
   * @param trainingId - Training id which will to look
   * @return - list of Consultation {@link Consultation}
   * @throws ServiceException package Service exception package Service exception package Service exception
   */
  List<Consultation> findConsultationsForTraining(int trainingId) throws ServiceException;

  /**
   * sends an order for a Consultation from a User for this
   * Training with a list of Topic studied and Task solved
   *
   * @param consultationId - Consultation id for which the order will be sent
   * @param studentId      - User id {@link User} who will to send
   * @param taskIds        - Task id {@link Task} list of studied Tasks
   * @param topicIds       - Topic id {@link Topic} list of studied Topics
   * @throws ServiceException package Service exception package Service exception
   */
  void sendOrderConsultation(int consultationId, int studentId,
                             List<Integer> taskIds, List<Integer> topicIds) throws ServiceException;

  /**
   * sends a consultation offer to the Mentor {@link UserType#MENTOR}
   *
   * @param trainingId - Training id {@link Training} what training will the consultation be for
   * @param date       - Date {@link Date}
   * @param price      - price of Consultation object {@link BigDecimal}
   * @throws ServiceException package Service exception {@link ServiceException}
   */
  void sendOfferConsultations(int trainingId, Date date, BigDecimal price) throws ServiceException;

  /**
   * finds consultations suggested by the Administrator {@link UserType#ADMIN}
   *
   * @param mentorId - User id {@link User} for which will to look
   * @return - object Map <Training, Date> {@link Training} {@link Date} when will be Consultation and on what Training
   * @throws ServiceException package Service exception {@link ServiceException}
   */
  Map<Training, Date> findConsultationsOffer(int mentorId) throws ServiceException;

  /**
   * sends the agreement or refusal of the mentor from consultation
   *
   * @param trainingId - Training id {@link Training} for which Training
   * @param date       - Date {@link Date} when will be Consultation
   * @param mark       - answer, boolean
   * @throws ServiceException package Service exception {@link ServiceException}
   */
  void sendAgreement(int trainingId, Date date, boolean mark) throws ServiceException;

}