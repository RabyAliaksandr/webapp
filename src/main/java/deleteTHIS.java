import com.epam.tc.connectionpool.ConnectionPool;
import com.epam.tc.connectionpool.ConnectionPoolException;
import com.epam.tc.dao.DaoException;
import com.epam.tc.entity.Consultation;
import com.epam.tc.entity.PaymentCard;
import com.epam.tc.entity.Task;
import com.epam.tc.manager.MessageManager;
import com.epam.tc.service.ConsultationService;
import com.epam.tc.service.PaymentCardService;
import com.epam.tc.service.TaskService;
import com.epam.tc.service.impl.ConsultationServiceImpl;
import com.epam.tc.service.impl.PaymentCardServiceImpl;
import com.epam.tc.service.impl.TaskServiceImpl;
import com.epam.tc.tag.ScoreTableLocal;
import com.google.protobuf.ServiceException;

import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.util.*;
import java.util.function.Predicate;

/**
 * The type Delete this.
 */ public class deleteTHIS implements c, a, b,f {
  public static void main(String[] args) throws ConnectionPoolException, ParseException, ServiceException, com.epam.tc.service.ServiceException, DaoException, IOException, ClassNotFoundException {

    System.out.printf(MessageManager.getProperty("label.price"));

  }

  @Override
  public void v() {
    System.out.printf("s");
  }

  static class SS {
     final  void j(){}
  }
}
interface a{
   void v();
}
interface b{
   void v();
}
interface f{
   void v();
}
interface c extends a, b, f {
   void v();
}
