package com.epam.tc.entity;

import java.sql.Date;

/**
 *  consultation class
 *  this class has fields id - unique number,
 *  date - consultations date,
 *  price - consultations price
 * @author alex raby
 * @version 1.0
 */

public class Consultation {

  /**
   * unique number
   */
  private int id;

  /**
   * consultation date
   */
  private Date date;

  /**
   * consultation price
   */
  private int price;

  /**
   * function to get the value of the field {@link Consultation#id}
   * @return unique number
   */
  public int getId() {
    return id;
  }

  /**
   * function to assignment value of the field {@link Consultation#id}
   * @param id - unique number
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * function to get the value of the field {@link Consultation#date}
   * @return consultations date
   */
  public Date getDate() {
    return date;
  }

  /**
   * function to assigment value of the field {@link Consultation#date}
   * @param date - consultations date
   */
  public void setDate(Date date) {
    this.date = date;
  }

  /**
   * function to get the value of the field {@link Consultation#price}
   * @return consultations price
   */
  public int getPrice() {
    return price;
  }

  /**
   * function to assigment the value of the field {@link Consultation#price}
   * @param price - consultations price
   */
  public void setPrice(int price) {
    this.price = price;
  }
}
