package com.epam.tc.service;

import com.epam.tc.entity.Consultation;
import com.epam.tc.entity.Training;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface ConsultationService {


  List<Consultation> findConsultationsForTraining(int trainingId) throws ServiceException;

  boolean sendOrderConsultation(int consultationId, int studentId,
                                List<Integer> taskIds, List<Integer> topicIds) throws ServiceException;

  boolean sendOfferConsultations(int trainingId, Date date, BigDecimal price) throws ServiceException;

  Map<Training, Date> findConsultationsOffer(int mentorId) throws ServiceException;

  boolean sendAgreement(int trainingId, Date date, boolean mark) throws ServiceException;

}