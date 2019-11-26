package com.epam.tc.command.impl;

import com.epam.tc.command.Command;
import com.epam.tc.command.PageName;
import com.epam.tc.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Student management command.
 *
 * @author alex raby
 * @version 1.0 return to student management page
 */
public class StudentManagementCommand implements Command {

  /** {@inheritDoc} */
  @Override
  public String execute(HttpServletRequest request) {
    return ConfigurationManager.getProperty(PageName.STUDENT_MANAGEMENT_PAGE);
  }
}
