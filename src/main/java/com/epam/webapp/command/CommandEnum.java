package com.epam.webapp.command;


import com.epam.webapp.command.impl.*;

public enum CommandEnum {
  LOGIN {
    {
      this.command = new AuthorizationCommand();
    }
  },
  LOGOUT {
    {
      this.command = new LogOutCommand();
    }
  },
  REGISTRATION {
    {
      this.command = new RegistrationUserCommand();
    }
  },
  TOREGISTRATION{
    {
      this.command = new ToRegistrationCommand();
    }
  },
  TRAININGS {
    {
      this.command = new TrainingsCommand();
    }
  },
  TOMAIN {
    {
      this.command = new ToMainCommand();
    }
  },
  TRAININGPAGEFORMENTOR { //TODO _______!!!!!!!!!!!!!
    {
      this.command = new TrainingPageForMentor();
    }
  };
  Command command;
  public Command getCurrentCommand() {
    return command;
  }
}