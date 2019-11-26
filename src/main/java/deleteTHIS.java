import com.epam.tc.connectionpool.ConnectionPoolException;
import com.epam.tc.connectionpool.DataBaseManager;
import com.epam.tc.dao.DaoException;
import com.epam.tc.encoder.PassEncoder;
import com.epam.tc.entity.User;
import com.epam.tc.service.PaymentCardService;
import com.epam.tc.service.ServiceFactory;
import com.epam.tc.service.UserService;
import com.epam.tc.entity.UserType;
import com.epam.tc.service.impl.TopicServiceImpl;
import com.epam.tc.validator.InputDataValidation;
import com.google.protobuf.ServiceException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Properties;
import java.util.regex.Pattern;


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
   */
  public static void main(String[] args) throws ConnectionPoolException, ParseException, ServiceException, com.epam.tc.service.ServiceException, DaoException, IOException {
    TopicServiceImpl topicService= new TopicServiceImpl();
    topicService.findTopicsForTraining(1);

      Properties properties = new Properties();

      properties.load(DataBaseManager.class.getClassLoader()
              .getResourceAsStream("C:\\IdeaProjects\\webapp\\src\\test\\com\\epam\\tc\\resource\\testDataBaseConnection.properties"));
  }
}
