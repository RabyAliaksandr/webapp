package com.epam.tc.dao;

import com.epam.tc.entity.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * interface contains methods for work with object Consultation
 *
 * @author alex raby
 * @version 1.0
 */
public interface ConsultationDao {

  /**
   * finds available consultations for this training
   *
   * @param trainingId - Training id which will to look
   * @return - list of Consultation {@link Consultation}
   * @throws DaoException the dao exception
   */
  List<Consultation> findConsultationsForTraining(int trainingId) throws DaoException;

  /**
   * sends an order for a Consultation from a User for this
   * Training with a list of Topic studied and Task solved
   *
   * @param consultationId - Consultation id for which the order will be sent
   * @param studentId      - User id {@link User} who will to send
   * @param taskIds        - Task id {@link Task} list of studied Tasks
   * @param topicIds       - Topic id {@link Topic} list of studied Topics
   * @throws DaoException the dao exception
   */
  void sendOrderConsultation(int consultationId, int studentId,
                             List<Integer> taskIds, List<Integer> topicIds) throws DaoException;

  /**
   * sends a consultation offer to the Mentor {@link UserType#MENTOR}
   *
   * @param trainingId - Training id {@link Training} what training will the consultation be for
   * @param date       - Date {@link Date}
   * @param price      - price of Consultation object {@link BigDecimal}
   * @throws DaoException the dao exception
   */
  boolean sendOfferConsultations(int trainingId, Date date, BigDecimal price) throws DaoException;

  /**
   * finds consultations suggested by the Administrator {@link UserType#ADMIN}
   *
   * @param mentorId - User id {@link User} for which will to look
   * @return - object Map <Training, Date> {@link Training} {@link Date} when will be Consultation and on what Training
   * @throws DaoException the dao exception
   */
  Map<Training, Date> findConsultationsOffer(int mentorId) throws DaoException;

  /**
   * sends the agreement or refusal of the mentor from consultation
   *
   * @param trainingId - Training id {@link Training} for which Training
   * @param date       - Date {@link Date} when will be Consultation
   * @param mark       - answer, boolean
   * @throws DaoException the dao exception
   */
  boolean sendAgreement(int trainingId, Date date, boolean mark) throws DaoException;
}
