package com.epam.tc.entity;

public class Training {


  private int id;
  private String name;
  private int grade;
  private String information;
  private User mentor;
  private boolean status;

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public User getMentor() {
    return mentor;
  }

  public void setMentor(User mentor) {
    this.mentor = mentor;
  }

  public String getInformation() {
    return information;
  }

  public void setInformation(String information) {
    this.information = information;
  }

  public int getGrade() {
    return grade;
  }

  public void setGrade(int grade) {
    this.grade = grade;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
