package com.epam.tc.dao;

import com.epam.tc.entity.Training;
import com.epam.tc.entity.Consultation;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * The interface Consultation dao.
 */
public interface ConsultationDao {

  /**
   * Find consultations for training list.
   *
   * @param trainingId the training id
   * @return the list
   * @throws DaoException the dao exception
   */
  List<Consultation> findConsultationsForTraining(int trainingId) throws DaoException;

  /**
   * Send order consultation.
   *
   * @param consultationId the consultation id
   * @param studentId      the student id
   * @param taskIds        the task ids
   * @param topicIds       the topic ids
   * @throws DaoException the dao exception
   */
  void sendOrderConsultation(int consultationId, int studentId,
                                List<Integer> taskIds, List<Integer> topicIds) throws DaoException;

  /**
   * Send offer consultations boolean.
   *
   * @param trainingId the training id
   * @param date       the date
   * @param price      the price
   * @return the boolean
   * @throws DaoException the dao exception
   */
  boolean sendOfferConsultations(int trainingId, Date date, BigDecimal price) throws DaoException;

  /**
   * Find consultations offer map.
   *
   * @param mentorId the mentor id
   * @return the map
   * @throws DaoException the dao exception
   */
  Map<Training, Date> findConsultationsOffer(int mentorId) throws DaoException;

  /**
   * Send agreement boolean.
   *
   * @param trainingId the training id
   * @param date       the date
   * @param mark       the mark
   * @return the boolean
   * @throws DaoException the dao exception
   */
  boolean sendAgreement(int trainingId, Date date, boolean mark) throws DaoException;
}
