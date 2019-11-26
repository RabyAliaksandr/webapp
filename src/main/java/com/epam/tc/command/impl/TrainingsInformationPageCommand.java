package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.PageName;
import com.epam.tc.command.VariableName;
import com.epam.tc.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Trainings information page command.
 *
 * @author alex raby
 * @version 1.0 return to training information page
 */
public class TrainingsInformationPageCommand implements Command {

  /** {@inheritDoc} */
  @Override
  public String execute(HttpServletRequest request) {
    String trainingId = request.getParameter(VariableName.TRAINING_ID);
    String editor = request.getParameter(VariableName.EDITOR);
    request.getSession().setAttribute(VariableName.TRAINING_ID, trainingId);
    request.getSession().setAttribute(VariableName.EDITOR, editor);
    return ConfigurationManager.getProperty(PageName.TRAININGS_INFORMATION_PAGE);
  }
}
