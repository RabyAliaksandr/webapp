package com.epam.tc.entity;

/**
 * @author alex raby
 * @version 1.0
 * describes the entity of Training
 */
public class Training {

  /**
   * unique number
   */
  private int id;

  /**
   * Training name, String
   * min size is 2 characters max 70
   */
  private String name;

  /**
   * grade for Training for User {@link User}
   * from 1 to 10
   */
  private int grade;

  /**
   * Training description
   * min size is 50 characters max 1000
   */
  private String information;

  /**
   * User who runs this training {@link User}
   */
  private User mentor;

  /**
   * training status, student enrollment open or closed
   */
  private boolean status;

  /**
   * method to get the value of the field {@link Training#status}
   * @return status, boolean
   */
  public boolean isStatus() {
    return status;
  }

  /**
   * method to assignment value of the field{@link Training#status}
   * @param status, boolean
   */
  public void setStatus(boolean status) {
    this.status = status;
  }

  /**
   * method to get the value of the field {@link Training#mentor}
   * @return mentor, User {@link User}
   */
  public User getMentor() {
    return mentor;
  }

  /**
   * method to assignment value of the field{@link Training#mentor}
   * @param mentor - User {@link User}
   */
  public void setMentor(User mentor) {
    this.mentor = mentor;
  }

  /**
   * method to get the value of the field {@link Training#information}
   * @return information, description Training
   */
  public String getInformation() {
    return information;
  }

  /**
   * method to assignment value of the field{@link Training#information}
   * min size is 50 characters max 1000
   * @param information description Training, String
   */
  public void setInformation(String information) {
    this.information = information;
  }

  /**
   * method to get the value of the field {@link Training#grade}
   * @return grade, int from 1 to 10
   */
  public int getGrade() {
    return grade;
  }

  /**
   * method to assignment value of the field{@link Training#grade}
   * @param grade, int from 1 to 10
   */
  public void setGrade(int grade) {
    this.grade = grade;
  }

  /**
   * method to get the value of the field {@link Training#id}
   * @return id, unique Training number
   */
  public int getId() {
    return id;
  }

  /**
   * method to assignment value of the field{@link Training#id}
   * @param id - Training unique number, int
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * method to get the value of the field {@link Training#name}
   * min size is 2 characters max 70
   * @return Training name, String
   */
  public String getName() {
    return name;
  }

  /**
   * method to assignment value of the field{@link Training#name}
   * min size is 50 characters max 1000
   * @param name, Training name, String
   */
  public void setName(String name) {
    this.name = name;
  }
}
