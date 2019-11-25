package com.epam.tc;


import com.epam.tc.connectionpool.ConnectionPoolException;
import com.epam.tc.dao.DaoException;
import com.epam.tc.entity.UserType;
import com.epam.tc.filter.XssRequestWrapper;
import com.epam.tc.service.PaymentCardService;
import com.epam.tc.service.ServiceFactory;
import com.epam.tc.service.TrainingService;
import com.epam.tc.service.UserService;
import com.epam.tc.validator.InputDataValidation;
import com.google.protobuf.ServiceException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class deleteTHIS {

  public static void main(String[] args) throws ConnectionPoolException, ParseException, ServiceException, com.epam.tc.service.ServiceException, DaoException {

    InputDataValidation validation = new InputDataValidation();
    String a = "dasdasd dsadsa <script> dssfsdfsdf sfdf sd </script>";
    String values  = Arrays.toString(UserType.values());
    String e = "(0\\.((0[1-9]{1})|([1-9]{1}([0-9]{1})?)))|(([1-9]+[0-9]*)(\\.([0-9]{1,2}))?)";
    Pattern pattern = Pattern.compile(e);
    String te = "fdbffdbfeweqwffd";
    UserService userService = ServiceFactory.getUserService();
    PaymentCardService paymentCardService = ServiceFactory.getPaymentCardService();
    paymentCardService.paymentConsultation(1, 9, 3);
  }
}
