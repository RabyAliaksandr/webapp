package com.epam.tc.command;

import com.epam.tc.command.impl.*;

/**
 * The enum Command enum.
 *
 * @author alex raby
 * @version 1.0 returns, depending on the name, the class of the implementing Command
 */
public enum CommandEnum {

  /**
   * The Add training to student.
   */
  ADD_TRAINING_TO_STUDENT {
    {
      this.command = new AddTrainingToStudentCommand();
    }
  },
  /**
   * The Log in.
   */
  LOG_IN {
    {
      this.command = new AuthorizationCommand();
    }
  },
  /**
   * The Log out.
   */
  LOG_OUT {
    {
      this.command = new LogOutCommand();
    }
  },
  /**
   * The Registration.
   */
  REGISTRATION {
    {
      this.command = new RegistrationUserCommand();
    }
  },
  /**
   * The Registration page.
   */
  REGISTRATION_PAGE {
    {
      this.command = new RegistrationPageCommand();
    }
  },
  /**
   * The Trainings page.
   */
  TRAININGS_PAGE {
    {
      this.command = new TrainingsPageCommand();
    }
  },
  /**
   * The Main page.
   */
  MAIN_PAGE {
    {
      this.command = new MainPageCommand();
    }
  },
  /**
   * The Trainings information page.
   */
  TRAININGS_INFORMATION_PAGE {
    {
      this.command = new TrainingsInformationPageCommand();
    }
  },
  /**
   * The Log in page.
   */
  LOG_IN_PAGE {
    {
      this.command = new LogInPageCommand();
    }
  },
  /**
   * The Cabinet.
   */
  CABINET {
    {
      this.command = new CabinetPageCommand();
    }
  },
  /**
   * The Topic page.
   */
  TOPIC_PAGE {
    {
      this.command = new TopicPageCommand();
    }
  },
  /**
   * The Create text.
   */
  CREATE_TEXT {
    {
      this.command = new CreateTextCommand();
    }
  },
  /**
   * The Update information about training.
   */
  UPDATE_INFORMATION_ABOUT_TRAINING {
    {
      this.command = new UpdateInformationAboutTrainingCommand();
    }
  },
  /**
   * The Add topic for training.
   */
  ADD_TOPIC_FOR_TRAINING {
    {
      this.command = new AddTopicForTrainingCommand();
    }
  },
  /**
   * The Add task for training.
   */
  ADD_TASK_FOR_TRAINING {
    {
      this.command = new AddTaskForTrainingCommand();
    }
  },
  /**
   * The Management page.
   */
  MANAGEMENT_PAGE {
    {
      this.command = new ManagementPageCommand();
    }
  },
  /**
   * The Create training.
   */
  CREATE_TRAINING {
    {
      this.command = new CreateTrainingCommand();
    }
  },
  /**
   * The Update trainings topic.
   */
  UPDATE_TRAININGS_TOPIC {
    {
      this.command = new UpdateTrainingsTopicCommand();
    }
  },
  /**
   * The Update user type.
   */
  UPDATE_USER_TYPE {
    {
      this.command = new UpdateUserTypeCommand();
    }
  },
  /**
   * The Mark topic.
   */
  MARK_TOPIC {
    {
      this.command = new MarkTopicCommand();
    }
  },
  /**
   * The Task page.
   */
  TASK_PAGE {
    {
      this.command = new TaskPageCommand();
    }
  },
  /**
   * The Update trainings task.
   */
  UPDATE_TRAININGS_TASK {
    {
      this.command = new UpdateTrainingsTaskCommand();
    }
  },
  /**
   * The Send solution.
   */
  SEND_SOLUTION {
    {
      this.command = new SendSolutionCommand();
    }
  },
  /**
   * The Student management.
   */
  STUDENT_MANAGEMENT {
    {
      this.command = new StudentManagementCommand();
    }
  },
  /**
   * The Mentoring.
   */
  MENTORING {
    {
      this.command = new MentoringCommand();
    }
  },
  /**
   * The Set mark for task.
   */
  SET_MARK_FOR_TASK {
    {
      this.command = new SetMarkCommand();
    }
  },
  /**
   * The Offer date.
   */
  OFFER_DATE {
    {
      this.command = new OfferDateCommand();
    }
  },
  /**
   * The Consultation for mentor.
   */
  CONSULTATION_FOR_MENTOR {
    {
      this.command = new ConsultationForMentorCommand();
    }
  },
  /**
   * The Send agreement.
   */
  SEND_AGREEMENT {
    {
      this.command = new SendAgreementCommand();
    }
  },
  /**
   * The Order consultation.
   */
  ORDER_CONSULTATION {
    {
      this.command = new OrderConsultationCommand();
    }
  },
  /**
   * The Send order consultation.
   */
  SEND_ORDER_CONSULTATION {
    {
      this.command = new SendOrderConsultationCommand();
    }
  },
  /**
   * The Set local.
   */
  SET_LOCAL {
    {
      this.command = new SetLocalCommand();
    }
  },
  /**
   * The Card management.
   */
  CARD_MANAGEMENT {
    {
      this.command = new CardManagementCommand();
    }
  },
  /**
   * The Replenish card.
   */
  REPLENISH_CARD {
    {
      this.command = new ReplenishCardCommand();
    }
  },
  /**
   * The Transfer money.
   */
  TRANSFER_MONEY {
    {
      this.command = new TransferMoneyCommand();
    }
  },
  /**
   * The Payment consultation.
   */
  PAYMENT_CONSULTATION {
    {
      this.command = new PaymentConsultationCommand();
    }
  },
  /**
   * The Set final grade.
   */
  SET_FINAL_GRADE {
    {
      this.command = new SetFinalGradeCommand();
    }
  },
  /**
   * The Close reception.
   */
  CLOSE_RECEPTION {
    {
      this.command = new CloseReceptionCommand();
    }
  },
  /**
   * The Delete user.
   */
  DELETE_USER {
    {
      this.command = new DeleteUserCommand();
    }
  },
  /**
   * The Delete training.
   */
  DELETE_TRAINING {
    {
      this.command = new DeleteTrainingCommand();
    }
  },
  /**
   * The Delete topic.
   */
  DELETE_TOPIC {
    {
      this.command = new DeleteTopicCommand();
    }
  },
  /**
   * The Delete task.
   */
  DELETE_TASK {
    {
      this.command = new DeleteTaskCommand();
    }
  },
  /**
   * The Give feedback.
   */
  GIVE_FEEDBACK {
    {
      this.command = new GiveFeedBackCommand();
    }
  },
  /**
   * The Set local cabinet.
   */
  SET_LOCAL_CABINET {
    {
      this.command = new SetLocalCabinetCommand();
    }
  },
  /**
   * The Add payment card.
   */
  ADD_PAYMENT_CARD {
    {
      this.command = new AddPaymentCardCommand();
    }
  },
  /**
   * The Reviews.
   */
  REVIEWS {
    {
      this.command = new ReviewsCommand();
    }
  },

  /**
   * The Center score.
   */
  CENTER_SCORE {
    {
      this.command = new CenterScoreCommand();
    }
  };

  /**
   * enum field Command {@link Command}
   */
  Command command;

  /**
   * returns Command the appropriate  name
   *
   * @return Command {@link Command}
   */
  public Command getCurrentCommand() {
    return command;
  }
}