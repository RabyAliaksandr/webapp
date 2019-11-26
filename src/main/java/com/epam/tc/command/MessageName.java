package com.epam.tc.command;

import com.epam.tc.manager.MessageManager;

/**
 * The type Message name.
 *
 * @author alex raby
 * @version 1.0 contains message name constants that are assigned in the request to receive a message using the class MessageManager {@link MessageManager}
 */
public class MessageName {

  /**
   * The constant WRONG_ACTION.
   */
  public static final String WRONG_ACTION = "wrongAction";
  /**
   * The constant MESSAGE_WRONG_ACTION.
   */
  public static final String MESSAGE_WRONG_ACTION = "message.wrongAction";
  /**
   * The constant MESSAGE_ABOUT_CHANGES.
   */
  public static final String MESSAGE_ABOUT_CHANGES = "changesSavedMessage";
  /**
   * The constant MESSAGE_CHANGES_SAVED.
   */
  public static final String MESSAGE_CHANGES_SAVED = "message.changesSaved";
  /**
   * The constant MESSAGE_CHANGES_ERROR.
   */
  public static final String MESSAGE_CHANGES_ERROR = "message.changesError";
  /**
   * The constant ERROR_MESSAGE.
   */
  public static final String ERROR_MESSAGE = "message.loginError";
  /**
   * The constant NAME_ERROR_MESSAGE.
   */
  public static final String NAME_ERROR_MESSAGE = "errorLoginPassMessage";
  /**
   * The constant MESSAGE_ABOUT_DONE.
   */
  public static final String MESSAGE_ABOUT_DONE = "markDoneMessage";
  /**
   * The constant MESSAGE_MARK_DONE.
   */
  public static final String MESSAGE_MARK_DONE = "message.markDone";
  /**
   * The constant MESSAGE_MARK_ERROR.
   */
  public static final String MESSAGE_MARK_ERROR = "message.markError";
  /**
   * The constant MESSAGE_OFFER_SENT.
   */
  public static final String MESSAGE_OFFER_SENT = "messageOfferSent";
  /**
   * The constant MESSAGE_SENT.
   */
  public static final String MESSAGE_SENT = "message.offerSent";
  /**
   * The constant MESSAGE_SENT_ERROR.
   */
  public static final String MESSAGE_SENT_ERROR = "message.offerDidntSend";
  /**
   * The constant MESSAGE_SENT_ERROR_DATE.
   */
  public static final String MESSAGE_SENT_ERROR_DATE = "message.offerDidntSendDate";
  /**
   * The constant MESSAGE_AGREEMENT_SENT.
   */
  public static final String MESSAGE_AGREEMENT_SENT = "message.agreementSent";
  /**
   * The constant AGREEMENT_SENT.
   */
  public static final String AGREEMENT_SENT = "messageAgreement";
  /**
   * The constant MESSAGE_AGREEMENT_ERROR.
   */
  public static final String MESSAGE_AGREEMENT_ERROR = "message.agreementDidntSend";
  /**
   * The constant ORDER_SENT_MESSAGE.
   */
  public static final String ORDER_SENT_MESSAGE = "orderSentMessage";
  /**
   * The constant MESSAGE_ORDER_SENT.
   */
  public static final String MESSAGE_ORDER_SENT = "message.orderSent";
  /**
   * The constant MESSAGE_ORDER_SENT_ERROR.
   */
  public static final String MESSAGE_ORDER_SENT_ERROR = "message.orderDidntSend";
  /**
   * The constant SEND_SOLUTION_MESSAGE.
   */
  public static final String SEND_SOLUTION_MESSAGE = "sendSolutionMessage";
  /**
   * The constant MESSAGE_SEND_SOLUTION.
   */
  public static final String MESSAGE_SEND_SOLUTION = "message.sendSolution";
  /**
   * The constant MESSAGE_SEND_SOLUTION_ERROR.
   */
  public static final String MESSAGE_SEND_SOLUTION_ERROR = "message.sendSolutionError";
  /**
   * The constant MESSAGE_NULL_PAGE.
   */
  public static final String MESSAGE_NULL_PAGE = "page is null";
  /**
   * The constant MESSAGE_OPERATION_DONE.
   */
  public static final String MESSAGE_OPERATION_DONE = "message.operation";
  /**
   * The constant MESSAGE_OPERATION.
   */
  public static final String MESSAGE_OPERATION = "messageOperation";
  /**
   * The constant MESSAGE_OPERATION_DIDNT.
   */
  public static final String MESSAGE_OPERATION_DIDNT = "message.operationDidnt";
  /**
   * The constant FINAL_GRADE_MESSAGE.
   */
  public static final String FINAL_GRADE_MESSAGE = "finalGradeMessage";
  /**
   * The constant FINAL_GRADE_MESSAGE_DONE.
   */
  public static final String FINAL_GRADE_MESSAGE_DONE = "message.finalGrade";
  /**
   * The constant VALIDATION_NAME.
   */
  public static final String VALIDATION_NAME = "messageValidationName";
  /**
   * The constant VALIDATION_NAME_WRONG.
   */
  public static final String VALIDATION_NAME_WRONG = "message.validationNameWrong";
  /**
   * The constant VALIDATION_SURNAME.
   */
  public static final String VALIDATION_SURNAME = "messageValidationSurName";
  /**
   * The constant VALIDATION_SURNAME_WRONG.
   */
  public static final String VALIDATION_SURNAME_WRONG = "message.validationSurNameWrong";
  /**
   * The constant VALIDATION_LOGIN.
   */
  public static final String VALIDATION_LOGIN = "messageValidationLogin";
  /**
   * The constant VALIDATION_LOGIN_WRONG.
   */
  public static final String VALIDATION_LOGIN_WRONG = "message.validationLoginWrong";
  /**
   * The constant VALIDATION_EMAIL.
   */
  public static final String VALIDATION_EMAIL = "messageValidationEmail";
  /**
   * The constant VALIDATION_EMAIL_WRONG.
   */
  public static final String VALIDATION_EMAIL_WRONG = "message.validationEmailWrong";
  /**
   * The constant VALIDATION_PASSWORD.
   */
  public static final String VALIDATION_PASSWORD = "messageValidationPassword";
  /**
   * The constant VALIDATION_PASSWORD_WRONG.
   */
  public static final String VALIDATION_PASSWORD_WRONG = "message.validationPasswordWrong";
  /**
   * The constant VALIDATION_PASSWORD_REPEAT.
   */
  public static final String VALIDATION_PASSWORD_REPEAT = "messageRepeatPassword";
  /**
   * The constant VALIDATION_PASSWORD_REPEAT_WRONG.
   */
  public static final String VALIDATION_PASSWORD_REPEAT_WRONG = "message.repeatPassword";
  /**
   * The constant LOGIN_IS_EXIST.
   */
  public static final String LOGIN_IS_EXIST = "messageLoginIsExist";
  /**
   * The constant LOGIN_IS_EXIST_MESSAGE.
   */
  public static final String LOGIN_IS_EXIST_MESSAGE = "message.loginIsExist";
  /**
   * The constant EMAIL_IS_EXIST.
   */
  public static final String EMAIL_IS_EXIST = "messageEmailIsExist";
  /**
   * The constant EMAIL_IS_EXIST_MESSAGE.
   */
  public static final String EMAIL_IS_EXIST_MESSAGE = "message.emilIsExist";
  /**
   * The constant CLOSE_RECEPTION_MESSAGE.
   */
  public static final String CLOSE_RECEPTION_MESSAGE = "message.closeReception";
  /**
   * The constant CLOSE_RECEPTION.
   */
  public static final String CLOSE_RECEPTION = "messageCloseReception";
  /**
   * The constant DELETE_USER.
   */
  public static final String DELETE_USER = "messageDeleteUser";
  /**
   * The constant DELETE_USER_MESSAGE.
   */
  public static final String DELETE_USER_MESSAGE = "message.deleteUser";
  /**
   * The constant DELETE_TRAINING.
   */
  public static final String DELETE_TRAINING ="deleteTraining";
  /**
   * The constant DELETE_TRAINING_MESSAGE.
   */
  public static final String DELETE_TRAINING_MESSAGE = "message.deleteTraining";
  /**
   * The constant DELETE_TRAINING_MESSAGE_WRONG.
   */
  public static final String DELETE_TRAINING_MESSAGE_WRONG = "message.deleteTrainingWrong";
  /**
   * The constant FEEDBACK_MESSAGE_SAVE.
   */
  public static final String FEEDBACK_MESSAGE_SAVE = "message.feedbackGave";
  /**
   * The constant FEEDBACK_MESSAGE.
   */
  public static final String FEEDBACK_MESSAGE = "messageFeedback";
  /**
   * The constant MESSAGE_ADD_PAYMENT_CARD.
   */
  public static final String MESSAGE_ADD_PAYMENT_CARD = "messageAddCard";
  /**
   * The constant MESSAGE_ADD_PAYMENT_CARD_DONE.
   */
  public static final String MESSAGE_ADD_PAYMENT_CARD_DONE = "message.addCardDone";
  /**
   * The constant MESSAGE_ADD_PAYMENT_CARD_WRONG.
   */
  public static final String MESSAGE_ADD_PAYMENT_CARD_WRONG = "message.addCardWrong";
  /**
   * The constant MESSAGE_SENT_ERROR_MONEY.
   */
  public static final String MESSAGE_SENT_ERROR_MONEY = "message.errorMoney";
  /**
   * The constant MESSAGE_ADD_PAYMENT_CARD_INVALID_NUMBER.
   */
  public static final String MESSAGE_ADD_PAYMENT_CARD_INVALID_NUMBER = "message.invalidCardNumber";
  /**
   * The constant MESSAGE_INVALID_SUM.
   */
  public static final String MESSAGE_INVALID_SUM = "message.invalidSum";
  /**
   * The constant MESSAGE_TEXTAREA_SIZE.
   */
  public static final String MESSAGE_TEXTAREA_SIZE = "message.textAreaSize";
  /**
   * The constant MESSAGE_TEXTAREA_NAME_SIZE.
   */
  public static final String MESSAGE_TEXTAREA_NAME_SIZE = "message.textAreaNameSize";
  /**
   * The constant FINAL_GRADE_INVALID.
   */
  public static final String FINAL_GRADE_INVALID = "message.invalidGrade";
}
