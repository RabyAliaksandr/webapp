import com.epam.tc.connectionpool.ConnectionPool;
import com.epam.tc.connectionpool.ConnectionPoolException;
import com.epam.tc.dao.DaoException;
import com.epam.tc.entity.Consultation;
import com.epam.tc.entity.PaymentCard;
import com.epam.tc.entity.Task;
import com.epam.tc.service.ConsultationService;
import com.epam.tc.service.PaymentCardService;
import com.epam.tc.service.TaskService;
import com.epam.tc.service.impl.ConsultationServiceImpl;
import com.epam.tc.service.impl.PaymentCardServiceImpl;
import com.epam.tc.service.impl.TaskServiceImpl;
import com.google.protobuf.ServiceException;

import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.util.*;
import java.util.function.Predicate;


/**
 * The type Delete this.
 */
public class deleteTHIS {
  /**
   * The entry point of application.
   *
   * @param args the input arguments
   * @throws ConnectionPoolException the connection pool exception
   * @throws ParseException          the parse exception
   * @throws ServiceException        the service exception
   * @throws ServiceException        the service exception
   * @throws DaoException            the dao exception
   * @throws IOException             the io exception
   */
  public static void main(String[] args) throws ConnectionPoolException, ParseException, ServiceException, com.epam.tc.service.ServiceException, DaoException, IOException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection1 = connectionPool.takeConnection();
    Connection connection2 = connectionPool.takeConnection();
    Connection connection3 = connectionPool.takeConnection();
    Connection connection4 = connectionPool.takeConnection();
    Connection connection5 = connectionPool.takeConnection();

    System.out.println(connection1.getClass());
    Connection actual = connectionPool.takeConnection();
    System.out.println(actual.getClass());
    System.out.println("dsdd");
  }
}
