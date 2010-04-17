
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.loststone.idescat.HttpFactory;
import net.loststone.idescat.indicadors.IndicadorsRequest;
import org.apache.commons.httpclient.HttpException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marc
 */
public class Test {
  private IndicadorsRequest a;
  HttpFactory factory;

  public void init( ) {
      factory = new HttpFactory();
  }

  public void start() throws IOException {
    a = factory.getIndicadorsRequest();
    a.addIndicador(5109);
    a.setOperacio(IndicadorsRequest.operacio.dades);
    try {
      a.get();
    } catch (HttpException ex) {
      Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
    }
    System.out.println(a.getResult());
  }

  public static void main(String args[]) throws IOException {
    Test a = new Test();
    a.init();
    a.start();
  }
}
