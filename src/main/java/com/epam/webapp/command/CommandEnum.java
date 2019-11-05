package com.epam.webapp.command;


import com.epam.webapp.command.impl.*;

public enum CommandEnum {
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
  REGISTRATION_PAGE{
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
  TRAININGS_INFORMATION_PAGE { //TODO _______!!!!!!!!!!!!!
    {
      this.command = new TrainingsInformationPageCommand();
    }
  },
  LOG_IN_PAGE {
    {
      this.command = new LogInPageCommand();
    }
  };
  Command command;
  public Command getCurrentCommand() {
    return command;
  }
}