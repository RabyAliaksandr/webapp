package com.epam.tc.command.impl;

import com.epam.tc.command.PageName;
import com.epam.tc.command.Command;
import com.epam.tc.command.VariableName;
import com.epam.tc.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Mentoring command.
 *
 * @author alex raby
 * @version 1.0 return to mentoring page with student id which will be controlled by a mentor
 */
public class MentoringCommand implements Command {

  /** {@inheritDoc} */
  @Override
  public String execute(HttpServletRequest request) {
    int studentId = Integer.parseInt(request.getParameter(VariableName.STUDENT_ID));
    request.getSession().setAttribute(VariableName.STUDENT_ID, studentId);
    return ConfigurationManager.getProperty(PageName.MENTORING_PAGE);
  }
}
