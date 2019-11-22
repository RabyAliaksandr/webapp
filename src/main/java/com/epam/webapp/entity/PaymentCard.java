package com.epam.webapp.entity;

import java.math.BigDecimal;

public class PaymentCard {

  private int id;
  private Integer number;
  private BigDecimal score;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public BigDecimal getScore() {
    return score;
  }

  public void setScore(BigDecimal score) {
    this.score = score;
  }
}
