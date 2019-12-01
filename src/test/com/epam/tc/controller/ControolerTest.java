package com.epam.tc.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.io.*;
import javax.servlet.http.*;
import org.junit.Test;
import org.mockito.Mockito;


/**
 * The type Controoler test.
 */
public class ControolerTest {

  /**
   * Test servlet.
   *
   * @throws Exception the exception
   */
  @Test
  public void testServlet() throws Exception {
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

    when(request.getParameter("user")).thenReturn("me");

    new Controller().doPost(request, response);

//    verify(request, atLeast(1)).getParameter("username"); // only if you want to verify username was called...
//    writer.flush(); // it may not have been flushed yet...
//    assertTrue(stringWriter.toString().contains("My expected string"));
  }
}
