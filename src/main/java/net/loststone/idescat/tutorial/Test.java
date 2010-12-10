package net.loststone.idescat.tutorial; 

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.loststone.idescat.HttpFactory;
import net.loststone.idescat.indicadors.IndicadorsRequest;
import org.apache.commons.httpclient.HttpException;

/**
 * Classe que serveix d'exemple per utilitzar l'API.
 * 
 * Per utilitzar l'API es s'han d'utilitzar 2 classes: 
 * Una Factory, que es la que crea les peticions i les peticions en si. 
 * @author marc
 */
public class Test {

  /**
   * Per fer crides a la API d'idescat haurem d'utilitzar un objecte que implementi
   * la classe AbstractRequest. Aquests objectes no es poden instanciar directament
   * per tant s'haura d'utilitzar una 'factory'. En aquest cas HttpFactory, que 
   * realitza les peticions utilitzant la llibreria apache http-client.
   */
  HttpFactory factory;

  /**
   * Depenent de la API que volguem utilitzar haurem d'utilitzar un objecte o un altre. 
   * En aquest cas l'exemple utilitza l'api 'Indicadors'. Per tant utilitzarem la classe
   * IndicadorsRequest.
   */
  private IndicadorsRequest request;
  
  /**
   * Inicialitzaem la factory, utilitzant la factory d'HTTP (la única actualment)
   */
  public void init( ) {
      factory = new HttpFactory();
  }

  public void start() throws IOException {
	
    // Aquest metode crea un objecte de la classe IndicadorsRequest, un cop 
    // tinguem l'objecte el podrem configurar (els parametres de la crida REST) i 
    // la podrem invocar, per tot seguit agafar-ne el resultat.
    request = factory.getIndicadorsRequest();
    
    // especifiquem que volem l'indicador 5109
    request.addIndicador(5109);
    // especifiquem quin tipus d'operaicó volem
    request.setOperacio(IndicadorsRequest.operacio.dades);
    try {
      // Aquest metode invoca la peticio HTTP amb els parametres utilitzats i guarda
      // el resultat.
      request.get();
    } catch (Exception ex) {
      Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
    } 
    // si tot ha anat be retornem en resultat (en xml)
    System.out.println(request.getResult());
  }

  public static void main(String args[]) throws IOException {
    Test a = new Test();
    a.init();
    a.start();
  }
}
