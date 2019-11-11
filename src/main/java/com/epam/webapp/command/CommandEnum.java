package com.epam.webapp.command;


import com.epam.webapp.command.impl.*;

public enum CommandEnum {
  ADD_TRAINING_TO_STUDENT {
    {
      this.command = new AddTrainingToStudentCommand();
    }
  },
  LOG_IN {
    {
      this.command = new AuthorizationCommand();
    }
  },
  LOG_OUT {
    {
      this.command = new LogOutCommand();
    }
  },
  REGISTRATION {
    {
      this.command = new RegistrationUserCommand();
    }
  },
  REGISTRATION_PAGE {
    {
      this.command = new RegistrationPageCommand();
    }
  },
  TRAININGS_PAGE {
    {
      this.command = new TrainingsPageCommand();
    }
  },
  MAIN_PAGE {
    {
      this.command = new MainPageCommand();
    }
  },
  TRAININGS_INFORMATION_PAGE {
    {
      this.command = new TrainingsInformationPageCommand();
    }
  },
  LOG_IN_PAGE {
    {
      this.command = new LogInPageCommand();
    }
  },
  GRADE {
    {
      this.command = new GradeCommand();
    }
  },
  CABINET {
    {
      this.command = new CabinetPageCommand();
    }
  },
  TOPIC_PAGE {
    {
      this.command = new TopicPageCommand();
    }
  },
  GET_TEXT {
    {
      this.command = new GetTextTopicCommand();
    }
  },
  CREATE_TEXT {
    {
      this.command = new CreateTextCommand();
    }
  },
  UPDATE_INFORMATION_ABOUT_TRAINING {
    {
      this.command = new UpdateInformationAboutTrainingCommand();
    }
  },
  ADD_TOPIC_FOR_TRAINING {
    {
      this.command = new AddTopicForTrainingCommand();
    }
  },
  ADD_TASK_FOR_TRAINING {
    {
      this.command = new AddTaskForTraining();
    }
  },
  MANAGEMENT_PAGE {
    {
      this.command = new ManagementPageCommand();
    }
  },
  CREATE_TRAINING {
    {
      this.command = new CreateTrainingCommand();
    }
  },
  UPDATE_TRAININGS_TOPIC {
    {
      this.command = new UpdateTrainingsTopicCommand();
    }
  },
  UPDATE_USER_TYPE {
    {
      this.command = new UpdateUserTypeCommand();
    }
  },
  MARK_TOPIC {
    {
      this.command = new MarkTopicCommand();
    }
  },
  TASK_PAGE {
    {
      this.command = new TaskPageCommand();
    }
  },
  UPDATE_TRAININGS_TASK {
    {
      this.command = new UpdateTrainingsTaskCommand();
    }
  },
  SEND_SOLUTION {
    {
      this.command = new SendSolutionCommand();
    }
  },
  STUDENT_MANAGEMENT {
    {
      this.command = new StudentManagementCommand();
    }
  };
  Command command;


  public Command getCurrentCommand() {
    return command;
  }
}