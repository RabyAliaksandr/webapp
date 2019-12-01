package com.epam.tc.entity;

/**
 * The type Task.
 *
 * @author alex raby
 * @version 1.0 class describes the essence of the task
 */
public class Task {

  /**
   * unique number
   */
  private int id;

  /**
   * Task name
   * min size is 2 characters max 70
   */
  private String name;

  /**
   * Task description
   * min size is 50 characters max 1000
   */
  private String task;

  /**
   * grade for the task from 1 to 10
   */
  private int mark;

  /**
   * answer to the task
   * min size is 50 characters max 1000
   */
  private String answer;

  /**
   * function to get the value of the field {@link Task#id}
   *
   * @return id - unique number, int
   */
  public int getId() {
    return id;
  }

  /**
   * function to assignment value of the field{@link Task#id}
   *
   * @param id - unique number, int
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * function to get the value of the field {@link Task#name}
   *
   * @return name - Task name, String
   */
  public String getName() {
    return name;
  }

  /**
   * function to assignment value of the field{@link Task#name}
   * min size is 2 characters max 70
   *
   * @param name - Task name, String
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * function to get the value of the field {@link Task#task}
   *
   * @return task - Task description, String
   */
  public String getTask() {
    return task;
  }

  /**
   * function to assignment value of the field{@link Task#task}
   * min size is 50 characters max 1000
   *
   * @param task - Task description, String
   */
  public void setTask(String task) {
    this.task = task;
  }

  /**
   * function to get the value of the field {@link Task#mark}
   *
   * @return int from 1 to 10
   */
  public int getMark() {
    return mark;
  }

  /**
   * function to assignment value of the field{@link Task#mark}
   *
   * @param mark - int form 1 to 10
   */
  public void setMark(int mark) {
    this.mark = mark;
  }

  /**
   * function to assignment value of the field{@link Task#answer}
   * min size is 50 characters max 1000
   *
   * @param answer - Task answer, String
   */
  public void setAnswer(String answer) {
    this.answer = answer;
  }

  /**
   * Getter for property 'answer'.
   *
   * @return Value for property 'answer'.
   */
  public String getAnswer() {
    return answer;
  }
}
