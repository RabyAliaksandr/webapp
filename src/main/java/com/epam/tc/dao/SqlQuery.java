package com.epam.tc.dao;

/**
 * The type Sql query.
 */
public class SqlQuery {

  /**
   * The constant SQL_FIND_FINAL_GRADE.
   */
  public static final String SQL_FIND_FINAL_GRADE = "SELECT grade_for_training FROM training_by_students WHERE " +
          "user_id = ? AND training_id = ?";
  /**
   * The constant SQL_TOPIC_BY_TOPIC_ID.
   */
  public static final String SQL_TOPIC_BY_TOPIC_ID = "SELECT * FROM topics_for_study WHERE (topic_id = ?)";
  /**
   * The constant SQL_UPDATE_TRAININGS_INFORMATION.
   */
  public static final String SQL_UPDATE_TRAININGS_INFORMATION = "UPDATE trainings SET information = ?, name = ? " +
          "WHERE training_id = ?";
  /**
   * The constant SQL_ADD_TOPIC_FOR_TRAINING.
   */
  public static final String SQL_ADD_TOPIC_FOR_TRAINING = "INSERT INTO topics_for_study " +
          "(training_id, name_topic, topic) VALUES (?, ?, ?)";
  /**
   * The constant SQL_ADD_TASK_FOR_TRAINING.
   */
  public static final String SQL_ADD_TASK_FOR_TRAINING = "INSERT INTO tasks " +
          "(training_id, task_name, task) VALUES (?, ?, ?)";
  /**
   * The constant SQL_TASKS_FOR_TRAINING.
   */
  public static final String SQL_TASKS_FOR_TRAINING = "SELECT * FROM tasks WHERE training_id = ?";
  /**
   * The constant SQL_ALL_TRAININGS.
   */
  public final static String SQL_ALL_TRAININGS = "SELECT * FROM trainings";//TODO
  /**
   * The constant SQL_TRAININGS_BY_STUDENT_ID.
   */
  public final static String SQL_TRAININGS_BY_STUDENT_ID = "SELECT * FROM trainings JOIN training_by_students" +
          " USING (training_id) where user_id = ?";
  /**
   * The constant SQL_TOPICS_FOR_TRAINING.
   */
  public static final String SQL_TOPICS_FOR_TRAINING = "SELECT name_topic, topic, topic_id FROM topics_for_study" +
          " WHERE training_id = ?";
  /**
   * The constant SQL_COMPLETED_TRAININGS_FOR_MENTOR.
   */
  public static final String SQL_COMPLETED_TRAININGS_FOR_MENTOR = "SELECT * FROM trainings WHERE mentor_id = ?";
  /**
   * The constant SQL_ALL_TRAININGS_BY_TRAINING_ID.
   */
  public static final String SQL_ALL_TRAININGS_BY_TRAINING_ID = "SELECT * FROM trainings WHERE training_id = ?";
  /**
   * The constant SQL_STUDENTS_BY_ID_TRAINING.
   */
  public final static String SQL_STUDENTS_BY_ID_TRAINING = "SELECT user_id, name, surname, grade_for_training FROM " +
          "users join training_by_students USING (user_id) WHERE (training_id =? and grade_for_training = 0)";
  /**
   * The constant SQL_COMPLETED_TRAININGS_FOR_STUDENTS.
   */
  public static final String SQL_COMPLETED_TRAININGS_FOR_STUDENTS = "SELECT * FROM trainings  " +
          "join trainingbystudents using (trainingid) where (trainingbystudents.userid = ? and " +
          "trainingbystudents.grade_for_training = 0)";
  /**
   * The constant SQL_CREATE_TRAINING.
   */
  public static final String SQL_CREATE_TRAINING = "INSERT  INTO trainings (name, mentor_id, information)" +
          " VALUES (?, ?, ?)";
  /**
   * The constant SQL_UPDATE_TRAININGS_TOPIC.
   */
  public static final String SQL_UPDATE_TRAININGS_TOPIC = "UPDATE topics_for_study SET name_topic = ?, topic = ? " +
          "WHERE (topic_id = ?)";
  /**
   * The constant SQL_CHECK_STATUS_TOPIC.
   */
  public static final String SQL_CHECK_STATUS_TOPIC = "SELECT topic_status FROM student_topic" +
          " WHERE (user_id = ? AND  topic_id = ?)";
  /**
   * The constant SQL_SET_MARK_TOPIC.
   */
  public static final String SQL_SET_MARK_TOPIC = "INSERT INTO student_topic (user_id, topic_id, topic_status) " +
          "VALUES (?, ?, true)";
  /**
   * The constant SQL_CHECK_TRAINING_STATUS_FOR_STUDENT.
   */
  public static final String SQL_CHECK_TRAINING_STATUS_FOR_STUDENT = "SELECT COUNT(1) FROM training_by_students " +
          "WHERE (user_id = ? AND training_id = ?);";
  /**
   * The constant SQL_TASK_BY_ID.
   */
  public static final String SQL_TASK_BY_ID = "SELECT * FROM trainings_center.tasks WHERE task_id = ?";
  /**
   * The constant SQL_UPDATE_TRAININGS_TASK.
   */
  public static final String SQL_UPDATE_TRAININGS_TASK = "UPDATE trainings_center.tasks" +
          " SET task_name = ?, task = ? WHERE task_id = ?";
  /**
   * The constant SQL_CHECK_STATUS_TASK.
   */
  public static final String SQL_CHECK_STATUS_TASK = "SELECT mark FROM student_task " +
          "WHERE (user_id = ?  AND task_id = ?)";
  /**
   * The constant SQL_SEND_SOLUTION.
   */
  public static final String SQL_SEND_SOLUTION = "UPDATE student_task SET answer = ? WHERE user_id= ? AND task_id= ?";
  /**
   * The constant SQl_TASK_SOLUTION.
   */
  public static final String SQl_TASK_SOLUTION = "SELECT answer, mark FROM student_task WHERE user_id = ? AND task_id = ?";
  /**
   * The constant SQL_GRADE_TASK.
   */
  public static final String SQL_GRADE_TASK = "UPDATE student_task SET mark = ? WHERE user_id = ? AND task_id = ?";
  /**
   * The constant SQL_MARK_FOR_TASK.
   */
  public static final String SQL_MARK_FOR_TASK = "SELECT mark FROM student_task WHERE user_id = ? AND task_id = ?";

  /**
   * The constant SQL_AVG_FOR_TASKS.
   */
  public static final String SQL_AVG_FOR_TASKS = "SELECT AVG(mark) FROM student_task INNER JOIN tasks USING (task_id) " +
          "LEFT JOIN trainings USING (training_id) WHERE user_id = ? AND training_id = ? AND mark > 0";
  /**
   * The constant SQL_AVG_MARK.
   */
  public static final String SQL_AVG_MARK = "AVG(mark)";
  /**
   * The constant SQL_COMPLETED_TASKS_FOR_STUDENT.
   */
  public static final String SQL_COMPLETED_TASKS_FOR_STUDENT = "SELECT task_id, task_name FROM student_task JOIN" +
          " tasks USING (task_id) WHERE user_id = ? AND training_id = ? AND mark > 0";
  /**
   * The constant SQL_LEARNED_TOPICS.
   */
  public static final String SQL_LEARNED_TOPICS = "SELECT topic_id, name_topic FROM student_topic JOIN topics_for_study " +
          "USING (topic_id) WHERE user_id = ? AND training_id = ?";
  /**
   * The constant SQL_CONSULTATIONS_FOR_TRAINING.
   */
  public static final String SQL_CONSULTATIONS_FOR_TRAINING = "SELECT consultation_id, date, price FROM consultations " +
          "LEFT JOIN trainings USING (training_id) WHERE training_id = ? AND date > CURRENT_DATE";

  /**
   * The constant SQL_TASKS_FOR_CONSULTATION.
   */
  public static final String SQL_TASKS_FOR_CONSULTATION = "INSERT INTO consultations_task " +
          "(consultation_id, user_id, task_id) VALUES (?, ?, ?)";
  /**
   * The constant SQL_TOPICS_FOR_CONSULTATION.
   */
  public static final String SQL_TOPICS_FOR_CONSULTATION = "INSERT INTO consultations_topics" +
          " (consultation_id, user_id, topic_id) VALUES (?, ?, ?)";
  /**
   * The constant SQL_REPLENISH_CARD.
   */
  public static final String SQL_REPLENISH_CARD = "UPDATE payment_cards JOIN users_payment_card USING (card_id)" +
          " SET card_score = card_score +  ? WHERE card_id = ?";
  /**
   * The constant SQL_TRANSFER_MONEY_CARD_TO_CARD.
   */
  public static final String SQL_TRANSFER_MONEY_CARD_TO_CARD = "UPDATE trainings_center.payment_cards p_c," +
          " (SELECT card_score FROM trainings_center.payment_cards a WHERE card_id = ?) test\n" +
          "SET p_c.card_score=p_c.card_score + CASE p_c.card_id\n" +
          "                                WHEN ? THEN - ?\n" +
          "                                WHEN ? THEN ?\n" +
          "    END\n" +
          "WHERE test.card_score >= ?\n" +
          "  AND p_c.card_id IN (?,?)";
  /**
   * The constant SQL_REMOVAL_MONEY.
   */
  public static final String SQL_REMOVAL_MONEY = "update trainings_center.payment_cards," +
          " trainings_center.trainings_center_score set card_score = card_score -(SELECT price FROM consultations " +
          "WHERE consultations.consultation_id = ?) WHERE payment_cards.card_id = ? AND card_score > " +
          "(SELECT price FROM consultations WHERE consultations.consultation_id = ?)";
  /**
   * The constant SQL_PUT_MONEY.
   */
  public static final String SQL_PUT_MONEY = "INSERT INTO trainings_center_score (consultation_id, sum, user_id, " +
          "card_id, payment_date) VALUES " +
          "(?, (SELECT price FROM consultations WHERE consultations.consultation_id = ?), ?, ?, current_date)";
  /**
   * The constant SQL_FIND_USERS_CARD.
   */
  public static final String SQL_FIND_USERS_CARD = "SELECT card_id, card_number, card_score FROM payment_cards JOIN " +
          "users_payment_card USING (card_id) WHERE user_id = ?";
  /**
   * The constant SQL_ADD_COURSE_TO_STUDENT.
   */
  public static final String SQL_ADD_COURSE_TO_STUDENT = "INSERT INTO trainingbystudents (userid, trainingid) " +
          "VALUES (?, ?);";
  /**
   * The constant SQL_NEW_USER.
   */
  public final static String SQL_NEW_USER = "INSERT INTO users (name, surname, email, login, password)" +
          " values (?, ?, ?, ?, ?)";
  /**
   * The constant SQL_GET_USER.
   */
  public final static String SQL_GET_USER = "SELECT user_id, name, surname, email, user_status, type FROM users WHERE " +
          "login = ? AND password = ?";
  /**
   * The constant SQL_GRADE.
   */
  public static final String SQL_GRADE = "UPDATE trainingbystudents SET grade_for_training = ? WHERE " +
          "(user_id = ? and training_id = ?)";
  /**
   * The constant SQL_ADD_TRAINING_TO_STUDENT.
   */
  public static final String SQL_ADD_TRAINING_TO_STUDENT = "INSERT INTO training_by_students " +
          "(user_id, training_id) VALUES (?, ?)";
  /**
   * The constant SQL_CHECK_ENROLLED.
   */
  public static final String SQL_CHECK_ENROLLED = "SELECT EXISTS(SELECT trainings_center.training_by_students.user_id " +
          "FROM training_by_students WHERE (user_id = ? and training_id =?))";
  /**
   * The constant SQL_ALL_MENTORS.
   */
  public static final String SQL_ALL_MENTORS = "SELECT user_id, users.name as user_name, surname, t.name, training_id " +
          "FROM users left join trainings t on users.user_id = t.mentor_id WHERE users.type = 'mentor'";
  /**
   * The constant SQL_ALL_USERS.
   */
  public static final String SQL_ALL_USERS = "SELECT user_id, name, surname, login, email, user_status, type FROM users";
  /**
   * The constant SQL_UPDATE_USER_TYPE.
   */
  public static final String SQL_UPDATE_USER_TYPE = "UPDATE users SET type = ?, user_status = ? WHERE user_id = ?";
  /**
   * The constant SQL_USER_STATUS.
   */
  public static final String SQL_USER_STATUS = "user_status";
  /**
   * The constant SQL_STUDENT_FOR_TRAINING.
   */
  public static final String SQL_STUDENT_FOR_TRAINING = "SELECT * FROM users  JOIN training_by_students " +
          "USING(user_id) WHERE (training_id = ? AND type = 'student')";
  /**
   * The constant SQL_ALL_MARKS_FOR_TRAINING.
   */
  public static final String SQL_ALL_MARKS_FOR_TRAINING = "SELECT task_id, task_name, mark, answer, task FROM " +
          "training_by_students INNER JOIN tasks USING (training_id) LEFT JOIN student_task USING (user_id, task_id) " +
          "WHERE user_id = ? AND training_id = ?";
  /**
   * The constant SQL_OFFER_CONSULTATION.
   */
  public static final String SQL_OFFER_CONSULTATION = "INSERT  INTO  trainings_center.consultations " +
          "(training_id, date, price) SELECT * FROM (SELECT ?, ?, ?) AS tmp WHERE " +
          "NOT EXISTS(SELECT training_id, date FROM trainings_center.consultations WHERE training_id = ? AND date = ?)";
  /**
   * The constant SQL_CONSULTATION_OFFER.
   */
  public static final String SQL_CONSULTATION_OFFER = "SELECT training_id, date, name AS training_name FROM " +
          "consultations JOIN trainings USING (training_id) WHERE mentor_id = ? AND mentor_mark IS NULL";
  /**
   * The constant SQL_TRAINING_NAME_AS.
   */
  public static final String SQL_TRAINING_NAME_AS = "training_name";
  /**
   * The constant SQL_DATE.
   */
  public static final String SQL_DATE = "date";
  /**
   * The constant SQL_SEND_AGREEMENT.
   */
  public static final String SQL_SEND_AGREEMENT = "UPDATE consultations SET mentor_mark = ? WHERE" +
          " training_id = ? AND date = ?";
  /**
   * The constant SQL_SET_FINAL_GRADE.
   */
  public static final String SQL_SET_FINAL_GRADE = "UPDATE training_by_students SET grade_for_training = ? WHERE " +
          "user_id = ? AND training_id = ?";
  /**
   * The constant SQL_CHECK_LOGIN.
   */
  public static final String SQL_CHECK_LOGIN = "SELECT login FROM users WHERE login = ?";
  /**
   * The constant SQL_CHECK_EMAIL.
   */
  public static final String SQL_CHECK_EMAIL = "SELECT email FROM users WHERE email = ?";
  /**
   * The constant SQL_CLOSE_RECEPTION.
   */
  public static final String SQL_CLOSE_RECEPTION = "UPDATE trainings SET training_status = false WHERE training_id = ?";
  /**
   * The constant SQL_DELETE_USER.
   */
  public static final String SQL_DELETE_USER = "DELETE FROM users WHERE user_id = ?";
  /**
   * The constant SQL_DELETE_TRAINING.
   */
  public static final String SQL_DELETE_TRAINING = "DELETE FROM trainings WHERE training_id = ?";
  /**
   * The constant SQL_CHECK_USERS_ON_TRAINING.
   */
  public static final String SQL_CHECK_USERS_ON_TRAINING = "SELECT COUNT(user_id) FROM training_by_students WHERE" +
          " training_id = ? AND grade_for_training = 0";
  /**
   * The constant SQL_DELETE_TOPIC.
   */
  public static final String SQL_DELETE_TOPIC = "DELETE FROM topics_for_study WHERE topic_id = ?";
  /**
   * The constant SQL_DELETE_TASK.
   */
  public static final String SQL_DELETE_TASK = "DELETE FROM tasks WHERE task_id = ?";
  /**
   * The constant SQL_GIVE_FEEDBACK.
   */
  public static final String SQL_GIVE_FEEDBACK = "INSERT INTO reviews (review_text) VALUE (?)";
  /**
   * The constant SQL_ADD_PAYMENT_CARD.
   */
  public static final String SQL_ADD_PAYMENT_CARD = "INSERT INTO payment_cards  (card_number) SELECT (?) WHERE " +
          "NOT EXISTS(SELECT card_number FROM payment_cards WHERE card_number = ?)";
  /**
   * The constant SQL_ADD_PAYMENT_CARD_TO_USER.
   */
  public static final String SQL_ADD_PAYMENT_CARD_TO_USER = "INSERT INTO users_payment_card (user_id, card_id) " +
          "SELECT ?, card_id FROM payment_cards WHERE card_number = ?";
  /**
   * The constant SQL_FIND_REVIEWS.
   */
  public static final String SQL_FIND_REVIEWS = "SELECT review_text FROM reviews";
}
