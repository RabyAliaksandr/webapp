package com.epam.tc.entity;

/**
 * The type Payment.
 */
public class Payment {
  private int id;
  private Consultation consultation;
  private User user;
  private PaymentCard paymentCard;

  /**
   * Getter for property 'id'.
   *
   * @return Value for property 'id'.
   */
  public int getId() {
    return id;
  }

  /**
   * Setter for property 'id'.
   *
   * @param id Value to set for property 'id'.
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Getter for property 'consultation'.
   *
   * @return Value for property 'consultation'.
   */
  public Consultation getConsultation() {
    return consultation;
  }

  /**
   * Setter for property 'consultation'.
   *
   * @param consultation Value to set for property 'consultation'.
   */
  public void setConsultation(Consultation consultation) {
    this.consultation = consultation;
  }

  /**
   * Getter for property 'user'.
   *
   * @return Value for property 'user'.
   */
  public User getUser() {
    return user;
  }

  /**
   * Setter for property 'user'.
   *
   * @param user Value to set for property 'user'.
   */
  public void setUser(User user) {
    this.user = user;
  }

  /**
   * Getter for property 'paymentCard'.
   *
   * @return Value for property 'paymentCard'.
   */
  public PaymentCard getPaymentCard() {
    return paymentCard;
  }

  /**
   * Setter for property 'paymentCard'.
   *
   * @param paymentCard Value to set for property 'paymentCard'.
   */
  public void setPaymentCard(PaymentCard paymentCard) {
    this.paymentCard = paymentCard;
  }
}
