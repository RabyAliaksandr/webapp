import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class deleteTHIS {


  public static void main(String[] args) throws ExecutionException, InterruptedException {
    FutureTask<String> hello = new FutureTask<>(new A());
    new Thread(hello).start();
//    System.out.println(hello.get());

  }
}
class A implements Callable<String> {

  @Override
  public String call() throws Exception {
    return "Hello world!!";
  }
}
