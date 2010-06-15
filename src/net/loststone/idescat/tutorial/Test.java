package net.loststone.idescat.tutorial; 

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.loststone.idescat.HttpFactory;
import net.loststone.idescat.indicadors.IndicadorsRequest;
import org.apache.commons.httpclient.HttpException;

/**
 * Classe que serveix d'exemple per utilitzar l'API.
 * @author marc
 */
public class Test {

  // aquesta classe farà la crida a la API d'idescat i ens retornarà el resultat.
  private IndicadorsRequest a;
  // Factory que crearà les requests.
  HttpFactory factory;

  /**
   * Inicialitzaem la factory, utilitzant la factory d'HTTP (la única actualment)
   */
  public void init( ) {
      factory = new HttpFactory();
  }

  public void start() throws IOException {
	
    // agafem una petició en blanc.
    a = factory.getIndicadorsRequest();
    // especifiquem que volem l'indicador 5109
    a.addIndicador(5109);
    // especifiquem quin tipus d'operaicó volem
    a.setOperacio(IndicadorsRequest.operacio.dades);
    try {
      // fem la petició.
      a.get();
    } catch (HttpException ex) {
      Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
    }
    // si tot ha anat bé retornem en resultat (en xml)
    System.out.println(a.getResult());
  }

  public static void main(String args[]) throws IOException {
    Test a = new Test();
    a.init();
    a.start();
  }
}
