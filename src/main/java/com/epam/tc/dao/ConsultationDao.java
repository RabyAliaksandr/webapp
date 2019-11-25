package com.epam.tc.dao;

import com.epam.tc.entity.Consultation;
import com.epam.tc.entity.Training;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface ConsultationDao {

  List<Consultation> findConsultationsForTraining(int trainingId) throws DaoException;

  void sendOrderConsultation(int consultationId, int studentId,
                                List<Integer> taskIds, List<Integer> topicIds) throws DaoException;

  boolean sendOfferConsultations(int trainingId, Date date, BigDecimal price) throws DaoException;

  Map<Training, Date> findConsultationsOffer(int mentorId) throws DaoException;

  boolean sendAgreement(int trainingId, Date date, boolean mark) throws DaoException;
}
