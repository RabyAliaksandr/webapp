package com.epam.tc.entity;

import com.epam.tc.validator.RegEx;

import java.math.BigDecimal;

/**
 * The type Payment card.
 *
 * @author alex raby
 * @version 1.0 PaymentCard class this class describes PaymentCards this class has fields id - unique number number - payment card number score - payment card score
 */
public class PaymentCard {

  /**
   * unique number
   */
  private int id;

  /**
   * card number
   * must match the pattern {@link RegEx#PATTERN_CARD_NUMBER}
   */
  private long number;

  /**
   * card score
   */
  private BigDecimal score;


  /**
   * function to get the value of the field {@link PaymentCard#id}
   *
   * @return id - unique number
   */
  public int getId() {
    return id;
  }

  /**
   * function to assignment value of the field{@link PaymentCard#id}
   *
   * @param id - unique number
   */
  public void setId(int id) {
    this.id = id;
  }
//
//  /**
//   * function to get the value of the field {@link PaymentCard#score}
//   * @return score - card score
//   */
//  public BigDecimal getScore() {
//    return score;
//  }

  /**
   * function to assignment value of the field {@link PaymentCard#score}
   *
   * @param score - card score
   */
  public void setScore(BigDecimal score) {
    this.score = score;
  }


  /**
   * Gets score.
   *
   * @return the score
   */
  public BigDecimal getScore() {
    return score;
  }

  /**
   * function to get the value of the field {@link PaymentCard#number}
   *
   * @return number - card number
   */
  public long getNumber() {
    return number;
  }

  /**
   * function to assignment value of the field {@link PaymentCard#number}
   * must match the pattern {@link RegEx#PATTERN_CARD_NUMBER}
   *
   * @param number - card number
   */
  public void setNumber(long number) {
    this.number = number;
  }
}
