package net.loststone.idescat;

import java.io.IOException;

import org.apache.commons.httpclient.HttpException;

/**
 * Aquesta interficie es la que han d'implementar les classes que fan el request
 * a la API en si. Han de gestionar els errors de connexio i altres per separar-ho
 * del domini. 
 * @author lant
 *
 */
public interface InnerRequest {
	
	/**
	 * Metode que realitza la connexio al servidor i invoca el metode GET donada una url.
	 * @param url URL a la qual es fara l'HTTP GET
	 * @throws HttpException
	 * @throws IOException
	 */
	void get(String url) throws HttpException, IOException;
	
	/**
	 * Metode que retorna el resultat del metode GET.
	 * @return El resultat en format String.
	 * @throws IOException
	 */
	String getResult() throws IOException;
	
	/**
	 * Metode que ens diu si la connexio s'ha realizat correctament.
	 * @return cert en cas de que tot hagi anat be, fals altrament.
	 */
	boolean getSuccess();
}

