package com.epam.tc.entity;

/**
 * @author alex raby
 * @version 1.0
 * describes the entity of the Topic
 */
public class Topic {

  /**
   * unique number
   */
  private int id;

  /**
   * Topic name
   * min size is 2 characters max 70
   */
  private String name;

  /**
   * Topic description
   * min size is 50 characters max 1000
   */
  private String topic;

  /**
   * method to get the value of the field {@link Topic#id}
   * @return id - unique number, int
   */
  public int getId() {
    return id;
  }

  /**
   * method to assignment value of the field{@link Topic#id}
   * @param id - unique number, int
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * method to get the value of the field {@link Topic#name}
   * @return name - Topic name, String
   */
  public String getName() {
    return name;
  }

  /**
   * method to assignment value of the field{@link Topic#name}
   * min size is 2 characters max 70
   * @param name - Topic name, String
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * method to get the value of the field {@link Topic#topic}
   * @return task - Topic description, String
   */
  public String getTopic() {
    return topic;
  }

  /**
   * method to assignment value of the field{@link Topic#topic}
   * min size is 50 characters max 1000
   * @param topic - Topic description, String
   */
  public void setTopic(String topic) {
    this.topic = topic;
  }
}
