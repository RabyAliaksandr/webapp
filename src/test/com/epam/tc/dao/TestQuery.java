package com.epam.tc.dao;

/**
 * The type Test query.
 */
public class TestQuery {

  /**
   * The constant INSERT_TEST_USER.
   */
  public static final String INSERT_TEST_USER = "INSERT INTO users (name, surname, password, login, email)" +
          "VALUES ('name', 'surname', 'password', 'login', 'email')";
  /**
   * The constant INSERT_TEST_PAYMENT_CARD_FULL_SCORE.
   */
  public static final String INSERT_TEST_PAYMENT_CARD_FULL_SCORE = "INSERT INTO payment_cards (card_number, card_score) VALUES " +
          "(1111111111111111, 1000)";
  /**
   * The constant INSERT_TEST_CONSULTATION.
   */
  public static final String INSERT_TEST_CONSULTATION = "INSERT INTO consultations (training_id, date, price, mentor_mark)" +
          "VALUES (1, CURRENT_DATE, 100, true)";
  /**
   * The constant INSERT_TEST_USERS_CARD.
   */
  public static final String INSERT_TEST_USERS_CARD = "INSERT INTO users_payment_card (user_id, card_id) VALUES (1, 1)";
  /**
   * The constant INSERT_TEST_PAYMENT_CARD_EMPTY_SCORE.
   */
  public static final String INSERT_TEST_PAYMENT_CARD_EMPTY_SCORE = "INSERT INTO payment_cards (card_number, card_score) VALUES " +
          "(1111111111111111, 1)";
  /**
   * The constant INSERT_TEST_TRAINING.
   */
  public static final String INSERT_TEST_TRAINING = "INSERT INTO trainings (name, information, mentor_id) VALUES " +
          "('training name', 'training description', 2)";
  /**
   * The constant ADD_TEST_TRAINING_TO_STUDENT.
   */
  public static final String ADD_TEST_TRAINING_TO_STUDENT = "INSERT INTO training_by_students (user_id, training_id)" +
          "VALUES (1, 1)";
  /**
   * The constant ADD_TEST_TRAINING_TO_STUDENT_WITH_GRADE.
   */
  public static final String ADD_TEST_TRAINING_TO_STUDENT_WITH_GRADE = "INSERT INTO training_by_students " +
          "(user_id, training_id, grade_for_training) VALUES (1, 1, 1)";
}
