package com.epam.webapp.entity;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {

  private final static String TYPE = "student";
  private int grade;
  private List<Training> trainings = new ArrayList<>();
  private List<String> list = new ArrayList<String>();

  public List<String> getList() {
    list.add("Hello");
    list.add("student");
    return list;
  }

  public static String getTYPE() {
    return TYPE;
  }

  public int getGrade() {
    return grade;
  }

  public void setGrade(int grade) {
    this.grade = grade;
  }

  public List<Training> getTrainings() {
    return trainings;
  }

  public void setTrainings(List<Training> trainings) {
    this.trainings = trainings;
  }
}
