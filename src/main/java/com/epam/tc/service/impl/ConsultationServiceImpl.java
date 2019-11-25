package com.epam.tc.service.impl;

import com.epam.tc.dao.ConsultationDao;
import com.epam.tc.dao.DaoException;
import com.epam.tc.dao.DaoFactory;
import com.epam.tc.entity.Consultation;
import com.epam.tc.entity.Training;
import com.epam.tc.service.ConsultationService;
import com.epam.tc.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * @author alex raby
 * @version 1.0
 * this class implements interface methods ConsultationService {@link ConsultationService}
 * methods of this class catch DaoException {@link DaoException} and throw ServiceException {@link ServiceException}
 */
public class ConsultationServiceImpl implements ConsultationService {

  /**
   * class object Logger {@link Logger}
   * writes important events to a log file
   */
  private static Logger logger = LogManager.getLogger(ConsultationServiceImpl.class);

  public final List<Consultation> findConsultationsForTraining(int trainingId) throws ServiceException {
    ConsultationDao consultationDao = DaoFactory.getConsultationDao();
    try {
      return consultationDao.findConsultationsForTraining(trainingId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final void sendOrderConsultation(int consultationId, int studentId, List<Integer> taskIds,
                                             List<Integer> topicIds) throws ServiceException {
    ConsultationDao consultationDao = DaoFactory.getConsultationDao();
    try {
      consultationDao.sendOrderConsultation(consultationId, studentId, taskIds, topicIds);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }

  public final void sendOfferConsultations(int trainingId, Date date, BigDecimal price) throws ServiceException {
    ConsultationDao consultationDao = DaoFactory.getConsultationDao();
    try {
      consultationDao.sendOfferConsultations(trainingId, date, price);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }
  public final Map<Training, Date> findConsultationsOffer(int mentorId) throws ServiceException {
    ConsultationDao consultationDao = DaoFactory.getConsultationDao();
    Map<Training, Date> consultations;
    try {
      consultations = consultationDao.findConsultationsOffer(mentorId);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
    return consultations;
  }

  public final void sendAgreement(int trainingId, Date date, boolean mark) throws ServiceException {
    ConsultationDao consultationDao = DaoFactory.getConsultationDao();
    try {
      consultationDao.sendAgreement(trainingId, date, mark);
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException("Error access database", e);
    }
  }
}
