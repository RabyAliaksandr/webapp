package com.epam.tc.dao;


/**
 * contains database query constants
 *
 * @author alex raby
 * @version 1.0
 */
public class SqlQuery {

  private SqlQuery() {

  }

  /**
   * find final grade for training
   *
   * @see com.epam.tc.entity.Training
   */
  public static final String SQL_FIND_FINAL_GRADE = "SELECT grade_for_training FROM training_by_students WHERE " +
          "user_id = ? AND training_id = ?";
  /**
   * find Topic by Topic id
   *
   * @see com.epam.tc.entity.Topic
   */
  public static final String SQL_TOPIC_BY_TOPIC_ID = "SELECT * FROM topics_for_study WHERE (topic_id = ?)";
  /**
   * updating trainings information
   *
   * @see com.epam.tc.entity.Training
   */
  public static final String SQL_UPDATE_TRAININGS_INFORMATION = "UPDATE trainings SET information = ?, name = ? " +
          "WHERE training_id = ?";
  /**
   * adding Topic for training
   *
   * @see com.epam.tc.entity.Training
   * @see com.epam.tc.entity.Topic
   */
  public static final String SQL_ADD_TOPIC_FOR_TRAINING = "INSERT INTO topics_for_study " +
          "(training_id, name_topic, topic) VALUES (?, ?, ?)";
  /**
   * adding Task for Training
   *
   * @see com.epam.tc.entity.Training
   * @see com.epam.tc.entity.Task
   */
  public static final String SQL_ADD_TASK_FOR_TRAINING = "INSERT INTO tasks " +
          "(training_id, task_name, task) VALUES (?, ?, ?)";
  /**
   * getting all information about Task
   *
   * @see com.epam.tc.entity.Task
   */
  public static final String SQL_TASKS_FOR_TRAINING = "SELECT * FROM tasks WHERE training_id = ?";
  /**
   * getting all information about Trainings
   *
   * @see com.epam.tc.entity.Training
   */
  public final static String SQL_ALL_TRAININGS = "SELECT * FROM trainings";
  /**
   * finding Training for Student
   *
   * @see com.epam.tc.entity.UserType#STUDENT
   * @see com.epam.tc.entity.User
   * @see com.epam.tc.entity.Training
   */
  public final static String SQL_TRAININGS_BY_STUDENT_ID = "SELECT * FROM trainings JOIN training_by_students" +
          " USING (training_id) where user_id = ?";
  /**
   * finding Topics for Training
   *
   * @see com.epam.tc.entity.Training
   * @see com.epam.tc.entity.Topic
   */
  public static final String SQL_TOPICS_FOR_TRAINING = "SELECT name_topic, topic, topic_id FROM topics_for_study" +
          " WHERE training_id = ?";
  /**
   * finding current Training for Mentor
   *
   * @see com.epam.tc.entity.UserType#MENTOR
   * @see com.epam.tc.entity.User
   * @see com.epam.tc.entity.Training
   */
  public static final String SQL_CURRENT_TRAININGS_FOR_MENTOR = "SELECT * FROM trainings WHERE mentor_id = ?";
  /**
   * finding Training by Training id
   *
   * @see com.epam.tc.entity.Training
   */
  public static final String SQL_ALL_TRAININGS_BY_TRAINING_ID = "SELECT * FROM trainings WHERE training_id = ?";
  /**
   * finding Students for Training
   *
   * @see com.epam.tc.entity.UserType#STUDENT
   * @see com.epam.tc.entity.User
   * @see com.epam.tc.entity.Training
   */
  public final static String SQL_STUDENTS_BY_ID_TRAINING = "SELECT user_id, name, surname, grade_for_training FROM " +
          "users join training_by_students USING (user_id) WHERE (training_id =? and grade_for_training = 0)";
  /**
   * finding completed Training for Student
   *
   * @see com.epam.tc.entity.UserType#STUDENT
   * @see com.epam.tc.entity.User
   * @see com.epam.tc.entity.Training
   */
  public static final String SQL_COMPLETED_TRAININGS_FOR_STUDENTS = "SELECT * FROM trainings  " +
          "join training_by_students using (training_id) where (training_by_students.user_id = ? and " +
          "training_by_students.grade_for_training = 0)";
  /**
   * create new Training
   * @see com.epam.tc.entity.Training
   */
  public static final String SQL_CREATE_TRAINING = "INSERT  INTO trainings (name, mentor_id, information)" +
          " VALUES (?, ?, ?)";
  /**
   * update Topic for Training
   * @see com.epam.tc.entity.Topic
   * @see com.epam.tc.entity.Training
   */
  public static final String SQL_UPDATE_TRAININGS_TOPIC = "UPDATE topics_for_study SET name_topic = ?, topic = ? " +
          "WHERE (topic_id = ?)";
  /**
   * check status for Topic {@link com.epam.tc.entity.Topic}
   */
  public static final String SQL_CHECK_STATUS_TOPIC = "SELECT topic_status FROM student_topic" +
          " WHERE (user_id = ? AND  topic_id = ?)";
  /**
   * set mark for Topic
   */
  public static final String SQL_SET_MARK_TOPIC = "INSERT INTO student_topic (user_id, topic_id, topic_status) " +
          "VALUES (?, ?, true)";
  /**
   * check Trainings status for student
   */
  public static final String SQL_CHECK_TRAINING_STATUS_FOR_STUDENT = "SELECT COUNT(1) FROM training_by_students " +
          "WHERE (user_id = ? AND training_id = ?);";
  /**
   * find Task by id
   */
  public static final String SQL_TASK_BY_ID = "SELECT * FROM trainings_center.tasks WHERE task_id = ?";
  /**
   * update Task
   */
  public static final String SQL_UPDATE_TRAININGS_TASK = "UPDATE trainings_center.tasks" +
          " SET task_name = ?, task = ? WHERE task_id = ?";
  /**
   * check status for Task
   */
  public static final String SQL_CHECK_STATUS_TASK = "SELECT mark FROM student_task " +
          "WHERE (user_id = ?  AND task_id = ?)";
  /**
   * send solution for Task
   */
  public static final String SQL_SEND_SOLUTION = "UPDATE student_task SET answer = ? WHERE user_id= ? AND task_id= ?";
  /**
   * find solution for Task
   */
  public static final String SQl_TASK_SOLUTION = "SELECT answer, mark FROM student_task WHERE user_id = ? AND task_id = ?";
  /**
   * set mark for Task
   */
  public static final String SQL_GRADE_TASK = "UPDATE student_task SET mark = ? WHERE user_id = ? AND task_id = ?";
  /**
   * find mark for task for student
   */
  public static final String SQL_MARK_FOR_TASK = "SELECT mark FROM student_task WHERE user_id = ? AND task_id = ?";

  /**
   * average rating for tasks for student
   */
  public static final String SQL_AVG_FOR_TASKS = "SELECT AVG(mark) FROM student_task INNER JOIN tasks USING (task_id) " +
          "LEFT JOIN trainings USING (training_id) WHERE user_id = ? AND training_id = ? AND mark > 0";
  /**
   * query AVG
   */
  public static final String SQL_AVG_MARK = "AVG(mark)";
  /**
   * completed Task for Student
   */
  public static final String SQL_COMPLETED_TASKS_FOR_STUDENT = "SELECT task_id, task_name FROM student_task JOIN" +
          " tasks USING (task_id) WHERE user_id = ? AND training_id = ?";
  /**
   * completed Topic for Student
   */
  public static final String SQL_LEARNED_TOPICS = "SELECT topic_id, name_topic FROM student_topic JOIN topics_for_study " +
          "USING (topic_id) WHERE user_id = ? AND training_id = ?";
  /**
   * consultations for Training
   */
  public static final String SQL_CONSULTATIONS_FOR_TRAINING = "SELECT consultation_id, date, price FROM consultations " +
          "LEFT JOIN trainings USING (training_id) WHERE training_id = ? AND date > CURRENT_DATE";

  /**
   * Tasks for Consultation
   */
  public static final String SQL_TASKS_FOR_CONSULTATION = "INSERT INTO consultations_task " +
          "(consultation_id, user_id, task_id) VALUES (?, ?, ?)";
  /**
   * Topics for consultation
   */
  public static final String SQL_TOPICS_FOR_CONSULTATION = "INSERT INTO consultations_topics" +
          " (consultation_id, user_id, topic_id) VALUES (?, ?, ?)";
  /**
   * replenish card
   */
  public static final String SQL_REPLENISH_CARD = "UPDATE payment_cards JOIN users_payment_card USING (card_id)" +
          " SET card_score = card_score +  ? WHERE card_id = ?";
  /**
   * transfer money from card to card
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
   * removal money from card
   */
  public static final String SQL_REMOVAL_MONEY = "update payment_cards," +
          " trainings_center_score set card_score = card_score -(SELECT price FROM consultations " +
          "WHERE consultations.consultation_id = ?) WHERE payment_cards.card_id = ? AND card_score > " +
          "(SELECT price FROM consultations WHERE consultations.consultation_id = ?)";
  /**
   * put money to card
   */
  public static final String SQL_PUT_MONEY = "INSERT INTO trainings_center_score (consultation_id, sum, user_id, " +
          "card_id, payment_date) VALUES " +
          "(?, (SELECT price FROM consultations WHERE consultations.consultation_id = ?), ?, ?, current_date)";
  /**
   * find user card
   */
  public static final String SQL_FIND_USERS_CARD = "SELECT card_id, card_number, card_score FROM payment_cards JOIN " +
          "users_payment_card USING (card_id) WHERE user_id = ?";
  /**
   * add training to student
   */
  public static final String SQL_ADD_COURSE_TO_STUDENT = "INSERT INTO training_by_students (user_id, training_id) " +
          "VALUES (?, ?);";
  /**
   * create new User
   */
  public final static String SQL_NEW_USER = "INSERT INTO users (name, surname, email, login, password)" +
          " values (?, ?, ?, ?, ?)";
  /**
   * get user by login and password
   */
  public final static String SQL_GET_USER = "SELECT user_id, name, surname, email, user_status, type FROM users WHERE " +
          "login = ? AND password = ?";
  /**
   * set final mark for training
   */
  public static final String SQL_GRADE = "UPDATE training_by_students SET grade_for_training = ? WHERE " +
          "(user_id = ? and training_id = ?)";
  /**
   * add training to student
   */
  public static final String SQL_ADD_TRAINING_TO_STUDENT = "INSERT INTO training_by_students " +
          "(user_id, training_id) VALUES (?, ?)";
  /**
   * check enrolled for student for this training
   */
  public static final String SQL_CHECK_ENROLLED = "SELECT EXISTS(SELECT trainings_center.training_by_students.user_id " +
          "FROM training_by_students WHERE (user_id = ? and training_id =?))";
  /**
   * finding all mentors
   */
  public static final String SQL_ALL_MENTORS = "SELECT user_id, users.name as user_name, surname, t.name, training_id " +
          "FROM users left join trainings t on users.user_id = t.mentor_id WHERE users.type = 'mentor'";
  /**
   * finding all users
   */
  public static final String SQL_ALL_USERS = "SELECT user_id, name, surname, login, email, user_status, type FROM users";
  /**
   * update user status
   */
  public static final String SQL_UPDATE_USER_TYPE = "UPDATE users SET type = ?, user_status = ? WHERE user_id = ?";
  /**
   * column user status
   */
  public static final String SQL_USER_STATUS = "user_status";
  /**
   * finding student for this training
   */
  public static final String SQL_STUDENT_FOR_TRAINING = "SELECT * FROM users  JOIN training_by_students " +
          "USING(user_id) WHERE (training_id = ? AND type = 'student')";
  /**
   * finding all marks for tasks for training for this user
   */
  public static final String SQL_ALL_MARKS_FOR_TRAINING = "SELECT task_id, task_name, mark, answer, task FROM " +
          "training_by_students INNER JOIN tasks USING (training_id) LEFT JOIN student_task USING (user_id, task_id) " +
          "WHERE user_id = ? AND training_id = ?";
  /**
   * create new Consultation
   */
  public static final String SQL_NEW_CONSULTATION = "INSERT  INTO  trainings_center.consultations " +
          "(training_id, date, price) SELECT * FROM (SELECT ?, ?, ?) AS tmp WHERE " +
          "NOT EXISTS(SELECT training_id, date FROM trainings_center.consultations WHERE training_id = ? AND date = ?)";
  /**
   * find all consultation for this mentor
   */
  public static final String SQL_CONSULTATION_OFFER = "SELECT training_id, date, name AS training_name FROM " +
          "consultations JOIN trainings USING (training_id) WHERE mentor_id = ? AND mentor_mark IS NULL";
  /**
   * send agreement at consultation by mentor
   */
  public static final String SQL_SEND_AGREEMENT = "UPDATE consultations SET mentor_mark = ? WHERE" +
          " training_id = ? AND date = ?";
  /**
   * set final mark for training
   */
  public static final String SQL_SET_FINAL_GRADE = "UPDATE training_by_students SET grade_for_training = ? WHERE " +
          "user_id = ? AND training_id = ?";
  /**
   * find login by login
   */
  public static final String SQL_CHECK_LOGIN = "SELECT login FROM users WHERE login = ?";
  /**
   * find email by email
   */
  public static final String SQL_CHECK_EMAIL = "SELECT email FROM users WHERE email = ?";
  /**
   * closing reception for training
   */
  public static final String SQL_CLOSE_RECEPTION = "UPDATE trainings SET training_status = false WHERE training_id = ?";
  /**
   * deleting this user by id
   */
  public static final String SQL_DELETE_USER = "DELETE FROM users WHERE user_id = ?";
  /**
   * deleting training by id
   */
  public static final String SQL_DELETE_TRAINING = "DELETE FROM trainings WHERE training_id = ?";
  /**
   * check training status for student
   */
  public static final String SQL_CHECK_USERS_ON_TRAINING = "SELECT COUNT(user_id) FROM training_by_students WHERE" +
          " training_id = ? AND grade_for_training = 0";
  /**
   * deleting training
   */
  public static final String SQL_DELETE_TOPIC = "DELETE FROM topics_for_study WHERE topic_id = ?";
  /**
   * deleting task
   */
  public static final String SQL_DELETE_TASK = "DELETE FROM tasks WHERE task_id = ?";
  /**
   * writing reviews
   */
  public static final String SQL_GIVE_FEEDBACK = "INSERT INTO reviews (review_text) VALUE (?)";
  /**
   * adding PaymentCard
   */
  public static final String SQL_ADD_PAYMENT_CARD = "INSERT INTO payment_cards  (card_number) SELECT (?) WHERE " +
          "NOT EXISTS(SELECT card_number FROM payment_cards WHERE card_number = ?)";
  /**
   * adding PaymentCard to user
   */
  public static final String SQL_ADD_PAYMENT_CARD_TO_USER = "INSERT INTO users_payment_card (user_id, card_id) " +
          "SELECT ?, card_id FROM payment_cards WHERE card_number = ?";
  /**
   * find reviews
   */
  public static final String SQL_FIND_REVIEWS = "SELECT review_text FROM reviews";
  /**
   * find all payments
   */
  public static final String FIND_ALL_PAYMENTS = "SELECT name, surname,payment_date, sum AS price, card_number " +
          " FROM trainings_center_score LEFT JOIN users USING (user_id) LEFT JOIN payment_cards USING (card_id)";
  /**
   * find all mentors
   */
  public static final String FIND_ALL_MENTORS = "SELECT user_id, name, surname FROM users WHERE type = 'mentor'";
}
