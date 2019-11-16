package com.epam.webapp;


import com.epam.webapp.connectionpool.ConnectionPoolException;
import com.epam.webapp.dao.DataListsDAO;
import com.epam.webapp.dao.impl.DataListDAOImpl;
import com.epam.webapp.dao.impl.UserDAOImpl;
import com.epam.webapp.entity.Task;
import com.epam.webapp.entity.Training;
import com.epam.webapp.entity.User;
import com.epam.webapp.entity.UserType;
import com.epam.webapp.service.TrainingsService;
import com.epam.webapp.service.UserService;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class deleteTHIS {

  public static void main(String[] args) throws ConnectionPoolException, ParseException {

    String[] ar  = new String[5];
    ar[0] = "1";
    ar[1] = "2";
    ar[2] = "3";
    ar[3] = "4";
    ar[4] = "5";
    String stringBuilder = new String();
    for (int i = 0; i < ar.length; i++) {
      stringBuilder = stringBuilder + ar[i] + ",";
    }
    System.out.println(stringBuilder);
    String[] aaa = stringBuilder.split(",");
    System.out.println(aaa.length);
    for (int i = 0; i < aaa.length; i++) {
      System.out.println(aaa[i]);
    }
  }


}
