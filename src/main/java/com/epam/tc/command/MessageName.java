package com.epam.tc.command;

import com.epam.tc.manager.MessageManager;

/**
 * contains message name constants that are assigned in the request to receive
 * a message using the class MessageManager {@link MessageManager}
 *
 * @author alex raby
 * @version 1.0
 */
public class MessageName {

  private MessageName() {

  }

  public static final String WRONG_ACTION = "wrongAction";

  public static final String MESSAGE_WRONG_ACTION = "message.wrongAction";

  public static final String MESSAGE_ABOUT_CHANGES = "changesSavedMessage";

  public static final String MESSAGE_CHANGES_SAVED = "message.changesSaved";

  public static final String MESSAGE_CHANGES_ERROR = "message.changesError";

  public static final String ERROR_MESSAGE = "message.loginError";

  public static final String NAME_ERROR_MESSAGE = "errorLoginPassMessage";

  public static final String MESSAGE_ABOUT_DONE = "markDoneMessage";

  public static final String MESSAGE_MARK_DONE = "message.markDone";

  public static final String MESSAGE_MARK_ERROR = "message.markError";

  public static final String MESSAGE_OFFER_SENT = "messageOfferSent";

  public static final String MESSAGE_SENT = "message.offerSent";

  public static final String MESSAGE_SENT_ERROR = "message.offerDidntSend";

  public static final String MESSAGE_SENT_ERROR_DATE = "message.offerDidntSendDate";

  public static final String MESSAGE_AGREEMENT_SENT = "message.agreementSent";

  public static final String AGREEMENT_SENT = "messageAgreement";

  public static final String MESSAGE_AGREEMENT_ERROR = "message.agreementDidntSend";

  public static final String ORDER_SENT_MESSAGE = "orderSentMessage";

  public static final String MESSAGE_ORDER_SENT = "message.orderSent";

  public static final String MESSAGE_ORDER_SENT_ERROR = "message.orderDidntSend";

  public static final String SEND_SOLUTION_MESSAGE = "sendSolutionMessage";

  public static final String MESSAGE_SEND_SOLUTION = "message.sendSolution";

  public static final String MESSAGE_SEND_SOLUTION_ERROR = "message.sendSolutionError";

  public static final String MESSAGE_NULL_PAGE = "page is null";

  public static final String MESSAGE_OPERATION_DONE = "message.operation";

  public static final String MESSAGE_OPERATION = "messageOperation";

  public static final String MESSAGE_OPERATION_DIDNT = "message.operationDidnt";

  public static final String FINAL_GRADE_MESSAGE = "finalGradeMessage";

  public static final String FINAL_GRADE_MESSAGE_DONE = "message.finalGrade";

  public static final String VALIDATION_NAME = "messageValidationName";

  public static final String VALIDATION_NAME_WRONG = "message.validationNameWrong";

  public static final String VALIDATION_SURNAME = "messageValidationSurName";

  public static final String VALIDATION_SURNAME_WRONG = "message.validationSurNameWrong";

  public static final String VALIDATION_LOGIN = "messageValidationLogin";

  public static final String VALIDATION_LOGIN_WRONG = "message.validationLoginWrong";

  public static final String VALIDATION_EMAIL = "messageValidationEmail";

  public static final String VALIDATION_EMAIL_WRONG = "message.validationEmailWrong";

  public static final String VALIDATION_PASSWORD = "messageValidationPassword";

  public static final String VALIDATION_PASSWORD_WRONG = "message.validationPasswordWrong";

  public static final String VALIDATION_PASSWORD_REPEAT = "messageRepeatPassword";

  public static final String VALIDATION_PASSWORD_REPEAT_WRONG = "message.repeatPassword";

  public static final String LOGIN_IS_EXIST = "messageLoginIsExist";

  public static final String LOGIN_IS_EXIST_MESSAGE = "message.loginIsExist";

  public static final String EMAIL_IS_EXIST = "messageEmailIsExist";

  public static final String EMAIL_IS_EXIST_MESSAGE = "message.emilIsExist";

  public static final String CLOSE_RECEPTION_MESSAGE = "message.closeReception";

  public static final String CLOSE_RECEPTION = "messageCloseReception";

  public static final String DELETE_USER = "messageDeleteUser";

  public static final String DELETE_USER_MESSAGE = "message.deleteUser";

  public static final String DELETE_TRAINING ="deleteTraining";

  public static final String DELETE_TRAINING_MESSAGE = "message.deleteTraining";

  public static final String DELETE_TRAINING_MESSAGE_WRONG = "message.deleteTrainingWrong";

  public static final String FEEDBACK_MESSAGE_SAVE = "message.feedbackGave";

  public static final String FEEDBACK_MESSAGE = "messageFeedback";

  public static final String MESSAGE_ADD_PAYMENT_CARD = "messageAddCard";

  public static final String MESSAGE_ADD_PAYMENT_CARD_DONE = "message.addCardDone";

  public static final String MESSAGE_ADD_PAYMENT_CARD_WRONG = "message.addCardWrong";

  public static final String MESSAGE_SENT_ERROR_MONEY = "message.errorMoney";

  public static final String MESSAGE_ADD_PAYMENT_CARD_INVALID_NUMBER = "message.invalidCardNumber";

  public static final String MESSAGE_INVALID_SUM = "message.invalidSum";

  public static final String MESSAGE_TEXTAREA_SIZE = "message.textAreaSize";

  public static final String MESSAGE_TEXTAREA_NAME_SIZE = "message.textAreaNameSize";

  public static final String FINAL_GRADE_INVALID = "message.invalidGrade";
}
