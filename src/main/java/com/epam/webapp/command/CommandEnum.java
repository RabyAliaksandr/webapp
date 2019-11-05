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
  };
  Command command;

  public Command getCurrentCommand() {
    return command;
  }
}