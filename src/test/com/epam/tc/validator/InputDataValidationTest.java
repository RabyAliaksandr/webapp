package com.epam.tc.validator;

import org.junit.Assert;
import org.junit.Test;

/**
 * The type Input data validation test.
 */
public class InputDataValidationTest {

  private InputDataValidation inputDataValidation;

  /**
   * Strip xss test.
   */
  @Test
  public void stripXssTest() {
    inputDataValidation = new InputDataValidation();
    String xss = "I am<script>alert something</script>";
    String expected = "I am";
    String actual = inputDataValidation.stripXSS(xss);
    Assert.assertEquals(expected,actual);
  }
}
