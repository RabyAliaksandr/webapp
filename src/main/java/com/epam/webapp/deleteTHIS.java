package com.epam.webapp;


import com.epam.webapp.connectionpool.exception.ConnectionPoolException;
import com.epam.webapp.entity.Student;
import com.epam.webapp.entity.Training;
import com.epam.webapp.service.TrainingsService;

import java.util.ArrayList;
import java.util.List;

public class deleteTHIS {

  public static void main(String[] args) throws ConnectionPoolException {
    TrainingsService service = new TrainingsService();
    List<Student> students = new ArrayList<>();
    students = service.getStudentsByIdTraining(1);
    System.out.println(students.size());
  }
}
